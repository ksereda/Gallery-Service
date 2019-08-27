package com.example.movieservice.controller;

import com.example.movieservice.exception.MovieNotFoundException;
import com.example.movieservice.model.Movie;
import com.example.movieservice.payload.ErrorResponse;
import com.example.movieservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
public class MovieController {

    @Autowired
    private Environment env;

    @Autowired
    private MovieRepository movieRepository;

    @RequestMapping("/")
    public String home() {
        return "Movie-Service running at port: " + env.getProperty("local.server.port");
    }

    @GetMapping("/getAll")
    public Flux<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @PostMapping("/create")
    public Mono<Movie> createMovie(@Valid @RequestBody Movie movie) {
        return movieRepository.save(movie);
    }

    @GetMapping("/get/{id}")
    public Mono<ResponseEntity<Movie>> getMovieById(@PathVariable(value = "id") String movieId) {
        return movieRepository.findById(movieId)
                .map(saveMovie -> ResponseEntity.ok(saveMovie))  // then the map operator is called on this Bucket to wrap it in a ResponseEntity object with status code 200 OK
                .defaultIfEmpty(ResponseEntity.notFound().build());   // finally there is a call to defaultIfEmpty to build an empty ResponseEntity with status 404 NOT FOUND if the Bucket was not found.
    }

    @PutMapping("/update/{id}")
    public Mono<ResponseEntity<Movie>> updateMovie(@PathVariable(value = "id") String movieId,
                                                         @Valid @RequestBody Movie movie) {
        return movieRepository.findById(movieId)
                .flatMap(existingMovie -> {
                    existingMovie.setGenre(movie.getGenre());
                    existingMovie.setImageLink(movie.getImageLink());    // then calls flatMap with this movie to update its entries using its setters and the values from the Bucket passed as argument.
                    return movieRepository.save(existingMovie);
                })
                .map(updatedMovie -> new ResponseEntity<>(updatedMovie, HttpStatus.OK))  // Then it saves them to the database and wraps this updated Bucket in a ResponseEntity with status code 200 OK in case of success or 404 NOT FOUND in case of failure.
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Void>> deleteMovie(@PathVariable(value = "id") String movieId) {

        return movieRepository.findById(movieId)  // First, you search the Bucket you want to delete.
                .flatMap(existingMovie ->
                        movieRepository.delete(existingMovie)  // Next, you delete and return 200 OK to show your delete was successful
                                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));  // or you return 404 NOT FOUND to say the Bucket was not found
    }

    @DeleteMapping("/deleteAllMovies")
    public Mono<Void> deleteAllMovies(){
        return movieRepository.deleteAll();
    }

    // Exception Handling Examples (These can be put into a @ControllerAdvice to handle exceptions globally)
    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity handleDuplicateKeyException(DuplicateKeyException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse("A movie with the same title already exists"));
    }

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity handleBucketNotFoundException(MovieNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }

}

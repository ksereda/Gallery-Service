
### Documentation

This is Query Performance application.
______

## Add/delete datasource

In this example, I used the `H2 Database`.
All settings you can see in the `application.yml` file.

In order to add/delete database, you must use the `application.yml` file.
No need to make any additional settings.

______


### H2 Database

Start H2.

Go to http://127.0.0.1:8082 in your browser.

Choose "Server" mode and connect to DB with this properties:

    url: jdbc:h2:tcp://localhost/~/test
    user: sa
    password:
    driverClassName: org.h2.Driver
    
    url: jdbc:h2:tcp://localhost/~/test2
    user: sa
    password:
    driverClassName: org.h2.Driver
        
    url: jdbc:h2:tcp://localhost/~/test3
    user: sa
    password:
    driverClassName: org.h2.Driver


## Create table

CREATE TABLE users ( 
   id INT NOT NULL,
      name VARCHAR(50),
      lastname VARCHAR(50),
      email VARCHAR(50),
      phone VARCHAR(50),
      location VARCHAR(50),
      address VARCHAR(50),
      zipcode VARCHAR(50),
      interests VARCHAR(50),
      projectname VARCHAR(50),
      PRIMARY KEY (id) 
);

## Insert data

INSERT INTO users VALUES(1, 'Nick', 'Spenser', 'nickspens@gmail.com', '9876543210', 'MA', '177 Huntington Ave', '123123', 'hockey', 'SSA');

INSERT INTO users VALUES(2, 'Tom', 'Love', 'tommy@gmail.com', '12345678', 'LA', '150 Str. Ave', '321654', 'hockey', 'Spotify');

INSERT INTO users VALUES(3, 'Valery', 'Simpson', 'vally@gmail.com', '989897676', 'ME', '11 Str. Sweet', '434343', 'baseball', 'Amazon');


______

## Check work
Check the work using `Postman`.

Send `POST` request with parameters:

### Headers:

`Accept` - application/json

`Content-Type` - application/json


### Body:

`raw` - JSON(application/json)

    {"query": "select * from users"}


You can send a request through `Postman` and immediately send the same request through a `Rest client` (for example) to make sure that the second request will wait until the first request completes the work and receives the result.



package com.example.loginservice.service;

import com.example.loginservice.model.LoginForm;
import com.example.loginservice.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class LoginService {

    @Autowired
    LoginRepository repository;

    @Transactional
    public LoginForm createOrUpdateEmployee(LoginForm entity) {

        if (entity.getId()  == null) {
            entity = repository.save(entity);
            return entity;
        } else {
            Optional<LoginForm> employee = Optional.ofNullable(repository.findOne(entity.getId()));

            if (employee.isPresent()) {

                LoginForm newEntity = employee.get();
                newEntity.setUsername(entity.getUsername());
                newEntity.setFirstName(entity.getFirstName());
                newEntity.setLastName(entity.getLastName());
                newEntity.setGender(entity.getGender());
                newEntity.setAge(entity.getAge());
                newEntity.setStatus(entity.getStatus());

                newEntity = repository.save(newEntity);

                return newEntity;
            } else {
                entity = repository.save(entity);

                return entity;
            }
        }

    }

}

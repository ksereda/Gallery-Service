package com.example.filterservice.service;

import com.example.filterservice.model.LoginForm;
import com.example.filterservice.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class LoginService {

    @Autowired
    LoginRepository repository;

    @Transactional
    public LoginForm updateUserStatusUnchecked(LoginForm entity) {
                LoginForm newEntity = entity;
                newEntity.setStatus("unchecked");
                newEntity = repository.save(newEntity);
                return newEntity;
    }

    @Transactional
    public LoginForm updateUserStatusChecked(LoginForm entity) {
        LoginForm newEntity = entity;
        newEntity.setStatus("checked");
        newEntity = repository.save(newEntity);
        return newEntity;
    }

    public void handle(LoginForm message) throws Exception {
        if (message.getAge() < 18) {
            updateUserStatusUnchecked(message);
            throw new Exception("User not allowed!");
        } else if (message.getAge() >= 18) {
            updateUserStatusChecked(message);
        }
    }

}

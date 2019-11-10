package com.example.loginservice.repository;

import com.example.loginservice.model.LoginForm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends CrudRepository<LoginForm, Long> {
}

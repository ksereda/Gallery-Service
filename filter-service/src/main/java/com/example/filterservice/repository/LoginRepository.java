package com.example.filterservice.repository;

import com.example.filterservice.model.LoginForm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends CrudRepository<LoginForm, Long> {
}

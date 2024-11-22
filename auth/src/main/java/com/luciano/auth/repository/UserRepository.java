package com.luciano.auth.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.luciano.auth.entity.UserCredential;


@Repository
public interface UserRepository extends CrudRepository<UserCredential, String> {
    
    UserCredential findByUsername(String username);

}

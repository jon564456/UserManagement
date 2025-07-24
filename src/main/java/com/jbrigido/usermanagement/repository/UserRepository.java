package com.jbrigido.usermanagement.repository;

import org.springframework.data.repository.CrudRepository;

import com.jbrigido.usermanagement.model.Users;

public interface UserRepository extends CrudRepository<Users, Integer>{


}

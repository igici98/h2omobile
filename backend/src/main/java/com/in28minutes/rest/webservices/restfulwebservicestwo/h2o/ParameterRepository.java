package com.in28minutes.rest.webservices.restfulwebservicestwo.h2o;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParameterRepository extends JpaRepository<Parameter, String> {

}

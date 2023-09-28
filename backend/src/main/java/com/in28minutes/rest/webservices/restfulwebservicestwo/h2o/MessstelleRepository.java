package com.in28minutes.rest.webservices.restfulwebservicestwo.h2o;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessstelleRepository extends JpaRepository<Messstelle, String> {

	List<Messstelle> getByID(String ID);

	Messstelle getMessstelleByID(String ID);
}

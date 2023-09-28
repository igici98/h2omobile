package com.in28minutes.rest.webservices.restfulwebservicestwo.h2o;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface H2oProbeRepository extends JpaRepository<H2oProbe, String> {

	List<H2oProbe> getByID(String ID);

	List<H2oProbe> getByMessstelleID(String ID);
}

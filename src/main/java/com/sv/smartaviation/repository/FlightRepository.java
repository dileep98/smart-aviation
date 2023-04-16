package com.sv.smartaviation.repository;

import com.sv.smartaviation.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, String> {

}

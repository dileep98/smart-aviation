package com.sv.smartaviation.repository;

import com.sv.smartaviation.entity.Flight;
import com.sv.smartaviation.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    Optional<List<Flight>> findAllByUser(User user);
}

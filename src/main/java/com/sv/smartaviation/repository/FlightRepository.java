package com.sv.smartaviation.repository;

import com.sv.smartaviation.entity.Role;
import com.sv.smartaviation.entity.RoleName;
import com.sv.smartaviation.entity.SavedFlight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<SavedFlight, Long> {
    Optional<SavedFlight> findByIdIn(Long id);

    Optional<List<SavedFlight>> findAllByUserId(Long id);


}

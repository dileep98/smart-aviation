package com.sv.smartaviation.repository;

import com.sv.smartaviation.entity.Flight;
import com.sv.smartaviation.entity.UserFlightPreference;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFlightPreferenceRepository extends JpaRepository<UserFlightPreference, Long> {
    List<UserFlightPreference> findAllByUserId(Long userId);
    List<UserFlightPreference> findAllByFlightIdAndEnabledIsTrue(String id);
    Optional<UserFlightPreference> findByUserIdAndFlightId(Long userId, String flightId);

    @Query("SELECT DISTINCT flight from UserFlightPreference")
    List<Flight> findDistinctByFlightId();
}

package com.sv.smartaviation.repository;

import com.sv.smartaviation.entity.UserFlightPreference;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFlightPreferenceRepository extends JpaRepository<UserFlightPreference, Long> {
    List<UserFlightPreference> findAllByUserId(Long userId);
}

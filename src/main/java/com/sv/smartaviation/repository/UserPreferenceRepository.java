package com.sv.smartaviation.repository;

import com.sv.smartaviation.entity.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPreferenceRepository extends JpaRepository<UserPreference, Long> {
    public UserPreference findByIdIn(String Id);

    public UserPreference findByUserId(String userId);


}

package com.sv.smartaviation.service;

import com.sv.smartaviation.entity.User;
import com.sv.smartaviation.entity.UserProfile;
import com.sv.smartaviation.mapper.UserProfileMapper;
import com.sv.smartaviation.model.auth.UserProfileModel;
import com.sv.smartaviation.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserProfileMapper userProfileMapper;

    public UserProfile saveUserProfile(UserProfileModel userProfileModel, User user) {
        var entity = userProfileMapper.mapToEntity(userProfileModel);
        entity.setUser(user);
        return userProfileRepository.save(entity);
    }

}

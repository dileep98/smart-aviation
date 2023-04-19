package com.sv.smartaviation.service;

import com.sv.smartaviation.entity.User;
import com.sv.smartaviation.entity.UserProfile;
import com.sv.smartaviation.exception.ResourceNotFoundException;
import com.sv.smartaviation.mapper.UserProfileMapper;
import com.sv.smartaviation.model.auth.UpdateUserProfileModel;
import com.sv.smartaviation.model.auth.UserProfileModel;
import com.sv.smartaviation.repository.UserProfileRepository;
import com.sv.smartaviation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserProfileMapper userProfileMapper;
    private final UserRepository userRepository;

    public UserProfile saveUserProfile(UserProfileModel userProfileModel, User user) {
        var entity = userProfileMapper.mapToEntity(userProfileModel);
        entity.setUser(user);
        return userProfileRepository.save(entity);
    }

    public UserProfile updateUserProfile(UserProfileModel userProfileModel, Long userId) {
        var entity = userProfileMapper.mapToEntity(userProfileModel);
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("UserProfile", "userId", userId));
        entity.setUser(user);
        var userProfileId = getUserProfile(userId).getId();
        entity.setId(userProfileId);
        return userProfileRepository.save(entity);
    }

    public UpdateUserProfileModel getUserProfile(Long userId){
        var entity = userProfileRepository.findByUserId(userId).orElseThrow(() -> new ResourceNotFoundException("UserProfile", "userId", userId));
        return userProfileMapper.mapToModel(entity);
    }

}

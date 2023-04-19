package com.sv.smartaviation.mapper;

import com.sv.smartaviation.entity.UserProfile;
import com.sv.smartaviation.model.auth.UserProfileModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    UserProfile mapToEntity(UserProfileModel userProfileModel);
}

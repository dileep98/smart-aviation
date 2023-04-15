package com.sv.smartaviation.mapper;

import com.sv.smartaviation.entity.Flight;
import com.sv.smartaviation.entity.User;
import com.sv.smartaviation.entity.UserPreference;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserPreferenceMapper {

    @Mapping(source = "userPreference.id", target = "id")
    @Mapping(source = "user", target = "user")
    @Mapping(source = "flight", target = "flight")
    UserPreference mapToEntity(com.sv.smartaviation.model.user.UserPreference userPreference, User user, Flight flight);
}

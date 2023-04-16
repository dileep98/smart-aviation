package com.sv.smartaviation.mapper;

import com.sv.smartaviation.entity.Flight;
import com.sv.smartaviation.entity.User;
import com.sv.smartaviation.entity.UserFlightPreference;
import com.sv.smartaviation.model.user.UserFlightPreferenceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserFlightPreferenceMapper {

    @Mapping(source = "userFlightPreference.id", target = "id")
    @Mapping(source = "user", target = "user")
    @Mapping(source = "flight", target = "flight")
    UserFlightPreference mapToEntity(com.sv.smartaviation.model.user.UserFlightPreference userFlightPreference, User user, Flight flight);

    @Mapping(source = "user.id", target = "flight.userId")
    @Mapping(source = "flight.arrivalDateTime", target = "flight.arrivalDateTime")
    @Mapping(source = "flight.carrierName", target = "flight.carrierName")
    @Mapping(source = "flight.departureDateTime", target = "flight.departureDateTime")
    @Mapping(source = "flight.destinationCode", target = "flight.destinationCode")
    @Mapping(source = "flight.originCode", target = "flight.originCode")
    @Mapping(source = "flight.originName", target = "flight.originName")
    @Mapping(source = "flight.price", target = "flight.price")
    @Mapping(source = "flight.stopCount", target = "flight.stopCount")
    UserFlightPreferenceResponse map(UserFlightPreference userFlightPreference);
}

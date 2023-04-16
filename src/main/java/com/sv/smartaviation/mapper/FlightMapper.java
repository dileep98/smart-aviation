package com.sv.smartaviation.mapper;

import com.sv.smartaviation.entity.Flight;
import com.sv.smartaviation.model.skyscanner.Leg;
import com.sv.smartaviation.model.skyscanner.PricingOption;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FlightMapper {

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "carrierName", expression = "java(leg.getCarriers().getMarketing().get(0).getName())")
    @Mapping(target = "price", source = "pricingOption.price.amount")
    @Mapping(target = "originName", source = "leg.origin.name")
    @Mapping(target = "originCode", source = "leg.origin.displayCode")
    @Mapping(target = "destinationName", source = "leg.destination.name")
    @Mapping(target = "destinationCode", source = "leg.destination.displayCode")
    @Mapping(target = "departureDateTime", source = "leg.departure")
    @Mapping(target = "arrivalDateTime", source = "leg.arrival")
    @Mapping(target = "id", source = "leg.id")
    Flight map(Leg leg, PricingOption pricingOption);
}

package com.flightticketgenerator.FlightTicketGenerator.models;

import com.flightticketgenerator.FlightTicketGenerator.entity.Booking;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {
    private Integer userId;
    private String userName;
    private String password;
    private  String email;
    private  boolean isAdmin;
    private Booking bookingId;
public boolean getIsAdmin() {
    return isAdmin;
}
}


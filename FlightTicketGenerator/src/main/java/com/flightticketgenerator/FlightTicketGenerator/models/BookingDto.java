package com.flightticketgenerator.FlightTicketGenerator.models;

import com.flightticketgenerator.FlightTicketGenerator.entity.Flight;
import com.flightticketgenerator.FlightTicketGenerator.entity.Seat;
import com.flightticketgenerator.FlightTicketGenerator.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingDto {
    private Integer bookingId;
    private User user;
    private Seat seat;
    private Flight flight;

}

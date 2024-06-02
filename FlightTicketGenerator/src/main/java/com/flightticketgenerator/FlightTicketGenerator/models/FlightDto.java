package com.flightticketgenerator.FlightTicketGenerator.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class FlightDto {
    private Integer flightId;
    private String flightName;
    private String flightDestination;
    private String flightPickUpPoint;
    private Date flightDate;
    private float price;
    private Integer totalNoOfSeat;
    private Integer availableNoOfSeat;
}

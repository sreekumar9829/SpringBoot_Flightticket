package com.flightticketgenerator.FlightTicketGenerator.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "flighttable")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer flightId;
    private String flightName;
    private String flightDestination;
    private String flightPickUpPoint;
   // @Temporal(TemporalType.DATE)
    private Date flightDate;
    private float price;
    private Integer totalNoOfSeat;
    private Integer availableNoOfSeat;
}

package com.flightticketgenerator.FlightTicketGenerator.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name ="Seatable")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seatId;
    @OneToOne(cascade = CascadeType.ALL)
    private Flight flight;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
    private boolean isAvailable;
    private float price;
    public boolean getIsAvailable() {
        return isAvailable;
    }
}

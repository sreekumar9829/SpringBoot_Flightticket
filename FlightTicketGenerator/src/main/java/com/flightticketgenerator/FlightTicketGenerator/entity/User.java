package com.flightticketgenerator.FlightTicketGenerator.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usertable")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String userName;
    private String password;
    private  String email;
    @Column(name = "isAdmin")
    private  boolean isAdmin;
    @OneToOne(cascade = CascadeType.ALL)
    private Booking bookingId;
    public boolean getisAdmin() {
        return isAdmin;
    }
}

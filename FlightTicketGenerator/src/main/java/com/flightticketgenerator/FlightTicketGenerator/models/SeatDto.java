package com.flightticketgenerator.FlightTicketGenerator.models;

import com.flightticketgenerator.FlightTicketGenerator.entity.Flight;
import com.flightticketgenerator.FlightTicketGenerator.entity.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SeatDto {
    private Integer seatId;
    private Flight flight;
    private User user;
    private boolean isAvailable;

    public boolean getIsAvailable() {
        return isAvailable;
    }

}

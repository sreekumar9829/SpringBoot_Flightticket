package com.flightticketgenerator.FlightTicketGenerator.repository;

import com.flightticketgenerator.FlightTicketGenerator.entity.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight,Integer> {
    List<Flight> findByFlightDestinationAndFlightPickUpPointAndFlightDate(String  destination,String pick_up_point,Date flight_date);

    @Override
    Page<Flight> findAll(Pageable pageable);
}

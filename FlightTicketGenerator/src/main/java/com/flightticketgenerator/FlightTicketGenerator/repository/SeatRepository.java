package com.flightticketgenerator.FlightTicketGenerator.repository;

import com.flightticketgenerator.FlightTicketGenerator.entity.Flight;
import com.flightticketgenerator.FlightTicketGenerator.entity.Seat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat,Integer> {
    @Override
    Page<Seat> findAll(Pageable pageable);
}

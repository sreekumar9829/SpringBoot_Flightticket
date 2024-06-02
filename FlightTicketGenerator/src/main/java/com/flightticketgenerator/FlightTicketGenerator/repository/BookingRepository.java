package com.flightticketgenerator.FlightTicketGenerator.repository;

import com.flightticketgenerator.FlightTicketGenerator.entity.Booking;
import com.flightticketgenerator.FlightTicketGenerator.entity.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Integer> {
    Page<Booking> findAll(Pageable pageable);
}


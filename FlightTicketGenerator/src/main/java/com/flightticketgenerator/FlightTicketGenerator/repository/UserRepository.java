package com.flightticketgenerator.FlightTicketGenerator.repository;

import com.flightticketgenerator.FlightTicketGenerator.entity.Seat;
import com.flightticketgenerator.FlightTicketGenerator.entity.User;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    Page<User>findAll(Pageable pageable);
}

package com.flightticketgenerator.FlightTicketGenerator.service;

import com.flightticketgenerator.FlightTicketGenerator.exception.BookingNotFoundException;
import com.flightticketgenerator.FlightTicketGenerator.exception.UserNotFoundException;
import com.flightticketgenerator.FlightTicketGenerator.models.BookingDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public interface BookingService {
    String addBooking(BookingDto bookingDto) throws BookingNotFoundException;
    Page<BookingDto> getBooking(Integer userId,String userName,String password, Integer offset,Integer pageSize)throws BookingNotFoundException, UserNotFoundException;
    String pdfCreation(Integer bookingId) throws BookingNotFoundException;
}


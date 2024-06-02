package com.flightticketgenerator.FlightTicketGenerator.service;

import com.flightticketgenerator.FlightTicketGenerator.exception.SeatNotFoundException;
import com.flightticketgenerator.FlightTicketGenerator.models.SeatDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SeatService {
    String addSeat(SeatDto seatDto)throws SeatNotFoundException;
    String updateSeat(Integer id, SeatDto seatDto)throws SeatNotFoundException;
    Page<SeatDto> getSeat(Integer offset,Integer pageSize)throws SeatNotFoundException;
}

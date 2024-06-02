package com.flightticketgenerator.FlightTicketGenerator.service;

import com.flightticketgenerator.FlightTicketGenerator.entity.Seat;
import com.flightticketgenerator.FlightTicketGenerator.exception.SeatNotFoundException;
import com.flightticketgenerator.FlightTicketGenerator.models.SeatDto;
import com.flightticketgenerator.FlightTicketGenerator.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeatServiceImpl implements SeatService{
    @Autowired
    SeatRepository seatRepository;
    @Override
    public String addSeat(SeatDto seatDto) throws SeatNotFoundException {
        Seat seat=new Seat();
        seat.setSeatId(seatDto.getSeatId());
        seat.setUser(seatDto.getUser());
        seat.setFlight(seatDto.getFlight());
        seat.setAvailable(seatDto.getIsAvailable());
        seatRepository.save(seat);

        return "Seat details Added successfully";
    }

    @Override
    public String updateSeat(Integer id, SeatDto seatDto) throws SeatNotFoundException {
       Seat seat=seatRepository.findById(id).
               orElseThrow(()->new SeatNotFoundException("seat not found"));

        seat.setSeatId(seatDto.getSeatId());
        seat.setFlight(seatDto.getFlight());
        seat.setAvailable(seatDto.getIsAvailable());
        seat.setUser(seatDto.getUser());
        seatRepository.save(seat);
        return "seat updated successfully";

    }

    @Override
    public Page<SeatDto> getSeat(Integer offset, Integer pageSize) throws SeatNotFoundException {
        Pageable pageable= PageRequest.of(offset,pageSize);
        Page<Seat>seatList=seatRepository.findAll(pageable);
        List<SeatDto>seatDto=seatList.stream().map(seat -> {
            SeatDto seatDtoList=new SeatDto();
            seatDtoList.setSeatId(seat.getSeatId());
            seatDtoList.setUser(seat.getUser());
            seatDtoList.setFlight(seat.getFlight());
            seatDtoList.setAvailable(seat.getIsAvailable());
            return seatDtoList;
        }).collect(Collectors.toList());

        return new PageImpl<>(seatDto, pageable, seatList.getTotalElements());

    }
}

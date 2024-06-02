package com.flightticketgenerator.FlightTicketGenerator.service;

import com.flightticketgenerator.FlightTicketGenerator.exception.FlightNotFoundException;
import com.flightticketgenerator.FlightTicketGenerator.exception.UserNotFoundException;
import com.flightticketgenerator.FlightTicketGenerator.models.FlightDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public interface FlightService {
    String addFlight(FlightDto flightDto,Integer id,String userName, String password) throws FlightNotFoundException, UserNotFoundException;
    Page<FlightDto> getFlight(Integer offset, Integer pageSize) throws FlightNotFoundException;
    List<FlightDto>searchFlight(String flightDestination,String pickUpPoint,Date date) throws  FlightNotFoundException;
    List<FlightDto>sortByField(String field)throws FlightNotFoundException;
/*
    String deleteFlight(Integer flightId,Integer userId,String userName,String password)throws FlightNotFoundException,UserNotFoundException;
*/
}

package com.flightticketgenerator.FlightTicketGenerator.service;

import com.flightticketgenerator.FlightTicketGenerator.exception.UserNotFoundException;
import com.flightticketgenerator.FlightTicketGenerator.models.UserDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
String addUser(UserDto userDto) throws UserNotFoundException;
Page<UserDto>getUser(Integer offset,Integer pageSize)throws UserNotFoundException;
}

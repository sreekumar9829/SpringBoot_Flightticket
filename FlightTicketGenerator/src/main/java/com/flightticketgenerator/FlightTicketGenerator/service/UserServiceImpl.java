package com.flightticketgenerator.FlightTicketGenerator.service;

import com.flightticketgenerator.FlightTicketGenerator.entity.Flight;
import com.flightticketgenerator.FlightTicketGenerator.entity.User;
import com.flightticketgenerator.FlightTicketGenerator.exception.FlightNotFoundException;
import com.flightticketgenerator.FlightTicketGenerator.exception.UserNotFoundException;
import com.flightticketgenerator.FlightTicketGenerator.models.FlightDto;
import com.flightticketgenerator.FlightTicketGenerator.models.UserDto;
import com.flightticketgenerator.FlightTicketGenerator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements  UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public String addUser(UserDto userDto) throws UserNotFoundException {
       User user=new User();
       user.setUserName(userDto.getUserName());
       user.setUserId(userDto.getUserId());
       user.setPassword(userDto.getPassword());
       user.setEmail(userDto.getEmail());
       user.setAdmin(userDto.getIsAdmin());
       userRepository.save(user);
       return "user added successfully";
    }


    @Override
    public Page<UserDto> getUser(Integer offset, Integer pageSize) throws UserNotFoundException {
        Pageable pageable= PageRequest.of(offset,pageSize);
        Page<User> userList=userRepository.findAll(pageable);
        List<UserDto> userDtoList=userList.stream().map(user -> {
            UserDto userDto=new UserDto();
            userDto.setUserName(user.getUserName());
            userDto.setUserId(user.getUserId());
            userDto.setEmail(user.getEmail());
            userDto.setPassword(user.getPassword());
            userDto.setAdmin(user.getisAdmin());
            userDto.setBookingId(user.getBookingId());
            return userDto;
        }).collect(Collectors.toList());
        return new PageImpl<>(userDtoList, pageable, userList.getTotalElements());
    }
}

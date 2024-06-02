package com.flightticketgenerator.FlightTicketGenerator.service;

import com.flightticketgenerator.FlightTicketGenerator.entity.Flight;
import com.flightticketgenerator.FlightTicketGenerator.entity.User;
import com.flightticketgenerator.FlightTicketGenerator.exception.FlightNotFoundException;
import com.flightticketgenerator.FlightTicketGenerator.exception.UserNotFoundException;
import com.flightticketgenerator.FlightTicketGenerator.models.FlightDto;
import com.flightticketgenerator.FlightTicketGenerator.repository.FlightRepository;
import com.flightticketgenerator.FlightTicketGenerator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService{
    @Autowired
    FlightRepository flightRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public String addFlight(FlightDto flightDto,Integer id, String userName, String password) throws FlightNotFoundException, UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("User not found");
        }

        User user = userOptional.get();
        // Validate username and password
        if (user.getUserName().equals(userName) && user.getPassword().equals(password)&&user.getisAdmin()==true) {
            Flight flight = new Flight();
            flight.setFlightId(flightDto.getFlightId());
            flight.setFlightName(flightDto.getFlightName());
            flight.setFlightDate(flightDto.getFlightDate());
            flight.setFlightDestination(flightDto.getFlightDestination());
            flight.setFlightPickUpPoint(flightDto.getFlightPickUpPoint());
            flight.setPrice(flightDto.getPrice());
            flight.setTotalNoOfSeat(flightDto.getTotalNoOfSeat());
            flight.setAvailableNoOfSeat(flightDto.getAvailableNoOfSeat());
            flightRepository.save(flight);

            return "flight details added successfully";
        } else {
            throw new UserNotFoundException("Invalid username or password");
        }
    }
    @Override
        public Page<FlightDto> getFlight(Integer offset, Integer pageSize) throws FlightNotFoundException {
        Pageable pageable= PageRequest.of(offset,pageSize);
        Page<Flight> flightList=flightRepository.findAll(pageable);
           List <FlightDto> flightDtoList=flightList.stream().map(flight -> {
                FlightDto flightDtos=new FlightDto();
                flightDtos.setFlightId(flight.getFlightId());
                flightDtos.setFlightName(flight.getFlightName());
               flightDtos.setFlightDate(flight.getFlightDate());
                flightDtos.setFlightDestination(flight.getFlightDestination());
                flightDtos.setFlightPickUpPoint(flight.getFlightPickUpPoint());
                flightDtos.setPrice(flight.getPrice());
                flightDtos.setTotalNoOfSeat(flight.getTotalNoOfSeat());
                flightDtos.setAvailableNoOfSeat(flight.getAvailableNoOfSeat());

                return flightDtos;
            }).collect(Collectors.toList());
        return new PageImpl<>(flightDtoList, pageable, flightList.getTotalElements());

    }

    @Override
    public List<FlightDto> searchFlight(String flightDestination,String pickUpPoint,Date date) throws FlightNotFoundException {
        List<Flight>flightList=flightRepository.findByFlightDestinationAndFlightPickUpPointAndFlightDate(flightDestination,pickUpPoint,date);
        return flightList.stream().map(flight -> {
            FlightDto flightDto = new FlightDto();
            flightDto.setFlightId(flight.getFlightId());
            flightDto.setFlightName(flight.getFlightName());
            flightDto.setFlightDate(flight.getFlightDate());
            flightDto.setFlightDestination(flight.getFlightDestination());
            flightDto.setFlightPickUpPoint(flight.getFlightPickUpPoint());
            flightDto.setPrice(flight.getPrice());
            flightDto.setTotalNoOfSeat(flight.getTotalNoOfSeat());
            flightDto.setAvailableNoOfSeat(flight.getAvailableNoOfSeat());
            return flightDto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<FlightDto> sortByField(String field) throws FlightNotFoundException {
        List<Flight>flightList=flightRepository.findAll(Sort.by(Sort.Direction.ASC,field));
        List<FlightDto>flightDtoList=flightList.stream().map(flight -> {
            FlightDto flightDto = new FlightDto();
            flightDto.setFlightId(flight.getFlightId());
            flightDto.setFlightName(flight.getFlightName());
            flightDto.setFlightDate(flight.getFlightDate());
            flightDto.setFlightDestination(flight.getFlightDestination());
            flightDto.setFlightPickUpPoint(flight.getFlightPickUpPoint());
            flightDto.setPrice(flight.getPrice());
            flightDto.setTotalNoOfSeat(flight.getTotalNoOfSeat());
            flightDto.setAvailableNoOfSeat(flight.getAvailableNoOfSeat());
            return flightDto;
        }).collect(Collectors.toList());
        return flightDtoList;
    }

    /*@Override
    public String deleteFlight(Integer flightId, Integer userId, String userName, String password)throws FlightNotFoundException,UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("User not found");
        }

        User user = userOptional.get();
        // Validate username and password
        if (user.getUserName().equals(userName) && user.getPassword().equals(password)&&user.getisAdmin()==true) {
            flightRepository.deleteById(flightId);
            return "Flight Deleted";
        }

        else {
            throw new UserNotFoundException("Invalid username or password");
        }
    }*/
}

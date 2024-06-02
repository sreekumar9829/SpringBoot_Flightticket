package com.flightticketgenerator.FlightTicketGenerator.controller;

import com.flightticketgenerator.FlightTicketGenerator.exception.SeatNotFoundException;
import com.flightticketgenerator.FlightTicketGenerator.exception.UserNotFoundException;
import com.flightticketgenerator.FlightTicketGenerator.models.SeatDto;
import com.flightticketgenerator.FlightTicketGenerator.models.UserDto;
import com.flightticketgenerator.FlightTicketGenerator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping
    public  ResponseEntity<String> addUser(@RequestBody UserDto userDto) throws  UserNotFoundException{
        String response=userService.addUser(userDto);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @GetMapping()
    public PagedModel<EntityModel<UserDto>> getAllUser(
            @RequestParam Integer offset,
            @RequestParam Integer pageSize,
            PagedResourcesAssembler<UserDto> assembler) throws UserNotFoundException {

        Page<UserDto> userDto = userService.getUser(offset,pageSize);
        PagedModel<EntityModel<UserDto>> resource = assembler.toModel(userDto);
        return resource;
    }
}

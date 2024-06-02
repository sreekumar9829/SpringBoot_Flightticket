package com.flightticketgenerator.FlightTicketGenerator.controller;

import com.flightticketgenerator.FlightTicketGenerator.entity.Flight;
import com.flightticketgenerator.FlightTicketGenerator.exception.FlightNotFoundException;
import com.flightticketgenerator.FlightTicketGenerator.exception.UserNotFoundException;
import com.flightticketgenerator.FlightTicketGenerator.models.FlightDto;
import com.flightticketgenerator.FlightTicketGenerator.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/flight")
public class FlightController {
    @Autowired
    FlightService flightService;
    @PostMapping
    public ResponseEntity<String>addFlight(@RequestBody FlightDto flightDto,@RequestParam Integer id, @RequestParam String userName,@RequestParam String password)throws FlightNotFoundException,UserNotFoundException{
        String response=flightService.addFlight(flightDto,id,userName,password);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping()
    public PagedModel<EntityModel<FlightDto>> getAllBooks(
            @RequestParam Integer offset,
            @RequestParam Integer pageSize,
            PagedResourcesAssembler<FlightDto> assembler) throws FlightNotFoundException {

        Page<FlightDto> flightDto = flightService.getFlight(offset, pageSize);
        PagedModel<EntityModel<FlightDto>> resource = assembler.toModel(flightDto);
        return resource;
    }
    @GetMapping("/search")
    public ResponseEntity<List<FlightDto>>serachByDateDestinationPickuppoint(
               @RequestParam String flightDestination,@RequestParam String pickupPoint,
               @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")  Date date) throws FlightNotFoundException{
        List<FlightDto>flightDto=flightService.searchFlight(flightDestination,pickupPoint,date);
        return  new ResponseEntity<>(flightDto,HttpStatus.OK);
    }
    @GetMapping("/sortybyfield")
    public ResponseEntity<List<FlightDto>>sortByField(@RequestParam String field)throws FlightNotFoundException{
        List<FlightDto>flightDtoList=flightService.sortByField(field);
        return new ResponseEntity<>(flightDtoList,HttpStatus.OK);
    }
   /* @DeleteMapping
    public ResponseEntity<String>deleteFlight(@RequestParam Integer flightId,@RequestParam Integer userId,
                                              @RequestParam String userName,@RequestParam String password)
                                                throws FlightNotFoundException,UserNotFoundException{
        String response=flightService.deleteFlight(flightId,userId,userName,password);
        return  new ResponseEntity<>(response,HttpStatus.OK);

    }*/



}

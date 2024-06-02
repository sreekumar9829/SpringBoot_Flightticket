package com.flightticketgenerator.FlightTicketGenerator.controller;

import com.flightticketgenerator.FlightTicketGenerator.exception.BookingNotFoundException;
import com.flightticketgenerator.FlightTicketGenerator.exception.SeatNotFoundException;
import com.flightticketgenerator.FlightTicketGenerator.models.BookingDto;
import com.flightticketgenerator.FlightTicketGenerator.models.SeatDto;
import com.flightticketgenerator.FlightTicketGenerator.service.SeatService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/seat")
public class SeatController {
    @Autowired
    SeatService seatService;
    @PostMapping
    public ResponseEntity<String> addSeat(@RequestBody SeatDto seatDto)throws SeatNotFoundException{
        String response=seatService.addSeat(seatDto);
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String>updateSeat(@PathVariable Integer id,@RequestBody SeatDto seatDto)throws SeatNotFoundException{
        String response=seatService.updateSeat(id,seatDto);
        return  new ResponseEntity<>(response,HttpStatus.OK);
    }
    @GetMapping()
    public PagedModel<EntityModel<SeatDto>> getAllSeat(
            @RequestParam Integer offset,
            @RequestParam Integer pageSize,
            PagedResourcesAssembler<SeatDto> assembler) throws SeatNotFoundException {

        Page<SeatDto> seatDto = seatService.getSeat(offset,pageSize);
        PagedModel<EntityModel<SeatDto>> resource = assembler.toModel(seatDto);
        return resource;
    }


}

package com.flightticketgenerator.FlightTicketGenerator.controller;

import com.flightticketgenerator.FlightTicketGenerator.entity.Booking;
import com.flightticketgenerator.FlightTicketGenerator.exception.BookingNotFoundException;
import com.flightticketgenerator.FlightTicketGenerator.exception.FlightNotFoundException;
import com.flightticketgenerator.FlightTicketGenerator.exception.UserNotFoundException;
import com.flightticketgenerator.FlightTicketGenerator.models.BookingDto;
import com.flightticketgenerator.FlightTicketGenerator.models.FlightDto;
import com.flightticketgenerator.FlightTicketGenerator.service.BookingService;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("api/v1/booking")
public class BookingController {
    @Autowired
    BookingService bookingService;
    @PostMapping
    public ResponseEntity<String>addBooking(@RequestBody BookingDto bookingDto)throws BookingNotFoundException{
        String response=bookingService.addBooking(bookingDto);
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping()
    public PagedModel<EntityModel<BookingDto>> getAllBooks(@RequestParam Integer userId, @RequestParam String userName,@RequestParam String password,
            @RequestParam Integer offset, @RequestParam Integer pageSize, PagedResourcesAssembler<BookingDto> assembler) throws BookingNotFoundException, UserNotFoundException {

        Page<BookingDto> bookingDto = bookingService.getBooking(userId,userName,password,offset,pageSize);
        PagedModel<EntityModel<BookingDto>> resource = assembler.toModel(bookingDto);
        return resource;
    }
    @GetMapping(value = "/generate", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<String> generatePdf(@RequestParam Integer bookingId)throws BookingNotFoundException {
       String reponse = bookingService.pdfCreation(bookingId);
       return new ResponseEntity<>(reponse,HttpStatus.OK);
    }
}

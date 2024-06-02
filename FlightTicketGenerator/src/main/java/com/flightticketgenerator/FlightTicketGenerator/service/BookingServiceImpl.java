package com.flightticketgenerator.FlightTicketGenerator.service;

import com.flightticketgenerator.FlightTicketGenerator.entity.Booking;
import com.flightticketgenerator.FlightTicketGenerator.entity.User;
import com.flightticketgenerator.FlightTicketGenerator.exception.BookingNotFoundException;
import com.flightticketgenerator.FlightTicketGenerator.exception.UserNotFoundException;
import com.flightticketgenerator.FlightTicketGenerator.models.BookingDto;
import com.flightticketgenerator.FlightTicketGenerator.repository.BookingRepository;
import com.flightticketgenerator.FlightTicketGenerator.repository.FlightRepository;
import com.flightticketgenerator.FlightTicketGenerator.repository.SeatRepository;
import com.flightticketgenerator.FlightTicketGenerator.repository.UserRepository;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;


import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService{
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    FlightRepository flightRepository;
    @Autowired
    SeatRepository seatRepository;
    @Override
    public String addBooking(BookingDto bookingDto) throws BookingNotFoundException {
        Booking bookingList=new Booking();
        bookingList.setBookingId(bookingDto.getBookingId());
        bookingList.setSeat(bookingDto.getSeat());
        bookingList.setFlight(bookingDto.getFlight());
        bookingList.setUser(bookingDto.getUser());
        bookingRepository.save(bookingList);
        return "Booking is added";
    }

    @Override
    public Page<BookingDto> getBooking(Integer userId,String userName,String password,Integer offset,Integer pageSize) throws BookingNotFoundException, UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("User not found");
        }

        User user = userOptional.get();
        // Validate username and password
        if (user.getUserName().equals(userName) && user.getPassword().equals(password)&&user.getisAdmin()==true) {

            Pageable pageable = PageRequest.of(offset, pageSize);
            Page<Booking> bookingList = bookingRepository.findAll(pageable);
            List<BookingDto> bookingDtoList = bookingList.stream().map(booking -> {
                BookingDto bookingDto = new BookingDto();
                bookingDto.setBookingId(booking.getBookingId());
                bookingDto.setFlight(booking.getFlight());
                bookingDto.setUser(booking.getUser());
                bookingDto.setSeat(booking.getSeat());
                return bookingDto;
            }).collect(Collectors.toList());
            return new PageImpl<>(bookingDtoList, pageable, bookingList.getTotalElements());
        }else {
            throw new  UserNotFoundException ("invalid user");
        }
    }

    @Override
    public String pdfCreation(Integer bookingId) throws BookingNotFoundException {
        String filepath = "D:\\TicketGenerator\\Flightticket.pdf";
        Optional<Booking> bookingList=bookingRepository.findById(bookingId);
        Booking bookingDetails=bookingList.get();
        bookingDetails.getBookingId();




        try {
            PdfWriter writer = new PdfWriter(filepath);
            PdfDocument pdfDoc = new PdfDocument(writer);
            pdfDoc.addNewPage();

            Document document = new Document(pdfDoc);
            Paragraph title = new Paragraph("Flight Ticket").setBold().setFontSize(18);
            document.add(title);
            Table table = new Table(4); // 3 columns
            table.addCell("Flight name:");
            table.addCell("Username");
            table.addCell("Seat number:");
            table.addCell("Price");
                table.addCell(bookingDetails.getFlight().getFlightName());
                table.addCell( bookingDetails.getUser().getUserName());
                table.addCell(bookingDetails.getSeat().getSeatId().toString());
                table.addCell(String.valueOf(bookingDetails.getFlight().getPrice()));
            document.add(table);
            document.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "Error generating PDF: " + e.getMessage();
        }

        return "Ticket generated successfully";
    }
    }


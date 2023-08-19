package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    @Test
    void should_return_parking_ticket_when_park_the_car_given_parking_lot_and_car() {
        //given
        ParkingLot parkinglot = new ParkingLot();
        Car car = new Car();
        //when
        ParkingTicket parkingTicket = parkinglot.park(car);
        //then
        assertNotNull(parkingTicket);
    }

    @Test
    void should_return_car_when_fetch_given_parking_lot_and_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLot.park(car);
        //when
        Car fetchedCar = parkingLot.fetch(parkingTicket);
        //then
        assertEquals(car, fetchedCar);
    }

    @Test
    void should_return_the_right_car_when_fetch_car_given_two_different_tickets() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingTicket parkingTicket1 = parkingLot.park(car1);
        ParkingTicket parkingTicket2 = parkingLot.park(car2);
        //when
        Car fetchedCar1 = parkingLot.fetch(parkingTicket1);
        Car fetchedCar2 = parkingLot.fetch(parkingTicket2);
        //then
        assertEquals(car1, fetchedCar1);
        assertEquals(car2, fetchedCar2);
    }

    @Test
    void should_return_unrecognizedTicketException_when_fetch_given_parking_lot_wrong_parking_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket wrongParkingTicket = new ParkingTicket();
        parkingLot.park(car);
        //when
        //then
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () -> {
            parkingLot.fetch(wrongParkingTicket);
        });
        assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_unrecognizedTicketException_when_fetch_given_used_parking_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot(0);
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLot.park(car);
        parkingLot.fetch(parkingTicket);
        //when
        //then
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () -> {
            parkingLot.fetch(parkingTicket);
        });
        assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_unavailableParkingLotException_when_park_given_car_and_park_without_availability() {
        // Given
        ParkingLot parkingLot = new ParkingLot(0);
        Car car = new Car();
        parkingLot.park(car);
        // When
        UnavailableParkingLotException unavailableParkingLotException = assertThrows(UnavailableParkingLotException.class, () -> {
            parkingLot.park(car);
        });
        // Then
        assertEquals("No available position.", unavailableParkingLotException.getMessage());
    }
}


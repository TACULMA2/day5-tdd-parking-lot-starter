package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StandardParkingBoyTest {
    @Test
    void should_park_the_car_into_the_first_parking_lot_when_park_the_car_given_a_standard_parking_boy_and_two_parking_lots_and_a_car() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);
        Car car = new Car();
        //when
        ParkingTicket parkingTicket = standardParkingBoy.park(car);
        //then
        assertNotNull(parkingTicket);
        assertEquals(9, firstParkingLot.getAvailableCapacity());
        assertEquals(10, secondParkingLot.getAvailableCapacity());
    }

    @Test
    void should_park_the_car_at_second_parking_lot_when_park_the_car_given_a_standard_boy_and_two_parking_lots_where_first_is_full_second_is_available() {
        //given
        ParkingLot firstParkingLot = new ParkingLot(0);
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);
        Car car = new Car();
        //when
        ParkingTicket parkingTicket = standardParkingBoy.park(car);
        //then
        assertNotNull(parkingTicket);
        assertEquals(0, firstParkingLot.getAvailableCapacity());
        assertEquals(9, secondParkingLot.getAvailableCapacity());
    }

    @Test
    void should_return_the_right_car_when_fetch_both_cars_given_standard_parking_boy_and_two_parking_ticket() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);
        Car car1 = new Car();
        Car car2 = new Car();
        //when
        ParkingTicket parkingTicket1 = standardParkingBoy.park(car1);
        ParkingTicket parkingTicket2 = standardParkingBoy.park(car2);
        Car fetchedCar1 = standardParkingBoy.fetch(parkingTicket1);
        Car fetchedCar2 = standardParkingBoy.fetch(parkingTicket2);
        //then
        assertNotNull(parkingTicket1);
        assertNotNull(parkingTicket2);
        assertEquals(car1, fetchedCar1);
        assertEquals(car2, fetchedCar2);
        assertEquals(10, firstParkingLot.getAvailableCapacity());
        assertEquals(10, secondParkingLot.getAvailableCapacity());
    }

    @Test
    void should_return_UnrecognizedParkingTicket_when_fetch_the_car_given_standard_parking_boy_and_two_parking_lots_and_unrecognized_ticket() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);
        Car car = new Car();
        ParkingTicket wrongParkingTicket = new ParkingTicket();
        standardParkingBoy.park(car);
        //when
        //then
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () -> {
            standardParkingBoy.fetch(wrongParkingTicket);
        });
        assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_UnrecognizedParkingTicket_when_fetch_the_car_given_standard_parking_boy_and_two_parking_lots_and_used_ticket() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);

        Car car = new Car();
        ParkingTicket parkingTicket = standardParkingBoy.park(car);
        standardParkingBoy.fetch(parkingTicket);
        //when
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () -> {
            standardParkingBoy.fetch(parkingTicket);
        });
        //then
        assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_UnavailableParkingLotException_when_park_the_car_given_standard_parking_boy_and_car_and_park_without_availability() {
        // Given
        ParkingLot firstParkingLot = new ParkingLot(0);
        ParkingLot secondParkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        Car newCar = new Car();
        Car parkedCar = new Car();
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);
        standardParkingBoy.park(parkedCar);
        // When
        UnavailableParkingLotException unavailableParkingLotException = assertThrows(UnavailableParkingLotException.class, () -> {
            standardParkingBoy.park(newCar);
        });
        // Then
        assertEquals("No available position.", unavailableParkingLotException.getMessage());
    }
}

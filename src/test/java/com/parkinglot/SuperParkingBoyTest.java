package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SuperParkingBoyTest {
    @Test
    void should_park_to_parking_lot_with_highest_available_rate_when_park_the_car_given_super_parking_boy_and_two_parking_lots_and_car() {
        //given
        ParkingLot firstParkingLot = new ParkingLot(8);
        ParkingLot secondParkingLot = new ParkingLot(9);
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);
        Car car = new Car();
        //when
        ParkingTicket parkingTicket = superParkingBoy.park(car);
        //then
        assertNotNull(parkingTicket);
        assertEquals(8, firstParkingLot.getAvailableCapacity());
        assertEquals(8, secondParkingLot.getAvailableCapacity());
    }

    @Test
    void should_park_at_second_parking_lot_when_park_the_car_given_a_super_boy_and_two_parking_lots_where_first_is_full_second_is_available() {
        //given
        ParkingLot firstParkingLot = new ParkingLot(0);
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);
        Car car = new Car();
        //when
        ParkingTicket parkingTicket = superParkingBoy.park(car);
        //then
        assertNotNull(parkingTicket);
        assertEquals(0, firstParkingLot.getAvailableCapacity());
        assertEquals(9, secondParkingLot.getAvailableCapacity());
    }

    @Test
    void should_return_right_car_when_fetch_both_car_given_super_parking_boy_and_two_parking_ticket() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);
        Car car1 = new Car();
        Car car2 = new Car();
        //when
        ParkingTicket parkingTicket1 = superParkingBoy.park(car1);
        ParkingTicket parkingTicket2 = superParkingBoy.park(car2);
        Car fetchedCar1 = superParkingBoy.fetch(parkingTicket1);
        Car fetchedCar2 = superParkingBoy.fetch(parkingTicket2);
        //then
        assertNotNull(parkingTicket1);
        assertNotNull(parkingTicket2);
        assertEquals(car1, fetchedCar1);
        assertEquals(car2, fetchedCar2);
        assertEquals(10, firstParkingLot.getAvailableCapacity());
        assertEquals(10, secondParkingLot.getAvailableCapacity());
    }

    @Test
    void should_return_UnrecognizedParkingTicket_when_fetch_the_car_given_super_parking_boy_and_two_parking_lots_and_unrecognized_ticket() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);
        Car car = new Car();
        ParkingTicket wrongParkingTicket = new ParkingTicket();
        superParkingBoy.park(car);
        //when
        //then
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () -> {
            superParkingBoy.fetch(wrongParkingTicket);
        });
        assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_UnrecognizedParkingTicket_when_fetch_the_car_given_super_parking_boy_and_two_parking_lots_and_used_ticket() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);

        Car car = new Car();
        ParkingTicket parkingTicket = superParkingBoy.park(car);
        superParkingBoy.fetch(parkingTicket);
        //when
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () -> {
            superParkingBoy.fetch(parkingTicket);
        });
        //then
        assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_UnavailableParkingLotException_when_park_the_car_given_super_parking_boy_and_car_and_park_without_availability() {
        // Given
        ParkingLot firstParkingLot = new ParkingLot(0);
        ParkingLot secondParkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        Car newCar = new Car();
        Car parkedCar = new Car();
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);
        superParkingBoy.park(parkedCar);
        // When
        UnavailableParkingLotException unavailableParkingLotException = assertThrows(UnavailableParkingLotException.class, () -> {
            superParkingBoy.park(newCar);
        });
        // Then
        assertEquals("No available position.", unavailableParkingLotException.getMessage());
    }
}

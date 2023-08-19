package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SuperParkingBoyTest {
    @Test
    void should_park_to_parking_lot_with_highest_available_rate_when_park_given_super_parking_boy_and_two_parking_lots_and_car() {
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
    void should_car_will_be_park_at_second_parking_lot_when_park_given_a_super_boy_and_two_parking_lots_first_is_full_second_available() {
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
}

package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SuperParkingBoyTest {
    @Test
    void should_park_to_first_parking_lot_when_park_given_a_super_parking_boy_and_two_parking_lots_and_a_car() {
        //given
        ParkingLot firstParkingLot = new ParkingLot(17); // 17/20 = 0.85
        ParkingLot secondParkingLot = new ParkingLot(18); // 18/20 = 0.9 this should where super parking boy park
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);
        Car car = new Car();
        //when
        ParkingTicket parkingTicket = superParkingBoy.park(car);
        //then
        assertNotNull(parkingTicket);
        assertEquals(17, firstParkingLot.getAvailableCapacity());
        assertEquals(17, secondParkingLot.getAvailableCapacity());
    }
}

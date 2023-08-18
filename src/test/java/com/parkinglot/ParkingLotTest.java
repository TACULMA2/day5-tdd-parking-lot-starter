package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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


}


package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
//    private Car car;
    private Map<ParkingTicket, Car> parkedCar = new HashMap<>();

    public ParkingTicket park(Car car) {
        ParkingTicket ticket = new ParkingTicket();
        parkedCar.put(ticket, car);
//        this.car = car;
//        return new ParkingTicket();
        return ticket;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return parkedCar.get(parkingTicket);
    }
}

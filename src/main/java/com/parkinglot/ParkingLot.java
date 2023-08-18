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
        Car fetchedCar = parkedCar.get(parkingTicket);

        if (fetchedCar != null) {
            parkedCar.remove(parkingTicket); // Remove the used parking ticket
        }

        return fetchedCar;
//        return parkedCar.get(parkingTicket);
    }
}

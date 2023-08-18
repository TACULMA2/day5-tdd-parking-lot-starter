package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    public static final int MAX_CAPACITY = 10;
    private Map<ParkingTicket, Car> parkedCar = new HashMap<>();

    public ParkingTicket park(Car car) {
        if (parkedCar.size() >= MAX_CAPACITY) {
            return null;
        }

        ParkingTicket ticket = new ParkingTicket();
        parkedCar.put(ticket, car);
        return ticket;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        Car fetchedCar = parkedCar.get(parkingTicket);

        if (fetchedCar != null) {
            parkedCar.remove(parkingTicket);
        }
        return fetchedCar;
    }
}

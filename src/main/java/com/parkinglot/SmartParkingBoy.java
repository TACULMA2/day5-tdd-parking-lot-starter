package com.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy {
    private final List<ParkingLot> parkingLots;

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) {
        ParkingLot parkingLotWithMostCapacity = parkingLots.stream()
                .max(Comparator.comparing(ParkingLot::getAvailableCapacity))
                .orElseThrow(UnavailableParkingLotException::new);
        return parkingLotWithMostCapacity.park(car);
    }
}

package com.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SuperParkingBoy {
    private final List<ParkingLot> parkingLots;

    public SuperParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) {
        ParkingLot parkingLotWithHighestAvailableRate = parkingLots.stream()
                .max(Comparator.comparingDouble(this::calculateAvailableRate))
                .orElseThrow(UnavailableParkingLotException::new);
        return parkingLotWithHighestAvailableRate.park(car);
    }

    private double calculateAvailableRate(ParkingLot parkingLot) {
        double totalCapacity = parkingLot.getTotalCapacity();
        double availableCapacity = parkingLot.getAvailableCapacity();
        double rate = availableCapacity / totalCapacity;
        return rate;
    }
}

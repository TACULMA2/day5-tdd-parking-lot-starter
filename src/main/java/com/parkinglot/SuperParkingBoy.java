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
                .filter(ParkingLot::hasAvailableCapacity)
                .sorted(Comparator.comparingInt(ParkingLot::getAvailableCapacity).reversed())
                .findFirst()
                .orElseThrow(UnavailableParkingLotException::new);
        return parkingLotWithHighestAvailableRate.park(car);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        for (ParkingLot parkingLot : parkingLots) {
            try {
                return parkingLot.fetch(parkingTicket);
            } catch (UnrecognizedTicketException ignored) {
            }
        }
        throw new UnrecognizedTicketException();
    }
}

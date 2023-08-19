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

    public Car fetch(ParkingTicket parkingTicket) {
        for (ParkingLot parkingLot : parkingLots) {
            try {
                return parkingLot.fetch(parkingTicket);
            } catch (UnrecognizedTicketException ignored) {
                // Catch and ignore the exception if the ticket is not recognized in this parking lot
            }
        }
        throw new UnrecognizedTicketException(); // If the ticket is not recognized in any of the parking lots
    }
}

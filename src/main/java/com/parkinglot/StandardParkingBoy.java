package com.parkinglot;

import java.util.List;

public class StandardParkingBoy {
    private final List<ParkingLot> parkingLots;

    public StandardParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) {
        return parkingLots.stream()
                .filter(ParkingLot::hasAvailableCapacity)
                .findFirst()
                .orElseThrow(UnavailableParkingLotException::new)
                .park(car);
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

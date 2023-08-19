package com.parkinglot;

import java.util.List;

public class StandardParkingBoy extends ParkingLot {
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
}

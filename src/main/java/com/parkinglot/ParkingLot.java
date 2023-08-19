package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    public static final int MAX_CAPACITY = 10;
    private final int capacity;
    Map<ParkingTicket, Car> parkedCar = new HashMap<>();

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public ParkingTicket park(Car car) {
        if (isFull()) {
            throw new UnavailableParkingLotException();
        }
        ParkingTicket ticket = new ParkingTicket();
        parkedCar.put(ticket, car);
        return ticket;
    }

    public ParkingLot() {
        this(MAX_CAPACITY);
    }

    private boolean isFull() {
        return parkedCar.size() == capacity;
    }

    public Car fetch(ParkingTicket parkingTicket) {
//        Car car = parkedCar.remove(parkingTicket);
        if (!parkedCar.containsKey(parkingTicket)){
            throw new UnrecognizedTicketException();
        }
        return parkedCar.remove(parkingTicket);
    }
////    public Car fetch(ParkingTicket parkingTicket) {
////        Car car = parkedCar.remove(parkingTicket);
////        if (car == null) {
////            throw new UnrecognizedTicketException();
////        }
////        return car;
////    }
//    public Car fetch(ParkingTicket parkingTicket) {
//        for (ParkingLot parkingLot : parkingLots) {
//        try {
//            return parkingLot.fetch(parkingTicket);
//        } catch (UnrecognizedTicketException ignored) {
//            // Catch and ignore the exception if the ticket is not recognized in this parking lot
//        }
//    }
//    throw new UnrecognizedTicketException(); // If the ticket is not recognized in any of the parking lots
//}


    public int getAvailableCapacity() {
        return capacity - parkedCar.size();
    }

    public boolean hasAvailableCapacity() {
        return !isFull();
    }
}

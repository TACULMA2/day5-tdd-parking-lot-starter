package com.parkinglot;

public class UnavailableParkingLotException extends RuntimeException {
    public UnavailableParkingLotException() {
        super("No available position.");
    }
}

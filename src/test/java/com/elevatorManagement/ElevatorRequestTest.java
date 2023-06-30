package com.elevatorManagement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElevatorRequestTest {

    private ElevatorRequest elevatorRequest;

    @BeforeEach
    void setUp() {
        elevatorRequest = new ElevatorRequest(1, 5);
    }

    @Test
    void testGetOriginFloor() {
        assertEquals(1, elevatorRequest.getOriginFloor());
    }

    @Test
    void testGetDestinationFloor() {
        assertEquals(5, elevatorRequest.getDestinationFloor());
    }
    
    @Test
    void testValidOriginFloor() {
        ElevatorRequest request = new ElevatorRequest(1, 10);
        assertEquals(1, request.getOriginFloor());
    }

    @Test
    void testValidDestinationFloor() {
        ElevatorRequest request = new ElevatorRequest(1, 10);
        assertEquals(10, request.getDestinationFloor());
    }
   
    
}


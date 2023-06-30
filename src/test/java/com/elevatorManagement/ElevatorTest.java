package com.elevatorManagement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class ElevatorTest {

    private Elevator elevator;
    
    @BeforeEach
    void setUp() {
        elevator = new Elevator();
    }
    
    @Test
    void testIsIdle() {
        assertTrue(elevator.isIdle());
    }
   
 
    @Test
    public void testFloorsBeyondBuildingCapacity() {
        Building building = new Building(10, 2);
        assertThrows(IllegalArgumentException.class, () -> building.addRequest(new ElevatorRequest(5, 11)));
    }

    @Test
    public void testSequentialRequests() {
        Building building = new Building(10, 2);
        building.addRequest(new ElevatorRequest(3, 7));
        building.addRequest(new ElevatorRequest(8, 2));
        assertEquals(2, building.getRequests().size());
    }


}


package com.elevatorManagement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuildingTest {

    private Building building;

    @BeforeEach
    void setUp() {
        building = new Building(10, 2);
    }

    @Test
    void testGetElevators() {
        assertEquals(2, building.getElevators().size());
    }
    
    @Test
    void testNumberOfElevators() {
        Building building = new Building(10, 2);
        assertEquals(2, building.getElevators().size());
    }

    @Test
    void testNumberOfFloors() {
        Building building = new Building(10, 2);
        assertEquals(10, building.getNumberOfFloors());
    }

    @Test
    void testAddRequest() {
        Building building = new Building(10, 2);
        building.addRequest(new ElevatorRequest(1, 5));
        assertFalse(building.getRequests().isEmpty());
    }
    
    @Test
    void testInvalidNumberOfElevators() {
        assertThrows(IllegalArgumentException.class, () -> new Building(10, -1));
    }

    @Test
    void testInvalidNumberOfFloors() {
        assertThrows(IllegalArgumentException.class, () -> new Building(-10, 2));
    }
}


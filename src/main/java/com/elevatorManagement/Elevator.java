package com.elevatorManagement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class represents an Elevator in a Building. It implements Runnable and executes in a separate thread.
 *
 * An Elevator services ElevatorRequests. It moves from floor to floor, loads and unloads passengers.
 */
public class Elevator implements Runnable {
	private static final Logger logger = LogManager.getLogger();
	private int currentFloor;
    private ElevatorRequest currentRequest;
    private Direction currentDirection;
	
	// An enum to represent the direction of elevator movement
    public enum Direction {
        UP, DOWN, IDLE
    }
    
	/**
	 * Constructor to create a new elevator that initially doesn't have any requests and is on the ground floor (floor 0).
	 */
    public Elevator() {
        this.currentFloor = 0;
        this.currentRequest = null;
        this.currentDirection = Direction.IDLE; // initially, the elevator is idle
    }

    /**
	 * This method is used to assign a request to the elevator. When a request is assigned,
	 * the direction of the elevator is decided based on the current and destination floors.
	 * This method also wakes up the elevator thread if it was waiting for a request.
	 *
	 * @param request The elevator request to be assigned
	 */
    public synchronized void assignRequest(ElevatorRequest request) {
        this.currentRequest = request;
        this.currentDirection = (currentFloor < request.getDestinationFloor()) ? Direction.UP : Direction.DOWN;
        notifyAll();
    }

	/**
	 * This is the main logic of the elevator, it waits for a request, processes it by moving to the origin floor, loading passengers,
	 * moving to the destination floor, and offloading passengers.
	 */
	public void run() {
		while(true) {
			synchronized (this) {
				while(currentRequest == null) {  // Wait for a request
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			moveTo(currentRequest.getOriginFloor());  // Move to the origin floor
			currentRequest.setStatus("in progress");
			loadPassengers();  // Load passengers
			moveTo(currentRequest.getDestinationFloor());  // Move to the destination floor
			offloadPassengers();  // Offload passengers
		}
	}
	
	// Getter method for current direction of the elevator
	public Direction getCurrentDirection() {
        return currentDirection;
    }
	
	/**
	 * This method is used to move the elevator to a certain floor.
	 *
	 * @param floor The floor to move to
	 */
	private void moveTo(int floor) {
        try {
            if(floor != this.currentFloor) {
                logger.info("Moving from floor " + this.currentFloor  + " to floor "+ floor);
                
                // time taken to move from current floor to indicated floor
                int time = Math.abs(this.currentFloor - floor) * 1000;
                
                // Update the current direction of elevator movement
                this.currentDirection = (this.currentFloor < floor) ? Direction.UP : Direction.DOWN;
                
                // Code for moving the elevator
                this.currentFloor = floor; // simple implementation
                
                Thread.sleep(time); // simulate time it takes to move to the floor
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

	/**
	 * This method is used to simulate the loading of passengers into the elevator.
	 */
	private void loadPassengers() {
		// Code for loading passengers
		try {
			logger.info("Loading passengers ...");
			Thread.sleep(5000); // simulate time it takes to load passengers
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to simulate the offloading of passengers from the elevator.
	 */
	private void offloadPassengers() {
		// Code for offloading passengers
		try {
			logger.info("Offloading passengers ...");
			Thread.sleep(5000); // simulate time it takes to offload passengers
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.currentRequest.setStatus("completed");
		currentRequest = null;  // Clear the current request
	}

	/**
	 * This method is used to get the current floor of the elevator.
	 *
	 * @return The current floor of the elevator
	 */
	public int getCurrentFloor() {
		return currentFloor;
	}

	/**
	 * This method is used to check if the elevator is idle (i.e., it doesn't have a current request).
	 *
	 * @return True if the elevator is idle, false otherwise
	 */
	public boolean isIdle() {
		return currentRequest == null;
	}

	/**
	 * This method provides a status update for the Elevator.
	 * 
	 * If the elevator is idle (i.e., not currently serving a request), it returns a string
	 * indicating that it's idle and states the floor number where it's currently located.
	 * 
	 * If the elevator is serving a request (i.e., moving between floors), it returns a string
	 * indicating it's moving, along with the origin and destination floor numbers.
	 * 
	 * @return String providing a status update for the Elevator.
	 */
	public String getStatus() {
        if(currentRequest == null) {
            return "Elevator is idle at floor " + currentFloor;
        } else {
            String movingDirection = (currentDirection == Direction.UP) ? "up" : "down";
            
            return "Elevator is moving " + movingDirection + " from floor " + currentFloor +
                " to floor " + currentRequest.getDestinationFloor();
        }
    }
}




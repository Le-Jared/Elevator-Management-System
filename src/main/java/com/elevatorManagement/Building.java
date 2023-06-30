package com.elevatorManagement;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * This class represents a Building that contains Elevators and manages ElevatorRequests.
 *
 * A Building contains a list of Elevators and a Queue of ElevatorRequests. An ElevatorRequest is serviced by an available Elevator.
 */
public class Building {
	private ArrayList<Elevator> elevators;  // List to hold all elevators in the building
	private Queue<ElevatorRequest> requests;  // Queue to hold all elevator requests
	private int numberOfFloors;  // Total number of floors in the building

	/**
	 * Constructor to create a new building with a certain number of floors and elevators.
	 *
	 * @param floors       The total number of floors in the building
	 * @param numElevators The total number of elevators in the building
	 */
	public Building(int floors, int numElevators) {
		if (floors < 1 || numElevators < 1) {
			throw new IllegalArgumentException("Number of floors and elevators must be at least 1.");
		}
		this.numberOfFloors = floors;
		this.elevators = new ArrayList<>();
		for (int i = 0; i < numElevators; i++) {
			Elevator elevator = new Elevator();
			elevators.add(elevator);
			new Thread(elevator).start(); // start each elevator's thread
		}
		this.requests = new LinkedList<>();
	}

	/**
	 * This method is used to add a new elevator request to the queue.
	 *
	 * @param request The elevator request to be added
	 */
	public void addRequest(ElevatorRequest request) {
		if (request.getOriginFloor() > numberOfFloors || request.getDestinationFloor() > numberOfFloors) {
			throw new IllegalArgumentException("Invalid floor number.");
		}
		requests.add(request);
	}

	/**
	 * This method is used to assign a request to an elevator. If there are no idle elevators, 
	 * the request remains in the queue. It aims to find the best elevator and request pair 
	 * such that the 'distance' (difference in floors) is minimized and, in case of a tie, 
	 * the direction of the elevator aligns with the direction of the request.
	 */
	public void assignRequest() {
	    Elevator bestElevator = null;
	    ElevatorRequest bestRequest = null;
	    int bestDistance = Integer.MAX_VALUE;

	    for (Elevator elevator : elevators) {
	        if (!requests.isEmpty()) {
	            for (ElevatorRequest request : requests) {
	                int distance = Math.abs(elevator.getCurrentFloor() - request.getOriginFloor());
	                Elevator.Direction direction = elevator.getCurrentFloor() < request.getOriginFloor() ? Elevator.Direction.UP : Elevator.Direction.DOWN;

	                if (distance < bestDistance ||
	                    (distance == bestDistance && elevator.getCurrentDirection() == direction)) {
	                    bestDistance = distance;
	                    bestElevator = elevator;
	                    bestRequest = request;
	                }
	            }
	        }
	    }

	    if (bestElevator != null && bestRequest != null) {
	        requests.remove(bestRequest);
	        bestElevator.assignRequest(bestRequest);
	    }
	}


	/**
	 * This method is used to get the list of all elevators in the building.
	 *
	 * @return The list of all elevators in the building
	 */
	public ArrayList<Elevator> getElevators() {
		return elevators;
	}

	/**
	 * This method is used to get the queue of all elevator requests.
	 *
	 * @return The queue of all elevator requests
	 */
	public Queue<ElevatorRequest> getRequests() {
		return requests;
	}

	/**
	 * This method is used to get the total number of floors in the building.
	 *
	 * @return The total number of floors in the building
	 */
	public int getNumberOfFloors() {
		return numberOfFloors;
	}
}





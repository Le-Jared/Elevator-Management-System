package com.elevatorManagement;

/**
 * This class represents an ElevatorManager that manages ElevatorRequests in a Building.
 *
 * The ElevatorManager runs in a separate thread. It assigns ElevatorRequests from the Building's Queue to available Elevators in the Building.
 */
public class ElevatorManager implements Runnable {
	private Building building;  // The building where the elevator manager operates

	/**
	 * Constructor to create a new elevator manager for a certain building.
	 *
	 * @param building The building where the elevator manager operates
	 */
	public ElevatorManager(Building building) {
		this.building = building;
	}

	/**
	 * This is the main logic of the elevator manager, it checks if there are any requests in the building and assigns them to elevators.
	 */
	public void run() {
		while(true) {
			if(!building.getRequests().isEmpty()) {  // Check if there are any requests
				building.assignRequest();  // Assign the next request to an elevator
			}
			try {
				// pause for 1 second before checking the requests again
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}



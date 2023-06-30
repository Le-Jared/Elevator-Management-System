package com.elevatorManagement;

/**
 * This class represents an ElevatorRequest in a Building.
 *
 * An ElevatorRequest has an origin floor and a destination floor. It also has a status that represents the state of the request.
 */
public class ElevatorRequest {
	private int originFloor;  // The floor where the request originates
	private int destinationFloor;  // The floor where the requester wants to go
	private String status; //Current status of elevator


	/**
	 * Constructs a new elevator request with the specified origin and destination floors.
	 *
	 * @param originFloor      The floor where the request originates
	 * @param destinationFloor The floor where the requester wants to go
	 */
	public ElevatorRequest(int originFloor, int destinationFloor) {
        if(originFloor == destinationFloor) {
            throw new IllegalArgumentException("Origin and destination floors cannot be the same.");
        }
        this.originFloor = originFloor;
        this.destinationFloor = destinationFloor;
        this.status = "pending";
    }

	/**
	 * This method is used to get the origin floor of the request.
	 *
	 * @return The origin floor of the request
	 */
	public int getOriginFloor() {
		return originFloor;
	}

	/**
	 * This method is used to get the destination floor of the request.
	 *
	 * @return The destination floor of the request
	 */
	public int getDestinationFloor() {
		return destinationFloor;
	}

	/**
	 * Returns the status of the elevator request.
	 *
	 * @return The status of the elevator request.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status of the elevator request.
	 *
	 * @param status The new status to be set for the elevator request.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}


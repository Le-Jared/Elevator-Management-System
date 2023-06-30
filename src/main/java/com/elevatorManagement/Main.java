package com.elevatorManagement;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This is the main class for the elevator management system.
 *
 * It initiates a Building with Elevators, starts an ElevatorManager, and accepts user input for ElevatorRequests.
 */
public class Main {
	private static final Logger logger = LogManager.getLogger();
	static int floors = 10;
	static int elevators = 2;

	public static void main(String[] args) {
		// Create a building with specified number of floors and elevators
		Building building = new Building(floors, elevators);

		// Start the elevator manager in a new thread
		Thread elevatorManagerThread = new Thread(new ElevatorManager(building));
		elevatorManagerThread.start();

		// Create a Scanner to read input from the console
		Scanner scanner = new Scanner(System.in);

		while(true) {
			// Ask the user to input the origin floor
			int originFloor = getValidFloorInput(scanner, "Enter the origin floor (or -1 to quit):");

			// If the user inputs -1, exit the program
			if(originFloor == -1) {
				logger.info("Exited the program.");
				break; 
			}
			
			// Ask the user to input the destination floor
			int destinationFloor = getValidFloorInput(scanner, "Enter the destination floor (or -1 to quit):");
			
			// If the user inputs -1, exit the program
			if(destinationFloor == -1) {
				logger.info("Exited the program.");
				break; 
			}
			
			while(destinationFloor == originFloor) {
				logger.error("orgin and destination floor is same - invalid.");
				destinationFloor = getValidFloorInput(scanner, "Origin and destination floor is same. Please indicate a different destination:");
			}

			// Create a new elevator request and add it to the building's request queue
			building.addRequest(new ElevatorRequest(originFloor, destinationFloor));

			// Print the current state of the building
			printBuildingState(building);
		}

		// Close the Scanner and interrupt the elevator manager thread when done
		scanner.close();
		elevatorManagerThread.interrupt(); 
	}

	/**
	 * Retrieves a valid floor number input from the user within a specified range.
	 *
	 * @param scanner The Scanner object used for user input.
	 * @param message The message to display to the user when prompting for the floor number.
	 * @return A valid floor number input within the range of -1 to max number of floors in the building (inclusive).
	 */
	private static int getValidFloorInput(Scanner scanner, String message) {
		int floor;
		while (true) {
			System.out.println(message);
			String input = scanner.nextLine();

			try {
				floor = Integer.parseInt(input);
				// Checks if within the range of floors
				if (floor >= -1 && floor <= floors) {
					break;
				}
			} catch (NumberFormatException e) {
				// Invalid input (not an integer)
			}
			logger.fatal("Invalid input for floor");
			System.out.println("Invalid input. Please enter a valid floor number between 0 and " + floors + ".");
		}

		return floor;
	}

	/**
	 * This method prints the current state of the building, including the current floor of each elevator and all pending requests.
	 *
	 * @param building The building whose state is to be printed
	 */
	private static void printBuildingState(Building building) {
		System.out.println("Current building state:");

		// Print each elevator's state
		int i = 1;
		for (Elevator elevator : building.getElevators()) {
			System.out.println("Elevator " + i + " status: " + elevator.getStatus());
			i++;
		}

		// Print queued requests
		System.out.println("Pending requests:");
		for (ElevatorRequest request : building.getRequests()) {
			System.out.println("Origin: " + request.getOriginFloor() + ", Destination: " + request.getDestinationFloor() + ", Status: " + request.getStatus());
		}
	}
}





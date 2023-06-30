# Elevator Management System
### Project Description
This project is a simple simulation of an Elevator Management System written in Java. It models the behavior of a building with multiple elevators and simulates the functionality of elevators moving up and down floors, servicing requests, and loading/unloading passengers.

This simulation can be helpful in understanding the complexity involved in designing an efficient elevator scheduling system and can be extended to incorporate more advanced features.

### Project Structure
This system is capable of managing multiple elevators in a building, assigning requests to these elevators, and moving the elevators between different floors to meet these requests. The core of the system consists of two classes: 

- Elevator: This class models the behavior of an individual elevator in the building. It is responsible for moving between floors, loading/unloading passengers, and servicing requests.

- Building: This class represents a building that contains multiple elevators. It manages the queue of elevator requests and assigns requests to the available elevators in an efficient manner.

- ElevatorRequest: This class encapsulates a single elevator request. Each request contains an origin floor and a destination floor.

- ElevatorManager: Manages elevator requests by assigning them to the elevators in the building.

- Main: Serves as the entry point for the elevator management system. It initiates a building, starts the elevator manager, and allows users to make elevator requests via the console.

### Features
Multi-threading: Each elevator operates in its own thread, simulating real-world operations of elevators running concurrently.

Synchronization: Elevator request assignment is synchronized to prevent multiple elevators from servicing the same request.

Exception handling: Proper handling of exceptions to ensure smooth operation.

Logging: Use of Apache Log4j to generate logs for debugging and monitoring.
 

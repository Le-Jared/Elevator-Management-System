# Elevator Management System
### Project Description
This project is a simple simulation of an Elevator Management System written in Java. It models the behavior of a building with multiple elevators and simulates the functionality of elevators moving up and down floors, servicing requests, and loading/unloading passengers.

This simulation can be helpful in understanding the complexity involved in designing an efficient elevator scheduling system and can be extended to incorporate more advanced features.

### Overview
This system is capable of managing multiple elevators in a building, assigning requests to these elevators, and moving the elevators between different floors to meet these requests. The core of the system consists of two classes: Elevator and Building.

- Elevator class: Represents an elevator in a building. An Elevator services ElevatorRequests, moving from floor to floor, loading and unloading passengers.

- Building class: Represents a building with a certain number of floors and elevators. It maintains a list of elevators and a queue of elevator requests. An ElevatorRequest is serviced by an available Elevator.

### Features
Multi-threading: Each elevator operates in its own thread, simulating real-world operations of elevators running concurrently.

Synchronization: Elevator request assignment is synchronized to prevent multiple elevators from servicing the same request.

Exception handling: Proper handling of exceptions to ensure smooth operation.

Logging: Use of Apache Log4j to generate logs for debugging and monitoring.
 

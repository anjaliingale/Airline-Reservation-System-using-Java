import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Flight {
    private String flightNumber;
    private int totalSeats;
    private boolean[] seats;
    private List<Passenger> passengers;

    public Flight(String flightNumber, int totalSeats) {
        this.flightNumber = flightNumber;
        this.totalSeats = totalSeats;
        this.seats = new boolean[totalSeats];
        this.passengers = new ArrayList<>();
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public boolean[] getSeats() {
        return seats;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public boolean reserveSeat(int seatNumber, Passenger passenger) {
        if (seatNumber >= 1 && seatNumber <= totalSeats) {
            if (!seats[seatNumber - 1]) {
                seats[seatNumber - 1] = true;
                passengers.add(passenger);
                return true;
            }
        }
        return false;
    }
}

class Passenger {
    private String name;
    private String email;

    public Passenger(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}

class AirlineReservationSystem {
    private List<Flight> flights;

    public AirlineReservationSystem() {
        this.flights = new ArrayList<>();
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public Flight findFlight(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                return flight;
            }
        }
        return null;
    }

    public void displayFlights() {
        System.out.println("Available Flights:");
        for (Flight flight : flights) {
            System.out.println("Flight Number: " + flight.getFlightNumber());
            System.out.println("Total Seats: " + flight.getTotalSeats());
            System.out.println("Available Seats: " + getAvailableSeats(flight));
            System.out.println();
        }
    }

    public void reserveSeat(Flight flight, int seatNumber, Passenger passenger) {
        boolean success = flight.reserveSeat(seatNumber, passenger);
        if (success) {
            System.out.println("Seat reserved successfully for " + passenger.getName() + " on Flight " + flight.getFlightNumber());
        } else {
            System.out.println("Sorry, the seat is already occupied or invalid seat number.");
        }
    }

    public int getAvailableSeats(Flight flight) {
        int availableSeats = 0;
        for (boolean seat : flight.getSeats()) {
            if (!seat) {
                availableSeats++;
            }
        }
        return availableSeats;
    }
}

public class Main {
    public static void main(String[] args) {
        AirlineReservationSystem airlineReservationSystem = new AirlineReservationSystem();

        Flight flight1 = new Flight("ABC123", 100);
        Flight flight2 = new Flight("XYZ456", 150);

        airlineReservationSystem.addFlight(flight1);
        airlineReservationSystem.addFlight(flight2);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the Airline Reservation System");
            System.out.println("1. Display Available Flights");
            System.out.println("2. Reserve a Seat");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                airlineReservationSystem.displayFlights();
            } else if (choice == 2) {
                System.out.print("Enter the flight number: ");
                String flightNumber = scanner.next();
                Flight flight = airlineReservationSystem.findFlight(flightNumber);
                if (flight != null) {
                    System.out.print("Enter your name: ");
                    String name = scanner.next();
                    System.out.print("Enter your email: ");
                    String email = scanner.next();
                    Passenger passenger = new Passenger(name, email);
                    System.out.print("Enter the seat number: ");
                    int seatNumber = scanner.nextInt();
                    airlineReservationSystem.reserveSeat(flight, seatNumber, passenger);
                } else {
                    System.out.println("Flight not found. Please try again.");
                }
            } else if (choice == 3) {
                System.out.println("Thank you for using the Airline Reservation System");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

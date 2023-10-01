import java.util.*;

class Flight {
    private String flightNumber;
    private String origin;
    private String destination;
    private int capacity;
    private int bookedSeats;

    public Flight(String flightNumber, String origin, String destination, int capacity) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.capacity = capacity;
        this.bookedSeats = 0;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getBookedSeats() {
        return bookedSeats;
    }

    public boolean bookSeat(int numSeats) {
        if (bookedSeats + numSeats <= capacity) {
            bookedSeats += numSeats;
            return true;
        }
        return false;
    }
}

class Passenger {
    private String name;
    private String phoneNumber;

    public Passenger(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}

class ReservationSystem {
    private List<Flight> flights = new ArrayList<>();
    private Map<String, List<Passenger>> reservations = new HashMap<>();

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public boolean bookFlight(String flightNumber, Passenger passenger, int numSeats) {
        Flight flight = flights.stream()
                .filter(f -> f.getFlightNumber().equals(flightNumber))
                .findFirst()
                .orElse(null);

        if (flight != null && flight.bookSeat(numSeats)) {
            reservations.putIfAbsent(flightNumber, new ArrayList<>());
            reservations.get(flightNumber).add(passenger);
            return true;
        }
        return false;
    }

    public void displayFlightDetails() {
        System.out.println("Flight Details:");
        for (Flight flight : flights) {
            System.out.println("Flight Number: " + flight.getFlightNumber());
            System.out.println("Origin: " + flight.getOrigin());
            System.out.println("Destination: " + flight.getDestination());
            System.out.println("Capacity: " + flight.getCapacity());
            System.out.println("Booked Seats: " + flight.getBookedSeats());
            System.out.println();
        }
    }
}

public class AirlineReservationSystem {
    public static void main(String[] args) {
        ReservationSystem reservationSystem = new ReservationSystem();

        // Adding flights to the system
        reservationSystem.addFlight(new Flight("F101", "New York", "Los Angeles", 150));
        reservationSystem.addFlight(new Flight("F102", "Chicago", "San Francisco", 200));

        // Booking flights
        Passenger passenger1 = new Passenger("Alice", "123-456-7890");
        Passenger passenger2 = new Passenger("Bob", "987-654-3210");

        boolean booking1 = reservationSystem.bookFlight("F101", passenger1, 2);
        boolean booking2 = reservationSystem.bookFlight("F102", passenger2, 3);

        if (booking1 && booking2) {
            System.out.println("Flights booked successfully.");
        } else {
            System.out.println("Failed to book one or more flights.");
        }

        // Displaying flight details
        reservationSystem.displayFlightDetails();
    }
}

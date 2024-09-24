

import java.util.ArrayList;
import java.util.List;

public class MovieTheater {
    private final String theaterName;
    private List<Seat> seats = new ArrayList<>();

    // Constructor to initialize the theater and seats
    public MovieTheater(String theaterName, int numRows, int seatsPerRow) {
        this.theaterName = theaterName;

        // Generate seat numbers based on rows and columns
        for (int row = 1; row <= numRows; row++) {
            for (int seat = 1; seat <= seatsPerRow; seat++) {
                String seatNumber = row + String.format("%02d", seat); // e.g., "101", "102"
                seats.add(new Seat(seatNumber));
            }
        }
    }

    // Method to retrieve the theater name
    public String getTheaterName() {
        return theaterName;
    }

    // Method to reserve a seat
    public boolean reserveSeat(String seatNumber) {
        Seat requestedSeat = findSeat(seatNumber);
        if (requestedSeat == null) {
            System.out.println("Seat " + seatNumber + " does not exist.");
            return false;
        }
        return requestedSeat.reserve();
    }

    // Method to cancel a reservation
    public boolean cancelSeat(String seatNumber) {
        Seat requestedSeat = findSeat(seatNumber);
        if (requestedSeat == null) {
            System.out.println("Seat " + seatNumber + " does not exist.");
            return false;
        }
        return requestedSeat.cancel();
    }

    // Method to print the seating chart
    public void printSeatingChart() {
        System.out.println("Seating Chart for " + theaterName + ":");
        for (Seat seat : seats) {
            System.out.print(seat.getSeatNumber() + (seat.isReserved() ? " (Held) " : " (Avail) ")); // 'R' for reserved, 'A' for available
            if (Integer.parseInt(seat.getSeatNumber()) % 10 == 0) { // Assuming 10 seats per row
                System.out.println();
            }
        }
    }

    // Private helper method to find a seat by seat number
    private Seat findSeat(String seatNumber) {
        for (Seat seat : seats) {
            if (seat.getSeatNumber().equals(seatNumber)) {
                return seat;
            }
        }
        return null;
    }

    // Inner Seat class
    private class Seat {
        private final String seatNumber;
        private boolean reserved = false;

        public Seat(String seatNumber) {
            this.seatNumber = seatNumber;
        }

        // Reserve the seat
        public boolean reserve() {
            if (!reserved) {
                reserved = true;
                System.out.println("Seat " + seatNumber + " reserved.");
                return true;
            } else {
                System.out.println("Seat " + seatNumber + " is already reserved.");
                return false;
            }
        }

        // Cancel the reservation
        public boolean cancel() {
            if (reserved) {
                reserved = false;
                System.out.println("Reservation for seat " + seatNumber + " canceled.");
                return true;
            } else {
                System.out.println("Seat " + seatNumber + " is not reserved.");
                return false;
            }
        }

        // Getter for seat number
        public String getSeatNumber() {
            return seatNumber;
        }

        // Check if the seat is reserved
        public boolean isReserved() {
            return reserved;
        }
    }

    public static void main(String[] args) {
        MovieTheater theater = new MovieTheater("Cineplex", 5, 10); // 5 rows, 10 seats per row

        // Print initial seating chart
        theater.printSeatingChart();

        // Reserve some seats
        theater.reserveSeat("103");
        theater.reserveSeat("105");

        // Try to reserve a seat that doesn't exist
        theater.reserveSeat("999");

        // Print seating chart after reservations
        theater.printSeatingChart();

        // Cancel a reservation
        theater.cancelSeat("105");

        // Print seating chart after cancellation
        theater.printSeatingChart();
    }
}

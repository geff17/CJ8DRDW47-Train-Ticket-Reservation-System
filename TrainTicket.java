/**
 * @author Gefte Machivene
 * CJ8DRDW47
 * ITJVA2-12 Formative Assessment
 */

public class TrainTicket {

    private String passengerName;
    private String destination;
    private String ticketClass;
    private double basePrice;

    public TrainTicket(String passengerName, String destination, String ticketClass, double basePrice) {
        this.passengerName = passengerName;
        this.destination = destination;
        this.ticketClass = ticketClass;
        this.basePrice = basePrice;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getDestination() {
        return destination;
    }

    public String getTicketClass() {
        return ticketClass;
    }

    public double getBasePrice() {
        return basePrice;
    }

    // Method to return information as a String for GUI
    public String getTicketInfo() {
        return "Passenger: " + passengerName + 
               " | Dest: " + destination + 
               " | Class: " + ticketClass + 
               " | Price: R" + String.format("%.2f", basePrice);
    }
    
    // The system printing out to the console
    public void printTicketInfo() {
        System.out.println("\n---- Ticket Details ----");
        System.out.println("Passenger Name: " + passengerName);
        System.out.println("Destination: " + destination);
        System.out.println("Ticket Class: " + ticketClass);
        System.out.printf("Total Price: R%.2f\n", basePrice);
    }
}
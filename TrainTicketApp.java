/**
 * @author Gefte Machivene
 * CJ8DRDW47
 * ITJVA2-12 Formative Assessment
 */
import java.util.Scanner;

public class TrainTicketApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String passengerName;
        String destination;
        String ticketClass;
        double basePrice = 0;
        double finalPrice;
        
        // Asking users to enter details 
        System.out.print("Enter Passenger Name: ");
        passengerName = scanner.nextLine();

        System.out.print("Enter Destination: ");
        destination = scanner.nextLine();

        while (true) {
            System.out.print("Enter Ticket Class (Economy/Business): ");
            ticketClass = scanner.nextLine();

            if (ticketClass.equalsIgnoreCase("Economy") || ticketClass.equalsIgnoreCase("Business")) {
                break;
            } else {
                System.out.println("Invalid ticket class.Please Try again.");
            }
        }

        while (true) {
            try {
                System.out.print("Enter Base Price: ");
                basePrice = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter a valid price.");
            }
        }
        
        // Logic of the Business Ticket Price
        if (ticketClass.equalsIgnoreCase("Economy")) {
            finalPrice = basePrice;
        } else {
            finalPrice = basePrice * 1.5;
        }

        TrainTicket ticket = new TrainTicket(passengerName, destination, ticketClass, finalPrice);

        ticket.printTicketInfo();

        scanner.close();
    }
}

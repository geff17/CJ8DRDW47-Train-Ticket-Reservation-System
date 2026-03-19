/**
 * @author Gefte Machivene
 * CJ8DRDW47
 * ITJVA2-12 Formative Assessment
 */

import java.util.ArrayList;
import java.sql.*; 

public class TicketManager {

    private ArrayList<TrainTicket> tickets;

    public TicketManager() {
        tickets = new ArrayList<>();
    }

 
    public void addTicket(TrainTicket ticket) {
        tickets.add(ticket);
    }
    
    // Code for showing all tickets
    public String displayTickets() {
    StringBuilder output = new StringBuilder();

    for (TrainTicket t : tickets) {
        output.append("Passenger: ").append(t.getPassengerName()).append("\n")
              .append("Destination: ").append(t.getDestination()).append("\n")
              .append("Class: ").append(t.getTicketClass()).append("\n")
              .append("Price: R").append(String.format("%.2f", t.getBasePrice())).append("\n")
              .append("----------------------------\n\n"); 
    }

    return output.toString();
}

    //Code Validating Input
    public String validateTicket(String name, String destination, String ticketClass) {
        if (name.isEmpty() || destination.isEmpty() || ticketClass.isEmpty()) {
            return "Please complete all required fields.";
        }

        if (!(ticketClass.equalsIgnoreCase("Economy") ||
                ticketClass.equalsIgnoreCase("Business"))) {
            return "Ticket class must be Economy or Business.";
        }

        return "Valid";
    }
    
    //Adding JDBC connection for MySQL
    public void saveTicketToDatabase(TrainTicket ticket) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/trainDB?useSSL=false&allowPublicKeyRetrieval=true", 
            "root", 
            "#Gmachivene18" //Come back and confirm password (Don't forget)
            );

            String sql = "INSERT INTO Tickets (passenger_name, destination, ticket_class, price) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, ticket.getPassengerName());
            stmt.setString(2, ticket.getDestination());
            stmt.setString(3, ticket.getTicketClass());
            stmt.setDouble(4, ticket.getBasePrice()); 

            stmt.executeUpdate();
            conn.close();

        } catch (SQLException e) { 
            System.out.println("Database Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public String searchTicketsByDestination(String destination) {
    StringBuilder result = new StringBuilder();

    for (TrainTicket ticket : tickets) {
        if (ticket.getDestination().equalsIgnoreCase(destination)) {
            result.append("Passenger: ").append(ticket.getPassengerName()).append("\n")
                  .append("Destination: ").append(ticket.getDestination()).append("\n")
                  .append("Class: ").append(ticket.getTicketClass()).append("\n") 
                  .append("Price: R").append(String.format("%.2f", ticket.getBasePrice())).append("\n")
                  .append("----------------------------\n\n");
        }
    }

    if (result.length() == 0) {
        return "No tickets found for: " + destination;
    }

    return result.toString();
}
}
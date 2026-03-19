/**
 * @author Gefte Machivene
 * CJ8DRDW47
 * ITJVA2-12 Formative Assessment
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class TicketGUI extends JFrame {

    private JTextField nameField, destinationField, classField, priceField;
    private JTextArea displayArea;
    private TicketManager manager;

    public TicketGUI() {
        manager = new TicketManager();

        
        setTitle("Train Ticket System");
        setSize(450, 700); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        // Main Container
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 30, 20, 30));
        mainPanel.setBackground(new Color(250, 250, 250));

        Font labelFont = new Font("SansSerif", Font.BOLD, 12);

        // Fields for Input 
        addField(mainPanel, "PASSENGER NAME", nameField = new JTextField(), labelFont);
        addField(mainPanel, "DESTINATION", destinationField = new JTextField(), labelFont);
        addField(mainPanel, "TICKET CLASS (Economy/Business)", classField = new JTextField(), labelFont);
        addField(mainPanel, "PRICE (R)", priceField = new JTextField(), labelFont);

        // Buttons Section
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(new Color(250, 250, 250));

        // Add Ticket Button
        JButton addButton = new JButton("ADD TICKET");
        styleButton(addButton, new Color(135, 206, 250)); 
        buttonPanel.add(addButton);

        // Search Button
        JButton searchButton = new JButton("SEARCH DESTINATION");
        styleButton(searchButton, new Color(180, 220, 240)); 
        buttonPanel.add(searchButton);

        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel logLabel = new JLabel("TICKET DETAILS");
        logLabel.setFont(labelFont);
        logLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(logLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        
        // Ticket Display Area
        displayArea = new JTextArea(12, 30);
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        displayArea.setLineWrap(true);
        displayArea.setWrapStyleWord(true);
        
        JScrollPane scrollPane = new JScrollPane(displayArea);
        mainPanel.add(scrollPane);

        add(mainPanel);

        // Action Listeners for thr 2 Buttons

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processTicket();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String destination = destinationField.getText();
                if (destination.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a destination to search.");
                    return;
                }
                String result = manager.searchTicketsByDestination(destination);
                displayArea.setText(result);
            }
        });
    }

    
    private void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setFont(new Font("SansSerif", Font.BOLD, 11));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(170, 40));
    }

    private void addField(JPanel panel, String labelText, JTextField field, Font font) {
        JLabel label = new JLabel(labelText);
        label.setFont(font);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);
        
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        field.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(field);
        
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
    }

    private void processTicket() {
        String name = nameField.getText();
        String destination = destinationField.getText();
        String ticketClass = classField.getText();
        String priceInput = priceField.getText();

        String validation = manager.validateTicket(name, destination, ticketClass);

        if (!validation.equals("Valid")) {
            JOptionPane.showMessageDialog(this, validation, "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            double basePrice = Double.parseDouble(priceInput);
            if (ticketClass.equalsIgnoreCase("Business")) {
                basePrice *= 1.5;
            }

            TrainTicket ticket = new TrainTicket(name, destination, ticketClass, basePrice);
            manager.addTicket(ticket);
            manager.saveTicketToDatabase(ticket);

            displayArea.setText(manager.displayTickets());

            nameField.setText("");
            destinationField.setText("");
            classField.setText("");
            priceField.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid price amount.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}

        SwingUtilities.invokeLater(() -> new TicketGUI().setVisible(true));
    }
}
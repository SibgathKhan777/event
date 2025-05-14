package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import db.DatabaseConnection;

public class EventForm extends JFrame {
    private JTextField eventNameField;
    private JTextField eventDateField;
    private JTextField eventLocationField;
    private JButton submitButton;

    public EventForm() {
        setTitle("Event Form");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        JLabel nameLabel = new JLabel("Event Name:");
        eventNameField = new JTextField();
        JLabel dateLabel = new JLabel("Event Date:");
        eventDateField = new JTextField();
        JLabel locationLabel = new JLabel("Event Location:");
        eventLocationField = new JTextField();
        submitButton = new JButton("Submit");

        add(nameLabel);
        add(eventNameField);
        add(dateLabel);
        add(eventDateField);
        add(locationLabel);
        add(eventLocationField);
        add(new JLabel()); // Empty cell
        add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitEvent();
            }
        });
    }

    private void submitEvent() {
        String name = eventNameField.getText();
        String date = eventDateField.getText();
        String location = eventLocationField.getText();

        DatabaseConnection dbConn = new DatabaseConnection();
        try (Connection connection = dbConn.getConnection()) {
            String sql = "INSERT INTO events (name, date, location) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, date);
            statement.setString(3, location);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Event submitted successfully!");
            dispose(); // Close the EventForm window after successful submission
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error submitting event: " + ex.getMessage());
        }
    }
}
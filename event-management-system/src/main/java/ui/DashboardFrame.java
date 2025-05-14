package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardFrame extends JFrame {
    private JButton createEventButton;
    private JButton viewEventsButton;
    private JButton deleteEventButton;
    private JButton logoutButton;
    private JTable eventTable;
    private JScrollPane tableScrollPane;

    public DashboardFrame() {
        setTitle("Event Management Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initializeComponents();
    }

    private void initializeComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        createEventButton = new JButton("Create Event");
        viewEventsButton = new JButton("View Events");
        deleteEventButton = new JButton("Delete Event");
        logoutButton = new JButton("Logout");

        createEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openEventForm();
            }
        });

        viewEventsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadEvents();
            }
        });

        deleteEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedEvent();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });

        panel.add(createEventButton);
        panel.add(viewEventsButton);
        panel.add(deleteEventButton);
        panel.add(logoutButton);

        add(panel, BorderLayout.NORTH);

        eventTable = new JTable();
        tableScrollPane = new JScrollPane(eventTable);
        add(tableScrollPane, BorderLayout.CENTER);
    }

    private void loadEvents() {
        controllers.EventController controller = new controllers.EventController();
        java.util.List<models.Event> events = controller.getAllEvents();
        String[] columnNames = {"ID", "Name", "Date", "Location"};
        Object[][] data = new Object[events.size()][4];
        for (int i = 0; i < events.size(); i++) {
            models.Event event = events.get(i);
            data[i][0] = event.getId();
            data[i][1] = event.getName();
            data[i][2] = event.getDate();
            data[i][3] = event.getLocation();
        }
        eventTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

    private void openEventForm() {
        EventForm eventForm = new EventForm() {
            @Override
            public void dispose() {
                super.dispose();
                DashboardFrame.this.setVisible(true);
                loadEvents();
            }
        };
        eventForm.setVisible(true);
        this.setVisible(false);
    }

    private void deleteSelectedEvent() {
        int selectedRow = eventTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an event to delete.");
            return;
        }
        int eventId = (int) eventTable.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this event?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            controllers.EventController controller = new controllers.EventController();
            controller.deleteEvent(eventId);
            loadEvents();
            JOptionPane.showMessageDialog(this, "Event deleted successfully.");
        }
    }

    private void logout() {
        // Logic to logout
        JOptionPane.showMessageDialog(this, "Logging out...");
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DashboardFrame dashboardFrame = new DashboardFrame();
            dashboardFrame.setVisible(true);
        });
    }
}
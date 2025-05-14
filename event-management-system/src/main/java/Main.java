public class Main {
    public static void main(String[] args) {
        // Initialize the application
        javax.swing.SwingUtilities.invokeLater(() -> {
            // Create and display the login frame
            new ui.LoginFrame().setVisible(true);
        });
    }
}
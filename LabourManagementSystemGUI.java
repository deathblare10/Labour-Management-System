import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LabourManagementSystemGUI extends JFrame {

    static final String DB_URL = "jdbc:mysql://localhost:3306/LabourDB";
    static final String USER = "root";
    static final String PASS = "123456789";

    private Connection conn;

    private JTextField nameField, ageField, contactNoField, jobRoleField, salaryField, statusField, idField;
    private JTextArea outputArea;

    public LabourManagementSystemGUI() {

        setTitle("Labour Management System");
        setSize(1920, 1080);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        connectToDatabase();

        JPanel topPanel = new JPanel(new GridLayout(9, 2));

        Font labelFont = new Font("Arial", Font.PLAIN, 25);

        Dimension inputFieldSize = new Dimension(200, 35);
        Font inputFieldFont = new Font("Arial", Font.PLAIN, 25);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(labelFont);
        topPanel.add(nameLabel);
        nameField = new JTextField();
        nameField.setPreferredSize(inputFieldSize);
        nameField.setFont(inputFieldFont);
        topPanel.add(nameField);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setFont(labelFont);
        topPanel.add(ageLabel);
        ageField = new JTextField();
        ageField.setPreferredSize(inputFieldSize);
        ageField.setFont(inputFieldFont);
        topPanel.add(ageField);

        JLabel contactLabel = new JLabel("Contact No:");
        contactLabel.setFont(labelFont);
        topPanel.add(contactLabel);
        contactNoField = new JTextField();
        contactNoField.setPreferredSize(inputFieldSize);
        contactNoField.setFont(inputFieldFont);
        topPanel.add(contactNoField);

        JLabel jobRoleLabel = new JLabel("Job Role:");
        jobRoleLabel.setFont(labelFont);
        topPanel.add(jobRoleLabel);
        jobRoleField = new JTextField();
        jobRoleField.setPreferredSize(inputFieldSize);
        jobRoleField.setFont(inputFieldFont);
        topPanel.add(jobRoleField);

        JLabel salaryLabel = new JLabel("Salary:");
        salaryLabel.setFont(labelFont);
        topPanel.add(salaryLabel);
        salaryField = new JTextField();
        salaryField.setPreferredSize(inputFieldSize);
        salaryField.setFont(inputFieldFont);
        topPanel.add(salaryField);

        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setFont(labelFont);
        topPanel.add(statusLabel);
        statusField = new JTextField();
        statusField.setPreferredSize(inputFieldSize);
        statusField.setFont(inputFieldFont);
        topPanel.add(statusField);

        JLabel idLabel = new JLabel("Labour ID:");
        idLabel.setFont(labelFont);
        topPanel.add(idLabel);
        idField = new JTextField();
        idField.setPreferredSize(inputFieldSize);
        idField.setFont(inputFieldFont);
        topPanel.add(idField);

        add(topPanel, BorderLayout.NORTH);




        JPanel buttonPanel = new JPanel();

        Dimension buttonSize = new Dimension(150, 60);

        JButton addButton = new JButton("Add Labour");
        addButton.setPreferredSize(buttonSize);
        JButton viewButton = new JButton("View All Labour");
        viewButton.setPreferredSize(buttonSize);
        JButton deleteButton = new JButton("Delete Labour");
        deleteButton.setPreferredSize(buttonSize);
        JButton updateButton = new JButton("Update Status");
        updateButton.setPreferredSize(buttonSize);
        JButton exitButton = new JButton("Exit");
        exitButton.setPreferredSize(buttonSize);

        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.CENTER);

        outputArea = new JTextArea(15, 50);
        outputArea.setFont(new Font("Arial", Font.PLAIN, 25));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addLabour());
        viewButton.addActionListener(e -> viewLabours());
        deleteButton.addActionListener(e -> deleteLabour());
        updateButton.addActionListener(e -> updateLabourStatus());
        exitButton.addActionListener(e -> System.exit(0));
    }

    private void connectToDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Connection Failed", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addLabour() {
        try {
            String query = "INSERT INTO labour (name, age, contactNo, jobRole, salary, status) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nameField.getText());
            pstmt.setInt(2, Integer.parseInt(ageField.getText()));
            pstmt.setString(3, contactNoField.getText());
            pstmt.setString(4, jobRoleField.getText());
            pstmt.setDouble(5, Double.parseDouble(salaryField.getText()));
            pstmt.setString(6, statusField.getText());
            pstmt.executeUpdate();
            outputArea.setText("Labour added successfully.\n");
        } catch (SQLException e) {
            e.printStackTrace();
            outputArea.setText("Error adding labour.\n");
        }
    }

    private void viewLabours() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM labour");

            StringBuilder output = new StringBuilder("Labour Records:\n");
            while (rs.next()) {
                output.append("ID: ").append(rs.getInt("id")).append(", ")
                        .append("Name: ").append(rs.getString("name")).append(", ")
                        .append("Age: ").append(rs.getInt("age")).append(", ")
                        .append("Contact No: ").append(rs.getString("contactNo")).append(", ")
                        .append("Job Role: ").append(rs.getString("jobRole")).append(", ")
                        .append("Salary: ").append(rs.getDouble("salary")).append(", ")
                        .append("Status: ").append(rs.getString("status")).append("\n");
            }
            outputArea.setText(output.toString());
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            outputArea.setText("Error retrieving labour records.\n");
        }
    }

    private void deleteLabour() {
        try {
            String query = "DELETE FROM labour WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, Integer.parseInt(idField.getText()));
            int rowsAffected = pstmt.executeUpdate();
            outputArea.setText(rowsAffected > 0 ? "Labour deleted successfully.\n" : "Labour not found.\n");
        } catch (SQLException e) {
            e.printStackTrace();
            outputArea.setText("Error deleting labour.\n");
        }
    }

    private void updateLabourStatus() {
        try {
            String query = "UPDATE labour SET status = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, statusField.getText());
            pstmt.setInt(2, Integer.parseInt(idField.getText()));
            int rowsAffected = pstmt.executeUpdate();
            outputArea.setText(rowsAffected > 0 ? "Labour status updated successfully.\n" : "Labour not found.\n");
        } catch (SQLException e) {
            e.printStackTrace();
            outputArea.setText("Error updating status.\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LabourManagementSystemGUI gui = new LabourManagementSystemGUI();
            gui.setVisible(true);
        });
    }
}
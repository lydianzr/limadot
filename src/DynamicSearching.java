/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nba;

/**
 *
 * @author alyar
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class DynamicSearching extends JFrame {
    private JTextField heightField;
    private JComboBox<String> heightConditionCombo;
    private JTextField weightField;
    private JComboBox<String> weightConditionCombo;
    private JTextField positionField;
    private JTextArea resultArea;
    private JPanel resultPanel;
/*
    public DynamicSearching() {
    setTitle("Player Search");
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new GridLayout(7, 2));
    getContentPane().setBackground(new Color(0x024089));

    Font customFont = new Font("Segoe UI Black", Font.BOLD, 16);

    // Create GUI components
    JLabel heightConditionLabel = new JLabel("  Height Condition (Greater/Less):");
    heightConditionLabel.setFont(customFont);
    heightConditionLabel.setForeground(Color.white);
    add(heightConditionLabel);
    heightConditionCombo = new JComboBox<>(new String[]{"  Greater", "  Less"});
    heightConditionCombo.setBackground(new Color(0xF18701));
    heightConditionCombo.setFont(customFont);
    heightConditionCombo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    add(heightConditionCombo);

    JLabel heightLabel = new JLabel("  Height:");
    heightLabel.setForeground(Color.white);
    heightLabel.setFont(customFont);
    add(heightLabel);
    heightField = new JTextField();
    heightField.setBackground(new Color (0xF0F0C9));
    heightField.setFont(customFont);
    add(heightField);

    JLabel weightConditionLabel = new JLabel("  Weight Condition (Greater/Less):");
    weightConditionLabel.setForeground(Color.white);
    weightConditionLabel.setFont(customFont);
    add(weightConditionLabel);
    weightConditionCombo = new JComboBox<>(new String[]{"  Greater", "  Less"});
    weightConditionCombo.setFont(customFont);
    weightConditionCombo.setBackground(new Color (0xF18701));
    weightConditionCombo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

    add(weightConditionCombo);

    JLabel weightLabel = new JLabel("  Weight:");
    weightLabel.setForeground(Color.white);
    weightLabel.setFont(customFont);
    add(weightLabel);
    weightField = new JTextField();
    weightField.setBackground(new Color (0xF0F0C9));
    weightField.setFont(customFont);
    add(weightField);

    JLabel positionLabel = new JLabel("  Position:");
    positionLabel.setForeground(Color.white);
    positionLabel.setFont(customFont);
    add(positionLabel);
    positionField = new JTextField();
    positionField.setFont(customFont);
    positionField.setBackground(new Color (0xF0F0C9));
    add(positionField);

    JButton searchButton = new JButton("Search");
    searchButton.setBackground(new Color (0xF18701));
    searchButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    searchButton.setFont(customFont);
    add(searchButton);

    resultArea = new JTextArea();
    resultArea.setFont(customFont);
    resultArea.setBackground(new Color (0xF0F0C9));
    add(new JScrollPane(resultArea));

    // Add search button listener
    searchButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            searchPlayers();
        }
    });
    }

    private void searchPlayers() {
        String height = heightField.getText();
        String heightCondition = (String) heightConditionCombo.getSelectedItem();
        String weight = weightField.getText();
        String weightCondition = (String) weightConditionCombo.getSelectedItem();
        String position = positionField.getText();

        resultArea.setText(""); // Clear previous results

        // Query the database and show results
        try (Connection conn = DriverManager.getConnection(PlayerManager.DB_URL, PlayerManager.DB_USER, PlayerManager.DB_PASSWORD)) {
            String sql = "SELECT * FROM players WHERE height " + (heightCondition.equals("Greater") ? ">" : "<") + " ? AND weight " + (weightCondition.equals("Greater") ? ">" : "<") + " ? AND position = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setDouble(1, Double.parseDouble(height));
                pstmt.setDouble(2, Double.parseDouble(weight));
                pstmt.setString(3, position);

                try (ResultSet rs = pstmt.executeQuery()) {
                    if (!rs.isBeforeFirst()) {
                        resultArea.append("No players found.\n");
                    } else {
                        while (rs.next()) {
                            String playerDetails = String.format("ID: %s, Name: %s, Age: %d, Height: %.2f, Weight: %.2f, Position: %s, Salary: %.2f, Points: %.2f, Rebounds: %.2f, Assists: %.2f\n",
                                    rs.getString("ID"), rs.getString("name"), rs.getInt("age"), rs.getDouble("height"),
                                    rs.getDouble("weight"), rs.getString("position"), rs.getDouble("salary"),
                                    rs.getDouble("points"), rs.getDouble("rebounds"), rs.getDouble("assists"));
                            resultArea.append(playerDetails);

                            // Add a button to add player to the team
                            JButton addButton = new JButton("Add Player");
                            addButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    try {
                                        PlayerInfo player = new PlayerInfo(
                                                rs.getString("ID"),
                                                rs.getString("name"),rs.getInt("age"), rs.getDouble("height"),
                                    rs.getDouble("weight"), rs.getString("position"), rs.getDouble("salary"),
                                    rs.getDouble("points"), rs.getDouble("rebounds"), rs.getDouble("assists"));

                                        Team team = new Team(); // Assuming you have a way to get the current team
                                        if (team.canAddPlayer(player)) {
                                            team.addPlayer(player);
                                            PlayerManager.insertPlayerIntoDatabase(player);
                                            JOptionPane.showMessageDialog(null, "Player added successfully.");
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Cannot add player. Adding this player violates the team composition rules.");
                                        }
                                    } catch (SQLException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            });
                            resultArea.add(addButton);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
*/
    

/*

    public DynamicSearching() {
        setTitle("Player Search");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 2));
        getContentPane().setBackground(new Color(0x024089));

        Font customFont = new Font("Segoe UI Black", Font.BOLD, 16);

        // Create GUI components
        JLabel heightConditionLabel = new JLabel("  Height Condition (Greater/Less):");
        heightConditionLabel.setFont(customFont);
        heightConditionLabel.setForeground(Color.white);
        add(heightConditionLabel);
        heightConditionCombo = new JComboBox<>(new String[]{"  Greater", "  Less"});
        heightConditionCombo.setBackground(new Color(0xF18701));
        heightConditionCombo.setFont(customFont);
        heightConditionCombo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        add(heightConditionCombo);

        JLabel heightLabel = new JLabel("  Height:");
        heightLabel.setForeground(Color.white);
        heightLabel.setFont(customFont);
        add(heightLabel);
        heightField = new JTextField();
        heightField.setBackground(new Color(0xF0F0C9));
        heightField.setFont(customFont);
        add(heightField);

        JLabel weightConditionLabel = new JLabel("  Weight Condition (Greater/Less):");
        weightConditionLabel.setForeground(Color.white);
        weightConditionLabel.setFont(customFont);
        add(weightConditionLabel);
        weightConditionCombo = new JComboBox<>(new String[]{"  Greater", "  Less"});
        weightConditionCombo.setFont(customFont);
        weightConditionCombo.setBackground(new Color(0xF18701));
        weightConditionCombo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        add(weightConditionCombo);

        JLabel weightLabel = new JLabel("  Weight:");
        weightLabel.setForeground(Color.white);
        weightLabel.setFont(customFont);
        add(weightLabel);
        weightField = new JTextField();
        weightField.setBackground(new Color(0xF0F0C9));
        weightField.setFont(customFont);
        add(weightField);

        JLabel positionLabel = new JLabel("  Position:");
        positionLabel.setForeground(Color.white);
        positionLabel.setFont(customFont);
        add(positionLabel);
        positionField = new JTextField();
        positionField.setFont(customFont);
        positionField.setBackground(new Color(0xF0F0C9));
        add(positionField);

        JButton searchButton = new JButton("Search");
        searchButton.setBackground(new Color(0xF18701));
        searchButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        searchButton.setFont(customFont);
        add(searchButton);

        resultArea = new JTextArea();
        resultArea.setFont(customFont);
        resultArea.setBackground(new Color(0xF0F0C9));
        add(new JScrollPane(resultArea));

        // Add search button listener
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchPlayers();
            }
        });
    }

    private void searchPlayers() {
        String height = heightField.getText();
        String heightCondition = (String) heightConditionCombo.getSelectedItem();
        String weight = weightField.getText();
        String weightCondition = (String) weightConditionCombo.getSelectedItem();
        String position = positionField.getText();

        resultArea.setText(""); // Clear previous results

        // Read and filter data from the CSV file
        String csvFilePath = "C:\\Users\\alyar\\OneDrive - Universiti Malaya\\player(Sheet1).csv";
        List<String> results = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                double playerHeight;
        try {
            playerHeight = Double.parseDouble(data[3]); // Assuming height is at index 3
        } catch (NumberFormatException e) {
            // Skip this line if height cannot be parsed
            continue;
        }
                double playerWeight = Double.parseDouble(data[4]);
                String playerPosition = data[5];

                boolean heightMatch = heightCondition.equals("  Greater") ? playerHeight > Double.parseDouble(height) : playerHeight < Double.parseDouble(height);
                boolean weightMatch = weightCondition.equals("  Greater") ? playerWeight > Double.parseDouble(weight) : playerWeight < Double.parseDouble(weight);
                boolean positionMatch = playerPosition.equalsIgnoreCase(position);

                if (heightMatch && weightMatch && positionMatch) {
                    results.add(String.format("ID: %s, Name: %s, Age: %s, Height: %s, Weight: %s, Position: %s, Salary: %s, Points: %s, Rebounds: %s, Assists: %s",
                            data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9]));
                }
            }
        } catch (IOException | NumberFormatException ex) {
            ex.printStackTrace();
        }

        if (results.isEmpty()) {
            resultArea.append("No players found.\n");
        } else {
            for (String result : results) {
                resultArea.append(result + "\n");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DynamicSearching frame = new DynamicSearching();
            frame.setVisible(true);
        });
    }
}
*/
    /*
    public DynamicSearching() {
        setTitle("Player Search");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 2));
        getContentPane().setBackground(new Color(0x024089));

        Font customFont = new Font("Segoe UI Black", Font.BOLD, 16);

        // Create GUI components
        JLabel heightConditionLabel = new JLabel("  Height Condition (Greater/Less):");
        heightConditionLabel.setFont(customFont);
        heightConditionLabel.setForeground(Color.white);
        add(heightConditionLabel);
        heightConditionCombo = new JComboBox<>(new String[]{"  Greater", "  Less"});
        heightConditionCombo.setBackground(new Color(0xF18701));
        heightConditionCombo.setFont(customFont);
        heightConditionCombo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        add(heightConditionCombo);

        JLabel heightLabel = new JLabel("  Height:");
        heightLabel.setForeground(Color.white);
        heightLabel.setFont(customFont);
        add(heightLabel);
        heightField = new JTextField();
        heightField.setBackground(new Color(0xF0F0C9));
        heightField.setFont(customFont);
        add(heightField);

        JLabel weightConditionLabel = new JLabel("  Weight Condition (Greater/Less):");
        weightConditionLabel.setForeground(Color.white);
        weightConditionLabel.setFont(customFont);
        add(weightConditionLabel);
        weightConditionCombo = new JComboBox<>(new String[]{"  Greater", "  Less"});
        weightConditionCombo.setFont(customFont);
        weightConditionCombo.setBackground(new Color(0xF18701));
        weightConditionCombo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        add(weightConditionCombo);

        JLabel weightLabel = new JLabel("  Weight:");
        weightLabel.setForeground(Color.white);
        weightLabel.setFont(customFont);
        add(weightLabel);
        weightField = new JTextField();
        weightField.setBackground(new Color(0xF0F0C9));
        weightField.setFont(customFont);
        add(weightField);

        JLabel positionLabel = new JLabel("  Position:");
        positionLabel.setForeground(Color.white);
        positionLabel.setFont(customFont);
        add(positionLabel);
        positionField = new JTextField();
        positionField.setFont(customFont);
        positionField.setBackground(new Color(0xF0F0C9));
        add(positionField);

        JButton searchButton = new JButton("Search");
        searchButton.setBackground(new Color(0xF18701));
        searchButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        searchButton.setFont(customFont);
        add(searchButton);

        resultArea = new JTextArea();
        resultArea.setFont(customFont);
        resultArea.setBackground(new Color(0xF0F0C9));
        add(new JScrollPane(resultArea));

        // Add search button listener
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchPlayers();
            }
        });
    }

    private void searchPlayers() {
        String height = heightField.getText();
        String heightCondition = (String) heightConditionCombo.getSelectedItem();
        String weight = weightField.getText();
        String weightCondition = (String) weightConditionCombo.getSelectedItem();
        String position = positionField.getText();

        resultArea.setText(""); // Clear previous results

        // Read and filter data from the CSV file
        String csvFilePath = "C:\\Users\\alyar\\OneDrive - Universiti Malaya\\player(Sheet1).csv";
        List<String> results = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                double playerHeight;
                try {
                    playerHeight = Double.parseDouble(data[3]); // Assuming height is at index 3
                } catch (NumberFormatException e) {
                    // Skip this line if height cannot be parsed
                    continue;
                }
                double playerWeight = Double.parseDouble(data[4]);
                String playerPosition = data[5];

                boolean heightMatch = heightCondition.equals("  Greater") ? playerHeight > Double.parseDouble(height) : playerHeight < Double.parseDouble(height);
                boolean weightMatch = weightCondition.equals("  Greater") ? playerWeight > Double.parseDouble(weight) : playerWeight < Double.parseDouble(weight);
                boolean positionMatch = playerPosition.equalsIgnoreCase(position);

                if (heightMatch && weightMatch && positionMatch) {
                    String playerDetails = String.format("ID: %s, Name: %s, Age: %d, Height: %.2f, Weight: %.2f, Position: %s, Salary: %.2f, Points: %.2f, Rebounds: %.2f, Assists: %.2f\n",
                            data[0], data[1], Integer.parseInt(data[2]), Double.parseDouble(data[3]), Double.parseDouble(data[4]), data[5],
                            Double.parseDouble(data[6]), Double.parseDouble(data[7]), Double.parseDouble(data[8]), Double.parseDouble(data[9]));
                    resultArea.append(playerDetails);

                    // Add a button to add player to the team
                    JButton addButton = new JButton("Add Player");
                    addButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                PlayerInfo player = new PlayerInfo(
                                        data[0], data[1], Integer.parseInt(data[2]), Double.parseDouble(data[3]),
                                        Double.parseDouble(data[4]), data[5], Double.parseDouble(data[6]),
                                        Double.parseDouble(data[7]), Double.parseDouble(data[8]), Double.parseDouble(data[9]));

                                Team team = new Team(); // Assuming you have a way to get the current team
                                if (team.canAddPlayer(player)) {
                                    team.addPlayer(player);
                                    PlayerManager.insertPlayerIntoDatabase(player);
                                    JOptionPane.showMessageDialog(null, "Player added successfully.");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Cannot add player. Adding this player violates the team composition rules.");
                                }
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }
                    });
                    resultArea.add(addButton);
                }
            }
        } catch (IOException | NumberFormatException ex) {
            ex.printStackTrace();
        }

        if (results.isEmpty()) {
            resultArea.append("No players found.\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DynamicSearching frame = new DynamicSearching();
            frame.setVisible(true);
        });
    }
}
*/
   
    public DynamicSearching() {
        setTitle("Player Search");
        setSize(780, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new java.awt.Color(0, 102, 102));

        Font customFont = new Font("Segoe UI", 0, 16);

        // Panel to hold search criteria
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(4, 2));
        searchPanel.setBackground(new java.awt.Color(0, 102, 102));

        // Create GUI components for search criteria
        JLabel heightConditionLabel = new JLabel("  Height Condition:");
        heightConditionLabel.setFont(customFont);
        heightConditionLabel.setForeground(Color.white);
        searchPanel.add(heightConditionLabel);
        heightConditionCombo = new JComboBox<>(new String[]{"  Greater", "  Less"});
        heightConditionCombo.setBackground(new java.awt.Color(0, 102, 102));
        heightConditionCombo.setFont(customFont);
        heightConditionCombo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        searchPanel.add(heightConditionCombo);
        
         
        heightField = new JTextField();
        heightField.setBackground(new Color(0xF0F0C9));
        heightField.setFont(customFont);
        searchPanel.add(heightField);
        
        
        JLabel weightConditionLabel = new JLabel("  Weight Condition:");
        weightConditionLabel.setForeground(Color.white);
        weightConditionLabel.setFont(customFont);
        searchPanel.add(weightConditionLabel);
        weightConditionCombo = new JComboBox<>(new String[]{"  Greater", "  Less"});
        weightConditionCombo.setFont(customFont);
        weightConditionCombo.setBackground(new java.awt.Color(0, 102, 102));
        weightConditionCombo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        searchPanel.add(weightConditionCombo);
        
       
        weightField = new JTextField();
        weightField.setBackground(new Color(0xF0F0C9));
        weightField.setFont(customFont);
        searchPanel.add(weightField);

        JLabel positionLabel = new JLabel("  Position:");
        positionLabel.setForeground(Color.white);
        positionLabel.setFont(customFont);
        searchPanel.add(positionLabel);
        positionField = new JTextField();
        positionField.setFont(customFont);
        positionField.setBackground(new Color(0xF0F0C9));
        searchPanel.add(positionField);

        JButton searchButton = new JButton("Search");
       // searchButton.setBackground(new Color(0xF18701));
        searchButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        searchButton.setFont(customFont);
        searchPanel.add(searchButton);

        // Panel to hold search results
        resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS)); // BoxLayout for vertical alignment
        resultPanel.setBackground(new Color(0xF0F0C9));
        JScrollPane scrollPane = new JScrollPane(resultPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Add search panel and result panel to main frame
        add(searchPanel, BorderLayout.NORTH);

        // Add search button listener
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchPlayers();
            }
        });
        
        JButton playerListButton = new JButton("Player List");
        //playerListButton.setBackground(new Color(0xF18701));
        playerListButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        playerListButton.setFont(customFont);
        // Set icon for the PlayerList button
        playerListButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/nba/Listbuttonimage.png")));
        searchPanel.add(playerListButton);


        // Add action listener to PlayerList button
        playerListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PlayerList().setVisible(true);
                dispose();
            }
        });
        
        JButton homepageButton = new JButton("Homepage");
    //homepageButton.setBackground(new Color(0xF18701));
    homepageButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    homepageButton.setFont(customFont);
    homepageButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/nba/Homepageimage.jpg"))); // Add image icon
    searchPanel.add(homepageButton);

    // Add action listener to Homepage button
    homepageButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new HomePage().setVisible(true);
            dispose();
        }
    });
}

         
        
        
    

    private void searchPlayers() {
        String height = heightField.getText();
        String heightCondition = (String) heightConditionCombo.getSelectedItem();
        String weight = weightField.getText();
        String weightCondition = (String) weightConditionCombo.getSelectedItem();
        String position = positionField.getText();

        resultPanel.removeAll(); // Clear previous results

        // Read and filter data from the CSV file
        String csvFilePath = "C:\\Users\\alyar\\OneDrive - Universiti Malaya\\player(Sheet1).csv";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                double playerHeight;
                try {
                    playerHeight = Double.parseDouble(data[3]); // Assuming height is at index 3
                } catch (NumberFormatException e) {
                    // Skip this line if height cannot be parsed
                    continue;
                }
                double playerWeight = Double.parseDouble(data[4]);
                String playerPosition = data[5];

                boolean heightMatch = heightCondition.equals("  Greater") ? playerHeight > Double.parseDouble(height) : playerHeight < Double.parseDouble(height);
                boolean weightMatch = weightCondition.equals("  Greater") ? playerWeight > Double.parseDouble(weight) : playerWeight < Double.parseDouble(weight);
                boolean positionMatch = playerPosition.equalsIgnoreCase(position);

                if (heightMatch && weightMatch && positionMatch) {
                    String playerDetails = String.format("ID: %s, Name: %s, Age: %d, Height: %.2f, Weight: %.2f, Position: %s, Salary: %.2f, Points: %.2f, Rebounds: %.2f, Assists: %.2f\n",
                            data[0], data[1], Integer.parseInt(data[2]), Double.parseDouble(data[3]), Double.parseDouble(data[4]), data[5],
                            Double.parseDouble(data[6]), Double.parseDouble(data[7]), Double.parseDouble(data[8]), Double.parseDouble(data[9]));

                    // Create panel for each player
                    JPanel playerPanel = new JPanel();
                    playerPanel.setLayout(new BorderLayout());
                    playerPanel.setBackground(new Color(0xF0F0C9));
                    playerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

                    JTextArea playerInfoArea = new JTextArea(playerDetails);
                    playerInfoArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                    playerInfoArea.setEditable(false);
                    playerPanel.add(playerInfoArea, BorderLayout.CENTER);

                    // Add "Add Player" button
                    JButton addButton = new JButton("Add Player");
                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        PlayerInfo player = new PlayerInfo(
                                data[0], data[1], Integer.parseInt(data[2]), playerHeight, playerWeight, playerPosition,
                                Double.parseDouble(data[6]), Double.parseDouble(data[7]), Double.parseDouble(data[8]), Double.parseDouble(data[9]));
                        Team team = new Team(); // Assuming you have a way to get the current team
                        if (team.canAddPlayer(player)) {
                            team.addPlayer(player);
                            PlayerManager.insertPlayerIntoDatabase(player);
                            JOptionPane.showMessageDialog(null, "Player added successfully.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Cannot add player. Adding this player violates the team composition rules.");
                        }
                    }
                });
                    playerPanel.add(addButton, BorderLayout.EAST);

                    resultPanel.add(playerPanel);
                }
            }
        } catch (IOException | NumberFormatException ex) {
            ex.printStackTrace();
        }

        if (resultPanel.getComponentCount() == 0) {
            resultPanel.add(new JLabel("No players found."));
        }

        // Refresh the result panel
        resultPanel.revalidate();
        resultPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DynamicSearching frame = new DynamicSearching();
            frame.setVisible(true);
        });
    }

     
}


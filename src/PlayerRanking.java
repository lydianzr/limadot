package projectds;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerRanking extends JFrame {
    
    private JComboBox<String> positionComboBox;
    private JPanel playerPanel;
    private List<Player> players;
    private double maxPoints;
    private double maxRebounds;
    private double maxAssists;
    private String imageDirectory = "C:\\Users\\user\\Documents\\NetBeansProjects\\projectds\\src\\projectds\\";
    
    public PlayerRanking() {
        // Setup frame
        setTitle("Player Ranking");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Set background color for the JFrame
        getContentPane().setBackground(Color.WHITE);
        
        //Set default color
        Color c1 = new Color(0, 102, 102);
        
        //Font
        Font customFont = new Font("Segoe UI", 0, 16);
        
        // Load players
        players = loadPlayersFromCSV("C:\\Users\\user\\Documents\\NetBeansProjects\\projectds\\player(Sheet1).csv");
        
        // Setup top panel with dropdown
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        
        //Setup Homepage button
        JButton homepageButton = new JButton("Homepage");
        homepageButton.setBackground(c1);
        homepageButton.setForeground(Color.WHITE);
        homepageButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        homepageButton.setPreferredSize(new Dimension(150, 40));
        
        JPanel rightTopPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightTopPanel.add(homepageButton);
        topPanel.add(rightTopPanel, BorderLayout.WEST); // Add rightTopPanel to the right side
        
        
        // Add action listener to Homepage button
        homepageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomePage().setVisible(true);
                dispose();
            }
        });
        
        String[] positions = {"Guard", "Center", "Forward"};
        positionComboBox = new JComboBox<>(positions);
        positionComboBox.setPreferredSize(new Dimension(140, 40));
        positionComboBox.setFont(customFont);
        positionComboBox.setForeground(Color.WHITE);
        positionComboBox.setBackground(c1);
        
        
        JButton rankButton = new JButton("Rank Players");
        
        // Customize button appearance
        rankButton.setBackground(c1);
        rankButton.setForeground(Color.WHITE);
        rankButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        rankButton.setPreferredSize(new Dimension(150, 40));
        
        topPanel.add(new JLabel("Select Position:"));
        topPanel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        topPanel.add(positionComboBox);
        topPanel.add(rankButton);
        
        // Setup player panel
        playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(playerPanel);
        
        // Add components to frame
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        // Add action listener to button
        rankButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rankPlayers();
            }
        });
    }
    
    private List<Player> loadPlayersFromCSV(String fileName) {
        List<Player> playerList = new ArrayList<>();
        maxPoints = Double.MIN_VALUE;
        maxRebounds = Double.MIN_VALUE;
        maxAssists = Double.MIN_VALUE;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            // Skip header line
            reader.readLine();
            
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String position = data[5];
                double points = Double.parseDouble(data[7]);
                double rebounds = Double.parseDouble(data[8]);
                double assists = Double.parseDouble(data[9]);
                
                // Update max values
                if (points > maxPoints) maxPoints = points;
                if (rebounds > maxRebounds) maxRebounds = rebounds;
                if (assists > maxAssists) maxAssists = assists;
                
                playerList.add(new Player(id, name, position, points, rebounds, assists));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return playerList;
    }
    
    private void rankPlayers() {
        String selectedPosition = (String) positionComboBox.getSelectedItem();
        List<Player> filteredPlayers = new ArrayList<>();
        
        for (Player player : players) {
            if (positionMatches(player.getPosition(), selectedPosition)) {
                filteredPlayers.add(player);
            }
        }
        
        filteredPlayers.sort((p1, p2) -> Double.compare(p2.getCompositeScore(), p1.getCompositeScore()));
        
        // Clear player panel
        playerPanel.removeAll();
        
        if (!filteredPlayers.isEmpty()) {
            int rank = 1; // Initialize rank counter
            
            for (Player player : filteredPlayers) {
                JPanel playerCard = createPlayerCard(rank, player);
                playerPanel.add(playerCard);
                rank++;
            }
        }
        
        playerPanel.revalidate();
        playerPanel.repaint();
    }
    private boolean positionMatches(String playerPosition, String selectedPosition) {
        switch (selectedPosition) {
            case "Guard":
                return playerPosition.equals("G");
            case "Center":
                return playerPosition.equals("C");
            case "Forward":
                return playerPosition.equals("F");
            default:
                return false;
        }
    }
    private JPanel createPlayerCard(int rank, Player player) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102), 4), // Outer border
                BorderFactory.createEmptyBorder(0, 10,0, 10) // Inner padding
        ));
        
        panel.setBackground(new java.awt.Color(0, 102, 102));
        
        // Set size for the card
        Dimension cardSize = new Dimension(1000, 180);
        panel.setPreferredSize(cardSize);
        panel.setMinimumSize(cardSize);
        
        
        // Create image label
        JLabel imageLabel = new JLabel(getPlayerImageIcon(player.getId()));
        panel.add(imageLabel, BorderLayout.WEST);
        panel.setBackground(Color.WHITE);
        
        
        // Create details panel
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(Color.white);
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 0,20 )); // Add gap
        
        
        JLabel rankLabel = new JLabel("Rank: " + rank);
        rankLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        detailsPanel.add(rankLabel);
        
        JLabel nameLabel = new JLabel("Name: " + player.getName());
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        nameLabel.setForeground(Color.BLACK);
        detailsPanel.add(nameLabel);
        
        JLabel scoreLabel = new JLabel("Composite Score: " + String.format("%.2f", player.getCompositeScore()));
        scoreLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        detailsPanel.add(scoreLabel);
        
        panel.add(detailsPanel, BorderLayout.CENTER);
        
        return panel;
    }
    private ImageIcon getPlayerImageIcon(int playerId) {
        String jpgImagePath = imageDirectory + "P" + playerId + ".jpg";
        String pngImagePath = imageDirectory + "P" + playerId + ".png";
        File jpgFile = new File(jpgImagePath);
        File pngFile = new File(pngImagePath);
        ImageIcon originalIcon = null;
        
        if (jpgFile.exists()) {
            originalIcon = new ImageIcon(jpgImagePath);
        } else if (pngFile.exists()) {
            originalIcon = new ImageIcon(pngImagePath);
        }
        
        if (originalIcon != null) {
            Image originalImage = originalIcon.getImage();
            // Resize the image to a fixed size
            Image scaledImage = originalImage.getScaledInstance(280, 180, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } else {
            // If image not found, return a placeholder image
            return new ImageIcon(new BufferedImage(280, 180, BufferedImage.TYPE_INT_ARGB));
        }
    }
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PlayerRanking gui = new PlayerRanking();
            gui.setVisible(true);
        });
    }
    
    
    // Inner class Player
    class Player {
        int id;
        String name;
        String position;
        double points;
        double rebounds;
        double assists;
        
        Player(int id, String name, String position, double points, double rebounds, double assists) {
            this.id = id;
            this.name = name;
            this.position = position;
            this.points = points;
            this.rebounds = rebounds;
            this.assists = assists;
        }
        
        int getId() {
            return id;
        }
        
        double getPoints() {
            return points;
        }
        
        double getRebounds() {
            return rebounds;
        }
        
        double getAssists() {
            return assists;
        }
        
        String getName() {
            return name;
        }
        
        String getPosition() {
            return position;
        }
        
        double getCompositeScore() {
            double normalizedPoints = points / maxPoints;
            double normalizedRebounds = rebounds / maxRebounds;
            double normalizedAssists = assists / maxAssists;
            
            double score = 0;
            switch (position) {
                case "G":
                    score = normalizedPoints * 0.5 + normalizedRebounds * 0.2 + normalizedAssists * 0.3;
                    break;
                case "C":
                    score = normalizedPoints * 0.3 + normalizedRebounds * 0.6 + normalizedAssists * 0.1;
                    break;
                case "F":
                    score = normalizedPoints * 0.4 + normalizedRebounds * 0.4 + normalizedAssists * 0.2;
                    break;
            }
            return score * 100; // Scale to 100
        }
    }
}


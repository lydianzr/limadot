/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectds;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class PlayerManager {
    static final String FILE_PATH = "C:\\Users\\Asus\\OneDrive - Universiti Malaya\\Desktop\\SEM 2 CS\\JAVA DS\\ProjectDS\\src\\projectds\\player(Sheet1).csv";
   static final String DB_URL ="jdbc:mysql://localhost:3306/basketball_team?user=root&password=Rosni_tam@79";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "Rosni_tam@79";

    public static void insertPlayers(int playerNo, Team team) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            br.readLine(); // Skip the header line
            int lineno = 0;
            String[][] playerInfo = new String[38][10];

            // Read player information from the CSV file into a 2D array
            while ((line = br.readLine()) != null) {
                String[] playerData = line.split(",");
                for (int col = 0; col < 10; col++) {
                    playerInfo[lineno][col] = playerData[col];
                }
                lineno++;
            }

            // Create a PlayerInfo object from the data
            PlayerInfo player = new PlayerInfo(
                    playerInfo[playerNo][0],
                    playerInfo[playerNo][1],
                    Integer.parseInt(playerInfo[playerNo][2]),
                    Double.parseDouble(playerInfo[playerNo][3]),
                    Double.parseDouble(playerInfo[playerNo][4]),
                    playerInfo[playerNo][5],
                    Double.parseDouble(playerInfo[playerNo][6]),
                    Double.parseDouble(playerInfo[playerNo][7]),
                    Double.parseDouble(playerInfo[playerNo][8]),
                    Double.parseDouble(playerInfo[playerNo][9]));

            // Add player to the team (if applicable)
            if (team.canAddPlayer(player)) {
                if (!playerExistsInDatabase(playerInfo[playerNo][0])){
                team.addPlayer(player);
                JOptionPane.showMessageDialog(null, "Player successfully added.");
                // Insert player into the database
                insertPlayerIntoDatabase(player);
                System.out.println("Player added successfully to the database.");
                }
                else{
                   JOptionPane.showMessageDialog(null, "Player already selected. Please choose another player."); 
                }
            } else {
                System.out.println("Cannot add player. Adding this player violates the team composition rules.");
                JOptionPane.showMessageDialog(null, "Cannot add player. Adding this player violate team composition rules.");
            }

            System.out.println(team); // Display team roster
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void insertPlayerIntoDatabase(PlayerInfo player) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO players (ID, name, age, height, weight, position, salary, points, rebounds, assists) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, player.getID());
                pstmt.setString(2, player.getName());
                pstmt.setInt(3, player.getAge());
                pstmt.setDouble(4, player.getHeight());
                pstmt.setDouble(5, player.getWeight());
                pstmt.setString(6, player.getPosition());
                pstmt.setDouble(7, player.getSalary());
                pstmt.setDouble(8, player.getPoints());
                pstmt.setDouble(9, player.getRebounds());
                pstmt.setDouble(10, player.getAssists());

                pstmt.executeUpdate();
            }
            catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Player already inside the team");
            JOptionPane.showMessageDialog(null, "Player already inside the team.");
        }  
        } catch (SQLException e) {
            e.printStackTrace();
        }  
    }
    
     public static double calculateTotalSalary() {
        double totalSalary = 0.0;
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT salary FROM players";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                try (ResultSet resultSet = pstmt.executeQuery()) {
                    while (resultSet.next()) {
                        double salary = resultSet.getDouble("salary");
                        totalSalary += salary;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalSalary;
    }
     
     static boolean playerExistsInDatabase(String playerId) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT COUNT(*) FROM players WHERE ID = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, playerId);
                try (ResultSet resultSet = pstmt.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt(1) > 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}



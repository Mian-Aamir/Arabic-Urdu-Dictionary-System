package com.dal;

import com.dto.RootDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) for Root entity.
 * Handles CRUD operations for the 'roots' table in the database.
 */
public class RootDAO implements IRootDAO {

    // Insert new root
    @Override
    public void addRoot(RootDTO root) {
        String sql = "INSERT INTO roots (root_letters) VALUES (?)";
        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, root.getRootLetters());
            ps.executeUpdate();
            System.out.println("Root added successfully.");

        } catch (SQLException e) {
            System.out.println("Error adding root: " + e.getMessage());
        }
    }

    // Retrieve all roots
    @Override
    public List<RootDTO> getAllRoots() {
        List<RootDTO> roots = new ArrayList<>();
        String sql = "SELECT * FROM roots";

        try (Connection con = DatabaseConfig.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                roots.add(extractRoot(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error fetching roots: " + e.getMessage());
        }

        return roots;
    }

    // Update existing root
    @Override
    public void updateRoot(int rootId, String newLetters) {
        String sql = "UPDATE roots SET root_letters = ? WHERE root_id = ?";
        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, newLetters);
            ps.setInt(2, rootId);

            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("Root updated successfully.");
            else
                System.out.println("No root found with the given ID.");

        } catch (SQLException e) {
            System.out.println("Error updating root: " + e.getMessage());
        }
    }

    // Delete root
    @Override
    public void deleteRoot(int rootId) {
        String sql = "DELETE FROM roots WHERE root_id = ?";
        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, rootId);
            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Root deleted successfully.");
            else
                System.out.println("No root found with the given ID.");

        } catch (SQLException e) {
            System.out.println("Error deleting root: " + e.getMessage());
        }
    }

    // Get root by exact letters
    @Override
    public RootDTO getRootByLetters(String letters) {
        RootDTO root = null;
        String sql = "SELECT * FROM roots WHERE root_letters = ?";

        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, letters);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                root = extractRoot(rs);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching root: " + e.getMessage());
        }

        return root;
    }

    // Search roots by partial letters (LIKE)
    @Override
    public List<RootDTO> searchRoots(String partialLetters) {
        List<RootDTO> roots = new ArrayList<>();
        String sql = "SELECT * FROM roots WHERE root_letters LIKE ?";

        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + partialLetters + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                roots.add(extractRoot(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error searching roots: " + e.getMessage());
        }

        return roots;
    }

    // Helper to extract data from ResultSet
    private RootDTO extractRoot(ResultSet rs) throws SQLException {
        RootDTO r = new RootDTO();
        r.setRootId(rs.getInt("root_id"));
        r.setRootLetters(rs.getString("root_letters"));
        return r;
    }
}

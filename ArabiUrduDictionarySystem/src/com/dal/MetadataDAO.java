//package com.dal;
//import com.dto.MetadataDTO;
//import java.sql.*;
//import java.util.*;
//
//public class MetadataDAO {
//    public void addMetadata(MetadataDTO meta) {
//        String sql = "INSERT INTO Metadata (Word_ID, Gender, Number_, Case_, Verb_Form, Tense, Transitivity) VALUES (?, ?, ?, ?, ?, ?, ?)";
//        try (Connection conn = DatabaseConfig.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, meta.getWordId());
//            stmt.setString(2, meta.getGender());
//            stmt.setString(3, meta.getNumber());
////            stmt.setString(4, meta.getCase_());
//            stmt.setString(5, meta.getVerbForm());
//            stmt.setString(6, meta.getTense());
//            stmt.setString(7, meta.getTransitivity());
//            stmt.executeUpdate();
//        } catch (SQLException e) { e.printStackTrace(); }
//    }
//
//    public List<MetadataDTO> getAllMetadata() {
//        List<MetadataDTO> list = new ArrayList<>();
//        String sql = "SELECT * FROM Metadata";
//        try (Connection conn = DatabaseConfig.getConnection();
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery(sql)) {
//            while (rs.next()) {
//                MetadataDTO m = new MetadataDTO();
//                m.setWordId(rs.getInt("Word_ID"));
//                m.setGender(rs.getString("Gender"));
//                m.setNumber(rs.getString("Number_"));
////                m.setCase_(rs.getString("Case_"));
//                m.setVerbForm(rs.getString("Verb_Form"));
//                m.setTense(rs.getString("Tense"));
//                m.setTransitivity(rs.getString("Transitivity"));
//                list.add(m);
//            }
//        } catch (SQLException e) { e.printStackTrace(); }
//        return list;
//    }
//}

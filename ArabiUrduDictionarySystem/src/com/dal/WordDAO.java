package com.dal;

import com.dto.WordDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of IWordDAO.
 * Handles all database operations related to Word entity.
 */
public class WordDAO implements IWordDAO {

    // CREATE
    @Override
    public void addWord(WordDTO word) {
        String sql = "INSERT INTO words (arabic_form, base_form, urdu_meaning, part_of_speech, root_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, word.getArabicForm());
            ps.setString(2, word.getBaseForm());
            ps.setString(3, word.getUrduMeaning());
            ps.setString(4, word.getPartOfSpeech());
            ps.setInt(5, word.getRootId());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // READ (All Words)
    @Override
    public List<WordDTO> getAllWords() {
        List<WordDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM words";
        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                WordDTO w = new WordDTO();
                w.setWordId(rs.getInt("word_id"));
                w.setArabicForm(rs.getString("arabic_form"));
                w.setBaseForm(rs.getString("base_form"));
                w.setUrduMeaning(rs.getString("urdu_meaning"));
                w.setPartOfSpeech(rs.getString("part_of_speech"));
                w.setRootId(rs.getInt("root_id"));
                list.add(w);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE
    @Override
    public void updateMeaning(int wordId, String newMeaning) {
        String sql = "UPDATE words SET urdu_meaning = ? WHERE word_id = ?";
        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, newMeaning);
            ps.setInt(2, wordId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE
    @Override
    public void deleteWord(int wordId) {
        String sql = "DELETE FROM words WHERE word_id = ?";
        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, wordId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // READ (Exact Arabic Match)
    @Override
    public WordDTO getWordByArabic(String arabic) {
        String sql = "SELECT * FROM words WHERE arabic_form = ?";
        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, arabic);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return extractWord(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // READ (Exact Urdu Match)
    @Override
    public WordDTO getWordByUrdu(String urdu) {
        String sql = "SELECT * FROM words WHERE urdu_meaning = ?";
        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, urdu);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return extractWord(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // READ (Search Arabic LIKE)
    @Override
    public List<WordDTO> searchWordsByArabicForm(String arabicPart) {
        List<WordDTO> results = new ArrayList<>();
        String sql = "SELECT * FROM words WHERE arabic_form LIKE ?";
        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + arabicPart + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                results.add(extractWord(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    // READ (Search Urdu LIKE)
    @Override
    public List<WordDTO> searchWordsByUrduMeaning(String urduPart) {
        List<WordDTO> results = new ArrayList<>();
        String sql = "SELECT * FROM words WHERE urdu_meaning LIKE ?";
        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + urduPart + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                results.add(extractWord(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    // Helper Method: Extract Word from ResultSet
    private WordDTO extractWord(ResultSet rs) throws SQLException {
        WordDTO w = new WordDTO();
        w.setWordId(rs.getInt("word_id"));
        w.setArabicForm(rs.getString("arabic_form"));
        w.setBaseForm(rs.getString("base_form"));
        w.setUrduMeaning(rs.getString("urdu_meaning"));
        w.setPartOfSpeech(rs.getString("part_of_speech"));
        w.setRootId(rs.getInt("root_id"));
        return w;
    }
}

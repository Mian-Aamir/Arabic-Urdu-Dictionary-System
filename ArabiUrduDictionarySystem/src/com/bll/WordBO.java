package com.bll;

import com.dal.*;
import com.dto.WordDTO;
import java.util.List;

/**
 * WordBO (Business Object)
 * Handles all validation, existence checks, and applies business rules
 * before interacting with the Data Access Layer (DAL).
 */
public class WordBO implements IWordBO {

    private final IDataAccessFacade dataAccess;

    public WordBO() {
        this.dataAccess = new DataAccessFacade();
    }

    // ✅ Add a new word with validation and duplicate check
    @Override
    public void addWord(WordDTO word) {
        if (word == null)
            throw new IllegalArgumentException("Word object cannot be null.");

        if (isNullOrEmpty(word.getArabicForm()))
            throw new IllegalArgumentException("Arabic form cannot be empty.");

        if (isNullOrEmpty(word.getUrduMeaning()))
            throw new IllegalArgumentException("Urdu meaning cannot be empty.");

        // Check for duplicates
        WordDTO existing = dataAccess.getWordByArabic(word.getArabicForm());
        if (existing != null) {
            throw new IllegalArgumentException("This Arabic word already exists in the dictionary.");
        }

        dataAccess.addWord(word);
    }

    // ✅ Fetch all words
    @Override
    public List<WordDTO> getAllWords() {
        return dataAccess.getAllWords();
    }

    // ✅ Update Urdu meaning
    @Override
    public void updateMeaning(int wordId, String newMeaning) {
        if (wordId <= 0)
            throw new IllegalArgumentException("Invalid Word ID.");

        if (isNullOrEmpty(newMeaning))
            throw new IllegalArgumentException("New meaning cannot be empty.");

        WordDTO existing = findWordById(wordId);
        if (existing == null)
            throw new IllegalArgumentException("Word ID not found.");

        dataAccess.updateMeaning(wordId, newMeaning);
    }

    // ✅ Delete a word after verifying it exists
    @Override
    public void deleteWord(int wordId) {
        if (wordId <= 0)
            throw new IllegalArgumentException("Invalid Word ID.");

        WordDTO existing = findWordById(wordId);
        if (existing == null)
            throw new IllegalArgumentException("Cannot delete — Word not found.");

        dataAccess.deleteWord(wordId);
    }

    // ✅ Get by Arabic form
    @Override
    public WordDTO getWordByArabic(String arabic) {
        if (isNullOrEmpty(arabic))
            throw new IllegalArgumentException("Arabic search term cannot be empty.");

        return dataAccess.getWordByArabic(arabic);
    }

    // ✅ Get by Urdu meaning
    @Override
    public WordDTO getWordByUrdu(String urdu) {
        if (isNullOrEmpty(urdu))
            throw new IllegalArgumentException("Urdu search term cannot be empty.");

        return dataAccess.getWordByUrdu(urdu);
    }

    // ✅ Search partial Arabic
    @Override
    public List<WordDTO> searchWordsByArabicForm(String arabicPart) {
        if (isNullOrEmpty(arabicPart))
            throw new IllegalArgumentException("Arabic search input cannot be empty.");

        return dataAccess.searchWordsByArabicForm(arabicPart);
    }

    // ✅ Search partial Urdu
    @Override
    public List<WordDTO> searchWordsByUrduMeaning(String urduPart) {
        if (isNullOrEmpty(urduPart))
            throw new IllegalArgumentException("Urdu search input cannot be empty.");

        return dataAccess.searchWordsByUrduMeaning(urduPart);
    }

    // 🔍 Helper to find word by ID (used in update/delete)
    private WordDTO findWordById(int wordId) {
        return dataAccess.getAllWords()
                .stream()
                .filter(w -> w.getWordId() == wordId)
                .findFirst()
                .orElse(null);
    }

    // 🧹 Utility method
    private boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }
}

package com.dal;

import com.dto.WordDTO;
import java.util.List;

/**
 * Interface for Word Data Access Object.
 * Defines all CRUD and search operations for the Word entity.
 */
public interface IWordDAO {
    void addWord(WordDTO word);                                 // CREATE
    List<WordDTO> getAllWords();                                // READ (all)
    void updateMeaning(int wordId, String newMeaning);          // UPDATE
    void deleteWord(int wordId);                                // DELETE
    WordDTO getWordByArabic(String arabic);                     // READ (exact Arabic)
    WordDTO getWordByUrdu(String urdu);                         // READ (exact Urdu)
    List<WordDTO> searchWordsByArabicForm(String arabicPart);   // READ (search Arabic)
    List<WordDTO> searchWordsByUrduMeaning(String urduPart);    // READ (search Urdu)
}

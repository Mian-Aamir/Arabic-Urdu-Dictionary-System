package com.bll;

import com.dto.WordDTO;
import java.util.List;

public interface IWordBO {
    void addWord(WordDTO word);
    List<WordDTO> getAllWords();
    void updateMeaning(int wordId, String newMeaning);
    void deleteWord(int wordId);
    WordDTO getWordByArabic(String arabic);
    WordDTO getWordByUrdu(String urdu);
    List<WordDTO> searchWordsByArabicForm(String arabicPart);
    List<WordDTO> searchWordsByUrduMeaning(String urduPart);
}

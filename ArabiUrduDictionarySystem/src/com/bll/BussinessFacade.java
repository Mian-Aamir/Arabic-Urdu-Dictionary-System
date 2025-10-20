package com.bll;

import com.dto.WordDTO;
import java.util.List;

/**
 * Business Facade
 * Acts as a single access point for the UI layer (CLI/GUI).
 * Delegates all operations to the corresponding Business Objects (BOs).
 * This layer stays lightweight — no validation or heavy logic here.
 */
public class BussinessFacade implements IBussinessFacade {

    private final IWordBO wordBO;

    public BussinessFacade() {
        this.wordBO = new WordBO();
    }

    // 🔹 Word CRUD Operations
    @Override
    public void addWord(WordDTO word) {
        wordBO.addWord(word);
    }

    @Override
    public List<WordDTO> getAllWords() {
        return wordBO.getAllWords();
    }

    @Override
    public void updateMeaning(int wordId, String newMeaning) {
        wordBO.updateMeaning(wordId, newMeaning);
    }

    @Override
    public void deleteWord(int wordId) {
        wordBO.deleteWord(wordId);
    }

    @Override
    public WordDTO getWordByArabic(String arabic) {
        return wordBO.getWordByArabic(arabic);
    }

    @Override
    public WordDTO getWordByUrdu(String urdu) {
        return wordBO.getWordByUrdu(urdu);
    }

    @Override
    public List<WordDTO> searchWordsByArabicForm(String arabicPart) {
        return wordBO.searchWordsByArabicForm(arabicPart);
    }

    @Override
    public List<WordDTO> searchWordsByUrduMeaning(String urduPart) {
        return wordBO.searchWordsByUrduMeaning(urduPart);
    }
}

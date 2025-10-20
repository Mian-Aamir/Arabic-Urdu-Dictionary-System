package com.dal;

import com.dto.WordDTO;
import com.dto.RootDTO;
import java.util.List;

/**
 * Facade implementation for Data Access Layer.
 * Acts as a single entry point to access different DAO objects.
 */
public class DataAccessFacade implements IDataAccessFacade {

    private final IWordDAO wordDAO;
    private final IRootDAO rootDAO;

    public DataAccessFacade() {
        this.wordDAO = new WordDAO();
        this.rootDAO = new RootDAO();
    }

    // ====================== WORD OPERATIONS ======================
    @Override
    public void addWord(WordDTO word) {
        wordDAO.addWord(word);
    }

    @Override
    public List<WordDTO> getAllWords() {
        return wordDAO.getAllWords();
    }

    @Override
    public void updateMeaning(int wordId, String newMeaning) {
        wordDAO.updateMeaning(wordId, newMeaning);
    }

    @Override
    public void deleteWord(int wordId) {
        wordDAO.deleteWord(wordId);
    }

    @Override
    public WordDTO getWordByArabic(String arabic) {
        return wordDAO.getWordByArabic(arabic);
    }

    @Override
    public WordDTO getWordByUrdu(String urdu) {
        return wordDAO.getWordByUrdu(urdu);
    }

    @Override
    public List<WordDTO> searchWordsByArabicForm(String arabicPart) {
        return wordDAO.searchWordsByArabicForm(arabicPart);
    }

    @Override
    public List<WordDTO> searchWordsByUrduMeaning(String urduPart) {
        return wordDAO.searchWordsByUrduMeaning(urduPart);
    }

    // ====================== ROOT OPERATIONS ======================
    public void addRoot(RootDTO root) {
        rootDAO.addRoot(root);
    }

    public List<RootDTO> getAllRoots() {
        return rootDAO.getAllRoots();
    }

    public void updateRoot(int rootId, String newLetters) {
        rootDAO.updateRoot(rootId, newLetters);
    }

    public void deleteRoot(int rootId) {
        rootDAO.deleteRoot(rootId);
    }

    public RootDTO getRootByLetters(String letters) {
        return rootDAO.getRootByLetters(letters);
    }

    public List<RootDTO> searchRoots(String partialLetters) {
        return rootDAO.searchRoots(partialLetters);
    }
}

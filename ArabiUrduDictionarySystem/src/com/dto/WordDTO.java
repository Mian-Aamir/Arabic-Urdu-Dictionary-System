package com.dto;

/**
 * Data Transfer Object (DTO) for the Word entity.
 * This class holds all the data related to a single word.
 * It is used to transfer data between the database layer and the business logic layer.
 */

public class WordDTO {

    private int wordId;
    private String arabicForm;
    private String baseForm;
    private String urduMeaning;
    private String partOfSpeech;
    private int rootId;

    // --- Default Constructor ---
    public WordDTO() {}

    // --- Parameterized Constructor ---
    public WordDTO(int wordId, String arabicForm, String baseForm,
                   String urduMeaning, String partOfSpeech, int rootId) {
        this.wordId = wordId;
        this.arabicForm = arabicForm;
        this.baseForm = baseForm;
        this.urduMeaning = urduMeaning;
        this.partOfSpeech = partOfSpeech;
        this.rootId = rootId;
    }

    // --- Getters and Setters ---
    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    public String getArabicForm() {
        return arabicForm;
    }

    public void setArabicForm(String arabicForm) {
        this.arabicForm = arabicForm;
    }

    public String getBaseForm() {
        return baseForm;
    }

    public void setBaseForm(String baseForm) {
        this.baseForm = baseForm;
    }

    public String getUrduMeaning() {
        return urduMeaning;
    }

    public void setUrduMeaning(String urduMeaning) {
        this.urduMeaning = urduMeaning;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public int getRootId() {
        return rootId;
    }

    public void setRootId(int rootId) {
        this.rootId = rootId;
    }

    // --- ToString() for easy printing ---
    @Override
    public String toString() {
        return "WordDTO{" +
                "wordId=" + wordId +
                ", arabicForm='" + arabicForm + '\'' +
                ", baseForm='" + baseForm + '\'' +
                ", urduMeaning='" + urduMeaning + '\'' +
                ", partOfSpeech='" + partOfSpeech + '\'' +
                ", rootId=" + rootId +
                '}';
    }
}

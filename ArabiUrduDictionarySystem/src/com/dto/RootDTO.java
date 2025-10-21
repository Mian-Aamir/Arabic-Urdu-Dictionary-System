package com.dto;

/**
 * Data Transfer Object (DTO) for the Root entity.
 * This class holds all data related to a root (Arabic 3 or 4-letter root).
 * It is used to transfer root data between the database and business layers.
 */
public class RootDTO {

    private int rootId;          // Unique identifier of the root
    private String rootLetters;  // Arabic root letters (e.g., ع-ل-م)

    // ✅ Default Constructor
    public RootDTO() {
    }

    // ✅ Parameterized Constructor
    public RootDTO(int rootId, String rootLetters) {
        this.rootId = rootId;
        this.rootLetters = rootLetters;
    }

    // ✅ Getters and Setters
    public int getRootId() {
        return rootId;
    }

    public void setRootId(int rootId) {
        this.rootId = rootId;
    }

    public String getRootLetters() {
        return rootLetters;
    }

    public void setRootLetters(String rootLetters) {
        this.rootLetters = rootLetters;
    }

    // ✅ For readable output (useful for CLI testing)
    @Override
    public String toString() {
        return "RootDTO{" +
                "rootId=" + rootId +
                ", rootLetters='" + rootLetters + '\'' +
                '}';
    }
}
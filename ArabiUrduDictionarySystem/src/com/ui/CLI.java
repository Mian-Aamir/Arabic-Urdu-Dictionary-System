package com.ui;

import com.bll.BussinessFacade;
import com.dto.WordDTO;

import java.util.List;
import java.util.Scanner;

/**
 * Command Line Interface (CLI) for Arabic ↔ Urdu Dictionary System.
 * Fully compatible with Java 8.
 * Handles user interaction and delegates logic to BussinessFacade.
 */
public class CLI implements UI {

    private final Scanner sc = new Scanner(System.in);
    private final BussinessFacade facade = new BussinessFacade();

    @Override
    public void start() {
        System.out.println("=== Arabic ↔ Urdu Dictionary ===");

        while (true) {
            System.out.println("\n1. Add Word");
            System.out.println("2. Show All Words");
            System.out.println("3. Update Urdu Meaning");
            System.out.println("4. Delete Word");
            System.out.println("5. Search Word (Arabic ↔ Urdu)");
            System.out.println("6. Exit");
            System.out.print("Choose Option: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    addWord();
                    break;
                case 2:
                    showAllWords();
                    break;
                case 3:
                    updateMeaning();
                    break;
                case 4:
                    deleteWord();
                    break;
                case 5:
                    searchMenu();
                    break;
                case 6:
                    System.out.println("Exiting... Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("⚠ Invalid choice. Try again!");
                    break;
            }
        }
    }

    // 🔹 Add new word
    private void addWord() {
        System.out.print("Arabic Form: ");
        String arabic = sc.nextLine().trim();
        if (!isArabic(arabic)) {
            System.out.println("❌ Only Arabic letters allowed for Arabic Form.");
            return;
        }

        System.out.print("Base Form: ");
        String base = sc.nextLine().trim();

        System.out.print("Urdu Meaning: ");
        String urdu = sc.nextLine().trim();
        if (!isUrdu(urdu)) {
            System.out.println("❌ Only Urdu letters allowed for Urdu Meaning.");
            return;
        }

        System.out.print("Part of Speech: ");
        String pos = sc.nextLine().trim();

        System.out.print("Root ID: ");
        int rootId = getIntInput();

        try {
            WordDTO word = new WordDTO(0, arabic, base, urdu, pos, rootId);
            facade.addWord(word);
            System.out.println("✅ Word added successfully!");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    // 🔹 Display all words
    private void showAllWords() {
        try {
            List<WordDTO> words = facade.getAllWords();
            if (words == null || words.isEmpty()) {
                System.out.println("⚠ No words found.");
                return;
            }

            System.out.println("\n=== Dictionary Entries ===");
            for (WordDTO w : words) {
                System.out.printf("[%d] %s → %s (%s)\n",
                        w.getWordId(),
                        w.getArabicForm(),
                        w.getUrduMeaning(),
                        w.getPartOfSpeech());
            }
        } catch (Exception e) {
            System.out.println("❌ Error while showing words: " + e.getMessage());
        }
    }

    // 🔹 Update Urdu meaning
    private void updateMeaning() {
        System.out.print("Enter Word ID to Update: ");
        int id = getIntInput();

        System.out.print("New Urdu Meaning: ");
        String meaning = sc.nextLine().trim();

        if (!isUrdu(meaning)) {
            System.out.println("❌ Only Urdu letters allowed for Urdu Meaning.");
            return;
        }

        try {
            facade.updateMeaning(id, meaning);
            System.out.println("✅ Urdu meaning updated successfully!");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    // 🔹 Delete word
    private void deleteWord() {
        System.out.print("Enter Word ID to Delete: ");
        int id = getIntInput();

        try {
            facade.deleteWord(id);
            System.out.println("✅ Word deleted successfully!");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    // 🔹 Search menu
    private void searchMenu() {
        System.out.println("\n1. Arabic → Urdu");
        System.out.println("2. Urdu → Arabic");
        System.out.print("Choose Option: ");
        int option = getIntInput();

        switch (option) {
            case 1:
                searchByArabic();
                break;
            case 2:
                searchByUrdu();
                break;
            default:
                System.out.println("⚠ Invalid option!");
                break;
        }
    }

    // 🔹 Search Arabic → Urdu
    private void searchByArabic() {
        System.out.print("Enter Arabic Word or Part: ");
        String input = sc.nextLine().trim();

        if (!isArabic(input)) {
            System.out.println("❌ Only Arabic text is allowed for search.");
            return;
        }

        try {
            List<WordDTO> results = facade.searchWordsByArabicForm(input);
            if (results == null || results.isEmpty()) {
                System.out.println("⚠ No matching Arabic words found.");
                return;
            }

            System.out.println("\n=== Search Results (Arabic → Urdu) ===");
            for (WordDTO w : results) {
                System.out.printf("%s → %s\n", w.getArabicForm(), w.getUrduMeaning());
            }
        } catch (Exception e) {
            System.out.println("❌ Error while searching: " + e.getMessage());
        }
    }

    // 🔹 Search Urdu → Arabic
    private void searchByUrdu() {
        System.out.print("Enter Urdu Word or Part: ");
        String input = sc.nextLine().trim();

        if (!isUrdu(input)) {
            System.out.println("❌ Only Urdu text is allowed for search.");
            return;
        }

        try {
            List<WordDTO> results = facade.searchWordsByUrduMeaning(input);
            if (results == null || results.isEmpty()) {
                System.out.println("⚠ No matching Urdu meanings found.");
                return;
            }

            System.out.println("\n=== Search Results (Urdu → Arabic) ===");
            for (WordDTO w : results) {
                System.out.printf("%s → %s\n", w.getUrduMeaning(), w.getArabicForm());
            }
        } catch (Exception e) {
            System.out.println("❌ Error while searching: " + e.getMessage());
        }
    }

    // 🧩 Helper: Safe integer input
    private int getIntInput() {
        while (true) {
            try {
                String input = sc.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.print("⚠ Please enter a number: ");
                    continue;
                }
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("⚠ Invalid number, try again: ");
            }
        }
    }

    // 🧩 Helper: Check if Arabic text
    private boolean isArabic(String text) {
        return text != null && text.matches("^[\\u0600-\\u06FF\\s]+$");
    }

    // 🧩 Helper: Check if Urdu text
    private boolean isUrdu(String text) {
        return text != null && text.matches("^[\\u0600-\\u06FF\\s]+$");
    }
}

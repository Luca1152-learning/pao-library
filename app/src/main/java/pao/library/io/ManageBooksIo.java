package pao.library.io;

import pao.library.api.model.Book;
import pao.library.api.service.BookService;

import java.util.ArrayList;
import java.util.Arrays;

public class ManageBooksIo {
    private static final NumberedOptions MAIN_OPTIONS = new NumberedOptions(new ArrayList<>(Arrays.asList(
            "Add book", // 1
            "Delete book", // 2
            "Change book details", // 3
            "Exit" // 4
    )));
    private static final NumberedOptions CONFIRM_OPTIONS = new NumberedOptions(new ArrayList<>(Arrays.asList(
            "Yes", // 1
            "No" // 2
    )));

    public void prompt() {
        while (true) {
            System.out.println();
            int option = MAIN_OPTIONS.prompt();
            if (option == 1) {
                // Add book
                int bookId = promptForBookId();
                if (bookId == -1) continue;

                // TODO
            } else if (option == 2) {
                // Delete book
                int bookId = promptForBookId();
                if (bookId == -1) continue;

                boolean confirmDeletion = confirmBookDeletion(bookId);
                if (confirmDeletion) {
                    BookService.deleteBookById(bookId);
                    System.out.println("\nThe book was successfully deleted.");
                }
            } else if (option == 3) {
                // Change book details
                int bookId = promptForBookId();
                if (bookId == -1) continue;

                // TODO
            } else if (option == 4) {
                // Exit
                break;
            } else {
                throw new RuntimeException("Option not handled.");
            }
        }
    }

    public int promptForBookId() {
        // Obtain strings with all the options for the menu
        System.out.println();
        ArrayList<Book> books = new ArrayList<>(BookService.getAllBooks());
        ArrayList<String> stringOptions =
                new ArrayList<>(books.stream().map(book -> book.getTitle() + " by " + String.join(", ",
                        BookService.getBookAuthors(book.getId()))).toList());
        stringOptions.add("Abort");

        // Create the menu
        NumberedOptions booksOptions = new NumberedOptions(stringOptions);
        int option = booksOptions.prompt();

        if (option >= 1 && option <= books.size()) {
            return books.get(option - 1).getId();
        } else if (option == books.size() + 1) {
            // Exit
            return -1;
        } else {
            throw new RuntimeException("Option not handled.");
        }
    }

    public boolean confirmBookDeletion(int bookId) {
        System.out.println();
        System.out.println("Are you sure you want to delete the book '" + BookService.getBookById(bookId).getTitle() + "'?");
        int option = CONFIRM_OPTIONS.prompt();
        return option == 1; // 1 = Yes, 2 = No
    }
}

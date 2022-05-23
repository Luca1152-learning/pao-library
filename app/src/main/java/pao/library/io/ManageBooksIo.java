package pao.library.io;

import pao.library.api.model.Author;
import pao.library.api.model.Book;
import pao.library.api.model.Category;
import pao.library.api.model.Publisher;
import pao.library.api.service.AuthorService;
import pao.library.api.service.BookService;
import pao.library.api.service.CategoryService;
import pao.library.api.service.PublisherService;

import java.util.ArrayList;
import java.util.Arrays;

public class ManageBooksIo {
    public static final NumberedOptions CONFIRM_OPTIONS = new NumberedOptions(new ArrayList<>(Arrays.asList(
            "Yes", // 1
            "No" // 2
    )));
    private static final NumberedOptions MAIN_OPTIONS = new NumberedOptions(new ArrayList<>(Arrays.asList(
            "Add...", // 1
            "Update...", // 2
            "Delete...", // 3
            "Exit" // 4
    )));

    public static int promptForBookId() {
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

    public static int promptForAuthorId() {
        // Obtain strings with all the options for the menu
        System.out.println();
        ArrayList<Author> authors = new ArrayList<>(AuthorService.getAllAuthors());
        ArrayList<String> stringOptions = new ArrayList<>(authors.stream().map(Author::getName).toList());
        stringOptions.add("Abort");

        // Create the menu
        NumberedOptions authorsOptions = new NumberedOptions(stringOptions);
        int option = authorsOptions.prompt();

        if (option >= 1 && option <= authors.size()) {
            return authors.get(option - 1).getId();
        } else if (option == authors.size() + 1) {
            // Exit
            return -1;
        } else {
            throw new RuntimeException("Option not handled.");
        }
    }

    public static int promptForPublisherId() {
        // Obtain strings with all the options for the menu
        System.out.println();
        ArrayList<Publisher> publishers = new ArrayList<>(PublisherService.getAllPublishers());
        ArrayList<String> stringOptions = new ArrayList<>(publishers.stream().map(Publisher::getName).toList());
        stringOptions.add("Abort");

        // Create the menu
        NumberedOptions publishersOptions = new NumberedOptions(stringOptions);
        int option = publishersOptions.prompt();

        if (option >= 1 && option <= publishers.size()) {
            return publishers.get(option - 1).getId();
        } else if (option == publishers.size() + 1) {
            // Exit
            return -1;
        } else {
            throw new RuntimeException("Option not handled.");
        }
    }

    public static int promptForCategoryId() {
        // Obtain strings with all the options for the menu
        System.out.println();
        ArrayList<Category> categories = new ArrayList<>(CategoryService.getAllCategories());
        ArrayList<String> stringOptions = new ArrayList<>(categories.stream().map(Category::getName).toList());
        stringOptions.add("Abort");

        // Create the menu
        NumberedOptions categoriesOptions = new NumberedOptions(stringOptions);
        int option = categoriesOptions.prompt();

        if (option >= 1 && option <= categories.size()) {
            return categories.get(option - 1).getId();
        } else if (option == categories.size() + 1) {
            // Exit
            return -1;
        } else {
            throw new RuntimeException("Option not handled.");
        }
    }

    public void prompt() {
        while (true) {
            System.out.println();
            int option = MAIN_OPTIONS.prompt();
            if (option == 1) {
                // Add menu
                (new AddIo()).prompt();
            } else if (option == 2) {
                // Update menu
                (new UpdateIo()).prompt();
            } else if (option == 3) {
                // Delete menu
                (new DeleteIo()).prompt();
            } else if (option == 4) {
                // Exit
                break;
            } else {
                throw new RuntimeException("Option not handled.");
            }
        }
    }
}

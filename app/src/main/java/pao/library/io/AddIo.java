package pao.library.io;

import pao.library.api.model.Author;
import pao.library.api.model.Category;
import pao.library.api.model.Publisher;
import pao.library.api.service.AuthorService;
import pao.library.api.service.CategoryService;
import pao.library.api.service.PublisherService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class AddIo {
    private static final NumberedOptions MAIN_OPTIONS = new NumberedOptions(new ArrayList<>(Arrays.asList("Add book",
            // 1
            "Add author", // 2
            "Add publisher", // 3
            "Add category", // 4
            "Exit" // 5
    )));

    public void prompt() {
        while (true) {
            System.out.println();
            int option = MAIN_OPTIONS.prompt();
            if (option == 1) {
                // Delete book
                promptAddBook();
            } else if (option == 2) {
                // Delete author
                promptAddAuthor();
            } else if (option == 3) {
                // Delete publisher
                promptAddPublisher();
            } else if (option == 4) {
                // Delete category
                promptAddCategory();
            } else if (option == 5) {
                // Exit
                break;
            } else {
                throw new RuntimeException("Option not handled.");
            }
        }
    }

    private void promptAddBook() {
        // TODO
    }

    private void promptAddAuthor() {
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Name: ");
        String name = scanner.nextLine();

        Author author = new Author(name);
        try {
            AuthorService.addAuthor(author);
            System.out.println("\nSuccessfully added author.");
        } catch (SQLException exception) {
            System.out.println("\nAuthor already exists in the database.");
        }
    }

    private void promptAddPublisher() {
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Name: ");
        String name = scanner.nextLine();

        Publisher publisher = new Publisher(name);
        try {
            PublisherService.addPublisher(publisher);
            System.out.println("\nSuccessfully added publisher.");
        } catch (SQLException exception) {
            System.out.println("\nPublisher already exists in the database.");
        }
    }

    private void promptAddCategory() {
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Name: ");
        String name = scanner.nextLine();

        Category category = new Category(name);
        try {
            CategoryService.addCategory(category);
            System.out.println("\nSuccessfully added category.");
        } catch (SQLException exception) {
            System.out.println("\nCategory already exists in the database.");
        }
    }
}

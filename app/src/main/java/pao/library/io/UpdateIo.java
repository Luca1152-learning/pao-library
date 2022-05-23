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

public class UpdateIo {
    private static final NumberedOptions MAIN_OPTIONS = new NumberedOptions(new ArrayList<>(Arrays.asList(
            "Update book", // 1
            "Update author", // 2
            "Update publisher", // 3
            "Update category", // 4
            "Exit" // 5
    )));

    public void prompt() {
        while (true) {
            System.out.println();
            int option = MAIN_OPTIONS.prompt();
            if (option == 1) {
                // Update book
                promptUpdateBook();
            } else if (option == 2) {
                // Update author
                int authorId = ManageBooksIo.promptForAuthorId();
                if (authorId == -1) continue;
                promptUpdateAuthor(authorId);
            } else if (option == 3) {
                // Update publisher
                int publisherId = ManageBooksIo.promptForPublisherId(true);
                if (publisherId == -1) continue;
                promptUpdatePublisher(publisherId);
            } else if (option == 4) {
                // Update category
                int categoryId = ManageBooksIo.promptForCategoryId();
                if (categoryId == -1) continue;
                promptUpdateCategory(categoryId);
            } else if (option == 5) {
                // Exit
                break;
            } else {
                throw new RuntimeException("Option not handled.");
            }
        }
    }

    private void promptUpdateBook() {
        // TODO
    }

    private void promptUpdateAuthor(int authorId) {
        Author author = AuthorService.getAuthorById(authorId);

        System.out.println();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Replace old name ('" + author.getName() + "') with: ");
        String name = scanner.nextLine();

        author.setName(name);
        try {
            AuthorService.updateAuthor(author);
            System.out.println("\nSuccessfully updated author.");
        } catch (SQLException exception) {
            System.out.println("\nAuthor already exists in the database.");
        }
    }

    private void promptUpdatePublisher(int publisherId) {
        Publisher publisher = PublisherService.getPublisherById(publisherId);

        System.out.println();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Replace old name ('" + publisher.getName() + "') with: ");
        String name = scanner.nextLine();

        publisher.setName(name);
        try {
            PublisherService.updatePublisher(publisher);
            System.out.println("\nSuccessfully updated publisher.");
        } catch (SQLException exception) {
            System.out.println("\nPublisher already exists in the database.");
        }
    }

    private void promptUpdateCategory(int categoryId) {
        Category category = CategoryService.getCategoryById(categoryId);

        System.out.println();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Replace old name ('" + category.getName() + "') with: ");
        String name = scanner.nextLine();

        category.setName(name);
        try {
            CategoryService.updateCategory(category);
            System.out.println("\nSuccessfully updated category.");
        } catch (SQLException exception) {
            System.out.println("\nCategory already exists in the database.");
        }
    }
}

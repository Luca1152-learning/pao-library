package pao.library.io;

import pao.library.api.model.Author;
import pao.library.api.model.Book;
import pao.library.api.model.Category;
import pao.library.api.model.Publisher;
import pao.library.api.service.AuthorService;
import pao.library.api.service.BookService;
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

    private static final NumberedOptions BOOK_OPTIONS = new NumberedOptions(new ArrayList<>(Arrays.asList(
            "Update title", // 1
            "Replace authors", // 2
            "Update description", // 3
            "Update publisher", // 4
            "Update publication year", // 5
            "Update pages count", // 6
            "Replace categories", // 7
            "Update available copies", // 8
            "Exit" // 9
    )));

    public void prompt() {
        while (true) {
            System.out.println();
            int option = MAIN_OPTIONS.prompt();
            if (option == 1) {
                // Update book
                int bookId = ManageBooksIo.promptForBookId();
                if (bookId == -1) continue;
                promptUpdateBook(bookId);
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

    private void promptUpdateBook(int bookId) {
        while (true) {
            System.out.println();
            int option = BOOK_OPTIONS.prompt();
            if (option == 1) {
                // Update title
                updateBookTitle(bookId);
            } else if (option == 2) {
                // Replace authors
                replaceBookAuthors(bookId);
            } else if (option == 3) {
                // Update description
                updateBookDescription(bookId);
            } else if (option == 4) {
                // Update publisher
                updateBookPublisher(bookId);
            } else if (option == 5) {
                // Update publication year
                updateBookPublicationYear(bookId);
            } else if (option == 6) {
                // Update pages count
                updateBookPagesCount(bookId);
            } else if (option == 7) {
                // Replace categories
                replaceBookCategories(bookId);
            } else if (option == 8) {
                // Update available copies
                updateBookAvailableCopies(bookId);
            } else if (option == 9) {
                // Exit
                break;
            } else {
                throw new RuntimeException("Option not handled.");
            }
        }
    }

    private void updateBookTitle(int bookId) {
        Book book = BookService.getBookById(bookId);

        System.out.println();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Replace old title ('" + book.getTitle() + "') with: ");
        String title = scanner.nextLine();

        book.setTitle(title);
        try {
            BookService.updateBook(book);
            System.out.println("\nSuccessfully updated book.");
        } catch (SQLException exception) {
            System.out.println("\nError while updating book.");
        }
    }

    private void replaceBookAuthors(int bookId) {
        // Delete all authors
        BookService.deleteAllBookAuthors(bookId);

        // Add new authors
        System.out.print("Pick the new authors.");
        ArrayList<Author> authors = ManageBooksIo.promptForAuthors();
        BookService.addBookAuthors(bookId, authors);
    }

    private void updateBookDescription(int bookId) {
        Book book = BookService.getBookById(bookId);

        System.out.println();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Replace old description ('" + book.getDescription() + "') with: ");
        String description = scanner.nextLine();

        book.setDescription(description);
        try {
            BookService.updateBook(book);
            System.out.println("\nSuccessfully updated book.");
        } catch (SQLException exception) {
            System.out.println("\nError while updating book.");
        }
    }

    private void updateBookPublisher(int bookId) {
        Book book = BookService.getBookById(bookId);

        System.out.println();

        System.out.print("Pick the new publisher.");
        int publisherId = ManageBooksIo.promptForPublisherId(false);

        book.setPublisherId(publisherId);
        try {
            BookService.updateBook(book);
            System.out.println("\nSuccessfully updated book.");
        } catch (SQLException exception) {
            System.out.println("\nError while updating book.");
        }
    }

    private void updateBookPublicationYear(int bookId) {
        Book book = BookService.getBookById(bookId);

        System.out.println();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Replace old publication year ('" + book.getFirstPublicationYear() + "') with: ");
        int firstPublicationYear = scanner.nextInt();

        book.setFirstPublicationYear(firstPublicationYear);
        try {
            BookService.updateBook(book);
            System.out.println("\nSuccessfully updated book.");
        } catch (SQLException exception) {
            System.out.println("\nError while updating book.");
        }
    }

    private void updateBookPagesCount(int bookId) {
        Book book = BookService.getBookById(bookId);

        System.out.println();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Replace old pages count ('" + book.getPagesCount() + "') with: ");
        int pagesCount = scanner.nextInt();

        book.setPagesCount(pagesCount);
        try {
            BookService.updateBook(book);
            System.out.println("\nSuccessfully updated book.");
        } catch (SQLException exception) {
            System.out.println("\nError while updating book.");
        }
    }

    private void replaceBookCategories(int bookId) {
        // Delete all categories
        BookService.deleteAllBookCategories(bookId);

        // Add new categories
        System.out.print("Pick the new categories.");
        ArrayList<Category> categories = ManageBooksIo.promptForCategories();
        BookService.addBookCategories(bookId, categories);
    }

    private void updateBookAvailableCopies(int bookId) {
        Book book = BookService.getBookById(bookId);

        System.out.println();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Replace old available copies ('" + book.getAvailableCopies() + "') with: ");
        int availableCopies = scanner.nextInt();

        book.setAvailableCopies(availableCopies);
        try {
            BookService.updateBook(book);
            System.out.println("\nSuccessfully updated book.");
        } catch (SQLException exception) {
            System.out.println("\nError while updating book.");
        }
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

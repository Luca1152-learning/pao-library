package pao.library.io;

import pao.library.api.model.Book;
import pao.library.api.model.User;
import pao.library.api.service.BookService;
import pao.library.api.service.BorrowService;
import pao.library.api.service.PublisherService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BorrowBookIo {
    private static final NumberedOptions BORROW_OPTIONS = new NumberedOptions(new ArrayList<>(Arrays.asList(
            "Borrow", // 1
            "Exit" // 2
    )));

    public void prompt(User user) {
        while (true) {
            System.out.println();
            ArrayList<Book> books = new ArrayList<>(BookService.getAllBooks());

            // Obtain strings with all the options for the menu
            ArrayList<String> stringOptions =
                    new ArrayList<>(books.stream().map(
                            book -> book.getTitle() +
                                    " by " + String.join(", ", BookService.getBookAuthors(book.getId())) +
                                    " (" + book.getAvailableCopies() + " available copies)"
                    ).toList());
            stringOptions.add("Exit");

            // Create the menu
            NumberedOptions booksOptions = new NumberedOptions(stringOptions);
            int option = booksOptions.prompt();

            if (option >= 1 && option <= books.size()) {
                promptBorrowBook(user.getId(), books.get(option - 1).getId());
            } else if (option == books.size() + 1) {
                // Exit
                break;
            } else {
                throw new RuntimeException("Option not handled.");
            }
        }
    }

    private void promptBorrowBook(int userId, int bookId) {
        Book book = BookService.getBookById(bookId);
        System.out.println();
        book.showDetails(
                BookService.getBookAuthors(bookId), BookService.getBookCategories(bookId),
                PublisherService.getPublisherById(book.getPublisherId()).getName()
        );

        if (book.getAvailableCopies() > 0) {
            System.out.println();
            int option = BORROW_OPTIONS.prompt();
            if (option == 1) {
                BorrowService.scheduleBookBorrow(userId, bookId);
                System.out.println("\nThe book is now yours. Come to the library to pick it up.");
            } else if (option == 2) {
                return;
            } else {
                throw new RuntimeException("Option not handled.");
            }
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.print("\nNo available copies. Type anything to continue: ");
            scanner.next();
        }
    }
}

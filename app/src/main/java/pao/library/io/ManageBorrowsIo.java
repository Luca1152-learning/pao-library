package pao.library.io;

import pao.library.api.model.Book;
import pao.library.api.model.Borrow;
import pao.library.api.model.User;
import pao.library.api.service.BookService;
import pao.library.api.service.BorrowService;
import pao.library.api.service.UserService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public class ManageBorrowsIo {
    public void prompt() {
        while (true) {
            Collection<Borrow> borrowRequests = BorrowService.getBorrowRequests();
            Collection<Borrow> borrowsToBeReturned = BorrowService.getBorrowsToBeReturned();

            NumberedOptions options =
                    new NumberedOptions(new ArrayList<>(Arrays.asList("Give books (" + borrowRequests.size() + " " +
                                    "requests)", // 1
                            "Receive books (" + borrowsToBeReturned.size() + " possible books)", // 2
                            "See history", // 3
                            "Exit" // 4
                    )));

            System.out.println();
            int option = options.prompt();
            if (option == 1) {
                // Give books
                promptGiveBooks();
            } else if (option == 2) {
                // Receive books
                promptReceiveBooks();
            } else if (option == 3) {
                // See history
                promptSeeHistory();
            } else if (option == 4) {
                // Exit
                break;
            } else {
                throw new RuntimeException("Option not handled.");
            }
        }
    }

    public void promptGiveBooks() {
        ArrayList<Borrow> borrowRequests = new ArrayList<>(BorrowService.getBorrowRequests());

        // Obtain strings with all the options for the menu
        System.out.println();
        ArrayList<String> stringOptions = new ArrayList<>(borrowRequests.stream().map(borrow -> {
            User user = UserService.getUserById(borrow.getUserId());
            Book book = BookService.getBookById(borrow.getBookId());
            String authors = String.join(", ", BookService.getBookAuthors(book.getId()));
            return "[" + book.getTitle() + " by " + authors + "] to be borrowed by " + user.getFirstName() + " " + user.getLastName();
        }).toList());
        stringOptions.add("Exit");

        // Create the menu
        NumberedOptions categoriesOptions = new NumberedOptions(stringOptions);
        int option = categoriesOptions.prompt();

        if (option >= 1 && option <= borrowRequests.size()) {
            BorrowService.satisfyBorrowRequest(borrowRequests.get(option - 1).getId());
        } else if (option == borrowRequests.size() + 1) {
            // Exit
            return;
        } else {
            throw new RuntimeException("Option not handled.");
        }
    }

    public void promptReceiveBooks() {
        ArrayList<Borrow> borrowsToBeReturned = new ArrayList<>(BorrowService.getBorrowsToBeReturned());

        // Obtain strings with all the options for the menu
        System.out.println();
        ArrayList<String> stringOptions = new ArrayList<>(borrowsToBeReturned.stream().map(borrow -> {
            User user = UserService.getUserById(borrow.getUserId());
            Book book = BookService.getBookById(borrow.getBookId());
            String authors = String.join(", ", BookService.getBookAuthors(book.getId()));
            // Borrow date
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            String borrowDateString = format.format(borrow.getBorrowDate());
            return "[" + book.getTitle() + " by " + authors + "] borrowed by " + user.getFirstName() + " " + user.getLastName() + " on " + borrowDateString;
        }).toList());
        stringOptions.add("Exit");

        // Create the menu
        NumberedOptions categoriesOptions = new NumberedOptions(stringOptions);
        int option = categoriesOptions.prompt();

        if (option >= 1 && option <= borrowsToBeReturned.size()) {
            BorrowService.receiveBook(borrowsToBeReturned.get(option - 1).getId());
        } else if (option == borrowsToBeReturned.size() + 1) {
            // Exit
            return;
        } else {
            throw new RuntimeException("Option not handled.");
        }
    }

    public void promptSeeHistory() {
        System.out.println();

        ArrayList<Borrow> borrows = new ArrayList<>(BorrowService.getAllBorrows());

        borrows.forEach(borrow -> {
            User user = UserService.getUserById(borrow.getUserId());
            Book book = BookService.getBookById(borrow.getBookId());
            String authors = String.join(", ", BookService.getBookAuthors(book.getId()));
            // Borrow date
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            String borrowDateString = "";
            if (borrow.getBorrowDate() != null) {
                borrowDateString = format.format(borrow.getBorrowDate());
            }
            // Return date
            String returnDateString = "";
            if (borrow.getReturnDate() != null) {
                returnDateString = format.format(borrow.getReturnDate());
            }

            if (borrow.getBorrowDate() == null) {
                System.out.println("Book [" + book.getTitle() + " by " + authors + "] is requested by " + user.getFirstName() + " " + user.getLastName());
            } else if (borrow.getReturnDate() == null) {
                System.out.println(
                        "Book [" + book.getTitle() + " by " + authors + "] was borrowed by " + user.getFirstName() +
                                " " + user.getLastName() + " on " + borrowDateString
                );
            } else {
                System.out.println(
                        "Book [" + book.getTitle() + " by " + authors + "] was borrowed by " + user.getFirstName() +
                                " " + user.getLastName() + " on " + borrowDateString + " and returned on " + returnDateString
                );
            }
        });

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nType anything to continue: ");
        scanner.next();

        // TODO
    }
}

package pao.library.io;

import pao.library.api.model.Book;
import pao.library.api.service.BookService;

import java.util.ArrayList;

public class BrowseBooksIo {
    public void prompt() {
        ArrayList<Book> books = new ArrayList<>(BookService.getAllBooks());

        while (true) {
            // Obtain strings with all the options for the menu
            ArrayList<String> stringOptions =
                    new ArrayList<>(books.stream().map(book -> book.getTitle() + " by " + String.join(", ",
                            BookService.getBookAuthors(book.getId()))).toList());
            stringOptions.add("Exit");

            // Create the menu
            NumberedOptions booksOptions = new NumberedOptions(stringOptions);
            int option = booksOptions.prompt();

            if (option >= 1 && option <= books.size()) {
                (new ShowBookIo()).prompt(books.get(option - 1).getId());
            } else if (option == books.size() + 1) {
                // Exit
                break;
            } else {
                throw new RuntimeException("Option not handled.");
            }
        }
    }
}

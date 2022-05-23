package pao.library.io;

import pao.library.api.model.Book;
import pao.library.api.service.BookService;
import pao.library.api.service.PublisherService;

import java.util.Scanner;

public class ShowBookIo {
    public void prompt(int bookId) {
        Book book = BookService.getBookById(bookId);

        System.out.println();
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author(s): " + String.join(", ", BookService.getBookAuthors(bookId)));
        System.out.println("Publication year: " + book.getFirstPublicationYear());
        System.out.println("Publisher: " + PublisherService.getPublisherById(book.getPublisherId()).getName());
        System.out.println("Description: " + book.getDescription());
        System.out.println("Pages: " + book.getPagesCount());
        System.out.println("Available copies: " + book.getAvailableCopies());

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nType anything to continue: ");
        scanner.next();
        System.out.println();
    }
}

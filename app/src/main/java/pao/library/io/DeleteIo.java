package pao.library.io;

import pao.library.api.service.AuthorService;
import pao.library.api.service.BookService;
import pao.library.api.service.CategoryService;
import pao.library.api.service.PublisherService;

import java.util.ArrayList;
import java.util.Arrays;

public class DeleteIo {
    private static final NumberedOptions MAIN_OPTIONS = new NumberedOptions(new ArrayList<>(Arrays.asList("Delete " +
                    "book", // 1
            "Delete author", // 2
            "Delete publisher", // 3
            "Delete category", // 4
            "Exit" // 5
    )));

    public void prompt() {
        while (true) {
            System.out.println();
            int option = MAIN_OPTIONS.prompt();
            if (option == 1) {
                // Delete book
                promptDeleteBook();
            } else if (option == 2) {
                // Delete author
                promptDeleteAuthor();
            } else if (option == 3) {
                // Delete publisher
                promptDeletePublisher();
            } else if (option == 4) {
                // Delete category
                promptDeleteCategory();
            } else if (option == 5) {
                // Exit
                break;
            } else {
                throw new RuntimeException("Option not handled.");
            }
        }
    }

    private void promptDeleteBook() {
        int bookId = ManageBooksIo.promptForBookId();
        if (bookId == -1) return;
        boolean confirmDeletion = confirmBookDeletion(bookId);
        if (confirmDeletion) {
            BookService.deleteBookById(bookId);
            System.out.println("\nThe book was successfully deleted.");
        }
    }

    public boolean confirmBookDeletion(int bookId) {
        System.out.println();
        System.out.println("Are you sure you want to delete the book '" + BookService.getBookById(bookId).getTitle() + "'?");
        int option = ManageBooksIo.CONFIRM_OPTIONS.prompt();
        return option == 1; // 1 = Yes, 2 = No
    }

    private void promptDeleteAuthor() {
        int authorId = ManageBooksIo.promptForAuthorId();
        if (authorId == -1) return;
        boolean confirmDeletion = confirmAuthorDeletion(authorId);
        if (confirmDeletion) {
            AuthorService.deleteAuthorById(authorId);
            System.out.println("\nThe author was successfully deleted.");
        }
    }

    public boolean confirmAuthorDeletion(int authorId) {
        System.out.println();
        System.out.println("Are you sure you want to delete the author '" + AuthorService.getAuthorById(authorId).getName() + "'?");
        int option = ManageBooksIo.CONFIRM_OPTIONS.prompt();
        return option == 1; // 1 = Yes, 2 = No
    }

    private void promptDeletePublisher() {
        int publisherId = ManageBooksIo.promptForPublisherId(true);
        if (publisherId == -1) return;
        boolean confirmDeletion = confirmPublisherDeletion(publisherId);
        if (confirmDeletion) {
            PublisherService.deletePublisherById(publisherId);
            System.out.println("\nThe publisher was successfully deleted.");
        }
    }

    public boolean confirmPublisherDeletion(int publisherId) {
        System.out.println();
        System.out.println("Are you sure you want to delete the publisher '" + PublisherService.getPublisherById(publisherId).getName() + "'?");
        int option = ManageBooksIo.CONFIRM_OPTIONS.prompt();
        return option == 1; // 1 = Yes, 2 = No
    }

    private void promptDeleteCategory() {
        int categoryId = ManageBooksIo.promptForCategoryId();
        if (categoryId == -1) return;
        boolean confirmDeletion = confirmCategoryDeletion(categoryId);
        if (confirmDeletion) {
            CategoryService.deleteCategoryById(categoryId);
            System.out.println("\nThe category was successfully deleted.");
        }
    }

    public boolean confirmCategoryDeletion(int categoryId) {
        System.out.println();
        System.out.println("Are you sure you want to delete the category '" + CategoryService.getCategoryById(categoryId).getName() + "'?");
        int option = ManageBooksIo.CONFIRM_OPTIONS.prompt();
        return option == 1; // 1 = Yes, 2 = No
    }
}

package pao.library.api.service;

import pao.library.api.dao.AuthorDao;
import pao.library.api.model.Author;

import java.sql.SQLException;
import java.util.Collection;

public class AuthorService {
    private static final AuthorDao AUTHOR_DAO = new AuthorDao();

    public static Collection<Author> getAllAuthors() {
        return AUTHOR_DAO.getAll();
    }

    public static Author getAuthorById(int authorId) {
        return AUTHOR_DAO.get(authorId);
    }

    public static void deleteAuthorById(int authorId) {
        AUTHOR_DAO.delete(AUTHOR_DAO.get(authorId));
    }

    public static void addAuthor(Author author) throws SQLException {
        AUTHOR_DAO.save(author);
    }

    public static void updateAuthor(Author author) throws SQLException {
        AUTHOR_DAO.update(author);
    }
}

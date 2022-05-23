package pao.library.api.service;

import pao.library.api.dao.PublisherDao;
import pao.library.api.model.Publisher;

import java.sql.SQLException;
import java.util.Collection;

public class PublisherService {
    private static final PublisherDao PUBLISHER_DAO = new PublisherDao();

    public static Collection<Publisher> getAllPublishers() {
        return PUBLISHER_DAO.getAll();
    }

    public static Publisher getPublisherById(int publisherId) {
        return PUBLISHER_DAO.get(publisherId);
    }

    public static void deletePublisherById(int publisherId) {
        PUBLISHER_DAO.delete(PUBLISHER_DAO.get(publisherId));
    }

    public static void addPublisher(Publisher publisher) throws SQLException {
        PUBLISHER_DAO.save(publisher);
    }
}

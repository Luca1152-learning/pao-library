package pao.library.api.service;

import pao.library.api.dao.PublisherDao;
import pao.library.api.model.Publisher;

public class PublisherService {
    private static final PublisherDao PUBLISHER_DAO = new PublisherDao();

    public static Publisher getPublisherById(int publisherId) {
        return PUBLISHER_DAO.get(publisherId);
    }
}

package pao.library.api.service;

import pao.library.api.dao.CategoryDao;
import pao.library.api.model.Category;

import java.util.Collection;

public class CategoryService {
    private static final CategoryDao CATEGORY_DAO = new CategoryDao();

    public static Collection<Category> getAllCategories() {
        return CATEGORY_DAO.getAll();
    }

    public static Category getCategoryById(int categoryId) {
        return CATEGORY_DAO.get(categoryId);
    }

    public static void deleteCategoryById(int categoryId) {
        CATEGORY_DAO.delete(CATEGORY_DAO.get(categoryId));
    }
}

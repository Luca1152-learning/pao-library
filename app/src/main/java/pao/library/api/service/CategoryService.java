package pao.library.api.service;

import pao.library.api.dao.CategoryDao;
import pao.library.api.model.Category;

import java.sql.SQLException;
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

    public static void addCategory(Category category) throws SQLException {
        CATEGORY_DAO.save(category);
    }

    public static void updateCategory(Category category) throws SQLException {
        CATEGORY_DAO.update(category);
    }
}

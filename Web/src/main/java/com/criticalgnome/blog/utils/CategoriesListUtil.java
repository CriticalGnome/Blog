package com.criticalgnome.blog.utils;

import com.criticalgnome.blog.entities.Category;
import com.criticalgnome.blog.entities.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Blog
 * Created on 08.04.2017.
 *
 * @author CriticalGnome
 */
public class CategoriesListUtil {

    /**
     * Create Categories list for display on pages
     *
     * @param categories Categories list
     * @return CategoriesDTO list
     */
    public static List<CategoryDTO> getCategoriesDTOList(List<Category> categories) {
        List<CategoryDTO> categoryDTOs = new ArrayList<>();
        return getSubcategories(categoryDTOs, categories, null, "");
    }

    /**
     * Get subcategories for current category
     *
     * @param categoryDTOs CategoriesDTO list
     * @param categories   Categories list
     * @param parent       parent Category
     * @param indent       indent symbols
     * @return CategoriesDTO list
     */
    private static List<CategoryDTO> getSubcategories(List<CategoryDTO> categoryDTOs, List<Category> categories, Category parent, String indent){

        for (Category category : categories) {
            if (category.getCategory() == parent) {
                CategoryDTO abc = new CategoryDTO(category.getId(), indent + category.getName());
                categoryDTOs.add(abc);
//                getSubcategories(categoryDTOs, categories, category, indent + category.getName() + ".");
                getSubcategories(categoryDTOs, categories, category, indent + "*");
            }
        }
        return categoryDTOs;
    }

}

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

    public static List<CategoryDTO> getCategoriesDTOList(List<Category> categories) {
        List<CategoryDTO> categoryDTOs = new ArrayList<>();
        return getSubcategories(categoryDTOs, categories, null, "");
    }

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

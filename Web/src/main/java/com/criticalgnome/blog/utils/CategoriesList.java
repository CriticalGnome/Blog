package com.criticalgnome.blog.utils;

import com.criticalgnome.blog.entities.Category;

import java.util.List;

/**
 * Project Blog
 * Created on 08.04.2017.
 *
 * @author CriticalGnome
 */
public class CategoriesList {

    public static List<CategoryLine> getSubcategories(List<CategoryLine> categoryLines, List<Category> categories, Category parent, String indent){

        for (Category category : categories) {
            if (category.getCategory() == parent) {
                CategoryLine abc = new CategoryLine(category.getId(), indent + category.getName());
                categoryLines.add(abc);
//                getSubcategories(categoryLines, categories, category, indent + category.getName() + ".");
                getSubcategories(categoryLines, categories, category, indent + "*");
            }
        }
        return categoryLines;
    }

}

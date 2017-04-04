package org.alfresco.demo.quick;

import org.alfresco.service.cmr.repository.ChildAssociationRef;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Helper utilities for Categories
 */
public class CategoriesHelper
{

    public static List<Category> toCategories(Collection<ChildAssociationRef> results) {
        List<Category> result = new ArrayList<>(results.size());
        results.forEach(item -> {
            result.add(new Category(item.getChildRef(), item.getQName().getLocalName()));
        });
        return result;
    }
}

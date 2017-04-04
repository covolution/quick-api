package org.alfresco.demo.quick;

import static org.alfresco.demo.quick.CategoriesHelper.toCategories;
import org.alfresco.rest.api.Nodes;
import org.alfresco.rest.api.nodes.NodesEntityResource;
import org.alfresco.rest.framework.resource.RelationshipResource;
import org.alfresco.rest.framework.resource.actions.interfaces.RelationshipResourceAction;
import org.alfresco.rest.framework.resource.parameters.CollectionWithPagingInfo;
import org.alfresco.rest.framework.resource.parameters.Parameters;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.search.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

/**
 * An endpoint for /-default-/private/mycompany/versions/1/categories/[categoryId]/children
 */
@RelationshipResource(name = "children", entityResource = CategoriesEntityResource.class, title = "Category children")
public class CategoriesChildrenRelation implements RelationshipResourceAction.Read<Category>
{

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private Nodes nodes;

    @Override
    public CollectionWithPagingInfo<Category> readAll(String parentCategoryId, Parameters parameters)
    {
        NodeRef nodeRef = nodes.validateOrLookupNode(parentCategoryId, null);
        Collection<ChildAssociationRef> children = categoryService.getChildren(nodeRef, CategoryService.Mode.SUB_CATEGORIES, CategoryService.Depth.IMMEDIATE);
        List<Category> result = toCategories(children);
        return CollectionWithPagingInfo.asPaged(parameters.getPaging(), result);
    }
}

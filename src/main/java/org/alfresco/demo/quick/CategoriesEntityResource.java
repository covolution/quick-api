package org.alfresco.demo.quick;

import static org.alfresco.demo.quick.CategoriesHelper.toCategories;
import org.alfresco.model.ContentModel;
import org.alfresco.query.PagingRequest;
import org.alfresco.query.PagingResults;
import org.alfresco.rest.api.Nodes;
import org.alfresco.rest.api.impl.Util;
import org.alfresco.rest.framework.resource.EntityResource;
import org.alfresco.rest.framework.resource.actions.interfaces.EntityResourceAction;
import org.alfresco.rest.framework.resource.parameters.CollectionWithPagingInfo;
import org.alfresco.rest.framework.resource.parameters.Paging;
import org.alfresco.rest.framework.resource.parameters.Parameters;
import org.alfresco.service.cmr.model.FileInfo;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Rest API endpoint for "categories"
 */
@EntityResource(name="categories", title = "Categories")
public class CategoriesEntityResource implements EntityResourceAction.Read<Category>,
                                                 EntityResourceAction.Create<Category>,
                                                 EntityResourceAction.Delete
{

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private Nodes nodes;

    @Override
    public CollectionWithPagingInfo<Category> readAll(Parameters parameters)
    {
        PagingRequest pagingRequest = Util.getPagingRequest(parameters.getPaging());
        PagingResults<ChildAssociationRef> pagingResults = categoryService.getRootCategories(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE, ContentModel.ASPECT_GEN_CLASSIFIABLE, pagingRequest, true);
        List<Category> result = toCategories(pagingResults.getPage());
        return CollectionWithPagingInfo.asPaged(parameters.getPaging(), result, pagingResults.hasMoreItems(), pagingResults.getTotalResultCount().getFirst());

    }

    @Override
    public List<Category> create(List<Category> categories, Parameters parameters)
    {
        List<Category> result = new ArrayList<>(categories.size());
        for (Category newCategory:categories)
        {
            NodeRef created = categoryService.createRootCategory(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE, ContentModel.ASPECT_GEN_CLASSIFIABLE, newCategory.getName());
            result.add(new Category(created,newCategory.getName()));
        }
        return result;
    }

    @Override
    public void delete(String categoryId, Parameters parameters)
    {
        NodeRef nodeRef = nodes.validateOrLookupNode(categoryId, null);
        categoryService.deleteCategory(nodeRef);
    }
}

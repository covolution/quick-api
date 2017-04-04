package org.alfresco.demo.quick;

import org.alfresco.model.ContentModel;
import org.alfresco.query.PagingRequest;
import org.alfresco.query.PagingResults;
import org.alfresco.rest.api.impl.Util;
import org.alfresco.rest.framework.resource.EntityResource;
import org.alfresco.rest.framework.resource.actions.interfaces.EntityResourceAction;
import org.alfresco.rest.framework.resource.parameters.CollectionWithPagingInfo;
import org.alfresco.rest.framework.resource.parameters.Paging;
import org.alfresco.rest.framework.resource.parameters.Parameters;
import org.alfresco.service.cmr.model.FileInfo;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Rest API endpoint for "categories"
 */
@EntityResource(name="categories", title = "Categories")
public class CategoriesEntityResource implements EntityResourceAction.Read<Category>
{

    @Autowired
    private CategoryService categoryService;

    @Override
    public CollectionWithPagingInfo<Category> readAll(Parameters parameters)
    {
        PagingRequest pagingRequest = Util.getPagingRequest(parameters.getPaging());
        PagingResults<ChildAssociationRef> pagingResults = categoryService.getRootCategories(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE, ContentModel.ASPECT_GEN_CLASSIFIABLE, pagingRequest, true);
        List<Category> result = new ArrayList<>(pagingResults.getPage().size());
        pagingResults.getPage().forEach(item -> {
            result.add(new Category(item.getChildRef(), item.getQName().getLocalName()));
        });
        return CollectionWithPagingInfo.asPaged(parameters.getPaging(), result, pagingResults.hasMoreItems(), pagingResults.getTotalResultCount().getFirst());

    }
}

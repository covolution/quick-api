package org.alfresco.demo.quick;

import org.alfresco.rest.framework.resource.UniqueId;
import org.alfresco.service.cmr.repository.NodeRef;

/**
 * A POJO representation of a Category that will be serialized to JSON.
 */
public class Category
{
    protected NodeRef nodeRef;
    protected String name;

    public Category(NodeRef nodeRef, String name)
    {
        this.nodeRef = nodeRef;
        this.name = name;
    }

    @UniqueId
    public NodeRef getNodeRef()
    {
        return nodeRef;
    }

    public String getName()
    {
        return name;
    }
}

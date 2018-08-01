package org.irods.jargon.metadatatemplate;

import java.util.List;
import java.util.UUID;

import org.irods.jargon.metadatatemplate.model.MDTemplate;
import org.irods.jargon.metadatatemplate.model.MDTemplateElement;

public abstract class AbstractMetadataService {

	public abstract void saveTemplate(MDTemplate metadataTemplate) throws MetadataTemplateException;

	public abstract boolean deleteTemplateByName(String uniqueName) throws MetadataTemplateException;
	
	public abstract boolean deleteTemplateByGuid(UUID guid) throws MetadataTemplateException;
	
	public abstract List<MDTemplate> listPublicTemplates();

	public abstract List<MDTemplate> listAllTemplates(final String path) throws MetadataTemplateException;

	public abstract MDTemplate findTemplateByGuid(UUID guid)
			throws MetadataTemplateNotFoundException, MetadataTemplateException;
	
	public abstract MDTemplate findTemplateByName(String templateName)
			throws MetadataTemplateNotFoundException, MetadataTemplateException;
	
	public abstract boolean isTemplateExist(String templateName)
			throws MetadataTemplateNotFoundException, MetadataTemplateException;
	
	public abstract void updateTemplate(MDTemplate metadataTemplate) throws MetadataTemplateException;

	
	public abstract void saveElement(UUID templateGuid, MDTemplateElement metadataTemplate) throws MetadataTemplateException;

	public abstract void updateElement(UUID templateGuid , MDTemplateElement metadataTemplate) throws MetadataTemplateException;
	
	public abstract boolean deleteElementByGuid(UUID templateGuid, UUID elementGuid) throws MetadataTemplateException;
	
	public abstract MDTemplateElement findElementByGuid(UUID templateGuid, UUID elementGuid)
			throws MetadataTemplateNotFoundException, MetadataTemplateException;
	
}

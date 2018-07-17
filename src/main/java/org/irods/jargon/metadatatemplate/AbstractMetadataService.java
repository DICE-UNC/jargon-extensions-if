package org.irods.jargon.metadatatemplate;

import java.util.List;
import java.util.UUID;

import org.irods.jargon.metadatatemplate.model.MDTemplate;

public abstract class AbstractMetadataService {

	public abstract UUID saveTemplate(MDTemplate metadataTemplate) throws MetadataTemplateException;

	public abstract boolean deleteTemplateByUUID(String uniqueName) throws MetadataTemplateException;
	public abstract List<MDTemplate> listPublicTemplates();

	public abstract List<MDTemplate> listAllTemplates(final String path) throws MetadataTemplateException;

	public abstract MDTemplate findTemplateByUUID(UUID uuid)
			throws MetadataTemplateNotFoundException, MetadataTemplateException;

	
}
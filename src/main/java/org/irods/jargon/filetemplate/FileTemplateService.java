/**
 * 
 */
package org.irods.jargon.filetemplate;

import java.util.List;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.exception.DuplicateDataException;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.core.service.AbstractJargonService;
import org.irods.jargon.filetemplate.exception.FileTemplateException;
import org.irods.jargon.filetemplate.exception.FileTemplateNotFoundException;

/**
 * Basic abstract class that defines a file type service to manage 'templates'
 * of files that can be instatiated with default content, and to create files
 * with the templates.
 * 
 * @author Mike Conway - DICE
 *
 */
public abstract class FileTemplateService extends AbstractJargonService {

	/**
	 * Generate a list of available file templates for the logged in user. These
	 * are the types of files that the creator service knows how to process.
	 * 
	 * @return <code>List</code> of {@link FileTemplate}
	 * @throws FileTemplateException
	 */
	public abstract List<FileTemplate> listAvailableFileTemplates()
			throws FileTemplateException;

	/**
	 * Given the unique identifier for a file template, create the given file
	 * underneath the given iRODS parent collection absolute path.
	 * 
	 * @param parentPath
	 *            <code>String</code> with the absolute iRODS path to the parent
	 *            collection that will contain the new file
	 * @param fileName
	 *            <code>String</code> with the file name + extension (file.txt)
	 *            to be created. Whether the implementation checks the extension
	 *            versus the MIME type of the file is implementation dependent,
	 *            but the file contents will be determined by the template
	 * @param templateUniqueIdentifier
	 *            <code>String</code> with the unique identifier that describes
	 *            the given template
	 * @return {@link TemplateCreatedFile} with details on the file that was
	 *         just created
	 * @throws DuplicateDataException
	 *             if the file already exists
	 * @throws FileTemplateNotFoundException
	 *             if the template cannot be found based on the unique id
	 * @throws FileTemplateException
	 *             general exception
	 */
	public abstract TemplateCreatedFile createFileBasedOnTemplate(
			final String parentPath, final String fileName,
			final String templateUniqueIdentifier)
			throws DuplicateDataException, FileTemplateNotFoundException,
			FileTemplateException;

	/**
	 * 
	 */
	public FileTemplateService() {
		super();
	}

	/**
	 * @param irodsAccessObjectFactory
	 * @param irodsAccount
	 */
	public FileTemplateService(
			IRODSAccessObjectFactory irodsAccessObjectFactory,
			IRODSAccount irodsAccount) {
		super(irodsAccessObjectFactory, irodsAccount);
	}

}

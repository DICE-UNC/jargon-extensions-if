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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Basic abstract class that defines a file type service to manage 'templates'
 * of files that can be instatiated with default content, and to create files
 * with the templates.
 * 
 * @author Mike Conway - DICE
 *
 */
public abstract class FileTemplateService extends AbstractJargonService {

	public static final Logger log = LogManager.getLogger(FileTemplateService.class);

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

	public TemplateCreatedFile createFileBasedOnTemplateUniqueIdentifier(
			String parentPath, String fileName, String templateUniqueIdentifier)
			throws DuplicateDataException, FileTemplateNotFoundException,
			FileTemplateException {

		log.info("createFileBasedOnTemplate()");
		if (parentPath == null || parentPath.isEmpty()) {
			throw new IllegalArgumentException("parentPath is null or empty");
		}

		if (fileName == null || fileName.isEmpty()) {
			throw new IllegalArgumentException("null or empty fileName");
		}

		if (templateUniqueIdentifier == null
				|| templateUniqueIdentifier.isEmpty()) {
			throw new IllegalArgumentException(
					"templateUniqueIdentifier is null or empty");
		}

		log.info("parentPath:{}", parentPath);
		log.info("fileName:{}", fileName);
		log.info("templateUniqueIdentifier:{}", templateUniqueIdentifier);

		FileTemplate fileTemplate = retrieveTemplateByUniqueName(templateUniqueIdentifier);
		log.info("found fileTemplate:{}", fileTemplate);
		TemplateCreatedFile templateCreatedFile = createFileBasedOnTemplate(
				fileTemplate, parentPath, fileName);

		return templateCreatedFile;
	}

	/**
	 * Given a template, create an iRODS file based on the template. To be
	 * implemented by the particular extension
	 * 
	 * @param fileTemplate
	 *            {@link FileTemplate} that has been resolved
	 * @param parentPath
	 *            <code>String</code> with the absolute iRODS path to the parent
	 *            collection that will contain the new file
	 * @param fileName
	 *            <code>String</code> with the file name + extension (file.txt)
	 *            to be created. Whether the implementation checks the extension
	 *            versus the MIME type of the file is implementation dependent,
	 *            but the file contents will be determined by the template
	 * @return {@link TemplateCreatedFile}
	 * @throws DuplicateDataException
	 * @throws FileTemplateException
	 */
	protected abstract TemplateCreatedFile createFileBasedOnTemplate(
			FileTemplate fileTemplate, String parentPath, String fileName)
			throws DuplicateDataException, FileTemplateException;

	/**
	 * 
	 * @param templateUniqueIdentifier
	 * @return
	 * @throws FileTemplateNotFoundException
	 * @throws FileTemplateException
	 */
	protected abstract FileTemplate retrieveTemplateByUniqueName(
			String templateUniqueIdentifier)
			throws FileTemplateNotFoundException, FileTemplateException;

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

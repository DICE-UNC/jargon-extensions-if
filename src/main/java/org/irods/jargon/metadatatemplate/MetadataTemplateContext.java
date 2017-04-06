/**
 *
 */
package org.irods.jargon.metadatatemplate;

import org.irods.jargon.core.connection.IRODSAccount;

/**
 * Generic context used to connect a metadata template service to the underlying
 * data store for resolving and using templates. This is typically extended in
 * concrete implementations to include other information.
 *
 * @author mconway
 *
 */
public class MetadataTemplateContext {

	public MetadataTemplateContext() {
	}

	/**
	 * iRODS account representing the user and zone for which metadata templates
	 * will be accessed
	 */
	private IRODSAccount irodsAccount;

	public IRODSAccount getIrodsAccount() {
		return irodsAccount;
	}

	public void setIrodsAccount(IRODSAccount irodsAccount) {
		this.irodsAccount = irodsAccount;
	}

}

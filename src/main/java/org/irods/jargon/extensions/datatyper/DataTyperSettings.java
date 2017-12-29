/**
 * 
 */
package org.irods.jargon.extensions.datatyper;

/**
 * Settings that determine the behavior of data typer across general
 * implementations. Particular instances may have other specific confiuration
 * knobs
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class DataTyperSettings {

	public static final String APPLICATION_IRODS_RULE = "application/irods-rule";

	/**
	 * Save the determination in a persistant store
	 */
	private boolean persistDataTypes = true;

	/**
	 * Do a detailed (perhaps through invoking a rule and inspecting file contents)
	 * versus a quick (perhaps through file extension) determination
	 */
	private boolean detailedDetermination = false;

	public boolean isPersistDataTypes() {
		return persistDataTypes;
	}

	public void setPersistDataTypes(boolean persistDataTypes) {
		this.persistDataTypes = persistDataTypes;
	}

	public boolean isDetailedDetermination() {
		return detailedDetermination;
	}

	public void setDetailedDetermination(boolean detailedDetermination) {
		this.detailedDetermination = detailedDetermination;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DataTyperSettings [persistDataTypes=").append(persistDataTypes)
				.append(", detailedDetermination=").append(detailedDetermination).append("]");
		return builder.toString();
	}

}

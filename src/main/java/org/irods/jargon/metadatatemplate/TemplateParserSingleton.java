package org.irods.jargon.metadatatemplate;

import java.io.IOException;

import org.irods.jargon.core.pub.io.IRODSFileFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * 
 * Provides the methods to generate a <code>MetadataTemplate</code> from a JSON
 * file, and the code to generate a JSON file from an instantiated
 * <code>MetadataTemplate</code>.
 * <p/>
 * Defined as a singleton because there should never be a need for multiple
 * parsers.
 * <p/>
 * Example:<br/>
 * <code>TemplateParserSingleton parser = TemplateParserSingleton.PARSER;<br/>
 * MetadataTemplate mt = parser.createMetadataTemplateFromFile(file);</code>
 * 
 * @author rskarbez
 *
 */

public final class TemplateParserSingleton {
	static Logger log = LoggerFactory.getLogger(IRODSFileFactoryImpl.class);
	public final static TemplateParserSingleton PARSER = new TemplateParserSingleton();

	private ObjectMapper mapper = null;

	private TemplateParserSingleton() {
		mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		// Exists only to defeat instantiation
	}

	/**
	 * Returns a FormBasedMetadataTemplate generated from the JSON string in the
	 * input parameter.
	 * 
	 * @param s
	 *            {@link String} containing the JSON representation of a
	 *            metadata template, most likely read from a saved metadata
	 *            template file.
	 * @return
	 */
	public FormBasedMetadataTemplate createMetadataTemplateFromJSON(String s)
			throws MetadataTemplateParsingException,
			MetadataTemplateProcessingException {
		log.info("createMetadataTemplateFromJSON()");

		FormBasedMetadataTemplate mt = new FormBasedMetadataTemplate();
		//mt.setName("NULL NAME - MAPPER DIDN'T DO ANYTHING");
		//log.info("createMetadataTemplateFromJSON");
		log.info(s);

		try {
			mt = mapper.readValue(s, FormBasedMetadataTemplate.class);
		} catch (JsonParseException | JsonMappingException je) {
			log.error("Error in template JSON", je);
			throw new MetadataTemplateParsingException("Error in templateJSON");
		} catch (IOException ioe) {
			log.error("Error in template file read", ioe);
			throw new MetadataTemplateProcessingException(
					"Error in template file read");
		}

		if (mt == null) {
			log.error("null mt returned from mapper");
		}

		log.info(mt.toString());

		// If default values are defined, copy into current value
		for (MetadataElement me : mt.getElements()) {
			if (!me.getDefaultValue().isEmpty())
				me.setCurrentValue(me.getDefaultValue());
		}

		return mt;
	}

	/**
	 * Returns a String containing a JSON representation of the MetadataTemplate
	 * parameter.</p>
	 * <p>
	 * If default values for variables are not defined, any current values will
	 * be saved as the default values.
	 * 
	 * @param s
	 *            {@link MetadataTemplate} containing a populated metadata
	 *            template
	 * @return {@link String}
	 */
	public String createJSONFromMetadataTemplate(
			FormBasedMetadataTemplate template)
			throws MetadataTemplateProcessingException {
		FormBasedMetadataTemplate mt = template.deepCopy();

		// If default values are not defined, assume current value should be
		// default
		for (MetadataElement me : mt.getElements()) {
			if (!me.getDefaultValue().isEmpty())
				me.setDefaultValue(me.getCurrentValue());
		}

		String json = "";
		try {
			json = mapper.writeValueAsString(mt);
		} catch (JsonProcessingException jpe) {
			log.error(
					"JsonProcessingException when writing template to String",
					jpe);
			throw new MetadataTemplateProcessingException(
					"Unable to generate String representation of template");
		}

		return json;
	}
	// TODO: Add a flag that can disable the saving of current values as default
	// values?
}

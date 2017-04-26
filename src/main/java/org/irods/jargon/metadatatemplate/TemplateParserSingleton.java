package org.irods.jargon.metadatatemplate;

import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;

//import javax.ws.rs.core.Response;

import org.irods.jargon.core.pub.io.IRODSFileFactoryImpl;
//import org.jboss.resteasy.client.jaxrs.ResteasyClient;
//import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
//import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
//import com.github.jsonldjava.core.JsonLdError;
//import com.github.jsonldjava.core.JsonLdOptions;
//import com.github.jsonldjava.core.JsonLdProcessor;
//import com.github.jsonldjava.utils.JsonUtils;

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
	// public FormBasedMetadataTemplate createMetadataTemplateFromJSON(String s)
	public MetadataTemplate createMetadataTemplateFromJSON(final String s)
			throws MetadataTemplateParsingException, MetadataTemplateProcessingException {
		log.info("createMetadataTemplateFromJSON()");

		// FormBasedMetadataTemplate mt = new FormBasedMetadataTemplate();
		MetadataTemplate mt;
		// String metadataTemplateType;

		log.info(s);

		try {
			// TODO Implement a smarter check than this - RTS
			// if (s.contains("\"schemaURI\"")) {
			// mt = new SchemaReferenceMetadataTemplate();
			// mt = mapper.readValue(s, SchemaReferenceMetadataTemplate.class);
			// metadataTemplateType = "SchemaReferenceMetadataTemplate";
			// } else {
			mt = new MetadataTemplate();
			mt = mapper.readValue(s, MetadataTemplate.class);
			// metadataTemplateType = "FormBasedMetadataTemplate";
			// }
		} catch (JsonParseException | JsonMappingException je) {
			log.error("Error in template JSON", je);
			throw new MetadataTemplateParsingException("Error in templateJSON");
		} catch (IOException ioe) {
			log.error("Error in template file read", ioe);
			throw new MetadataTemplateProcessingException("Error in template file read");
		}

		if (mt == null) {
			log.error("null mt returned from mapper");
		}

		log.info(mt.toString());

		for (MetadataElement me : mt.getElements()) {
			if (!me.getDefaultValue().isEmpty()) {
				me.setCurrentValue(me.getDefaultValue());
			}
		}

		return mt;
	}

	/**
	 * Returns a String containing a JSON representation of the MetadataTemplate
	 * parameter.
	 * </p>
	 * <p>
	 * If saveCurrentValuesAsDefaults is set to true, the current values of the
	 * MetadataElements will be saved as default values in the template. Default
	 * values that are already set will NOT be overwritten.
	 * </p>
	 *
	 * @params {@link MetadataTemplate} containing a populated metadata template
	 *         boolean indicating whether current values should be stored as
	 *         default values
	 * @return {@link String}
	 */
	public String createJSONFromMetadataTemplate(final MetadataTemplate template,
			final boolean saveCurrentValuesAsDefaults) throws MetadataTemplateProcessingException {

		String json = "";
		try {
			json = mapper.writeValueAsString(template);
		} catch (JsonProcessingException jpe) {
			log.error("JsonProcessingException when writing template to String", jpe);
			throw new MetadataTemplateProcessingException("Unable to generate String representation of template");
		}

		return json;
	}

	public String createJSONFromMetadataTemplate(final MetadataTemplate template)
			throws MetadataTemplateProcessingException {
		return createJSONFromMetadataTemplate(template, true);
	}
}

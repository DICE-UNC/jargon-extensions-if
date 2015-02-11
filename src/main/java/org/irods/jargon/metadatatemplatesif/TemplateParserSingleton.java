package org.irods.jargon.metadatatemplatesif;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.StringWriter;

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
	public final static TemplateParserSingleton PARSER = new TemplateParserSingleton();
	
	private ObjectMapper mapper = null;

	private TemplateParserSingleton() {
		mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		// Exists only to defeat instantiation
	}

	public FormBasedMetadataTemplate createMetadataTemplateFromJSON(byte[] jsonData) {
		FormBasedMetadataTemplate mt = ObjectMapper.readValue(jsonData, FormBasedMetadataTemplate.class);
		
		// If default values are defined, copy into current value
		for (MetadataElement me: mt.getElements()) {
			if (!me.getDefaultValue().isEmpty())
				me.setCurrentValue(me.getDefaultValue());
		}
			
		return mt;
	}
	
	public String createJSONFromMetadataTemplate(FormBasedMetadataTemplate template) {
		FormBasedMetadataTemplate mt = new FormBasedMetadataTemplate(template);
		
		// If default values are not defined, assume current value should be default
		for (MetadataElement me: mt.getElements()) {
			if (!me.getDefaultValue().isEmpty())
				me.setDefaultValue(me.getCurrentValue());
		}
		
		String json = mapper.writeValueAsString(mt);
		
		return json;
	}
}

package org.irods.jargon.metadatatemplatesif;

import java.io.IOException;
import java.io.InputStream;

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

	//public FormBasedMetadataTemplate createMetadataTemplateFromJSON(byte[] jsonData) {
	//public FormBasedMetadataTemplate createMetadataTemplateFromJSON(InputStream is) {
	public FormBasedMetadataTemplate createMetadataTemplateFromJSON(String s) {

		FormBasedMetadataTemplate mt = new FormBasedMetadataTemplate();
		mt.setName("NULL NAME - MAPPER DIDN'T DO ANYTHING");
		log.info("createMetadataTemplateFromJSON");
		//log.info(jsonData.toString());
		log.info(s);
		
		try {
			//mt = mapper.readValue(jsonData, FormBasedMetadataTemplate.class);
			mt = mapper.readValue(s, FormBasedMetadataTemplate.class);
		} catch (JsonParseException jpe) {
			System.out.println("Json Parse exception: " + jpe);
			// XXX Handle JsonParseException
		} catch (JsonMappingException jme) {
			System.out.println("Json Mapping exception: " + jme);
			// XXX Handle JsonMappingException
		} catch (IOException ioe) {
			System.out.println("IO Exception: " + ioe);
			// XXX Handle IOException
		}
		
		if (mt == null) {
			log.info("null mt returned from mapper");
		}
		
		log.info(mt.getName());
		log.info(mt.toString());
		log.info(mt.getVersion());
		
		// If default values are defined, copy into current value
		for (MetadataElement me: mt.getElements()) {
			if (!me.getDefaultValue().isEmpty())
				me.setCurrentValue(me.getDefaultValue());
		}
			
		return new FormBasedMetadataTemplate(mt);
	}
	
	public String createJSONFromMetadataTemplate(FormBasedMetadataTemplate template) {
		FormBasedMetadataTemplate mt = new FormBasedMetadataTemplate(template);
		
		// If default values are not defined, assume current value should be default
		for (MetadataElement me: mt.getElements()) {
			if (!me.getDefaultValue().isEmpty())
				me.setDefaultValue(me.getCurrentValue());
		}
		
		String json = "";
		try {
			json = mapper.writeValueAsString(mt);
		} catch (JsonProcessingException jpe) {
			// XXX Handle JsonProcessingException
		}
		
		return json;
	}
}

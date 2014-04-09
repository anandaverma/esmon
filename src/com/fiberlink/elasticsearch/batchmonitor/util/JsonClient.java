package com.fiberlink.elasticsearch.batchmonitor.util;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonClient {
	private static ObjectMapper mapper = new ObjectMapper();
	private static Logger logger = Logger.getLogger(JsonClient.class);
	
	public JsonClient() {
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    	mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);	
	}
	
	@SuppressWarnings("unchecked")
	public static <T> Object readJson(String response, T esClass) {
		T obClass = esClass;
		try {
			obClass = (T) mapper.readValue(response, (Class<T>) esClass);
		} catch (JsonParseException e) {
			logger.error("Parse Error", e);
		} catch (JsonMappingException e) {
			logger.error("Mapping Error", e);
		} catch (IOException e) {
			logger.error("IO Error", e);
		}
		return obClass;
	}
}

package com.github.dhaeb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JDBCAppenderExample {
	private static final Logger logger = LogManager.getLogger(JDBCAppenderExample.class);

	public static void main(String[] args) 
	{
		logger.info("JDBCAppender Example ");
		try {
			logger.debug("Demo Statement");
			
			int i = 1/0;
			
		} catch (Exception e) {
			logger.error("Runtime error", e);
		}
	}
}
package com.github.dhaeb.db;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class H2LogsStoreConnectionFactory
{
	private static BasicDataSource dataSource;

	private H2LogsStoreConnectionFactory() {
	}

	public static Connection getConnection() throws SQLException {
		if (dataSource == null) {
			dataSource = new BasicDataSource();
			dataSource.setUrl("jdbc:h2:mem:testdb;TRACE_LEVEL_SYSTEM_OUT=2;INIT=RUNSCRIPT FROM 'classpath:schema.sql'\\;");
			dataSource.setDriverClassName("org.h2.Driver");
			dataSource.setUsername("sa");
			dataSource.setPassword("");
		}
		return dataSource.getConnection();
	}
}
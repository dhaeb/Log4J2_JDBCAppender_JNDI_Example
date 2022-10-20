package com.github.dhaeb.db;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class PostgreLogsStoreConnectionFactory
{
	private static BasicDataSource dataSource;

	private PostgreLogsStoreConnectionFactory() {
	}

	public static Connection getConnection() throws SQLException {
		if (dataSource == null) {
			dataSource = new BasicDataSource();
			dataSource.setUrl("jdbc:postgresql://localhost:5432/");
			dataSource.setDriverClassName("org.postgresql.Driver");
			dataSource.setUsername("postgres");
			dataSource.setPassword("changeme");
		}
		return dataSource.getConnection();
	}
}
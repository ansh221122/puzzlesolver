package com.trie.index.implement.db;

import java.sql.Connection;
import java.sql.DriverManager;

import reflection.utility.Provides;



public class mysqlConnectionProvider {
	@Provides
	public Connection buildConnectionMysql() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/reflection", "root", "");
		return connection;
	}
}

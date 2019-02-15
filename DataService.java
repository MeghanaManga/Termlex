package com.noesinformatica.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


public class DataService {

	private static String dbURL = "jdbc:derby://localhost:1527/myDB;create=true;user=me;password=mine";
	private static String tableName = "terms";
	// jdbc Connection
	private static Connection conn = null;
	private static Statement stmt = null;

	@SuppressWarnings("unused")
	private static void createConnection() {

		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Get a connection
		try {
			conn = DriverManager.getConnection(dbURL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected static void saveTerm(Term term) {
		try {
			stmt = conn.createStatement();
			if (null != term && null != term.getTermData() && null != term.getLastUserId()) {
				stmt.execute("insert into " + tableName + " values (" + term.getTermData() + ",'" + term.getLastUserId()
						+ "')");
			}
			stmt.close();

		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
	}

	protected static void getAllTerms() {
		try {
			stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("select * from " + tableName);
			ResultSetMetaData rsmd = results.getMetaData();
			int numberCols = rsmd.getColumnCount();
			for (int i = 1; i <= numberCols; i++) {
				// print Column Names
				System.out.print(rsmd.getColumnLabel(i) + "\t\t");
			}

			System.out.println("\n-------------------------------------------------");

			while (results.next()) {

				String termData = results.getString(1);
				Long lastUserId = results.getLong(2);
				System.out.println(termData + "\t\t" + lastUserId);
			}
			results.close();
			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
	}

}	



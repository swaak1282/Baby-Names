package babyNames;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Driver {

	public static void main(String[] args) {
		final String DATABASE = "jdbc:derby:babynames";
		
		try {
			Connection connection = DriverManager.getConnection(DATABASE);
			
			
			//run list of queries
			ResultSetMetaData query1 = queryDatabase(connection,
													"SELECT NAME "
													+ "FROM babynames "
													+ "WHERE US_STATE = 'MD' AND GENDER = 'F' AND DATE_YEAR = '1995'"
													+ "ORDER BY NUM_BABIES DESC"
													+ "FETCH FIRST 1 ROW ONLY");
			System.out.println(query1);
			
			ResultSetMetaData query2 = queryDatabase(connection,
													"SELECT DATE_YEAR "
													+ "FROM babynames "
													+ "WHERE GENDER = 'M' AND NAME = 'John'"
													+ "ORDER BY NUM_BABIES DESC"
													+ "FETCH FIRST 1 ROW ONLY");
			System.out.println(query2);
			
			ResultSetMetaData query3 = queryDatabase(connection,
													"SELECT DATE_YEAR "
													+ "FROM babynames "
													+ "WHERE GENDER = 'F' AND NAME = 'Rosemary'"
													+ "ORDER BY NUM_BABIES DESC"
													+ "FETCH FIRST 1 ROW ONLY");
			System.out.println(query3);
			
			ResultSetMetaData query4 = queryDatabase(connection,
													"SELECT NAME "
													+ "FROM babynames "
													+ "WHERE US_STATE = 'MD' AND NUM_BABIES > '500'");
			System.out.println(query4);
			
			ResultSetMetaData query5 = queryDatabase(connection,
													"SELECT US_STATE "
													+ "FROM babynames "
													+ "WHERE DATE_YEAR = '2014' AND NAME = 'Xavier' AND GENDER = 'M'"
													+ "ORDER BY NUM_BABIES ASC"
													+ "FETCH FIRST 1 ROW ONLY");
			System.out.println(query5);
			
			ResultSetMetaData query6 = queryDatabase(connection,
													"SELECT US_STATE "
													+ "FROM babynames "
													+ "WHERE DATE_YEAR = '1997' AND NAME = 'Hannah' AND GENDER = 'F'"
													+ "ORDER BY NUM_BABIES DESC"
													+ "FETCH FIRST 1 ROW ONLY");
			System.out.println(query6);
			
			queryDatabase(connection,
							"INSERT INTO babynames (ID, NAME, DATE_YEAR, GENDER, US_STATE, NUM_BABIES)"
							+ "VALUES ('10000000', 'Joseph', '2016', 'M', 'PA', '476'");
			
			queryDatabase(connection,
							"DELETE FROM babynames "
							+ "WHERE ID = '10000000'");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	} //end main

	public static ResultSetMetaData queryDatabase(Connection connection, String query){
		ResultSetMetaData metadata = null;
		try{
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			metadata = resultSet.getMetaData();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return metadata;
	}
}

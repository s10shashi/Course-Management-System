package utility;

import java.sql.Connection;
import java.sql.SQLException;

public class Demo {

	public static void main(String[] args) {
		Connection conn= DButil.provideConnection();
		try {
			System.out.println(conn.getWarnings());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

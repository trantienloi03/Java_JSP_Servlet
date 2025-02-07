package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConnection {
	public Connection cn;
	public void KetNoi() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String url="jdbc:sqlserver://DESKTOP-41HALSU:1433;databaseName=Java_TranTienLoi;user=sa; password=sa";
		cn = DriverManager.getConnection(url);	
	}

}

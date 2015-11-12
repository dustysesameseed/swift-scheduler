package capstone.gwttrial.server;

import capstone.gwttrial.client.login.service.LoginService;
import capstone.gwttrial.shared.FieldVerifier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements
		LoginService {

	@Override
	public Boolean getLoginSuccess(String un, String pw) throws IllegalArgumentException {
		
		//Make sure username/password combo is in database, create session instance server-side		
		 Connection connect = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 Boolean exists = false;
		 
		 try {
			 Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/swiftdb?" + "user=root&password=pass");
		
			
		 preparedStatement = connect.prepareStatement("SELECT EXISTS(SELECT * FROM Users WHERE username='" + un+ "' AND password='"+pw+"');");
		 resultSet = preparedStatement.executeQuery();
		 resultSet.next();
		 exists = resultSet.getBoolean(1);
	} catch (SQLException | ClassNotFoundException e) {
					e.printStackTrace();
		}
		
		return exists;
	}
	

}

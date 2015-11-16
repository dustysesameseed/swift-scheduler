package capstone.gwttrial.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import capstone.gwttrial.client.login.service.LoginService;
import capstone.gwttrial.client.register.service.RegisterService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;


	/**
	 * The server-side implementation of the RPC service.
	 */
	@SuppressWarnings("serial")
	public class RegisterServiceImpl extends RemoteServiceServlet implements
			RegisterService {

		@Override
		public Boolean getRegisterSuccess(String un, String pw) throws IllegalArgumentException {
			
			//Make sure username/password combo is in database, create session instance server-side		
			 Connection connect = null;
			 PreparedStatement preparedStatement = null;
			 ResultSet resultSet = null;
			 Boolean exists = false;
			 Boolean noDatabaseMode = true;
			 
			 try {
				 Class.forName("com.mysql.jdbc.Driver");
				connect = DriverManager.getConnection("jdbc:mysql://localhost/swiftdb?" + "user=root&password=pass");
			
			 preparedStatement = connect.prepareStatement("INSERT INTO Users VALUES ('"+un+"','"+pw+"','normal');");
			 preparedStatement.executeUpdate();
			 preparedStatement = connect.prepareStatement("SELECT EXISTS(SELECT * FROM Users WHERE username='" + un+ "' AND password='"+pw+"');");
			 resultSet = preparedStatement.executeQuery();
			 resultSet.next();
			 exists = resultSet.getBoolean(1);
		} catch (SQLException | ClassNotFoundException e) {
						e.printStackTrace();
			}
			if (noDatabaseMode) exists = true;
			return exists;
		}
	}
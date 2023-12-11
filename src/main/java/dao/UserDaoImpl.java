package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static utils.DBUtils.*;
import pojos.User;

public class UserDaoImpl implements UserDao {
	private Connection cn;
	private PreparedStatement ps1, ps2, ps3,ps4;

	public UserDaoImpl() throws SQLException {
		cn = getConnection();
		ps1 = cn.prepareStatement("select * from users where email=? and password=?");
		ps2 = cn.prepareStatement("insert into users values(default,?,?,?,?,?,?,?)");
		ps3 = cn.prepareStatement("update users set status = ? where id = ?"); 
		ps4 = cn.prepareStatement("delete from users where id =?");

	}

	@Override
	public User authenticateUser(String email, String pass) throws SQLException {
		ps1.setString(1, email);
		ps1.setString(2, pass);
		try (ResultSet rst = ps1.executeQuery()) {
			if (rst.next())
				return new User(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5),
						rst.getDate(6), rst.getBoolean(7), rst.getString(8));
			return null;
		}
	}

	@Override
	public String registerNewUser(User voter) throws SQLException {
		
		ps2.setString(1, voter.getfName());
		ps2.setString(2, voter.getlName());
		ps2.setString(3, voter.geteMail());
		ps2.setString(4, voter.getPassword());
		ps2.setDate(5, voter.getDob());
		ps2.setBoolean(6, false);
		ps2.setString(7,"voter" );
						int row= ps2.executeUpdate();
	 if(row!=1) 
		 return "voter registration failed to insert into DB";
	 	 return	"voter registered successfully...";
		
	}
			

	@Override
	public String updateVotingStatus(User voter) throws SQLException {
		ps3.setBoolean(1, true);
		ps3.setInt(2, voter.getId());
		int row = ps3.executeUpdate();
		if(row==1)
			return "voting status updated succesfully..";
		return "voting status not updated!";
	}

	@Override
	public String deleteVoter(int id) throws SQLException {
		ps4.setInt(1, id);
		int row =ps4.executeUpdate();
		if(row==1)
			return "voter deleted";
		return "Could not delete!";
	}

}

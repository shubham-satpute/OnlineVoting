package dao;

import java.sql.SQLException;

import pojos.User;

public interface UserDao {
	
	User authenticateUser(String email,String pass)throws SQLException;
	
	String registerNewUser(User voter)throws SQLException;

	String updateVotingStatus(User voter)throws SQLException;
	
	String deleteVoter(int id)throws SQLException;
	
}

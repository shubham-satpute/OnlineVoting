package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import pojos.Candidate;

public interface CandidateDao {
	
	List <Candidate> getAllCandidate()throws SQLException;
	List <Candidate> getTop2MaxVotes()throws SQLException;
	String IncrementVotes(int id)throws SQLException;
	Map<String,Integer> partywiseVotes()throws SQLException;
	
}

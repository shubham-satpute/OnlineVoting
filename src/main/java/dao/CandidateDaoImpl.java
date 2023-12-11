package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static utils.DBUtils.*;
import pojos.Candidate;
import pojos.User;

public class CandidateDaoImpl implements CandidateDao {

	private Connection cn;
	private PreparedStatement ps1, ps2, ps3, ps4;

	public CandidateDaoImpl() throws SQLException {
		cn = getConnection();
		ps1 = cn.prepareStatement("select * from candidates");
		ps2 = cn.prepareStatement("select * from candidates order by votes desc limit 2");
		ps3 = cn.prepareStatement("update candidates set votes=votes+1 where id = ?");
		ps4 = cn.prepareStatement("select party, sum(votes)votes from candidates group by party");
	}

	@Override
	public List<Candidate> getAllCandidate() throws SQLException {
		List<Candidate> list = new ArrayList<>();
		try (ResultSet rst = ps1.executeQuery()) {
			while (rst.next()) {
				list.add(new Candidate(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getInt(4)));
			}
		}
		;
		return list;
	}

	@Override
	public List<Candidate> getTop2MaxVotes() throws SQLException {
		List<Candidate> list = new ArrayList<>();
		try (ResultSet rst = ps2.executeQuery()) {
			while (rst.next()) {
				list.add(new Candidate(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getInt(4)));
			}
		}
		;
		return list;
	}

	@Override
	public String IncrementVotes(int id) throws SQLException {
		ps3.setInt(1, id);
		int row =ps3.executeUpdate();
		if(row==1) 
			return "Votes incremented Successfully";
		return "Not able to increment Votes";
	}

	@Override
	public Map<String, Integer> partywiseVotes() throws SQLException {
		Map<String, Integer> map =new HashMap<>();
	
	try(ResultSet rst= ps4.executeQuery()){
		while(rst.next()) {
			map.put(rst.getString(1), rst.getInt(2));
		}
	}
	return map;	
}

}

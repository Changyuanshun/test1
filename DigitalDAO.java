package telecom.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import telecom.entity.Digital;

public class DigitalDAO extends FactoryDataBase{

	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//查询所有数码产品的信息
	public ArrayList Querydigital() {
		String sql = "select * from Digital";
		ArrayList list = new ArrayList();
		int id = 1;
		try {
			pstmt = getConn().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String dtype = rs.getString("dtype");
				String dname = rs.getString("dname");
				int needscore = rs.getInt("needscore");
				int i = id ++;
			Digital	dig = new Digital(dtype,dname,needscore,i);
			list.add(dig);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs,pstmt,null);
		}
		return list;
	}
	
	public Digital QueryDigitalByDtype(String dtype) {
		String sql = "select * from Digital where dtype = ?";
		Digital d = new Digital();
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setString(1, dtype);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				d.setDname(rs.getString("dname"));
				d.setNeedscore(rs.getInt("needscore"));
				d.setDtype(dtype);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(rs, pstmt, null);
		}
		return d;
	}
}

package telecom.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import telecom.entity.Digital;
import telecom.entity.Digitalexchange;
import telecom.entity.Userbill;

public class DigitalexchangeDAO extends FactoryDataBase{

	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public int digitalex() {
		String sql = "select * from digitalexchange";
		int count = 0;
		ArrayList list = new ArrayList();
		try {
			pstmt = getConn().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				count++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs,pstmt,null);
		}
		return count;
	}
	
	public ArrayList Querydigitalex(int begin, int count) {
		String sql = "select tel,dtype from digitalexchange order by tel limit ?,? ";
		ArrayList list = new ArrayList();
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setInt(1,begin);
			pstmt.setInt(2,count);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String tel = rs.getString("tel");
				String dtype = rs.getString("dtype");
				Digitalexchange	de = new Digitalexchange();
				de.setTel(tel);
				de.setDtype(dtype);
				list.add(de);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs,pstmt,null);
		}
		
		return list;
	}

	
	public void AddDigexchange(String tel,String dtype) {
		String sql = "insert into Digitalexchange(tel,dtype) values(?,?)";
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setString(1,tel);
			pstmt.setString(2,dtype);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(null,pstmt,null);
		}
	}
	
	public ArrayList QueryDigitalByDtype(String tel) {
		
		String sql = "select * from Digitalexchange where tel = ?";
		ArrayList list = new ArrayList();
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setString(1, tel);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String dtype = rs.getString("dtype");
				Digitalexchange de = new Digitalexchange(tel,dtype);
				list.add(de);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(rs, pstmt, null);
		}
		return list;
	}
	
}

package telecom.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import telecom.entity.Flowbag;

public class FlowbagDAO extends FactoryDataBase{

	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public ArrayList QueryFlowbag() {
		String sql = "select * from Flowbag";
		ArrayList list = new ArrayList();
		try {
			pstmt = getConn().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String fid = rs.getString("fid");
				int ftotal = rs.getInt("ftotal");
				int fmoney = rs.getInt("fmoney");
				Flowbag fb = new Flowbag(fid,ftotal,fmoney);
				list.add(fb);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(rs, pstmt, null);
		}
		return list;
	}
	public Flowbag QueryFlowbagByFid(String fid) {
		String sql = "select * from Flowbag where fid = ?";
		Flowbag fb = null;
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setString(1, fid);
			rs = pstmt.executeQuery();
			//处理结果集：把结果集中的数据导入到list集合中
			if(rs.next()) {
				int ftotal = rs.getInt("ftotal");
				int fmoney = rs.getInt("fmoney");
				fb = new Flowbag(fid,ftotal,fmoney);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(rs, pstmt, null);
		}
		return fb;
	}
	
	
	/**
	 * 流量增
	 */
	public void addflowbag(String fid, int ftotal, int fmoney) {
		String sql = "insert into flowbag values(?,?,?)";
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setString(1, fid);
			pstmt.setInt(2, ftotal);
			pstmt.setInt(3, fmoney);
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(rs, pstmt, null);
		}
	}
	/**
	 * 流量删
	 */
	public void delflowbag(String fid) {
		String sql = "delete from flowbag where fid = ?";
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setString(1,fid );
			pstmt.executeUpdate();	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(null, pstmt, null);
		}
	}
	/**
	 * 改流量包
	 */
	public void updateflowbag(int ftotal, int fmoney, String fid) {
		String sql = "update flowbag set ftotal = ?, fmoney = ? where fid = ?";
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setInt(1, ftotal);
			pstmt.setInt(2, fmoney);
			pstmt.setString(3,fid );
			pstmt.executeUpdate();	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(null, pstmt, null);
		}
	}
	
	public boolean JudgeFid(String fid) {
		String sql = "select * from flowbag where fid = ?";
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setString(1,fid );
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(rs, pstmt, null);
		}
		return true;
	}

}

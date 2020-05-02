package telecom.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import telecom.entity.NoFeeUser;
import telecom.entity.Userbill;
import telecom.entity.Useryue;

public class UseryueDAO extends FactoryDataBase{

	private PreparedStatement pstmt;
	private ResultSet rs;
	public Useryue QueryUseryueByTel(String tel) {
		String sql = "select * from Useryue where tel = ?";
		Useryue uy = null;
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setString(1, tel);
			rs = pstmt.executeQuery();
			//处理结果集：把结果集中的数据导入到list集合中
			if(rs.next()) {
				int yue = rs.getInt("yue");
				int restflow = rs.getInt("restflow");
				int resttime = rs.getInt("resttime");
				uy = new Useryue(tel,yue,restflow,resttime);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(rs, pstmt, null);
		}
		return uy;
	}
	
	public void Updateyue(String tel,int yue,int restflow) {
		String sql = "update Useryue set yue = ?,restflow = ? where tel = ?";
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setInt(1, yue);
			pstmt.setInt(2, restflow);
			pstmt.setString(3,tel);
			pstmt.executeUpdate();	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(null, pstmt, null);
		}
	}
	
	public void Updateyue(String tel,int yue) {
		String sql = "update Useryue set yue = ? where tel = ? ";
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setInt(1, yue);
			pstmt.setString(2,tel);
			pstmt.executeUpdate();	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(null, pstmt, null);
		}
	}
	
	public ArrayList FYnofeeuser(int begin, int count) {
		String sql = "select * from qianfeiuser order by yue asc limit ?,?";
		ArrayList list = new ArrayList();
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setInt(1, begin);
			pstmt.setInt(2, count);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String tel = rs.getString("tel");
				String name = rs.getString("name");
				int yue = rs.getInt("yue");
				
				NoFeeUser nfu = new NoFeeUser(tel,name,yue);
				list.add(nfu);
			}	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(rs, pstmt, null);
		}
		return list;
	}
	public ArrayList QueryNoFeeUser() {
		String sql = "select * from qianfeiuser order by yue asc";
		ArrayList list = new ArrayList();
		try {
			pstmt = getConn().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String tel = rs.getString("tel");
				String name = rs.getString("name");
				int yue = rs.getInt("yue");
				
				NoFeeUser nfu = new NoFeeUser(tel,name,yue);
				list.add(nfu);
			}	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(rs, pstmt, null);
		}
		return list;
	}
	


	/**
	 * 管理员删除、、useryuedao
	 * @param tel
	 */
	public void addeletealluseryue(String tel) {
		String sql = "delete FROM useryue where tel = ?";
		
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setString(1, tel);
		
			 pstmt.executeUpdate();
			//处理结果集：把结果集中的数据导入到list集合中
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(null, pstmt, null);
		}
		
	}
	
	public void AddUserinfo(String tel) {
		String sql = "insert into Useryue values(?,0,0,0)";
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setString(1,tel );
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(null, pstmt, null);
		}
	}
	
}

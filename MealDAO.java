package telecom.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import telecom.entity.Digital;
import telecom.entity.Meal;
import telecom.entity.Usermeal;

public class MealDAO extends FactoryDataBase{

	private PreparedStatement pstmt;
	private ResultSet rs;
	public ArrayList QueryMeal() {
		String sql = "select * from meal";
		ArrayList list = new ArrayList();
		try {
			pstmt = getConn().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String mid = rs.getString("mid");
				int mealfee = rs.getInt("mealfee");
				int flow = rs.getInt("flow");
				int time = rs.getInt("time");
			Meal m = new Meal(mid,mealfee,flow,time);
			list.add(m);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs,pstmt,null);
		}
		return list;
	}
	public Usermeal queryBytelUsermeal(String tel) {
		String sql = "select * from Usermeal where tel = ?";
		Usermeal um = null;
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setString(1, tel);
			rs = pstmt.executeQuery();
			//处理结果集：把结果集中的数据导入到list集合中
			if(rs.next()) {
				String mid = rs.getString("mid");
				um = new Usermeal(tel,mid);

			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(rs, pstmt, null);
		}
		return um;
	}
	
	public void UpdateMeal(String mid,int mealfee,int flow,int time) {
		String sql = "update meal set mealfee = ?,flow = ?,time = ? where mid = ?";
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setInt(1, mealfee);
			pstmt.setInt(2, flow);
			pstmt.setInt(3, time);
			pstmt.setString(4, mid);
			pstmt.executeUpdate();	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(null, pstmt, null);
		}
	}
	
	public void DeleteMeal(String mid) {
		String sql = "delete FROM meal where mid = ?";
		
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setString(1, mid);
		
			pstmt.executeUpdate();

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(null, pstmt, null);
		}
	}
	public boolean JudgeMid(String mid) {
		String sql = "select * from meal where mid = ?";
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setString(1,mid );
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
	
	public void AddMeal(String mid,int mealfee,int flow,int time) {
		String sql = "insert into meal values(?,?,?,?)";
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setString(1,mid );
			pstmt.setInt(2,mealfee );
			pstmt.setInt(3,flow );
			pstmt.setInt(4,time );
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(null, pstmt, null);
		}
	}
}

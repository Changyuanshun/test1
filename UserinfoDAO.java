package telecom.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import telecom.entity.Aduserinfo;
import telecom.entity.Friendstel;
import telecom.entity.Userbill;
import telecom.entity.Userinfo;
import telecom.entity.Useryue;

public class UserinfoDAO extends FactoryDataBase{
	
	private PreparedStatement pstmt;
	private ResultSet rs;

	/**
	 * 查找亲情号功能
	 */
   public ArrayList QueryTag(int tag, String tel) {
	   String sql = "select tel,name FROM userinfo WHERE tag = ? and tel != ?";
	   ArrayList list = new ArrayList();
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setInt(1, tag);
			pstmt.setString(2, tel);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String Tel = rs.getString("tel");
				String name = rs.getString("name");
                Userinfo info = new Userinfo();
                info.setName(name);
                info.setTel(Tel);
                list.add(info);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(rs, pstmt, null);
		}
		return list;
   }
   
   //查tag
   public int QueryUserTagByTel(String tel) {
		String sql = "select tag from Userinfo where tel = ?";
		int tag = 0;
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setString(1, tel);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				tag = rs.getInt("tag");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(rs, pstmt, null);
		}
		return tag;
	}
   
//登陆
	public boolean login(String tel, String pwd, int level) {
		String sql = "select * from Userinfo where tel = ? and pwd = ? and level = ?";
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setString(1,tel );
			pstmt.setString(2,pwd );
			pstmt.setInt(3,level );
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(rs, pstmt, null);
		}
		return false;
	}
	
	//积分查询	
	public int QueryUserScoreByTel(String tel) {
		String sql = "select score from Userinfo where tel = ?";
		int score = 0;
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setString(1, tel);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				score = rs.getInt("score");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(rs, pstmt, null);
		}
		return score;
	}
	
	//更新积分
	public void Updateyue(String tel,int score) {
		String sql = "update Userinfo set score = ? where tel = ?";
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setInt(1, score);
			pstmt.setString(2, tel);
			pstmt.executeUpdate();	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(null, pstmt, null);
		}
	}
	//查询信息
	public Userinfo QueryUserInfoByTel(String tel) {
		String sql = "select * from Userinfo where tel = ?";
		Userinfo ui = new Userinfo();
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setString(1, tel);
			rs = pstmt.executeQuery();
			//处理结果集：把结果集中的数据导入到list集合中
			while(rs.next()) {
				String name = rs.getString("name");
				String pwd = rs.getString("pwd");
				String sex = rs.getString("sex");
				String address = rs.getString("address");
				int score = rs.getInt("score");
				ui.setTel(tel);
				ui.setName(name);
				ui.setPwd(pwd);
				ui.setSex(sex);
				ui.setAddress(address);
				ui.setScore(score);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(rs, pstmt, null);
		}
		return ui;
	}
	//修改信息
	public void Updateinfo(String tel,String name,String pwd,String sex,String address) {
		String sql = "update Userinfo set name = ?,pwd= ?,sex = ?,address = ? where tel = ?";
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, pwd);
			pstmt.setString(3, sex);
			pstmt.setString(4, address);
			pstmt.setString(5, tel);
			pstmt.executeUpdate();	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(null, pstmt, null);
		}
	}
	
	
	//扣积分
	public void  updatescore(int score,int needscore, String tel) {
		   String sql = "update userinfo,digital set userinfo.score = userinfo.score - digital.needscore where tel = ?";
			try {
				pstmt = getConn().prepareStatement(sql);
				pstmt.setString(1, tel);
				pstmt.executeUpdate();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				close(null, pstmt, null);
			}
	   }
	
	
	/**
	 * 查询总记录数，用于用户信息查询
	 * @return
	 */
	public ArrayList Queryuser() {
		String sql = "select * from userinfo ";
		ArrayList list = new ArrayList();
		try {
			pstmt = getConn().prepareStatement(sql);
			rs = pstmt.executeQuery();
			//处理结果集：把结果集中的数据导入到list集合中
			while(rs.next()) {
				
				String tel = rs.getString("tel");
				
				Userinfo info = new Userinfo();
			    info.setTel(tel);
			    list.add(info);
				
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
	 * 查询用户信息，单表查询
	 * @return
	 */
	public ArrayList QueryUserinfo(int begin, int count) {
		String sql = "select * from alluserinfo limit ?,? ";
		ArrayList list = new ArrayList();
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setInt(1, begin);
			pstmt.setInt(2, count);
			rs = pstmt.executeQuery();
			//处理结果集：把结果集中的数据导入到list集合中
			while(rs.next()) {
				
				String tel = rs.getString("tel");
				String name = rs.getString("name");
				String sex = rs.getString("sex");
				String address = rs.getString("address");
				int score = rs.getInt("score");
				int yue = rs.getInt("yue");
				Aduserinfo info = new Aduserinfo();
			    info.setTel(tel);
			    info.setName(name);
			    info.setSex(sex);
			    info.setAddress(address);
			    info.setScore(score);
			    info.setYue(yue);
			    list.add(info);
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
	 * 查询所有用户信息方法，多表连接
	 */
	public ArrayList QueryAllUser(int pageNum,int count) {
		
		String sql = "select * from userinfo info left outer join useryue yue on info.tel = yue.tel limit ?,? ";
		ArrayList list = new ArrayList();
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setInt(1, pageNum);
			pstmt.setInt(2, count);
			rs = pstmt.executeQuery();
			//处理结果集：把结果集中的数据导入到list集合中
			while(rs.next()) {
				
				String tel = rs.getString("tel");
				String name = rs.getString("name");
				String pwd = rs.getString("pwd");
				String sex = rs.getString("sex");
				String address = rs.getString("address");
				int score = rs.getInt("score");
				int yue = rs.getInt("yue");
				int restflow = rs.getInt("restflow");
				int resttime = rs.getInt("resttime");
				Userinfo info = new Userinfo();
			    info.setTel(tel);
			    info.setName(name);
			    info.setPwd(pwd);
			    info.setSex(sex);
			    info.setAddress(address);
			    info.setScore(score);
			    //需要在类userinfo中添加对象yue,restflow,resttime
			    info.setYue(yue);
			    info.setRestflow(restflow);
			    info.setResttime(resttime);
			    list.add(info);
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
	 * 查询用户积分排名
	 * @param tel
	 * @param name
	 * @param score
	 * @return
	 */
	
	public ArrayList QueryUserscore() {
		   String sql = "select tel,name,score from Userinfo where tel !=1111 order by score desc";
		   ArrayList qlist = new ArrayList();
			try {
				pstmt = getConn().prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					String Tel = rs.getString("tel");
					String Name = rs.getString("name");
					int Score = rs.getInt("score");
	                Userinfo uf = new Userinfo();
	                uf.setName(Name);
	                uf.setTel(Tel);
	                uf.setName(Name);
	                uf.setScore(Score);
	                qlist.add(uf);
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				close(rs, pstmt, null);
			}
			return qlist;
	   }
	
	/**
	 * 积分排名分页查询
	 * @param pageNum
	 * @param count
	 * @return
	 */
	public ArrayList QueryUserscore(int pageNum,int count) {
		String sql = "select tel,name,score from Userinfo where tel!=1111 order by score desc limit ?,?";
		ArrayList list = new ArrayList();
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setInt(1, pageNum);
			pstmt.setInt(2, count);
			rs = pstmt.executeQuery();
			//处理结果集：把结果集中的数据导入到list集合中
			while(rs.next()) {
				String tel = rs.getString("tel");
				String name = rs.getString("name");
				int score = rs.getInt("score");
				Userinfo uf = new Userinfo(tel, name,score);
				list.add(uf);
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
	 * 管理员删除用户信息
	 * @return
	 */
	public void addeletealluserinfo(String tel) {
		String sql = "delete FROM userinfo where tel = ?";
		
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setString(1, tel);
		
			pstmt.executeUpdate();

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(null, pstmt, null);
		}
	}
	
	public ArrayList adQueryFYfriend(int begin, int count) {
		String sql = "SELECT * FROM friendstel limit ?,?";
		ArrayList list = new ArrayList();
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setInt(1, begin);
			pstmt.setInt(2, count);
			rs = pstmt.executeQuery();
			//处理结果集：把结果集中的数据导入到list集合中
			while(rs.next()) {
				String name = rs.getString("name");
				String tel = rs.getString("tel");
				int tag = rs.getInt("tag");
				Friendstel ftag= new Friendstel(name,tel,tag);
				list.add(ftag);
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
	 * 查询友情号
	 * @return
	 */
	public ArrayList adqueryfriend() {
		String sql = "SELECT * FROM friendstel";
		ArrayList list = new ArrayList();
		try {
			pstmt = getConn().prepareStatement(sql);
		
			rs = pstmt.executeQuery();
			//处理结果集：把结果集中的数据导入到list集合中
			while(rs.next()) {
				String name = rs.getString("name");
				String tel = rs.getString("tel");
				int tag = rs.getInt("tag");
				
				Friendstel ftag= new Friendstel(name,tel,tag);
				list.add(ftag);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(rs, pstmt, null);
		}
		return list;
	}
	
	public Aduserinfo AdqueryUserinfobyTel(String tel) {
		String sql = "SELECT * FROM alluserinfo where tel = ? ";
		Aduserinfo ui = new Aduserinfo();
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setString(1, tel);
			rs = pstmt.executeQuery();
			//处理结果集：把结果集中的数据导入到list集合中
			while(rs.next()) {
				String name = rs.getString("name");
				String sex = rs.getString("sex");
				String address = rs.getString("address");
				int score = rs.getInt("score");
				int yue = rs.getInt("yue");
				ui = new Aduserinfo(tel,name,sex,address,score,yue);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(null, pstmt, null);
		}
		return ui;
	}
	
	public void AdUpdateinfo(String tel,String name,String sex,String address) {
		String sql = "update Userinfo set name = ?,sex = ?,address = ? where tel = ?";
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, sex);
			pstmt.setString(3, address);
			pstmt.setString(4, tel);
			pstmt.executeUpdate();	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(null, pstmt, null);
		}
	}
	public void AddUserinfo(String tel,String name,String pwd,String sex,String address) {
		String sql = "insert into Userinfo values(?,?,?,?,?,1,-1,0)";
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setString(1,tel );
			pstmt.setString(2,name );
			pstmt.setString(3,pwd );
			pstmt.setString(4,sex );
			pstmt.setString(5,address );
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(null, pstmt, null);
		}
	}
	
	
	public boolean JudgeTel(String tel) {
		String sql = "select * from Userinfo where tel = ?";
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setString(1,tel );
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


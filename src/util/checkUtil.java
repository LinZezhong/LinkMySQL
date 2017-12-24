package util;

import java.sql.ResultSet;

import LinkUtil.LinkMySQL;

public class checkUtil{
	private static ResultSet rs;
	public static int validLogin(String userid,String password){
		int loginPer=0;
		LinkMySQL linkMySQL = new LinkMySQL();
		linkMySQL.Link();
		String sql="select * from UserInfo where User_Id='"+userid+"'";
		rs=linkMySQL.search(sql);
		try{
			if(rs.next() && rs.getString("User_Pwd").equals(password)){
				loginPer=1;	
			}
			else{
				loginPer=0;
			}
			System.out.println("»¶Ó­Äú£º"+userid);
			return loginPer;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return loginPer;
	}
}

package LinkUtil;

import java.sql.*;
public class LinkMySQL {
	private Connection connect;
	private Statement stmt;
	private ResultSet rs=null;
	public void Link(){
		try {
		     Class.forName("com.mysql.jdbc.Driver");
		     System.out.println("Success loading Mysql Driver!");
		    }
		    catch (Exception e) {
		      System.out.print("Error loading Mysql Driver!");
		      e.printStackTrace();
		    }
		    try {
		      connect = DriverManager.getConnection(
		          "jdbc:mysql://localhost:3308/comunity","root","1234");
		           //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
		      System.out.println("Success connect Mysql server!");		     
		    }
		    catch (Exception e) {
		    
		      e.printStackTrace();
		    }
	}
	public ResultSet search(String sql){
		try{
			stmt = connect.createStatement();
		    rs = stmt.executeQuery(sql);   
		}
		catch(Exception e){
			System.out.print("get data error!");
			e.printStackTrace();
		}
		return rs;
	}
	public int regist(String userid,String password)  
	{  
	    int i=0;  
	    String sql="insert into UserInfo(User_Id,User_Pwd) values(?,?)";       	  
	    try{  
	        PreparedStatement preStmt =connect.prepareStatement(sql);  
	        preStmt.setString(1,userid);  
	        preStmt.setString(2,password);//或者：preStmt.setInt(1,值);  
	        i=preStmt.executeUpdate();  
	    }  
	    catch (SQLException e)  
	    {  
	        e.printStackTrace();  
	    }  
	    return i;//返回影响的行数，1为执行成功  
	}
	public int Org_Apply(String User_Id,String Org_Id,String reason,int Department_Id){
		int i=0;
		String sql = "insert into Org_Apply(User_Id,Org_Id,Org_Apply_Reason,Org_Department_Id) values(?,?,?,?)";
		try{
			PreparedStatement preStmt =connect.prepareStatement(sql);  
	        preStmt.setString(1,User_Id);  
	        preStmt.setString(2,Org_Id);//或者：preStmt.setInt(1,值);  
	        preStmt.setString(3,reason);
	        preStmt.setInt(4,Department_Id);
	        i=preStmt.executeUpdate();  
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return i;
	}
	public boolean updateUserInfo(String sql) {  
        try {  
        	PreparedStatement preStmt = connect.prepareStatement(sql);  
            preStmt.executeUpdate();  
            return true;  
        } catch (SQLException e) {  
            System.out.println("插入数据库时出错：");  
            e.printStackTrace();  
        } catch (Exception e) {  
            System.out.println("插入时出错：");  
            e.printStackTrace();  
        }  
        return false;  
    }
	public boolean updateOrgApply(String sql) {  
        try {  
        	PreparedStatement preStmt = connect.prepareStatement(sql);  
            preStmt.executeUpdate();  
            return true;  
        } catch (SQLException e) {  
            System.out.println("插入数据库时出错：");  
            e.printStackTrace();  
        } catch (Exception e) {  
            System.out.println("插入时出错：");  
            e.printStackTrace();  
        }  
        return false;  
    }
}

package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import LinkUtil.LinkMySQL;

public class getUserInfoServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public getUserInfoServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ResultSet rs;
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type","text/html;charset=UTF-8");
		String User_Id=request.getParameter("User_Id");
		LinkMySQL linkMySQL = new LinkMySQL();
		linkMySQL.Link();
		String sql="select * from UserInfo left outer join College on UserInfo.Col_Id=College.Col_Id where User_Id='"+User_Id+"'";
		rs=linkMySQL.search(sql);
		JSONObject jsonObj =new JSONObject();
		try{
				rs.next();
				try{
				jsonObj.put("User_Id",rs.getString("User_Id"));
				if(rs.getString("User_Name")==null){
					jsonObj.put("User_Name","Œ¥…Ë÷√");
				}
				else{
					jsonObj.put("User_Name",rs.getString("User_Name"));
				}
				if(rs.getString("User_Sex")==null){
					jsonObj.put("User_Sex","Œ¥…Ë÷√");
				}
				else{
					jsonObj.put("User_Sex",rs.getString("User_Sex"));
				}
				
				if(rs.getString("Col_Name")==null){
					jsonObj.put("User_Col","Œ¥…Ë÷√");
				}
				else{
					jsonObj.put("User_Col",rs.getString("Col_Name"));
				}
				if(rs.getString("User_Like")==null){
					jsonObj.put("User_Like","Œ¥…Ë÷√");
				}
				else{
					jsonObj.put("User_Like",rs.getString("User_Like"));
				}
				if(rs.getString("User_Phone")==null){
					jsonObj.put("User_Pho","Œ¥…Ë÷√");
				}
				else{
					jsonObj.put("User_Pho",rs.getString("User_Phone"));
				}
				response.getWriter().println(jsonObj.toString());
				}
				catch(JSONException ex)
				{
					ex.printStackTrace();
				}

			System.out.println(jsonObj.toString());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}

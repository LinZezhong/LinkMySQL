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

public class getMyOrgServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public getMyOrgServlet() {
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
		String sql = "select * from org_person_org_depart_view where User_Id='"+User_Id+"' and Org_Level_Id>=3";
		LinkMySQL linkMySQL=new LinkMySQL();
		linkMySQL.Link();
		rs=linkMySQL.search(sql);
		try{
			JSONObject jsonObj = new JSONObject();
			int i=0;
			try{
				while(rs.next()){
					JSONObject json = new JSONObject();
					json.put("Org_Id", rs.getString("Org_Id"));
					json.put("Org_Name",rs.getString("Org_Name"));
					json.put("Org_Department_Id",rs.getString("Org_Department_Id"));
					json.put("Org_Department_Name",rs.getString("Org_Department_Name"));
					json.put("Org_Intro", rs.getString("Org_Intro"));
					jsonObj.put("MyOrg_Depart"+String.valueOf(i), json);
					i++;
				}
				response.getWriter().println(jsonObj.toString());
				System.out.println(jsonObj.toString());
				
			}
			catch(JSONException e){
				e.printStackTrace();
			}
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

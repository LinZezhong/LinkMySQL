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

public class getMyApplyServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public getMyApplyServlet() {
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
		System.out.println(User_Id);
		String sql="select * from User_Apply_Depart_View where User_Id='"+User_Id+"'";
		LinkMySQL linkMySQL=new LinkMySQL();
		linkMySQL.Link();
		rs=linkMySQL.search(sql);
		try{
			try{
				JSONObject jsonObj = new JSONObject();
				int i=0;
				while(rs.next()){
					JSONObject json = new JSONObject();
					json.put("Org_Department_Id", rs.getString("Org_Department_Id"));
					json.put("Org_Department_Name", rs.getString("Org_Department_Name"));
					json.put("State", rs.getString("State"));
					json.put("Org_Name", rs.getString("Org_Name"));
					jsonObj.put("Info"+String.valueOf(i), json);
					i++;
				}
				System.out.println(jsonObj.toString());
				response.getWriter().println(jsonObj.toString());
			}
			catch(JSONException e){
				
			}
		}
		catch(Exception e)
		{
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

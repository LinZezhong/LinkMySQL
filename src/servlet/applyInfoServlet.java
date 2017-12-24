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

public class applyInfoServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public applyInfoServlet() {
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
		String Org_Department_Id=request.getParameter("Org_Department_Id");
		String sql="select * from User_Info_Apply_Info_View where User_Id='"
				+User_Id+"' and Org_Department_Id='"+Org_Department_Id+"'";
		LinkMySQL linkMySQL = new LinkMySQL();
		linkMySQL.Link();
		rs=linkMySQL.search(sql);
		try{
			JSONObject jsonObj = new JSONObject();
			try{
				if(rs.next()){
					jsonObj.put("User_Name", rs.getString("User_Name"));
					jsonObj.put("User_Sex", rs.getString("User_Sex"));
					jsonObj.put("Org_Apply_Reason",rs.getString("Org_Apply_Reason"));
					jsonObj.put("User_Phone", rs.getString("User_Phone"));
					jsonObj.put("User_Like", rs.getString("User_Like"));
					jsonObj.put("Col_Name", rs.getString("Col_Name"));
					jsonObj.put("message", "³É¹¦");
				}
				else{
					jsonObj.put("message", "Ê§°Ü");
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

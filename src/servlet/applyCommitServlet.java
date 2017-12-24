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

public class applyCommitServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public applyCommitServlet() {
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
		String Org_Id=request.getParameter("Org_Id");
		String Org_Department_Name=request.getParameter("Org_Department_Name");
		String Org_Apply_Reason = request.getParameter("Org_Apply_Reason");
		LinkMySQL linkMySQL=new LinkMySQL();
		linkMySQL.Link();
		String sql="select * from Org_Department where Org_Id='"+Org_Id+"' and Org_Department_Name='"+Org_Department_Name+"'";
		rs=linkMySQL.search(sql);
		JSONObject json = new JSONObject();
		try{
			rs.next();
			int Org_Department_Id=rs.getInt("Org_DePartment_Id");
			System.out.println(Org_Department_Id);
			int success=linkMySQL.Org_Apply(User_Id, Org_Id, Org_Apply_Reason, Org_Department_Id);
			try{
				if(success>0){
					json.put("message","申请已提交成功");
				}
				else{
					json.put("message", "申请失败");
				}
				response.getWriter().println(json.toString());
				System.out.println(json.toString());
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

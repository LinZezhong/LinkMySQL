package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import LinkUtil.LinkMySQL;

public class chanegUserInfoServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public chanegUserInfoServlet() {
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
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type","text/html;charset=UTF-8");
		String User_Id=request.getParameter("User_Id");
		String User_Name=request.getParameter("User_Name");
		String User_Sex=request.getParameter("User_Sex");
		String Col_Name=request.getParameter("User_Col");
		String User_Like=request.getParameter("User_Like");
		String User_Phone=request.getParameter("User_Pho");
		String sql="update UserInfo set User_Name='"+User_Name
				+"',User_Sex='"+User_Sex+"',User_Like='"+User_Like+"',User_Phone='"+
				User_Phone+"',Col_Id=(select Col_Id from College where Col_Name='"
				+Col_Name+"')" +" where User_Id='"+User_Id+"'";
		LinkMySQL linkMySQL = new LinkMySQL();
		linkMySQL.Link();
		Boolean success=linkMySQL.updateUserInfo(sql);
		JSONObject jsonObj=new JSONObject();
		try{
			if(success){
				try{
					jsonObj.put("message","修改成功");
					response.getWriter().println(jsonObj.toString());
				}
				catch(JSONException e){
					e.printStackTrace();
				}
			}
			else{
				try{
					jsonObj.put("message","修改失败");
					response.getWriter().println(jsonObj.toString());
				}
				catch(JSONException e){
					e.printStackTrace();
				}
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

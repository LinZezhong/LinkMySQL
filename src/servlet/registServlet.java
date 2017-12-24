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

public class registServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public registServlet() {
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type","text/html;charset=UTF-8");
		String userid=request.getParameter("User_Id");
		String password=request.getParameter("User_Pwd");
		LinkMySQL linkMySQL = new LinkMySQL();
		linkMySQL.Link();
		int i = linkMySQL.regist(userid, password);
		System.out.println("×¢²áÖÐ");
		JSONObject jsonObj =new JSONObject();
		try{
			if(i==0){
				try{
					jsonObj.put("success","true");
					response.getWriter().println(jsonObj.toString());
				}
				catch(JSONException ex)
				{
					ex.printStackTrace();
				}
				
			}
			else{
				try{
					jsonObj.put("success","false");
					response.getWriter().println(jsonObj.toString());
				}
				catch(JSONException ex)
				{
					ex.printStackTrace();
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}	
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
		String userid=request.getParameter("User_Id");
		String password=request.getParameter("User_Pwd");
		LinkMySQL linkMySQL = new LinkMySQL();
		linkMySQL.Link();
		int i = linkMySQL.regist(userid, password);
		System.out.println(userid+"×¢²áÖÐ");
		JSONObject jsonObj =new JSONObject();
		try{
			if(i==1){
				try{
					jsonObj.put("success","true");
					response.getWriter().println(jsonObj.toString());
				}
				catch(JSONException ex)
				{
					ex.printStackTrace();
				}
				
			}
			else{
				try{
					jsonObj.put("success","ÕËºÅÒÑ´æÔÚ");
					response.getWriter().println(jsonObj.toString());
				}
				catch(JSONException ex)
				{
					ex.printStackTrace();
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

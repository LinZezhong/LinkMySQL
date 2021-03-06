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
import bean.OrgInfo;

public class OrgApplyServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public OrgApplyServlet() {
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
		OrgInfo orgInfo = new OrgInfo();
		String User_Id=request.getParameter("User_Id");
		String Org_Apply_Reason=request.getParameter("Org_Apply_Reason");
		String Org_Id=request.getParameter("Org_Id");
		String Org_Depart_Name=request.getParameter("Org_Depart_Name");
		String sql ="select * from Organization";
		LinkMySQL linkMySQL=new LinkMySQL();
		linkMySQL.Link();
		rs=linkMySQL.search(sql);JSONObject jsonObjAll=new JSONObject();
		try{
			
			try{
				int i=0;
				
				while(rs.next()){
					orgInfo.Org_Id=rs.getString("Org_Id");
					orgInfo.Org_Name=rs.getString("Org_Name");
					orgInfo.Org_Level=rs.getString("Org_Level");
					orgInfo.Org_Col=rs.getString("Org_Id");
					JSONObject jsonObj = new JSONObject();
					jsonObj.put("Org_Id", orgInfo.Org_Id);
					jsonObj.put("Org_Name", orgInfo.Org_Name);
					jsonObj.put("Org_Level", orgInfo.Org_Level);
					jsonObj.put("Org_Col", orgInfo.Org_Col);
					System.out.println(jsonObj.toString());
					jsonObjAll.put("OrgInfo"+String.valueOf(i++),jsonObj);
					System.out.println("1111");
				}
//				jsonArr
				response.getWriter().println(jsonObjAll.toString());
				System.out.println(jsonObjAll.toString());
				
				
			}
			catch(JSONException e){
				e.printStackTrace();
			}
			
		}
		catch(Exception e){
			
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

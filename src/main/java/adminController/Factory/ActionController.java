package adminController.Factory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import factory.Factory;
import factory.FactoryBO;



/**
 * Servlet implementation class ActionController
 */
@WebServlet("/ActionController")
public class ActionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActionController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id;
			String action = "";
			FactoryBO fBO = new FactoryBO();
			if(request.getParameter("action") != null || request.getParameter("id") != null) {
				action = request.getParameter("action");
				id =request.getParameter("id")!=null? Integer.parseInt(request.getParameter("id")):0;
				if(action.equals("add")) {
					RequestDispatcher rd = request.getRequestDispatcher("admin/Factory/AddFactory.jsp");
					rd.forward(request, response);
				}
				else if(action.equals("delete")) {
					fBO.deleteFactory(id);
					response.sendRedirect("Admin_FactoryController");
					return;
				}
				else if(action.equals("edit")) {
					Factory factory = fBO.getFactoryById(id);
					request.setAttribute("factory", factory);
					RequestDispatcher rd = request.getRequestDispatcher("admin/Factory/EditFactory.jsp");
					rd.forward(request, response);
				}
			}
		}
		catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

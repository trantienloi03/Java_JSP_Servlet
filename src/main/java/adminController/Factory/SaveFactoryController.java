package adminController.Factory;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factory.Factory;
import factory.FactoryBO;

/**
 * Servlet implementation class SavaFactoryController
 */
@WebServlet("/SaveFactoryController")
public class SaveFactoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveFactoryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String tenloaihang = request.getParameter("factoryName") != null ? request.getParameter("factoryName") : "";
			Factory factory  = new Factory(0, tenloaihang);
			FactoryBO fBO = new FactoryBO();			
			int id = fBO.insertFactory(factory);
			System.out.println(id);
			if(id == -1) {
				String error = "Đã tồn tại";
				request.setAttribute("error", error);
				RequestDispatcher rd = request.getRequestDispatcher("admin/Factory/AddFactory.jsp");
				rd.forward(request, response);
				return;
			}
				RequestDispatcher rd = request.getRequestDispatcher("Admin_FactoryController");
				rd.forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
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

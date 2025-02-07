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
 * Servlet implementation class SaveEditController
 */
@WebServlet("/SaveEditController")
public class SaveEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveEditController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			FactoryBO fBO = new FactoryBO();
			String tenloaihang = request.getParameter("factoryName") != null ? request.getParameter("factoryName") : "";
			int id = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")) : 0;
			Factory factory  = fBO.getFactoryById(id);
			factory.setFactoryName(tenloaihang);
			System.out.println(tenloaihang);			
			int kq = fBO.updateFactory(factory);
			System.out.println(id);
			if(kq == 0) {
				String error = "Đã tồn tại";
				request.setAttribute("error", error);
				RequestDispatcher rd = request.getRequestDispatcher("admin/Factory/EditFactory.jsp");
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

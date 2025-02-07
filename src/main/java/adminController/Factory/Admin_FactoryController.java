package adminController.Factory;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factory.Factory;
import factory.FactoryBO;
import target.TargetBO;

/**
 * Servlet implementation class Admin_FactoryController
 */
@WebServlet("/Admin_FactoryController")
public class Admin_FactoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin_FactoryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {

			FactoryBO fBO = new FactoryBO();
			String indexcurrent = request.getParameter("index");
			int index = 1;
			if(indexcurrent != null) {
				 index = Integer.parseInt(indexcurrent);
				 System.out.println(index);
			}
			int total;
			total = fBO.Count();
			int Page = total/10;
			if(total % 10 !=0)
				Page +=1;
			List<Factory> lstFactory = fBO.getListFactory(index);
			for (Factory factory : lstFactory) {
				System.out.println(factory.toString());
			}
			request.setAttribute("lstFactory", lstFactory);
			request.setAttribute("index", index);
			request.setAttribute("Page", Page);
			RequestDispatcher rd = request.getRequestDispatcher("admin/Factory/Factory.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
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

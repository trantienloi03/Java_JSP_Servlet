package clientController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factory.Factory;
import factory.FactoryBO;
import product.Product;
import product.ProductBO;
import target.Target;
import target.TargetBO;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/Home")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			ProductBO pBO = new ProductBO();
			FactoryBO fBO = new FactoryBO();
			TargetBO tBO  = new TargetBO();
			String indexcurrent = request.getParameter("index");
			int index = 1;
			if(indexcurrent != null) {
				 index = Integer.parseInt(indexcurrent);
				 System.out.println(index);
			}
			int factoryID = request.getParameter("factory") != null ? Integer.parseInt(request.getParameter("factory")) : 0;
			int targetID = request.getParameter("target") != null ? Integer.parseInt(request.getParameter("target")) : 0;
			String productName = request.getParameter("ProductName") != null ? (String)request.getParameter("ProductName") : null;
			int key = 1;
			int toltalProduct = pBO.Count(factoryID, targetID, productName);
			int Page = toltalProduct/12;
			if(toltalProduct % 12 !=0)
				Page +=1;
			List<Factory> lstFactory = fBO.getListFactory(1);
			for (Factory factory : lstFactory) {
				System.out.println(factory.toString());
			}
			List<Target> lstTarget = tBO.getListTarget();
			for (Target target : lstTarget) {
				System.out.println(target.toString());
			}
			ArrayList<Product> lstProduct = pBO.getListProducts(index, factoryID, targetID, productName);
			System.out.println("Danh sach:");
			for (Product product : lstProduct) {
				System.out.println(product.toString());
			}
			request.setAttribute("index", index);
			request.setAttribute("factory", factoryID);
			request.setAttribute("target", targetID);
			request.setAttribute("lstProduct", lstProduct);
			request.setAttribute("lstFactory", lstFactory);
			request.setAttribute("lstTarget", lstTarget);
			request.setAttribute("Page", Page);
			RequestDispatcher rd = request.getRequestDispatcher("client/Home.jsp");
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

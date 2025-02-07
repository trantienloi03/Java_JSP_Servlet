package adminController.Product;

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
import product.Product;
import product.ProductBO;
import target.Target;
import target.TargetBO;

/**
 * Servlet implementation class Admin_ProductController
 */
@WebServlet("/Admin_ProductController")
public class Admin_ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin_ProductController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			FactoryBO fBO = new FactoryBO();
			TargetBO tBO  = new TargetBO();
			ProductBO pBO = new ProductBO();
			String indexcurrent = request.getParameter("index");
			int index = 1;
			if(indexcurrent != null) {
				 index = Integer.parseInt(indexcurrent);
				 System.out.println(index);
			}
			int total;
			total = pBO.Count(0, 0, null);
			int Page = total/10;
			if(total % 10 !=0)
				Page +=1;
			List<Product> lstProduct = pBO.getListProducts(index, 0, 0, null);
			List<Target> lstTarget = tBO.getListTarget();
			for (Target target : lstTarget) {
				System.out.println(target.toString());
				
			}
			List<Factory> lstFactory = fBO.getListFactory(1);
			for (Factory factory : lstFactory) {
				System.out.println(factory.toString());
			}
			for (Product product : lstProduct) {
				System.out.println(product.toString());
			}
			request.setAttribute("lstFactory", lstFactory);
			request.setAttribute("lstTarget", lstTarget);
			request.setAttribute("lstProduct", lstProduct);
			request.setAttribute("index", index);
			request.setAttribute("Page", Page);
			RequestDispatcher rd = request.getRequestDispatcher("admin/Product/Product.jsp");
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

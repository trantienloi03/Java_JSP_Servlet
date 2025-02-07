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
import promotion.Promotion;
import promotion.PromotionBO;
import target.Target;
import target.TargetBO;

/**
 * Servlet implementation class XuLyController
 */
@WebServlet("/XuLyController")
public class XuLyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XuLyController() {
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
			PromotionBO prBO = new PromotionBO();
			int id;
			String action = "";
			if(request.getParameter("action") != null || request.getParameter("id") != null) {
				action = request.getParameter("action");
				id =request.getParameter("id")!=null? Integer.parseInt(request.getParameter("id")):0;
				if(action.equals("add")) {
					List<Target> lstTarget = tBO.getListTarget();
					List<Factory> lstFactory = fBO.getListFactory(1);
					request.setAttribute("lstFactory", lstFactory);
					request.setAttribute("lstTarget", lstTarget);
					RequestDispatcher rd = request.getRequestDispatcher("admin/Product/ProductAdd.jsp");
					rd.forward(request, response);
					return;
				}
				else if(action.equals("delete")) {
					pBO.deleteProduct(id);
					response.sendRedirect("Admin_ProductController");
					return;
				}
				else if(action.equals("detail")) {
					Product product = pBO.getProductByID(id);
					if(product != null) {
						request.setAttribute("product", product);
						RequestDispatcher rd = request.getRequestDispatcher("admin/Product/ProductDetail.jsp");
						rd.forward(request, response);
						return;
					}
					else {
						response.sendRedirect("Admin_ProductController");
						return;
					}
				}
				else if(action.equals("edit")) {
					Product product = null;
					product = pBO.getProductByID(id);
					System.out.println("----------"+product.toString());
					List<Target> lstTarget = tBO.getListTarget();
					List<Factory> lstFactory = fBO.getListFactory(1);
					List<Promotion> lstPromotion = prBO.getAllPromotions();
					for (Promotion promotion : lstPromotion) {
						System.out.println(promotion.toString());
					}
					request.setAttribute("lstFactory", lstFactory);
					request.setAttribute("lstTarget", lstTarget);
					request.setAttribute("promotion", lstPromotion);
					request.setAttribute("product", product);
					RequestDispatcher rd = request.getRequestDispatcher("admin/Product/ProductEdit.jsp");
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

package clientController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.Product;
import product.ProductBO;

/**
 * Servlet implementation class GetDetailController
 */
@WebServlet("/Detail")
public class GetDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			ProductBO pBO = new ProductBO();
			int ID = request.getParameter("ID") == null ? 0 : Integer.parseInt(request.getParameter("ID"));
			System.out.println(ID);
			Product detail = pBO.getProductByID(ID);
			System.out.println(detail.toString());
			List<Product> lstSimilarProduct = pBO.getListSimilarProducts(ID);
			for (Product product : lstSimilarProduct) {
				System.out.println(product.toString());
			}
			request.setAttribute("product", detail);
			request.setAttribute("lstSimilar", lstSimilarProduct);
			RequestDispatcher rd = request.getRequestDispatcher("client/DetailProduct.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			response.sendRedirect("/Home");
			return;
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

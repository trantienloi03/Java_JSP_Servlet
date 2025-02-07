package testDAO;

import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.SetOfIntegerSyntax;

import factory.Factory;
import factory.FactoryBO;
import order.Order;
import order.OrderBO;
import orderView.OrderDetailView;
import orderView.OrderDetailViewBO;
import product.Product;
import product.ProductBO;
import target.Target;
import target.TargetBO;
import user.User;
import user.UserBO;

public class TESTDAO {
	public static void main(String[] args) throws Exception {
		ProductBO pBO = new ProductBO();
		FactoryBO fBO = new FactoryBO();
		OrderBO oBO = new OrderBO();
		UserBO uBO = new UserBO();
		List<User> lst = new ArrayList<User>();
		lst = uBO.getListUser(1, 2);
		int count = uBO.Count(2);
		System.out.println(count);
		for (User user : lst) {
			System.out.println(user.toString());
		}
//		OrderDetailViewBO odvBO = new OrderDetailViewBO();
//		List<OrderDetailView> lst = odvBO.getOrderDetails(0);
//		for (OrderDetailView orderDetailView : lst) {
//			System.out.println(orderDetailView.toString());	
//		}
//		System.out.println(odvBO.TongTien(19));	
//		Order order = oBO.getOrderByOrderID(21);
//		System.out.println(order.toString());
		
//		int userID;
//		List<Order> lstOrder = oBO.getListOrderByUserID(0);
//		for (Order _order : lstOrder) {
//			System.out.println(_order.toString());
//		}
//		UserBO uBO = new UserBO();
//		User kh = uBO.getUserById(1);
//		System.out.println(kh.toString());

//		TargetBO tBO  = new TargetBO();
//		OrderBO oBO = new OrderBO();
//		Order order = new Order();
//		order.setTotalPrice(1000000);
//		order.setUserID(1);
//		order.setStatusID(2);
//		order.setSum(5);
//		order.setUserName("Nguyen Van A");
//		order.setAddress("123 Nguyen Trai, Ha Noi");
//		order.setPhone("0123456789");
//
//		int newOrderID = oBO.insertOrder(order);
//		System.out.println("OrderID vừa chèn: " + newOrderID);
//		ArrayList<Product> lstProduct = pBO.getListProducts(1, 0, 0, null);
//		for (Product product : lstProduct) {
//			System.out.println(product.toString());
//		}
//		Product _product = pBO.getProductByID(1);
//		System.out.println(_product.toString());
//		ArrayList<Product> lstSimilarProduct = pBO.getListSimilarProducts(_product.getiD());
//		for (Product product : lstSimilarProduct) {
//			System.out.println(product.toString());
//		}
//		List<Factory> lstFactory = fBO.getListFactory(1);
//		for (Factory factory : lstFactory) {
//			System.out.println(factory.toString());
//		}
//		System.out.println(fBO.Count());
//		List<Target> lstTarget = tBO.getListTarget();
//		for (Target target : lstTarget) {
//			System.out.println(target.toString());
//		}
	}

}

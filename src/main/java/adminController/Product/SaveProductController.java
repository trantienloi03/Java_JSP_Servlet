package adminController.Product;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import factory.Factory;
import factory.FactoryBO;
import product.Product;
import product.ProductBO;
import promotion.Promotion;
import promotion.PromotionBO;
import target.Target;
import target.TargetBO;

/**
 * Servlet implementation class SaveProductController
 */
@WebServlet("/SaveProductController")
public class SaveProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveProductController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		 DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
		 ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
		 String dirUrl1 = request.getServletContext().getRealPath("") +  File.separator + "Images";
		 FactoryBO fBO = new FactoryBO();
		 TargetBO tBO  = new TargetBO();
		 ProductBO pBO = new ProductBO();
		 PromotionBO prBO = new PromotionBO();
		 Product product = new Product();
		 Map<String, String> errors = new HashMap<String, String>();
		 boolean isUploaded = false;
		try {
			List<FileItem> fileItems = upload.parseRequest(request);
			for (FileItem fileItem : fileItems) {
				 if (!fileItem.isFormField()) {
					String nameimg = fileItem.getName();
					if (!nameimg.equals("")) {
						String dirUrl = request.getServletContext().getRealPath("") +  File.separator + "Images";
						File dir = new File(dirUrl);
						if (!dir.exists()) {
							dir.mkdir();
						}
					           String fileImg = dirUrl +File.separator + nameimg;
					           product.setImage("Images/"+ nameimg);
					           File file = new File(fileImg);//tạo file
					            try {
					               fileItem.write(file);//lưu file
					              System.out.println("UPLOAD THÀNH CÔNG...!");
					              System.out.println("Đường dẫn lưu file là: "+dirUrl);
					              isUploaded = true;
					 } catch (Exception e) {
					    e.printStackTrace();
					 	}
					}
				 }
				 else//Neu la control
				 {
					
					 String fieldName = fileItem.getFieldName(); 
					   
					    switch (fieldName) {
					        case "productName": 
					        	if(fileItem.getString() == null || fileItem.getString().trim().isEmpty() )
					        		errors.put("productName", "Tên sản phẩm không được để trống");
					        	else {
					        		product.setProductName(fileItem.getString("UTF-8"));
					        	}
					            break;
					        case "factory": 
					        	if (fileItem.getString().equals("0")) {
					                errors.put("factory", "Vui lòng chọn loại hàng.");
					            } else {
					         
									try {
										Factory _factory;
										_factory = fBO.getFactoryById(Integer.parseInt(fileItem.getString("UTF-8")));
										product.setFactory(_factory);
									} catch (NumberFormatException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (ClassNotFoundException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (UnsupportedEncodingException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
					            }
					        case "target": 
					        	if (fileItem.getString().equals("0")) {
					                errors.put("target", "Vui lòng mục tiêu.");
					            } else {
					               try {
									Target target = tBO.getTargetById(Integer.parseInt(fileItem.getString("UTF-8")));
									product.setTarget(target);
								} catch (NumberFormatException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (ClassNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (UnsupportedEncodingException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
					               
					            }
					            break;
					        
					        case "price": 
					        	String giabanStr = fileItem.getString().trim(); // Lấy giá trị và loại bỏ khoảng trắng
					            if (giabanStr.isEmpty()) {
					                errors.put("price", "Giá bán không được để trống.");
					            } else {
					                try {
					                    Long giaban = Long.parseLong(giabanStr);
					                    if (giaban <= 0) {
					                        errors.put("giaban", "Giá bán phải lớn hơn 0.");
					                    } else {
					                        product.setPrice(giaban);
					                    }
					                } catch (NumberFormatException e) {
					                    errors.put("giaban", "Giá bán phải là một số nguyên hợp lệ.");
					                }
					            }
					            break;
					        case "detailDesc": 
					        	product.setShortDesc(fileItem.getString("UTF-8"));
					            break;
					        case "shortDesc": 
					        		product.setShortDesc(fileItem.getString("UTF-8"));
					            break;
					    }
					}
			 }
			
			if (!errors.isEmpty() || isUploaded == false) {
				if(isUploaded == false)
					errors.put("uploadfile", "Chua chon anh");
		        request.setAttribute("errors", errors);
		        request.setAttribute("product", product);
		        List<Target> lstTarget = tBO.getListTarget();
				List<Factory> lstFactory = fBO.getListFactory(1);
				request.setAttribute("lstFactory", lstFactory);
				request.setAttribute("lstTarget", lstTarget);
		        RequestDispatcher rd = request.getRequestDispatcher("admin/Product/ProductAdd.jsp");
				rd.forward(request, response);
		 }
			 try {
				Promotion promotion = prBO.getPromotionById(4);
				product.setPromotion(promotion);
				pBO.insertProduct(product);
				RequestDispatcher rd = request.getRequestDispatcher("Admin_ProductController");
			    rd.forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().println("Lưu sách thất bại!");
			}
		
		} catch (FileUploadException e) {
			e.printStackTrace();
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

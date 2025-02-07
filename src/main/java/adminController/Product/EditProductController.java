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

@WebServlet("/EditProductController")
public class EditProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditProductController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        String dirUrl = request.getServletContext().getRealPath("") + File.separator + "Images";

        FactoryBO fBO = new FactoryBO();
        TargetBO tBO = new TargetBO();
        ProductBO pBO = new ProductBO();
        PromotionBO prBO = new PromotionBO();

        Product product = new Product();
        Map<String, String> errors = new HashMap<>();

        try {
            List<FileItem> fileItems = upload.parseRequest(request);

            for (FileItem fileItem : fileItems) {
                if (!fileItem.isFormField()) {
                    String nameimg = fileItem.getName();
                    if (!nameimg.isEmpty()) {
                        File dir = new File(dirUrl);
                        if (!dir.exists()) {
                            dir.mkdir();
                        }
                        String fileImg = dirUrl + File.separator + nameimg;
                        product.setImage("Images/" + nameimg);
                        File file = new File(fileImg);
                        fileItem.write(file);
                    }
                } else {
                    String fieldName = fileItem.getFieldName();
                    switch (fieldName) {
                        case "id":
                            product.setiD(Integer.parseInt(fileItem.getString("UTF-8")));
                            break;
                        case "productName":
                            if (fileItem.getString().trim().isEmpty()) {
                                errors.put("productName", "Tên sản phẩm không được để trống");
                            } else {
                                product.setProductName(fileItem.getString("UTF-8"));
                            }
                            break;
                        case "factory":
                            if (fileItem.getString().equals("0")) {
                                errors.put("factory", "Vui lòng chọn loại hàng.");
                            } else {
                                Factory factoryObj = fBO.getFactoryById(Integer.parseInt(fileItem.getString("UTF-8")));
                                product.setFactory(factoryObj);
                            }
                            break;
                        case "target":
                            if (fileItem.getString().equals("0")) {
                                errors.put("target", "Vui lòng chọn mục tiêu.");
                            } else {
                                Target target = tBO.getTargetById(Integer.parseInt(fileItem.getString("UTF-8")));
                                product.setTarget(target);
                            }
                            break;
                        case "promotion":
                            if (fileItem.getString().equals("0")) {
                                errors.put("promotion", "Vui lòng chọn giảm giá.");
                            } else {
                                Promotion promotion = prBO.getPromotionById(Integer.parseInt(fileItem.getString("UTF-8")));
                                product.setPromotion(promotion);
                            }
                            break;
                        case "price":
                            try {
                                long price = Long.parseLong(fileItem.getString().trim());
                                if (price <= 0) {
                                    errors.put("price", "Giá bán phải lớn hơn 0.");
                                } else {
                                    product.setPrice(price);
                                }
                            } catch (NumberFormatException e) {
                                errors.put("price", "Giá bán phải là một số hợp lệ.");
                            }
                            break;
                        case "detailDesc":
                            if (fileItem.getString() == null || fileItem.getString().trim().isEmpty()) {
                                errors.put("detailDesc", "Mô tả chi tiết không được để trống.");
                            } else {
                                product.setDetailDesc(fileItem.getString("UTF-8"));
                            }
                            break;

                        case "shortDesc":
                            if (fileItem.getString() == null || fileItem.getString().trim().isEmpty()) {
                                errors.put("shortDesc", "Mô tả ngắn gọn không được để trống.");
                            } else {
                                product.setShortDesc(fileItem.getString("UTF-8"));
                            }
                            break;
                        case "oldfile":
                            if (product.getImage() == null || product.getImage().isEmpty()) {
                                product.setImage(fileItem.getString("UTF-8"));
                            }
                            break;
                    }
                }
            }

            if (!errors.isEmpty()) {
                request.setAttribute("errors", errors);
                request.setAttribute("product", product);
                request.setAttribute("lstFactory", fBO.getListFactory(1));
                request.setAttribute("lstTarget", tBO.getListTarget());
                request.setAttribute("lstPromotion", prBO.getAllPromotions());
                RequestDispatcher rd = request.getRequestDispatcher("admin/Product/ProductEdit.jsp");
                rd.forward(request, response);
                return;
            }

            pBO.updateProduct(product);
            response.sendRedirect("Admin_ProductController");

        } catch (FileUploadException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().println("Lưu sản phẩm thất bại!");
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

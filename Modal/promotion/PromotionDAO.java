package promotion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.GetConnection;

public class PromotionDAO {

	public List<Promotion> getAllPromotions() throws ClassNotFoundException, SQLException {
	    List<Promotion> promotions = new ArrayList(); // Danh sách lưu trữ các đối tượng Promotion
	    GetConnection kn = new GetConnection();
	    kn.KetNoi();

	    String sql = "SELECT * FROM Promotion";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);

	    // Thực thi câu lệnh SQL
	    ResultSet rs = cmd.executeQuery();

	    // Duyệt qua ResultSet và thêm vào danh sách
	    while (rs.next()) {
	        int promotionID = rs.getInt("PromotionID");
	        double discountPercent = rs.getDouble("Discount_percent");

	        // Tạo đối tượng Promotion và thêm vào danh sách
	        Promotion promotion = new Promotion(promotionID, discountPercent);
	        promotions.add(promotion);
	    }
	    rs.close();
	    kn.cn.close();
	    return promotions;
	}

	public int insertPromotion(Promotion promotion) throws ClassNotFoundException, SQLException {
	    int id = 0; // ID tự tăng được trả về
	    GetConnection kn = new GetConnection();
	    kn.KetNoi();

	    String sql = "INSERT INTO Promotion (Discount_percent) VALUES (?) SELECT SCOPE_IDENTITY()";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);

	    // Gán tham số
	    cmd.setDouble(1, promotion.getDiscountPercent());

	    // Thực thi lệnh
	    ResultSet rs = cmd.executeQuery();
	    if (rs.next()) {
	        id = rs.getInt(1); // Lấy giá trị ID tự tăng
	    }
	    rs.close();
	    kn.cn.close();
	    return id;
	}
	public int updatePromotion(Promotion promotion) throws ClassNotFoundException, SQLException {
	    int affectedRows = 0; // Số dòng bị ảnh hưởng
	    GetConnection kn = new GetConnection();
	    kn.KetNoi();

	    String sql = "UPDATE Promotion SET Discount_percent = ? WHERE PromotionID = ?";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);

	    // Gán tham số
	    cmd.setDouble(1, promotion.getDiscountPercent());
	    cmd.setInt(2, promotion.getPromotionID());

	    // Thực thi lệnh
	    affectedRows = cmd.executeUpdate();

	    kn.cn.close();
	    return affectedRows;
	}
	public int deletePromotion(int promotionID) throws ClassNotFoundException, SQLException {
	    int affectedRows = 0; // Số dòng bị xóa
	    GetConnection kn = new GetConnection();
	    kn.KetNoi();

	    String sql = "DELETE FROM Promotion WHERE PromotionID = ?";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);

	    // Gán tham số
	    cmd.setInt(1, promotionID);

	    // Thực thi lệnh
	    affectedRows = cmd.executeUpdate();

	    kn.cn.close();
	    return affectedRows;
	}
	public Promotion getPromotionById(int promotionId) throws ClassNotFoundException, SQLException {
	    Promotion promotion = null;
	    GetConnection kn = new GetConnection();
	    kn.KetNoi();

	    String sql = "SELECT PromotionID, Discount_percent FROM Promotion WHERE PromotionID = ?";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setInt(1, promotionId);

	    ResultSet rs = cmd.executeQuery();
	    if (rs.next()) {
	        int id = rs.getInt("PromotionID");
	        double discount = rs.getDouble("Discount_percent");
	        promotion = new Promotion(id, discount);
	    }
	    rs.close();
	    kn.cn.close();
	    return promotion;
	}



}

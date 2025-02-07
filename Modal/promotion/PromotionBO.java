package promotion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.GetConnection;

public class PromotionBO {
	PromotionDAO prDAO = new PromotionDAO();
	public List<Promotion> getAllPromotions() throws ClassNotFoundException, SQLException {
	   return prDAO.getAllPromotions();
	}

	public int insertPromotion(Promotion promotion) throws ClassNotFoundException, SQLException {
	   return prDAO.insertPromotion(promotion);
	}
	public int updatePromotion(Promotion promotion) throws ClassNotFoundException, SQLException {
		return prDAO.updatePromotion(promotion);
	}
	public int deletePromotion(int promotionID) throws ClassNotFoundException, SQLException {
	    return prDAO.deletePromotion(promotionID);
	}
	public Promotion getPromotionById(int promotionId) throws ClassNotFoundException, SQLException {
	   return prDAO.getPromotionById(promotionId);
	}


}

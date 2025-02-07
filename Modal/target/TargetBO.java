package target;

import java.sql.SQLException;
import java.util.List;

import factory.Factory;
import factory.FactoryDAO;

public class TargetBO {
	private TargetDAO tDAO = new TargetDAO();
	public List<Target> getListTarget() throws Exception, Exception{
		return tDAO.getListTarget();
	}
	public Target getTargetById(int targetId) throws ClassNotFoundException, SQLException {
		return tDAO.getTargetById(targetId);
	}

}

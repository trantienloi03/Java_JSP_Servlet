package factory;

import java.sql.SQLException;
import java.util.List;

public class FactoryBO {
	private FactoryDAO fDAO = new FactoryDAO();
	public List<Factory> getListFactory(int index) throws Exception, Exception{
		return fDAO.getListFactory(index);
	}
	public int insertFactory(Factory factory) throws ClassNotFoundException, SQLException {
		return fDAO.insertFactory(factory);
	}
	public int updateFactory(Factory factory) throws ClassNotFoundException, SQLException {
		return fDAO.updateFactory(factory);
	}
	public int deleteFactory(int factoryID) throws ClassNotFoundException, SQLException {
		return fDAO.deleteFactory(factoryID);
	}
	public Factory getFactoryById(int factoryId) throws ClassNotFoundException, SQLException {
		return fDAO.getFactoryById(factoryId);
	}
	public int Count() throws Exception {
		return fDAO.Count();
	}

}

package BudgetManager.modelFx;

import BudgetManager.converters.ConverterRevenues;
import BudgetManager.database.dao.RevenuesDao;
import BudgetManager.database.dbutils.DbManager;
import BudgetManager.database.models.Revenues;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;
public class RevenuesModel {

	private ObjectProperty<RevenuesFx> revenuesFxObjectProperty = new SimpleObjectProperty<>(new RevenuesFx());
	private ObservableList<RevenuesFx> revenuesFxObservableList = FXCollections.observableArrayList();
	
	public void init() throws Exception {
		RevenuesDao revenuesDao = new RevenuesDao();
		this.revenuesFxObservableList.clear();
		List<Revenues> revenuesList = revenuesDao.queryForAll(Revenues.class);
		revenuesList.forEach(revenues ->{
			this.revenuesFxObservableList.add(ConverterRevenues.convertToRevenuesFx(revenues));
		});
	}



	public RevenuesFx getRevenuesFxObjectProperty() {
        return revenuesFxObjectProperty.get();
    }

    public ObjectProperty<RevenuesFx> revenuesFxObjectPropertyProperty() {
        return revenuesFxObjectProperty;
    }

    public void setRevenuesFxObjectProperty(RevenuesFx revenuesFxObjectProperty) {
        this.revenuesFxObjectProperty.set(revenuesFxObjectProperty);
    }
    public ObservableList<RevenuesFx> getRevenuesFxObservableList() {
        return revenuesFxObservableList;
    }

    public void setRevenuesFxObservableList(ObservableList<RevenuesFx> revenuesFxObservableList) {
        this.revenuesFxObservableList = revenuesFxObservableList;
    }
    
    public void saveRevenuesInDataBase() throws Exception  {
    	RevenuesDao revenuesDao = new RevenuesDao();
    	Revenues revenues = ConverterRevenues.convertRevenuesFxToRevenues(this.getRevenuesFxObjectProperty());
    	revenuesDao.createOrUpdate(revenues);
    	this.init();
    }
    
    public void deleteRevenuesInDataBase(RevenuesFx revenuesFx) throws Exception{
    	RevenuesDao revenuesDao = new RevenuesDao();
    	revenuesDao.deleteById(Revenues.class, revenuesFx.getId());
    	this.init();
    }
    


}

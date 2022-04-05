package BudgetManager.modelFx;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ExpensesFx {
	 	private IntegerProperty id = new SimpleIntegerProperty();
	    private StringProperty amount = new SimpleStringProperty();
	    private ObjectProperty<LocalDate> date = new SimpleObjectProperty<>();
	    private StringProperty name = new SimpleStringProperty();

	    public ExpensesFx() {
	    	
	    }
	    public ExpensesFx(StringProperty amount, ObjectProperty<LocalDate> date) {
	    	this.amount = amount;
	        this.date = date;
	    }
	    
	    
	    public int getId() {
	        return id.get();
	    }

	    public IntegerProperty idProperty() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id.set(id);
	    }

	    public String getAmount() {
	        return amount.get();
	    }

	    public StringProperty amountProperty() {
	        return amount;
	    }

	    public void setAmount(String amount) {
	        this.amount.set(amount);
	    }

	    public LocalDate getDate() {
	        return date.get();
	    }

	    public ObjectProperty<LocalDate> dateProperty() {
	        return date;
	    }
	   
	    public void setDate(LocalDate date) {
	        this.date.set(date);
	    }
	    
	    
	    public String getName() {
	        return name.get();
	    }

	    public StringProperty nameProperty() {
	        return name;
	    }
	    
	    public void setName(String name) {
	        this.name.set(name);
	    }
	    
}
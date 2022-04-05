package BudgetManager.modelFx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ResultFx {
	private StringProperty revenuesValue = new SimpleStringProperty();
	private StringProperty expensesValue = new SimpleStringProperty();
	private StringProperty resultValue = new SimpleStringProperty();
	
	public ResultFx() {
		
	}
	
	@Override
    public String toString() {
        return "ResultFx{" +
                "revenuesValue=" + revenuesValue +
                ", expensesValue=" + expensesValue +
                ", resultValue=" + resultValue +
                '}';
    }
    public String getRevenuesValue() {
        return revenuesValue.get();
    }

    public StringProperty revenuesValueProperty() {
        return revenuesValue;
    }

    public void setRevenuesValue(String revenuesValue) {
        this.revenuesValue.set(revenuesValue);
    }
    
    public String getExpensesValue() {
        return expensesValue.get();
    }

    public StringProperty expensesValueProperty() {
        return expensesValue;
    }

    public void setExpensesValue(String expensesValue) {
        this.expensesValue.set(expensesValue);
    }
    public String getResultValue() {
        return resultValue.get();
    }

    public StringProperty resultValueProperty() {
        return resultValue;
    }

    public void setResultValue(String resultValue) {
        this.resultValue.set(resultValue);
    }
	
}

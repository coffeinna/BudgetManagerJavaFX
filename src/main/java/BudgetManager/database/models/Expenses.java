package BudgetManager.database.models;

import java.time.LocalDate;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "EXPENSES")
public class Expenses implements BaseModel {
	public Expenses() {
		
	}
	
	@DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "KWOTA")
    private double amount;

    @DatabaseField(columnName = "DATA")
    private Date date;
    
    @DatabaseField(columnName = "NAZWA", canBeNull= false)
    private String name;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date Date) {
        this.date = Date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

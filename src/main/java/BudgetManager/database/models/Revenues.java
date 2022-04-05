package BudgetManager.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;


@DatabaseTable(tableName = "REVENUES")
public class Revenues implements BaseModel {
	public Revenues() {
		
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

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

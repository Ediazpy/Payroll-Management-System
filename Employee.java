import java.text.DecimalFormat;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Emmanuel Diaz
 */
public class Employee
{
    // attributes
    private int employeeID;
    private String name;
    private String address;
    private Double sales;
    private Double tips;
    private Double parts;
    

    // constructors
    public Employee()
    {
        employeeID = 0;
        name = "n/a";
        address = "n/a";
        sales = 0.0;
        tips = 0.0;
        parts = 0.0;
        
    }

    public Employee(int employeeID, String name, String address, Double sales, Double tips, Double parts)
    {
        this.employeeID = employeeID;
        this.name = name;
        this.address = address;
        this.sales = sales;
        this.tips = tips;
        this.parts = parts;
        
    }

    // behaviors

    @Override
    public String toString()
    {
        return name + ":" + address + ":" + "\nTotal Weekly Sales:" + calculateWeeklySales();
    }
    
   public double calculateWeeklySales()
    {
        double weeklySales = ((sales - parts)/2) + tips;
        return weeklySales;
    }
   
   public String getDetails()
   {
       DecimalFormat fmt = new DecimalFormat("$#,##0.00");
       String output = "ID: " + employeeID + "\n";
       output += name + "\n";
       output += address + "\n";
       output += "Sales:" + fmt.format(sales) + "\n";
       output += "Tips:" + fmt.format(tips) + "\n";
       output += "Parts:" + fmt.format(parts) + "\n";
       
       return output;
       
   }
   
    
    /*public Double ytdPay()
    {
        //////////////////////////////////////////////////////
    }*/
    
    
    // getters and setters

    public int getEmployeeID()
    {
        return employeeID;
    }

    public void setEmployeeID(int employeeID)   // validate
    {
        this.employeeID = employeeID;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)    // validate
    {
        this.name = name;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)  // validate
    {
        this.address = address;
    }

    public Double getSales()
    {
        return sales;
    }

    public void setSales(Double sales)  
    {
        if ( sales > 0.0)
            this.sales = sales;
        else
            this.sales = 0.0;
    }

    public Double getTips()
    {
        return tips;
    }

    public void setTips(Double tips)    // validate
    {
        this.tips = tips;
    }

    public Double getParts()
    {
        return parts;
    }

    public void setParts(Double parts)  // validate
    {
        this.parts = parts;
    }
    
}

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
        // Commission calculation: 50% of (sales minus parts) plus tips
        double weeklySales = ((sales - parts) / 2.0) + tips;
        return Math.max(weeklySales, 0.0); // Ensure non-negative result
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
   
    
    // Validation method for input
    public static boolean isValidID(String id)
    {
        try
        {
            int empId = Integer.parseInt(id);
            return empId > 0;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }

    public static boolean isValidNumber(String num)
    {
        try
        {
            double value = Double.parseDouble(num);
            return value >= 0.0;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }
    
    
    // getters and setters

    public int getEmployeeID()
    {
        return employeeID;
    }

    public void setEmployeeID(int employeeID)
    {
        if (employeeID > 0)
            this.employeeID = employeeID;
        else
            this.employeeID = 0;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        if (name != null && !name.trim().isEmpty())
            this.name = name.trim();
        else
            this.name = "n/a";
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        if (address != null && !address.trim().isEmpty())
            this.address = address.trim();
        else
            this.address = "n/a";
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

    public void setTips(Double tips)
    {
        if (tips != null && tips >= 0.0)
            this.tips = tips;
        else
            this.tips = 0.0;
    }

    public Double getParts()
    {
        return parts;
    }

    public void setParts(Double parts)
    {
        if (parts != null && parts >= 0.0)
            this.parts = parts;
        else
            this.parts = 0.0;
    }
    
}

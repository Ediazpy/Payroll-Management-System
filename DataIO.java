
import java.util.ArrayList;
import java.sql.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Emmanuel Diaz
 */
public class DataIO
{
    // class level constants
    private final String CONNECTION_STRING = "jdbc:sqlite:database.db";
    private final String USER_NAME = "n/a";
    private final String PASSWORD = "n/a";
    
    // constructor
    public DataIO() throws ClassNotFoundException, SQLException
    {
        // check for the driver
        Class.forName("org.sqlite.JDBC");
        
        // connect to the database
        
        Connection conn = DriverManager.getConnection(CONNECTION_STRING);
        
        // create the table if it does not already exist
         String sqlCreateEmployeeTable = "CREATE TABLE IF NOT EXISTS employee " 
                + "(employeeID INTEGER PRIMARY KEY, "
                + "EmployeeName TEXT, "
                + "EmployeeAddress TEXT, "
                + "Sales REAL, "
                + "Tips REAL, "
                + "Parts REAL) ";
            
        Statement stmt = conn.createStatement();
        stmt.execute(sqlCreateEmployeeTable);
        
        // close the connection
        
        conn.close();
        
    }
    
    // behaviors
    public void add( Employee emp) throws SQLException
    {
        // always use preparedStatement to write to database when you get input
        //from the user to help prevent hacking. Injection attacks ae comm against
        // database.
        
        // connect to the database
        Connection conn = DriverManager.getConnection(CONNECTION_STRING);
        
        // write the customer record to the database
        String sqlStr = "INSERT INTO employee (employeeID, EmployeeName, EmployeeAddress, "
                + "Sales, Tips, Parts ) VALUES (?,?,?,?,?,?) ";
        PreparedStatement pstmt = conn.prepareStatement(sqlStr);
        pstmt.setInt(1, emp.getEmployeeID());
        pstmt.setString(2,emp.getName());
        pstmt.setString(3, emp.getAddress());
        pstmt.setDouble(4, emp.getSales() );
        pstmt.setDouble(5, emp.getTips());
        pstmt.setDouble(6, emp.getParts());
        
        
        
        pstmt.execute();
        
        // close the connection
        conn.close();
        
        
    }
    
    public boolean delete (int employeeID ) throws SQLException
    {
        // connect to the database

        Connection conn = DriverManager.getConnection(CONNECTION_STRING);

        // Delete the record

        String strSQL = "DELETE FROM employee WHERE EmployeeID = ?";
        PreparedStatement pstmt = conn.prepareStatement(strSQL);
        pstmt.setInt(1,employeeID);
        int rowsAffected = pstmt.executeUpdate();

        // close the connection

        conn.close();

        if ( rowsAffected > 0)
            return true;        //record was deleted
        else
            return false;       // zero rows affected so record was not found
    }

    public boolean update(Employee emp) throws SQLException
    {
        // connect to the database
        Connection conn = DriverManager.getConnection(CONNECTION_STRING);

        // Update the employee record
        String sqlStr = "UPDATE employee SET EmployeeName = ?, EmployeeAddress = ?, "
                + "Sales = ?, Tips = ?, Parts = ? WHERE employeeID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sqlStr);
        pstmt.setString(1, emp.getName());
        pstmt.setString(2, emp.getAddress());
        pstmt.setDouble(3, emp.getSales());
        pstmt.setDouble(4, emp.getTips());
        pstmt.setDouble(5, emp.getParts());
        pstmt.setInt(6, emp.getEmployeeID());

        int rowsAffected = pstmt.executeUpdate();

        // close the connection
        conn.close();

        return rowsAffected > 0; // true if record was updated
    }

    public Employee getById(int employeeID) throws SQLException
    {
        // connect to the database
        Connection conn = DriverManager.getConnection(CONNECTION_STRING);

        // get the specific employee record
        String strSQL = "SELECT * FROM employee WHERE employeeID = ?";
        PreparedStatement pstmt = conn.prepareStatement(strSQL);
        pstmt.setInt(1, employeeID);
        ResultSet rs = pstmt.executeQuery();

        Employee emp = null;
        if (rs.next())
        {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String address = rs.getString(3);
            Double sales = rs.getDouble(4);
            Double tips = rs.getDouble(5);
            Double parts = rs.getDouble(6);

            emp = new Employee(id, name, address, sales, tips, parts);
        }

        conn.close();
        return emp;
    }

    public boolean exists(int employeeID) throws SQLException
    {
        Connection conn = DriverManager.getConnection(CONNECTION_STRING);
        String strSQL = "SELECT COUNT(*) FROM employee WHERE employeeID = ?";
        PreparedStatement pstmt = conn.prepareStatement(strSQL);
        pstmt.setInt(1, employeeID);
        ResultSet rs = pstmt.executeQuery();

        boolean exists = false;
        if (rs.next())
        {
            exists = rs.getInt(1) > 0;
        }

        conn.close();
        return exists;
    }       
    
    public ArrayList<Employee> getList() throws SQLException
    {
        // create arraylist
        ArrayList<Employee> list = new ArrayList<Employee>();
        
        // connect to the database
        Connection conn = DriverManager.getConnection(CONNECTION_STRING);
        
        // get the records from the database
        String strSQL = "SELECT * FROM employee";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(strSQL);
        
        while( rs.next() )
        {
            int employeeID = rs.getInt(1);
            String name = rs.getString(2);
            String address = rs.getString(3);
            Double sales = rs.getDouble(4);
            Double tips = rs.getDouble(5);
            Double parts = rs.getDouble(6);
            
            Employee emp = new Employee(employeeID, name, address, sales, tips,
                    parts);
            list.add(emp);          
        }
        conn.close();
        
        // return the arraylist
        return list;
    }
}
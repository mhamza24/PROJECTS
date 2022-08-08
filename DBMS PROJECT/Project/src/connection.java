import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class connection
{
    Connection con;
    Statement st;
    connection()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","1234");
            st=con.createStatement();
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMySQL Connected Successfully");
        }
        catch (Exception e)
        {
            //System.out.println(e);
            //e.printStackTrace();
        }
    }
    public static void main(String[] args)
    {

    }
}

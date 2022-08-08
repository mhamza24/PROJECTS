import java.sql.ResultSet;
import java.util.Scanner;

public class Logs
{

    void design()
    {
        System.out.println("\t**********************************************************************************");
        System.out.println("\t\t\t******  Auto Dealership  ******");
        System.out.println("\t\t\t\t\t\t\t\t\tBuy your wheels");
        System.out.println("\n");
        System.out.println("\t\t\t\t\t\t\tLogs");
        System.out.println("\n\n");

    }
    void pausepaly()
    {
        Scanner box2 = new Scanner(System.in);
        System.out.print("go to Log options ?[Y/N]");
        String Status =box2.nextLine();
        if (Status.equals("Y") || Status.equals("y"))
        {

            log();
        }
        else
        {
            System.exit(0);
        }
    }
    void log()
    {
        design();
        System.out.println("\t\t[1] View Order\t\t\t\t\t\t[2] Inventory");
        System.out.println("\t\t[3] Mechanic  \t\t\t\t\t\t[4] Salesperon");
        System.out.println("\t\t[5] Cars      \t\t\t\t\t\t[6] Exit");
        System.out.println();
        Scanner box = new Scanner(System.in);
        int choice;
        System.out.print("\t\tEnter Choice:");
        choice = box.nextInt();
        if (choice==1)
        {
            viewOrder();

        }
        else if (choice==2)
        {
            inventory();
        }
        else if (choice==3)
        {
            Mechanic();
        }
        else if (choice==4)
        {
            Salesperon();
        }
        else if (choice==5)
        {
            cars();
        }
        else if (choice==6)
        {
            Main_menu main_menu = new Main_menu();
            main_menu.mainMenu();
        }
        else
        {
            System.out.println("Wrong Input,try again");
            log();
        }

    }

     void viewOrder()
     {
        try
        {
            int orderno=1;
            connection con = new connection();
            ResultSet resultSet =con.st.executeQuery("select * from Sales_Invoice;");
            System.out.println("\t\t\t\t\tORDERS");
            while (resultSet.next())
            {
                int indexno=1;
                System.out.println("\t\t\t"+"["+indexno+++"]"+"Order No :"+orderno++);
                System.out.println("\t\t\t"+"["+indexno+++"]"+"Sales Invoice ID : "+resultSet.getInt("Sales_Invoice_ID"));
                System.out.println("\t\t\t"+"["+indexno+++"]"+"Car ID           : "+resultSet.getInt("Car_ID"));
                System.out.println("\t\t\t"+"["+indexno+++"]"+"Customer ID      : "+resultSet.getInt("Customer_ID"));
                System.out.println("\t\t\t"+"["+indexno+++"]"+"Salesperson ID   : "+resultSet.getInt("Salesperson_ID"));
                System.out.println("\t\t\t"+"["+indexno+++"]"+"Date             : "+resultSet.getString("Date"));
                System.out.println("\t\t\t"+"["+indexno+++"]"+"Price            : "+resultSet.getFloat("price"));
                System.out.println();

            }
        }
        catch (Exception e)
        {

        }
         pausepaly();
     }
    void inventory()
    {
        try
        {
            int itemno=1;
            connection con = new connection();
            ResultSet resultSet =con.st.executeQuery("select * from Parts");
            System.out.println("\t\t\t\t\tINVENTORY");
            while (resultSet.next())
            {
                int indexno=1;
                System.out.println("\t\t\tItem no :"+itemno++);
                System.out.println("\t\t\t"+"["+indexno+++"]"+"Part ID     : "+resultSet.getInt("Part_ID"));
                System.out.println("\t\t\t"+"["+indexno+++"]"+"Part no     : "+resultSet.getInt("Part_no"));
                System.out.println("\t\t\t"+"["+indexno+++"]"+"Description : "+resultSet.getString("Desciption"));
                System.out.println("\t\t\t"+"["+indexno+++"]"+"Price       : "+resultSet.getInt("Price"));
                System.out.println("\t\t\t"+"["+indexno+++"]"+"Units       : "+resultSet.getInt("units"));
                System.out.println();
            }
        }
        catch (Exception e)
        {

        }
    }
    void Mechanic()
    {
        try
        {
            int mechanicNo=1;
            connection con = new connection();
            ResultSet resultSet =con.st.executeQuery("select * from Mechanic");
            System.out.println("\t\t\t\t\tMechanics");
            while (resultSet.next())
            {
                int indexno=1;
                System.out.println("\t\t\tMechanicNo no :"+mechanicNo++);
                System.out.println("\t\t\t"+"["+indexno+++"]"+"Mechanic_ID    : "+resultSet.getInt("Mechanic_ID"));
                System.out.println("\t\t\t"+"["+indexno+++"]"+"First name     : "+resultSet.getString("First_Name"));
                System.out.println("\t\t\t"+"["+indexno+++"]"+"Last name      : "+resultSet.getString("Last_Name"));
                System.out.println();
            }
        }
        catch (Exception e)
        {

        }
        pausepaly();
    }
    void Salesperon()
    {
        try
        {
            int Salesperon=1;
            connection con = new connection();
            ResultSet resultSet =con.st.executeQuery("select * from Salesperson");
            System.out.println("\t\t\t\t\tSalesperon");
            while (resultSet.next())
            {
                int indexno=1;
                System.out.println("\t\t\tSalesperon no : "+Salesperon++);
                System.out.println("\t\t\t"+"["+indexno+++"]"+"Salesperson    : "+resultSet.getInt("Salesperson_ID"));
                System.out.println("\t\t\t"+"["+indexno+++"]"+"First name     : "+resultSet.getString("First_Name"));
                System.out.println("\t\t\t"+"["+indexno+++"]"+"Last name      : "+resultSet.getString("Last_Name"));
                System.out.println();
            }
        }
        catch (Exception e)
        {

        }
        pausepaly();
    }
    void cars()
    {
        try
        {
            int cars=1;
            connection con = new connection();
            ResultSet resultSet =con.st.executeQuery("select * from car");
            System.out.println("\t\t\t\t\tCar");
            while (resultSet.next())
            {
                int indexno=1;
                System.out.println("\t\t\t"+"["+indexno+++"]"+"Car no : "+cars++);
                System.out.println("\t\t\t"+"["+indexno+++"]"+"ID          :"+resultSet.getInt("Car_ID"));
                System.out.println("\t\t\t"+"["+indexno+++"]"+"Serial no   :"+resultSet.getString("Serial_No"));
                System.out.println("\t\t\t"+"["+indexno+++"]"+"Make        :"+resultSet.getString("Make"));
                System.out.println("\t\t\t"+"["+indexno+++"]"+"Model       :"+resultSet.getString("Model"));
                System.out.println("\t\t\t"+"["+indexno+++"]"+"Color       :"+resultSet.getString("Color"));
                System.out.println("\t\t\t"+"["+indexno+++"]"+"Year        :"+resultSet.getString("Year"));
                System.out.println("\t\t\t"+"["+indexno+++"]"+"Price       :"+resultSet.getString("price"));
                System.out.println("\t\t\t"+"["+indexno+++"]"+"Sale_Status :"+resultSet.getString("Sale_Status"));
                System.out.println();
            }
        }
        catch (Exception e)
        {

        }
        pausepaly();

    }
}

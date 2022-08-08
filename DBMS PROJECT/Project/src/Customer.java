import com.mysql.cj.x.protobuf.MysqlxCrud;

import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.sql.*;
import java.util.concurrent.Executor;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Customer
{
    void design() {
        System.out.println("\t**********************************************************************************");
        System.out.println("\t\t\t******  Auto Dealership  ******");
        System.out.println("\t\t\t\t\t\t\t\t\tBuy your wheels");
        System.out.println("\n");
        System.out.println("\t\t\t\t\t\t\tCustomer Portal");
        System.out.println("\n\n");
    }
    void customer() throws SQLException {
        design();
        System.out.println("\t\t[1] Login\t\t\t\t\t\t[2] SignUp");
        System.out.println("\t\t[3] Exit");
        System.out.println();
        Scanner box = new Scanner(System.in);
        int choice;
        System.out.print("\t\tEnter Choice:");
        choice = box.nextInt();
        if (choice==1)
        {
            login();

        }
        else if (choice==2)
        {
            Signup();
        }
        else if (choice==3)
        {
            Main_menu main_menu = new Main_menu();
            main_menu.mainMenu();
        }
        else
        {
            System.out.println("Wrong Input,try again");
            customer();
        }
    }
    void login()
    {
        design();
        try
        {
            int ID = 0;
            String First_name,Last_name,phoneNo,Address,City,province,Country,postal_code;
            connection con = new connection();
            Scanner box1 = new Scanner(System.in);
            System.out.print("Customer ID : ");
            ID= box1.nextInt();
            ResultSet resultSet =con.st.executeQuery("select * from customer where Customer_ID ="+ID);
            while (resultSet.next())
            {

                ID=resultSet.getInt("Customer_ID");
                First_name=resultSet.getString("First_Name");
                Last_name=resultSet.getString("Last_Name");
                phoneNo=resultSet.getString("Phone_Number");
                Address=resultSet.getString("Address");
                City=resultSet.getString("City");
                province=resultSet.getString("Province");
                Country=resultSet.getString("Country");
                postal_code=resultSet.getString("Postal_code");

                System.out.println("\t\t\tID          :"+resultSet.getInt("Customer_ID"));
                System.out.println("\t\t\tFirst Name  :"+resultSet.getString("First_Name"));
                System.out.println("\t\t\tLast Name   :"+resultSet.getString("Last_Name"));
                System.out.println("\t\t\tPhone No    :"+resultSet.getString("Phone_Number"));
                System.out.println("\t\t\tAddress     :"+resultSet.getString("Address"));
                System.out.println("\t\t\tCity        :"+resultSet.getString("City"));
                System.out.println("\t\t\tProvince    :"+resultSet.getString("Province"));
                System.out.println("\t\t\tCountry     :"+resultSet.getString("Country"));
                System.out.println("\t\t\tPostal code :"+resultSet.getString("Postal_code"));

            }
        }
        catch (Exception e)
        {
            //System.out.println(e);
        }


        Scanner box2 = new Scanner(System.in);
        System.out.print("go to Customer options ?[Y/N]");
        String Status =box2.nextLine();
        if (Status.equals("Y") || Status.equals("y"))
        {
            try {

                customer();
            } catch (SQLException e) {
                // e.printStackTrace();
            }
        }
        else
        {
            System.exit(0);
        }

    }


    void Signup(){
        design();
        int ID,tempid=0;
        String First_name,Last_name,phoneNo,Address,City,province,Country,postal_code;
        System.out.println("\t\t\tNew Customer");
        Scanner box1 = new Scanner(System.in);
        Scanner box2 = new Scanner(System.in);
        connection conn = new connection();
        try {
            ResultSet resultSet =conn.st.executeQuery("select * from customer");
            while (resultSet.next())
            {
                tempid=resultSet.getInt("Customer_ID");
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        }

        ID=++tempid;
        System.out.print("\t\tEnter First name:");
        First_name=box2.nextLine();
        System.out.print("\t\tEnter Last name:");
        Last_name=box2.nextLine();
        System.out.print("\t\tEnter Contact no :");
        phoneNo=box2.nextLine();
        System.out.print("\t\tEnter Address :");
        Address=box2.nextLine();
        System.out.print("\t\tEnter City :");
        City=box2.nextLine();
        System.out.print("\t\tEnter province :");
        province=box2.nextLine();
        System.out.print("\t\tEnter Country :");
        Country=box2.nextLine();
        System.out.print("\t\tEnter postal code :");
        postal_code=box2.nextLine();
        try{
            connection con = new connection();
            String sql = "insert INTO Customer VALUES("+ID+",'"+First_name+"','"+Last_name+"','"+phoneNo+"','"+Address+"','"+City+"','"+province+"','"+Country+"','"+postal_code+"')";
            con.st.executeUpdate(sql);
            System.out.println("\t\t\t\t\t\tSignUp successfully");
            System.out.println("\t\t\tYour Customer ID : "+ID);

        }
        catch (Exception e)
        {
            //System.out.println(e);
            //e.printStackTrace();
        }
        boolean status=false;
        System.out.print("go to Customer options ?[Y/N]");
        String Status =box2.nextLine();
        if (Status.equals("Y") || Status.equals("y"))
        {
            try {

                customer();
            } catch (SQLException e) {
                //e.printStackTrace();
            }
        }
        else
        {
            System.exit(0);
        }



    }

    public static void main(String[] args) {
        Customer customer =new Customer();
        try {
            customer.customer();
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        customer.Signup();
        customer.login();


    }
}

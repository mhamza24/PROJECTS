import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Scanner;

public class Service
{
    void design()
    {
        System.out.println("\t**********************************************************************************");
        System.out.println("\t\t\t******  Auto Dealership  ******");
        System.out.println("\t\t\t\t\t\t\t\t\tBuy your wheels");
        System.out.println("\n");
        System.out.println("\t\t\t\t\t\t\tService Portal");
        System.out.println("\n\n");
    }
    void pausepaly()
    {
        Scanner box2 = new Scanner(System.in);
        System.out.print("go to Service options ?[Y/N]");
        String Status =box2.nextLine();
        if (Status.equals("Y") || Status.equals("y"))
        {

            service();
        }
        else
        {
            System.exit(0);
        }
    }



     void service()
    {
        design();
        System.out.println("\t\t[1] Oil Change & Car wash\t\t\t\t\t\t[2] General inspection");
        System.out.println("\t\t[3]  Premium  inspection  \t\t\t\t\t\t[4] Exit");
        System.out.println();
        Scanner box = new Scanner(System.in);
        int choice;
        System.out.print("\t\tEnter Choice:");
        choice = box.nextInt();
        if (choice==1)
        {
            oilChange();

        }
        else if (choice==2)
        {
            generalinspection();
        }
        else if (choice==3)
        {
            premiumInspection();
        }
        else if (choice==4)
        {
            Main_menu main_menu = new Main_menu();
            main_menu.mainMenu();
        }
        else
        {
            System.out.println("Wrong Input,try again");
            service();
        }
    }

    void oilChange()
    {
        design();
        try
        {
            int tempid=0,tempserailno=0,Service_ticket_ID,Service_ticket_No,Car_ID,Customer_ID,Mechanic_id;
            String Received_Date,Return_Date,Comments;
            float price=4950;
            Scanner box1 = new Scanner(System.in);
            Scanner box2 = new Scanner(System.in);
            connection conn = new connection();
            ResultSet resultSet =conn.st.executeQuery("select * from Service_ticket");
            while (resultSet.next())
            {
                tempid=resultSet.getInt("Service_ticket_ID");
                tempserailno=resultSet.getInt("Service_ticket_No");
            }
            ResultSet resultSet1 =conn.st.executeQuery("select * from Parts where Part_ID=3");
            while (resultSet1.next())
            {
                price+=resultSet1.getFloat("Price");
            }ResultSet resultSet2 =conn.st.executeQuery("select * from Parts where Part_ID=4");
            while (resultSet2.next())
            {
                price+=resultSet2.getFloat("Price");
            }
            Service_ticket_ID=++tempid;
            Service_ticket_No=++tempserailno;
            System.out.print("\t\tEnter Car ID      :");
            Car_ID=box1.nextInt();
            System.out.print("\t\tEnter CustomerID  :");
            Customer_ID=box1.nextInt();
            System.out.println("\tAvalable Mechanics ");
            System.out.println("\t\t[1]Aslam Khan");
            System.out.println("\t\t[2]Muhammad Sohaib");
            System.out.println("\t\t[3]Shakoor Sheikh");
            System.out.println("\t\t[4]Muhammad Haroon");
            System.out.print("\t\tEnter Mechanic ID :");
            Mechanic_id=box1.nextInt();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();
            Received_Date=  dtf.format(now);
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            cal.add(Calendar.DAY_OF_MONTH, 1);
            Return_Date=sdf.format(cal.getTime());
            System.out.print("\t\tEnter comments    :");
            Comments=box2.nextLine();

                try
                {

                    String sql = "insert INTO Service_ticket VALUES(" + Service_ticket_ID + "," + Service_ticket_No + "," + Car_ID + "," + Customer_ID + ",'"+Received_Date+"','"+Return_Date+"','"+Comments+"',"+Mechanic_id+","+price+")";
                    conn.st.executeUpdate(sql);
                    System.out.println("\t\tService ticket generated successfully");
                }
                catch (Exception e)
                {
                    // System.out.println(e);
                }




        }catch (Exception e)
        {

        }
        pausepaly();


    }
    void generalinspection()
    {
        design();
        try
        {
            int tempid=0,tempserailno=0,Service_ticket_ID,Service_ticket_No,Car_ID,Customer_ID,Mechanic_id;
            String Received_Date,Return_Date,Comments;
            float price=2499;
            Scanner box1 = new Scanner(System.in);
            Scanner box2 = new Scanner(System.in);
            connection conn = new connection();
            ResultSet resultSet =conn.st.executeQuery("select * from Service_ticket");
            while (resultSet.next())
            {
                tempid=resultSet.getInt("Service_ticket_ID");
                tempserailno=resultSet.getInt("Service_ticket_No");
            }
            int min = 1;
            int max = 10;

            int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
            ResultSet resultSet1 =conn.st.executeQuery("select * from Parts where Part_ID="+random_int);
            while (resultSet1.next())
            {
                price+=resultSet1.getFloat("Price");

            }
            Service_ticket_ID=++tempid;
            Service_ticket_No=++tempserailno;
            System.out.print("\t\tEnter Car ID      :");
            Car_ID=box1.nextInt();
            System.out.print("\t\tEnter CustomerID  :");
            Customer_ID=box1.nextInt();
            System.out.println("\tAvalable Mechanics ");
            System.out.println("\t\t[1]Aslam Khan");
            System.out.println("\t\t[2]Muhammad Sohaib");
            System.out.println("\t\t[3]Shakoor Sheikh");
            System.out.println("\t\t[4]Muhammad Haroon");
            System.out.print("\t\tEnter Mechanic ID :");
            Mechanic_id=box1.nextInt();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();
            Received_Date=  dtf.format(now);
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            cal.add(Calendar.DAY_OF_MONTH, 3);
            Return_Date=sdf.format(cal.getTime());
            System.out.print("\t\tEnter comments    :");
            Comments=box2.nextLine();
            try
            {

                String sql = "insert INTO Service_ticket VALUES(" + Service_ticket_ID + "," + Service_ticket_No + "," + Car_ID + "," + Customer_ID + ",'"+Received_Date+"','"+Return_Date+"','"+Comments+"',"+Mechanic_id+","+price+")";
                conn.st.executeUpdate(sql);
                System.out.println("\t\tService ticket generated successfully");
            }
            catch (Exception e)
            {

            }

        }catch (Exception e)
        {

        }
        pausepaly();
    }

    void premiumInspection()
    {
        design();
        try
        {
            int tempid=0,tempserailno=0,Service_ticket_ID,Service_ticket_No,Car_ID,Customer_ID,Mechanic_id;
            String Received_Date,Return_Date,Comments;
            float price=8499;
            Scanner box1 = new Scanner(System.in);
            Scanner box2 = new Scanner(System.in);
            connection conn = new connection();
            ResultSet resultSet =conn.st.executeQuery("select * from Service_ticket");
            while (resultSet.next())
            {
                tempid=resultSet.getInt("Service_ticket_ID");
                tempserailno=resultSet.getInt("Service_ticket_No");
            }
            int min = 11;
            int max = 20;

            int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
            ResultSet resultSet1 =conn.st.executeQuery("select * from Parts where Part_ID="+random_int);
            while (resultSet1.next())
            {
                price+=resultSet1.getFloat("Price");

            }
            Service_ticket_ID=++tempid;
            Service_ticket_No=++tempserailno;
            System.out.print("\t\tEnter Car ID      :");
            Car_ID=box1.nextInt();
            System.out.print("\t\tEnter CustomerID  :");
            Customer_ID=box1.nextInt();
            System.out.println("\tAvalable Mechanics ");
            System.out.println("\t\t[1]Aslam Khan");
            System.out.println("\t\t[2]Muhammad Sohaib");
            System.out.println("\t\t[3]Shakoor Sheikh");
            System.out.println("\t\t[4]Muhammad Haroon");
            System.out.print("\t\tEnter Mechanic ID :");
            Mechanic_id=box1.nextInt();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();
            Received_Date=  dtf.format(now);
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            cal.add(Calendar.DAY_OF_MONTH, 5);
            Return_Date=sdf.format(cal.getTime());
            System.out.print("\t\tEnter comments    :");
            Comments=box2.nextLine();
            try
            {

                String sql = "insert INTO Service_ticket VALUES("+ Service_ticket_ID + "," + Service_ticket_No + "," + Car_ID + "," + Customer_ID + ",'"+Received_Date+"','"+Return_Date+"','"+Comments+"',"+Mechanic_id+","+price+")";
                conn.st.executeUpdate(sql);
                System.out.println("\t\tService ticket generated successfully");
            }
            catch (Exception e)
            {

            }

        }catch (Exception e)
        {

        }
        pausepaly();

    }

    public static void main(String[] args) {
        Service s=new Service();
        s.service();
    }

}


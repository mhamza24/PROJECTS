import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class Car
{
    void design() {
        System.out.println("\t**********************************************************************************");
        System.out.println("\t\t\t******  Auto Dealership  ******");
        System.out.println("\t\t\t\t\t\t\t\t\tBuy your wheels");
        System.out.println("\n");
        System.out.println("\t\t\t\t\t\t\tCar Portal");
        System.out.println("\n\n");

    }
    void pausepaly()
    {
        Scanner box2 = new Scanner(System.in);
        System.out.print("go to Car options ?[Y/N]");
        String Status =box2.nextLine();
        if (Status.equals("Y") || Status.equals("y"))
        {

            carMenu();
        }
        else
        {
            System.exit(0);
        }
    }

    void carMenu()
    {
        design();
        System.out.println("\t\t[1] Sell\t\t\t\t\t\t[2] Buy");
        System.out.println("\t\t[3] View\t\t\t\t\t\t[4] Exit");
        System.out.println();
        Scanner box = new Scanner(System.in);
        int choice;
        System.out.print("\t\tEnter Choice:");
        choice = box.nextInt();
        if (choice==1)
        {
            sell();

        }
        else if (choice==2)
        {
            buy();
        }
        else if (choice==3)
        {
            view();
        }
        else if (choice==4)
        {
            Main_menu main_menu = new Main_menu();
            main_menu.mainMenu();
        }
        else
        {
            System.out.println("Wrong Input,try again");
            carMenu();
        }

    }

    void sell()
    {
        int Car_ID,Serial_No,tempserailno=0,tempid=0,price;
        String Make,Model,Color,Year,Sale_Status;
        System.out.println("\t\t\tNew Car");
        Scanner box1 = new Scanner(System.in);
        Scanner box2 = new Scanner(System.in);
        connection conn = new connection();
        try {
            ResultSet resultSet =conn.st.executeQuery("select * from car");
            while (resultSet.next())
            {
                tempid=resultSet.getInt("Car_ID");
                tempserailno=resultSet.getInt("Serial_No");
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        }

        Car_ID=++tempid;
        Serial_No=++tempserailno;
        System.out.print("\t\tEnter Make            :");
        Make=box2.nextLine();
        System.out.print("\t\tEnter Model           :");
        Model=box2.nextLine();
        System.out.print("\t\tEnter Color           :");
        Color=box2.nextLine();
        System.out.print("\t\tEnter production year :");
        Year=box2.nextLine();
        System.out.print("\t\tSet price             :");
        price=box1.nextInt();
        Sale_Status="In Stock";
        try{
            connection con = new connection();
            String sql = "insert INTO Car VALUES("+Car_ID+","+Serial_No+",'"+Make+"','"+Model+"','"+Color+"','"+Year+"',"+price+    ",'"+Sale_Status+"')";
            con.st.executeUpdate(sql);
            System.out.println("\t\tCar Listed successfully");

        }
        catch (Exception e)
        {
            //e.printStackTrace();
        }
        pausepaly();

    }
    void view()
    {
        design();
        try
        {
            int Car_ID,Serial_No,tempserailno=0,tempid=0,price;
            String Make,Model,Color,Year,Sale_Status;
            connection con = new connection();
            Scanner box1 = new Scanner(System.in);
            Scanner box2 = new Scanner(System.in);
            System.out.print("\t\tSerch Options ");
            System.out.print("\t\t\t[1] Car ID   ");
            System.out.print("\t\t\t[2] Color ");
            System.out.print("\t\t\t[3] Year");
            System.out.print("\t\t\t[4] Brand");
            System.out.println();
            System.out.print("Enter serch option:");
            int choice=box1.nextInt();
            if (choice==1)
            {
                System.out.print("Car ID : ");
                int car_ID= box2.nextInt();
                ResultSet resultSet =con.st.executeQuery("select * from Car where Car_ID ="+car_ID);
                while (resultSet.next())
                {

                    Car_ID=resultSet.getInt("Car_ID");
                    Serial_No=resultSet.getInt("Serial_No");
                    Make=resultSet.getString("Make");
                    Model=resultSet.getString("Model");
                    Color=resultSet.getString("Color");
                    Year=resultSet.getString("Year");
                    price= (int) resultSet.getFloat("price");
                    Sale_Status=resultSet.getString("Sale_Status");


                    System.out.println("\t\t\tID          :"+resultSet.getInt("Car_ID"));
                    System.out.println("\t\t\tSerial no   :"+resultSet.getString("Serial_No"));
                    System.out.println("\t\t\tMake        :"+resultSet.getString("Make"));
                    System.out.println("\t\t\tModel       :"+resultSet.getString("Model"));
                    System.out.println("\t\t\tColor       :"+resultSet.getString("Color"));
                    System.out.println("\t\t\tYear        :"+resultSet.getString("Year"));
                    System.out.println("\t\t\tPrice       :"+resultSet.getString("price"));
                    System.out.println("\t\t\tSale_Status :"+resultSet.getString("Sale_Status"));


                }
            }
            else if (choice==2)
            {
                System.out.println("\t\tAvaliable Colors : Black,White");
                System.out.print("Car Color : ");
                String color= box2.nextLine();
                System.out.println();
                String sql;
                if(color.equals("Black") || color.equals("black"))
                {
                     sql="select * from Car where Color like 'Black'";
                }
                else if(color.equals("White") || color.equals("white"))
                {
                     sql="select * from Car where Color like 'White'";
                }
                else
                {
                     sql="select * from Car";
                }

                ResultSet resultSet =con.st.executeQuery(sql);
                while (resultSet.next())
                {

                    Car_ID=resultSet.getInt("Car_ID");
                    Serial_No=resultSet.getInt("Serial_No");
                    Make=resultSet.getString("Make");
                    Model=resultSet.getString("Model");
                    Color=resultSet.getString("Color");
                    Year=resultSet.getString("Year");
                    price= (int) resultSet.getFloat("price");
                    Sale_Status=resultSet.getString("Sale_Status");


                    System.out.println("\t\t\tID          :"+resultSet.getInt("Car_ID"));
                    System.out.println("\t\t\tSerial no   :"+resultSet.getString("Serial_No"));
                    System.out.println("\t\t\tMake        :"+resultSet.getString("Make"));
                    System.out.println("\t\t\tModel       :"+resultSet.getString("Model"));
                    System.out.println("\t\t\tColor       :"+resultSet.getString("Color"));
                    System.out.println("\t\t\tYear        :"+resultSet.getString("Year"));
                    System.out.println("\t\t\tPrice        :"+resultSet.getString("price"));
                    System.out.println("\t\t\tSale_Status :"+resultSet.getString("Sale_Status"));


                }
            }
            else if (choice==3)
            {
                System.out.println("\t\tAvaliable Year : 2020,2021,2022");
                System.out.print("Car Year : ");
                Year= box2.nextLine();
                String sql;
                if(Year.equals("2020"))
                {
                    sql="select * from Car where Year like '2020'";
                }
                else if(Year.equals("2021"))
                {
                    sql="select * from Car where Year like '2021'";
                }
                else if(Year.equals("2022"))
                {
                    sql="select * from Car where Year like '2022'";
                }
                else
                {
                    sql="select * from Car";
                }
                ResultSet resultSet =con.st.executeQuery(sql);
                while (resultSet.next())
                {

                    Car_ID=resultSet.getInt("Car_ID");
                    Serial_No=resultSet.getInt("Serial_No");
                    Make=resultSet.getString("Make");
                    Model=resultSet.getString("Model");
                    Color=resultSet.getString("Color");
                    Year=resultSet.getString("Year");
                    price= (int) resultSet.getFloat("price");
                    Sale_Status=resultSet.getString("Sale_Status");


                    System.out.println("\t\t\tID          :"+resultSet.getInt("Car_ID"));
                    System.out.println("\t\t\tSerial no   :"+resultSet.getString("Serial_No"));
                    System.out.println("\t\t\tMake        :"+resultSet.getString("Make"));
                    System.out.println("\t\t\tModel       :"+resultSet.getString("Model"));
                    System.out.println("\t\t\tColor       :"+resultSet.getString("Color"));
                    System.out.println("\t\t\tYear        :"+resultSet.getString("Year"));
                    System.out.println("\t\t\tPrice        :"+resultSet.getString("price"));
                    System.out.println("\t\t\tSale_Status :"+resultSet.getString("Sale_Status"));


                }
            }
            else if (choice==4)
            {
                System.out.println("\t\tAvaliable Year : Honda,Toyota,Suzuki");
                System.out.print("Car Brand : ");
                Make= box2.nextLine();
                String sql;
                if(Make.equals("Honda") || Make.equals("honda"))
                {
                    sql="select * from Car where Make like 'Honda'";
                }
                else if(Make.equals("Toyota") || Make.equals("toyota"))
                {
                    sql="select * from Car where Make like 'Toyota'";
                }
                else if(Make.equals("Suzuki") || Make.equals("suzuki"))
                {
                    sql="select * from Car where Make like 'Suzuki'";
                }
                else
                {
                    sql="select * from Car";
                }
                ResultSet resultSet =con.st.executeQuery(sql);
                while (resultSet.next())
                {

                    Car_ID=resultSet.getInt("Car_ID");
                    Serial_No=resultSet.getInt("Serial_No");
                    Make=resultSet.getString("Make");
                    Model=resultSet.getString("Model");
                    Color=resultSet.getString("Color");
                    Year=resultSet.getString("Year");
                    price= (int) resultSet.getFloat("price");

                    Sale_Status=resultSet.getString("Sale_Status");


                    System.out.println("\t\t\tID          :"+resultSet.getInt("Car_ID"));
                    System.out.println("\t\t\tSerial no   :"+resultSet.getString("Serial_No"));
                    System.out.println("\t\t\tMake        :"+resultSet.getString("Make"));
                    System.out.println("\t\t\tModel       :"+resultSet.getString("Model"));
                    System.out.println("\t\t\tColor       :"+resultSet.getString("Color"));
                    System.out.println("\t\t\tYear        :"+resultSet.getString("Year"));
                    System.out.println("\t\t\tPrice        :"+resultSet.getString("price"));
                    System.out.println("\t\t\tSale_Status :"+resultSet.getString("Sale_Status"));


                }
            }
            else
            {
                view();
            }


        }
        catch (Exception e)
        {
            //System.out.println(e);
        }

        pausepaly();

    }

    void buy() {
        design();
        try {
            int ID, tempid = 0, Salesperson_ID;
            float price= 0;
            String date;
            connection con = new connection();
            Scanner box1 = new Scanner(System.in);
            Scanner box2 = new Scanner(System.in);
            System.out.println("\t\t\tPurchasing");
            System.out.print("\t\tCustomer ID           : ");
            int Customer_ID = box1.nextInt();
            System.out.println();
            System.out.print("\t\tEnter Car ID          : ");
            int Car_ID = box1.nextInt();
            try {
                ResultSet resultSet = con.st.executeQuery("select * from car");
                while (resultSet.next()) {
                    price = resultSet.getInt("price");
                }
            } catch (Exception e) {
                //System.out.println(e);
            }
            try {
                ResultSet resultSet = con.st.executeQuery("select * from Sales_Invoice");
                while (resultSet.next()) {
                    tempid = resultSet.getInt("Sales_Invoice_ID");
                }
            } catch (Exception e) {
                //System.out.println(e);
            }
            ID = ++tempid;
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();
            date=dtf.format(now);
            System.out.println("\tAvalable Salesperson ");
            System.out.println("\t\t[1]Ayan Khan");
            System.out.println("\t\t[2]Muhammad Imran");
            System.out.println("\t\t[3]Mohsin Ahmed");
            System.out.println("\t\t[4]Sohail Tanveer");
            System.out.print("\t\tEnter Salesperson ID   :");
            Salesperson_ID = box1.nextInt();
            try {

                connection conn = new connection();
                String sql = "insert INTO Sales_Invoice VALUES(" + ID + "," + date + "," + Car_ID + "," + Customer_ID + "," +Salesperson_ID+","+price+")";
                String sql2="update car set Sale_status=('Sold out') where Car_ID="+Car_ID;
                conn.st.executeUpdate(sql);
                conn.st.executeUpdate(sql2);
                System.out.println("\t\t\t\tInvoice generated successfully");
                System.out.println("\t\tYour car will be deliver within 15 days,Thankyou for choosing us !");

            } catch (Exception e) {
                //e.printStackTrace();
            }

        } catch (Exception e) {
            //e.printStackTrace();
        }
        pausepaly();


    }

    public static void main(String[] args) {
        Car car=new Car();
        car.buy();
    }
}


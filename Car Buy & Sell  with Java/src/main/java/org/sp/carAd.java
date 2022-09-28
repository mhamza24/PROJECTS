package org.sp;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class carAd
{
    Scanner box1 =new Scanner(System.in);
    Scanner box2 =new Scanner(System.in);
    static int customerId=0;

    void design()
    {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("\t\t\t*************************** Wheels For You ***************************");
        System.out.println("\t\t\t\t*************************** Buy your sawari today ***************************");
        System.out.println();
        System.out.println("\t\tCar Portal");
        System.out.println();
        System.out.println("\t\t\t\t\t\t*************************** ");
        System.out.println();
    }

    void sell(int customerId)
    {
        System.out.println();
        System.out.println();
        design();
        System.out.println();
        System.out.println("\t\tSell your Sawari today !");
        System.out.println();
        String make,model,varient,engineCapacity,color,year,additionalNote,dateTime;
        float price;
        boolean saleSatatus,fastersale;
        int view;

        System.out.print("\t\t Make                  : ");
        make = box1.nextLine();
        System.out.println();
        System.out.print("\t\t Model                 : ");
        model = box1.nextLine();
        System.out.println();
        System.out.print("\t\t Varient               : ");
        varient = box1.nextLine();
        System.out.println();
        System.out.print("\t\t Engine Capaccity (cc) : ");
        engineCapacity = box1.nextLine();
        System.out.println();
        System.out.print("\t\t Color                 : ");
        color = box1.nextLine();
        System.out.println();
        System.out.print("\t\t launch Year           : ");
        year = box1.nextLine();
        System.out.println();
        System.out.print("\t\t Price                 : ");
        price = box2.nextFloat();
        System.out.println();
        System.out.print("\t\t Additional note to customer (max 50 words) : ");
        additionalNote = box1.nextLine();
        System.out.println();
        System.out.println("\tDo you want to use are faster sale service ?");
        System.out.println("\tRs 1785/Daily");
        System.out.println();
        System.out.println("\t\tY/N");
        String yN=box1.nextLine();
        if(yN.equals("Y") || yN.equals("y"))
        {
            fastersale=true;
            System.out.println("\t\tYou will be chareged Rs 1785 daily");
        }
        else
        {
            fastersale=false;
        }
        saleSatatus=false;
        view=0;
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        dateTime = currentDateTime.format(myFormatObj);


        try
        {
            dbconnection dbconnection = new dbconnection();
            String sql = "insert into car values('"+0+"','" + customerId + "','" + make + "','" + model + "','" + varient + "','" + engineCapacity + "','" + color + "','" + year + "','" + price + "'," + saleSatatus + ",'" + additionalNote + "'," + fastersale + ",'"+view+"','"+dateTime+"')";
            dbconnection.st.executeUpdate(sql);
            System.out.println();
            System.out.println();
            System.out.println("\t\t\tCar ad Posted successfully");
            System.out.println();
            try
            {
                Thread.sleep(2000);
            }
            catch (InterruptedException ex)
            {
                ex.printStackTrace();

            }
            System.out.println();
            ResultSet resultSet=dbconnection.st.executeQuery("select * from car where Customer_ID="+customerId+" order by Car_ID desc limit 1");
            while (resultSet.next())
            {
                System.out.println();
                System.out.println("\t\tYour Post ID : "+resultSet.getInt("Car_ID"));
                System.out.println();
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException ex)
                {
                    ex.printStackTrace();
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        customer customer =new customer();
        customer.loginMenu();

    }
    void buy(int customerId)
    {
        dbconnection dbconnection=  new dbconnection();

        int carId,userId = 0;
        String dateTime,userMail = null;
        float price=0;
        boolean saleSatatus,fastersale,verificationStatus;

        System.out.println();
        System.out.println();
        design();
        System.out.println();
        System.out.println("\t\t\tBuy your Sawari today  !");
        System.out.println();
        System.out.println();
        System.out.print("\t\tCar ID :");
        carId=box2.nextInt();

        try
        {
            allFunctionsCar allFunctionsCar = new allFunctionsCar();
            ResultSet resultSet = dbconnection.st.executeQuery("select * from car where car_Id="+carId);
            allFunctionsCar.carDetails(resultSet,"insider");
            System.out.println();
            System.out.println();
            ResultSet resultSet3 = dbconnection.st.executeQuery("select * from car where car_Id="+carId);
            while (resultSet3.next())
            {
                if (resultSet3.getBoolean("Sale_Status")==true)
                {
                    System.out.println();
                    System.out.println("\t\tYou are late ,car has been sold !");
                }
                else {
                    userId=resultSet3.getInt("customer_id");
                    price=resultSet3.getFloat("price");
                }


            }

            System.out.println();
            System.out.println("\t\tConfirm this Purchase by email verification");
            ResultSet resultSet2=dbconnection.st.executeQuery("select * from customer where customer_Id="+customerId);
            while (resultSet2.next())
            {
                userMail=resultSet2.getString("email");

            }
            if(userId==customerId)
            {
                System.out.println();
                System.out.println("\t\tYou can not buy your own car");
                try {
                    Thread.sleep(3000);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                return;

            }
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            dateTime = currentDateTime.format(myFormatObj);
            allFunctionsCustomer allFunctionsCustomer=new allFunctionsCustomer();
            verificationStatus=allFunctionsCustomer.codeGeneratorAndSender(userMail,"Purchase Confirmation");
            if(verificationStatus==true)
            {
                String sql="insert into Sales_Invoice values ('"+0+"','"+dateTime+"','"+carId+"','"+customerId+"','"+price+"')";
                dbconnection.st.executeUpdate(sql);
                System.out.println("Purchased Confirm");
                dbconnection.st.executeUpdate("update car set Sale_Status = 1 where Car_ID="+carId);

                try {
                    Thread.sleep(3000);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                allFunctionsCustomer.emailNotification(customerId,"Purchase Confirm");
                try {
                    Thread.sleep(1000);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                allFunctionsCustomer.emailNotification(userId,"Car Sold");

            }
            else
            {
                System.out.println();
                System.out.println("\t\tPurchased Failed.");

            }
            customer customer = new customer();
            customer.loginMenu();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    void view(int customerId)
    {
        System.out.println();
        System.out.println();
        design();
        System.out.println();
        System.out.println("\t\t\tSawariyan hi Sawariyan,buy yours today !");
        System.out.println();
        System.out.println("\t\t[1] Your Ads  \t\t[2] All Ads");
        System.out.println();
        System.out.println("\t\t[3] Random Ads \t\t[4] Filters");
        System.out.println();
        System.out.println("\t\t[5] Exit ");
        System.out.println();
        System.out.print("\tEnter choice : ");
        int choice =box2.nextInt();
        if(choice==1)
        {

            allFunctionsCar allFunctionsCar = new allFunctionsCar();
            allFunctionsCar.customerAd(customerId);
        }
        else if(choice==2)
        {
            allFunctionsCar allFunctionsCar = new allFunctionsCar();
            allFunctionsCar.allAds(customerId);
        }
        else if(choice==3)
        {
            allFunctionsCar allFunctionsCar = new allFunctionsCar();
            allFunctionsCar.randomAds(customerId);
        }
        else if(choice==4)
        {
            allFunctionsCar allFunctionsCar = new allFunctionsCar();
            allFunctionsCar.filters(customerId);
        }
        else if(choice==5)
        {
            if(customerId==0)
            {
                mainScreen mainScreen =new mainScreen();
                mainScreen.mainMenu();
            }
            else
            {
                customer customer =new customer();
                customer.loginMenu();
            }
        }
        else
        {
            if(customerId==0)
            {
                mainScreen mainScreen =new mainScreen();
                mainScreen.mainMenu();
            }
            else
            {
                customer customer =new customer();
                customer.loginMenu();
            }
        }


    }
}

package org.sp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class allFunctionsCar
{
    Scanner box1 =new Scanner(System.in);
    Scanner box2 =new Scanner(System.in);






    void changefasterSaleOption(int customerId,int adId)
    {

        dbconnection dbconnection = new dbconnection();
        boolean status = false;
        System.out.println();
        System.out.println("\t\t[1] Eanble\t\t[2]Disable");
        System.out.println();
        System.out.println("\t\t[3] Exit");
        System.out.println();
        System.out.println();
        int choice;
        System.out.print("\tYour Choice: ");
        choice = box2.nextInt();
        customer customer = new customer();
        try
        {
            ResultSet resultSet=dbconnection.st.executeQuery("select * from car where car_ID="+adId);
            while (resultSet.next())
            {
                status=resultSet.getBoolean("fastersale");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        if(choice==1)
        {
            if (status == true)
            {
                System.out.println();
                System.out.println("\t\tFasterSale is already Enabled ");
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                customer.loginMenu();
            }
            else
            {
                String sql="update car set fastersale = 1 where Car_ID="+adId;
                try
                {
                    dbconnection.st.executeUpdate(sql);
                    System.out.println();
                    System.out.println("\t\tFasterSale is Enabled");
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
        else if (choice==2)
        {
            if (status == false)
            {
                System.out.println();
                System.out.println("\t\tFasterSale is already Disabled ");
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                customer.loginMenu();
            }
            else
            {
                String sql="update car set fastersale = 0 where Car_ID="+adId;
                try
                {
                    dbconnection.st.executeUpdate(sql);
                    System.out.println();
                    System.out.println("\t\tFasterSale is Disable");

                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
        else
        {
            customer.loginMenu();
        }
        try
        {
            Thread.sleep(3000);
        }
        catch (InterruptedException ex)
        {
            ex.printStackTrace();
        }



    }
    int tries=1;
    boolean loginStatus=true;
    void customerAd(int customerId)
    {
        dbconnection dbconnection = new dbconnection();
        String userName;

        if(customerId==0)
        {
            System.out.println();
            System.out.print("\tEnter your Username:");
            userName = box1.nextLine();
            try
            {
                String sql="select Customer_ID from Customer where Username like '%"+userName+"'";
                ResultSet resultSet=dbconnection.st.executeQuery(sql);
                while (resultSet.next())
                {
                    customerId=resultSet.getInt("Customer_ID");
                    loginStatus=false;
                }
                System.out.println();
                if (customerId==0)
                {
                    System.out.println("\t\tUsername Not Found");
                }
                try
                {
                    Thread.sleep(2000);
                }
                catch (InterruptedException ex)
                {
                    ex.printStackTrace();
                }
                if(tries++==3)
                {
                    mainScreen mainScreen =new mainScreen();
                    mainScreen.mainMenu();
                }
                customerAd(customerId);

            }

            catch (Exception e)
            {
                e.printStackTrace();
            }
            System.out.println();
        }

        try
        {
            ResultSet resultSet =  dbconnection.st.executeQuery("select * from car where Customer_ID="+customerId);
            System.out.println();
            carDetails(resultSet,"insider");
            if(loginStatus==true)
            {
                carAd carAd= new carAd();
                carAd.view(customerId);
            }
            else
            {
                mainScreen mainScreen =new mainScreen();
                mainScreen.mainMenu();
            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    void carDetails(ResultSet resultSet ,String status)
    {
       try
       {
           dbconnection dbconnection = new dbconnection();
           int count=0;
           while (resultSet.next())
           {

              if (status=="insider" || resultSet.getBoolean("Sale_Status")==false)
              {
                  System.out.println("\t\tAd no."+ ++count);
                  System.out.println();
                  System.out.println("\t\tAd Id                : "+resultSet.getInt("Car_ID"));
                  System.out.println("\t\tCustomer Id          : "+resultSet.getInt("customer_id"));
                  System.out.println("\t\tMake                 : "+resultSet.getString("Make"));
                  System.out.println("\t\tModel                : "+resultSet.getString("Model"));
                  System.out.println("\t\tVarient              : "+resultSet.getString("varient"));
                  System.out.println("\t\tEngine capacity (cc) : "+resultSet.getString("enginecapacity"));
                  System.out.println("\t\tColor                : "+resultSet.getString("Color"));
                  System.out.println("\t\tManufacture Year     : "+resultSet.getString("Year"));
                  System.out.println("\t\tPrice Rs             : "+resultSet.getFloat("price"));
                  System.out.println("\t\tSeller Note          : "+resultSet.getString("additionalInformation"));
                  System.out.println("\t\tViews                : "+resultSet.getInt("views"));
                  System.out.println("\t\tAd Posted on         : "+resultSet.getString("datetime"));

                  if(status.equals("insider"))
                  {
                      if(resultSet.getBoolean("Sale_Status")==true)
                      {
                  System.out.println("\t\tSale Status          : Sold");
                      }
                      else
                      {
                  System.out.println("\t\tSale Status          : Listed");
                      }

                  }

                  System.out.println();

                  if(status.equals("outsider"))
                  {
                      int views =resultSet.getInt("views");
                      int Car_ID =resultSet.getInt("Car_ID");
                      dbconnection.st.executeUpdate("update car set views="+ ++views+" where Car_ID="+Car_ID);
                      views=0;
                  }

              }
           }

            if (count<1)
            {
                System.out.println();
                System.out.println("\t\tNo ads has been listed with this account .");
                System.out.println();
            }



           System.out.println();
           System.out.print("\t\tPress Y to continue.");
           String y=box1.nextLine();
           if(y.equals("y") ||y.equals("Y"))
           {
                return;
           }

       }
       catch (Exception e)
       {
           e.printStackTrace();
       }
    }

    void  allAds(int customerId)
    {
        dbconnection dbconnection = new dbconnection();
        try
        {
            ResultSet resultSet = dbconnection.st.executeQuery("Select * from car");
            carDetails(resultSet,"outsider");
            if(loginStatus==true)
            {
                carAd carAd= new carAd();
                carAd.view(customerId);
            }
            else
            {
                mainScreen mainScreen =new mainScreen();
                mainScreen.mainMenu();
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    void randomAds(int customerId)
    {
        int max=0,min=0;
        System.out.println();
        dbconnection dbconnection = new dbconnection();
        try
        {
           ResultSet resultSet1= dbconnection.st.executeQuery("select Car_ID from car order by Car_ID asc limit 1");
            while (resultSet1.next())
            {
               min=resultSet1.getInt("Car_ID");
            }
            ResultSet resultSet2= dbconnection.st.executeQuery("select Car_ID from car order by Car_ID desc limit 1");
            while (resultSet2.next())
            {
                max=resultSet2.getInt("Car_ID");;
            }
            int  randomAd= (int)Math.floor(Math.random()*(max-min+1)+min);
                if(min!=0)
                {
                  ResultSet resultSet3= dbconnection.st.executeQuery("select * from car where Car_ID="+randomAd);
                  carDetails(resultSet3,"outsider");
                }
                else
                {
                    System.out.println("\t\tNo Ads found");
                }
            System.out.println();
            System.out.println();
            System.out.println("\t\tNext Ad\tY/N");
            String y=box1.nextLine();
            if(y.equals("y") ||y.equals("Y"))
            {
                randomAds(customerId);
            }
            else
            {
                carAd carAd = new carAd();
                carAd.view(customerId);
            }




        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    void filters(int customerId)
    {
        dbconnection dbconnection = new dbconnection();
        System.out.println();
        System.out.println();
        System.out.println("\t\t[1] By Make   \t\t[2] By Model");
        System.out.println();
        System.out.println("\t\t[3] By Color  \t\t[4] By Engine (cc)");
        System.out.println();
        System.out.println("\t\t[5] By Year   \t\t[6] By Date");
        System.out.println();
        System.out.println("\t\t[7] By Price  \t\t[8] By Views");
        System.out.println();
        System.out.println("\t\t[9] Exit ");
        System.out.println();
        System.out.print("\tEnter choice : ");
        int choice =box2.nextInt();
        String sql=null,filterString;
        int filterInt;

        if(choice==1)
        {
            System.out.println();
            sql="select DISTINCT make from car";
            ResultSet resultSet=filters(sql);
            sql=null;
            System.out.print("\tAll Makes: ");
            try
            {
                while (resultSet.next())
                {
                    System.out.print(resultSet.getString("make")+",");
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            System.out.println();
            System.out.print("\t\tMake : ");
            filterString =box1.nextLine();
            sql="select * from car where make like '"+filterString+"'";
            ResultSet resultSet1=filters(sql);
            System.out.println();
            carDetails(resultSet1,"outsider");


        }
        else if(choice==2)
        {

            System.out.println();
            sql="select DISTINCT model from car";
            ResultSet resultSet=filters(sql);
            sql=null;
            System.out.print("\tAll Models: ");
            try
            {
                while (resultSet.next())
                {
                    System.out.print(resultSet.getString("model")+",");
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            System.out.println();
            System.out.print("\t\tModel : ");
            filterString =box1.nextLine();
            sql="select * from car where model like '"+filterString+"'";
            ResultSet resultSet1=filters(sql);
            System.out.println();
            carDetails(resultSet1,"outsider");

        }
        else if(choice==3)
        {
            System.out.println();
            sql="select DISTINCT color from car";
            ResultSet resultSet=filters(sql);
            sql=null;
            System.out.print("\tAll Color: ");
            try
            {
                while (resultSet.next())
                {
                    System.out.print(resultSet.getString("color")+",");
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            System.out.println();
            System.out.print("\t\tcolor : ");
            filterString =box1.nextLine();
            sql="select * from car where color like '"+filterString+"'";
            ResultSet resultSet1=filters(sql);
            System.out.println();
            carDetails(resultSet1,"outsider");
        }
        else if(choice==4)
        {
            System.out.println();
            sql="select DISTINCT enginecapacity from car order by enginecapacity asc";
            ResultSet resultSet=filters(sql);
            sql=null;
            System.out.print("\tAll Engine capacity: ");
            try
            {
                while (resultSet.next())
                {
                    System.out.print(resultSet.getString("enginecapacity")+",");
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            System.out.println();
            System.out.print("\t\tEngine capacity : ");
            filterString =box1.nextLine();
            sql="select * from car where enginecapacity like '"+filterString+"'";
            ResultSet resultSet1=filters(sql);
            System.out.println();
            carDetails(resultSet1,"outsider");
        }
        else if(choice==5)
        {
            System.out.println();
            sql="select DISTINCT year from car";
            ResultSet resultSet=filters(sql);
            sql=null;
            System.out.print("\tAll Year: ");
            try
            {
                while (resultSet.next())
                {
                    System.out.print(resultSet.getString("year")+",");
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            System.out.println();
            System.out.print("\t\tYear : ");
            filterString =box1.nextLine();
            sql="select * from car where year like '"+filterString+"'";
            ResultSet resultSet1=filters(sql);
            System.out.println();
            carDetails(resultSet1,"outsider");
        }
        else if(choice==6)
        {
            System.out.println();
            System.out.println();
            System.out.print("\t\t[1] Recent \t\t[2] Older");
            System.out.println();
            System.out.println();
            System.out.print("\t\t[3] Exit ");
            System.out.println();
            System.out.println();
            System.out.print("\t\tSelect : ");
            filterInt = box2.nextInt();
            System.out.println();
            if (filterInt==1)
            {
                sql="select * from car order by Car_ID asc";
            }
            else if (filterInt==2)
            {
                sql="select * from car order by Car_ID desc";
            }
            else if (filterInt==3)
            {
                if (customerId==0)
                {
                    customer customer= new customer();
                    customer.loginMenu();
                }
                else
                {
                    carAd carAd=new carAd();
                    carAd.view(customerId);
                }

            }
            else
            {
                mainScreen mainScreen = new mainScreen();
                mainScreen.mainMenu();
            }
            ResultSet resultSet1=filters(sql);
            System.out.println();
            carDetails(resultSet1,"outsider");
        }
        else if(choice==7)
        {
            System.out.println();
            System.out.println();
            System.out.print("\t\t[1] High to low         \t\t[2] low to high");
            System.out.println();
            System.out.println();
            System.out.print("\t\t[3] Custom price braket \t\t[4] Fixed Price  ");
            System.out.println();
            System.out.println();
            System.out.print("\t\t[5] Exit ");
            System.out.println();
            System.out.println();
            System.out.print("\t\tSelect : ");
            filterInt = box2.nextInt();
            System.out.println();
            if (filterInt==1)
            {
                sql="select * from car order by price desc";
                ResultSet resultSet=filters(sql);
            }
            else if (filterInt==2)
            {
                sql="select * from car order by price asc";
                ResultSet resultSet=filters(sql);
            }
            else if (filterInt==3)
            {
                System.out.println();
                System.out.print("\t Min price :");
                float min=box2.nextFloat();
                System.out.print("\t Max price :");
                float max=box2.nextFloat();
                sql="select * from car where price>="+min+" && price <="+max;
                ResultSet resultSet=filters(sql);

            }
            else if (filterInt==4)
            {
                System.out.println();
                System.out.print("\t Price :");
                float price=box2.nextFloat();
                sql="select * from car where price="+price;
            }
            else if (filterInt==5)
            {
                if (customerId==0)
                {
                    customer customer= new customer();
                    customer.loginMenu();
                }
                else
                {
                    carAd carAd=new carAd();
                    carAd.view(customerId);
                }

            }
            else
            {
                mainScreen mainScreen = new mainScreen();
                mainScreen.mainMenu();
            }
            ResultSet resultSet=filters(sql);
            System.out.println();
            carDetails(resultSet,"outsider");
        }
        else if(choice==8)
        {
            System.out.println();
            System.out.println();
            System.out.print("\t\t[1] Most views \t\t[2] Less views");
            System.out.println();
            System.out.println();
            System.out.print("\t\t[3] Exit ");
            System.out.println();
            System.out.println();
            System.out.print("\t\tSelect : ");
            filterInt = box2.nextInt();
            System.out.println();
            if (filterInt==1)
            {
                sql="select * from car order by views desc";
            }
            else if (filterInt==2)
            {
                sql="select * from car order by views asc";

            }
            else if (filterInt==3)
            {
                if (customerId==0)
                {
                    customer customer= new customer();
                    customer.loginMenu();
                }
                else
                {
                    carAd carAd=new carAd();
                    carAd.view(customerId);
                }

            }
            else
            {
                mainScreen mainScreen = new mainScreen();
                mainScreen.mainMenu();
            }
            ResultSet resultSet=filters(sql);
            System.out.println();
            carDetails(resultSet,"outsider");


        }
        else if(choice==9)
        {
            carAd carAd = new carAd();
            carAd.view(customerId);
        }
        else
        {
            if(customerId==0)
            {
                mainScreen mainScreen = new mainScreen();
                mainScreen.mainMenu();
            }
            else
            {
                customer customer= new customer();
                customer.loginMenu();
            }
        }

        filters(customerId);

    }

    ResultSet filters(String sql)
    {
        dbconnection dbconnection = new dbconnection();
        ResultSet resultSet = null;
        try
        {
            resultSet = dbconnection.st.executeQuery(sql);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return resultSet;
    }



    void editAd(int customerId)
    {
        System.out.println(customerId);
        int adId=0;
        dbconnection dbconnection = new dbconnection();
        System.out.println();
        System.out.println();
        try
        {
            ResultSet resultSet = dbconnection.st.executeQuery("select * from car where Customer_ID=" + customerId);
            while (resultSet.next())
            {
                System.out.println();
                System.out.println("\t\t\tPost ID : " + resultSet.getInt("Car_ID"));
                System.out.println();

            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            Thread.sleep(3000);
        } catch (InterruptedException ex)
        {
            ex.printStackTrace();

        }
        System.out.println(customerId);
        System.out.println("\tSelect your Ad for fastersale");
        System.out.print("\t\tAd ID: ");
        adId = box2.nextInt();
        System.out.println();
        System.out.println("\t\t[1] Price  \t\t[2] Additional information");
        System.out.println();
        System.out.println("\t\t[3] Color  \t\t[4] Fastersale ");
        System.out.println();
        System.out.println("\t\t[5] Exit");
        System.out.println();
        System.out.println();
        int choice;
        System.out.print("\tYour Choice: ");
        choice = box2.nextInt();

        if(choice==1)
        {
            updatePrice(customerId,adId);
        }
        else if(choice==2)
        {
            updateNote(customerId,adId);
        }
        else if(choice==3)
        {
            updateColor(customerId,adId);
        }
        else if(choice==4)
        {
            changefasterSaleOption(customerId,adId);
        }
        else if(choice==5)
        {
            customer customer = new customer();
            customer.loginMenu();
        }
        else
        {
           mainScreen mainScreen = new mainScreen();
           mainScreen.mainMenu();
        }


    }

    void updatePrice(int customerId,int adId)
    {
        float previousPrice = 0,newPrice=0;
        dbconnection dbconnection = new dbconnection();
        try
        {
            ResultSet resultSet=dbconnection.st.executeQuery("select * from car where car_ID="+adId);
            while (resultSet.next())
            {
               previousPrice=resultSet.getFloat("price");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println();
        System.out.println("\t\tCurrent Ad price : "+previousPrice);
        System.out.println();
        System.out.print("\t\tEnter new Ad price : ");
        newPrice=box2.nextFloat();
        try
        {
            dbconnection.st.executeUpdate("update car set price = "+newPrice+" where car_ID="+adId);
            System.out.println("\t\t\tAd Price Updated.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }



    void updateColor(int customerId,int adId)
    {
        String previousColor = null,newColor=null;
        dbconnection dbconnection = new dbconnection();
        try
        {
            ResultSet resultSet=dbconnection.st.executeQuery("select * from car where car_ID="+adId);
            while (resultSet.next())
            {
                previousColor=resultSet.getString("Color");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println();
        System.out.println("\t\tCurrent Ad Color : "+previousColor);
        System.out.println();
        System.out.print("\t\tEnter new Ad Color : ");
        newColor=box1.nextLine();
        try
        {
            dbconnection.st.executeUpdate("update car set color = '"+newColor+"' where car_ID="+adId);
            System.out.println("\t\t\tAd Color Updated.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }


    void updateNote(int customerId,int adId)
    {
        String previousNote = null,newNote=null;
        dbconnection dbconnection = new dbconnection();
        try
        {
            ResultSet resultSet=dbconnection.st.executeQuery("select * from car where car_ID="+adId);
            while (resultSet.next())
            {
                previousNote=resultSet.getString("additionalInformation");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println();
        System.out.println("\t\tCurrent Ad Note : "+previousNote);
        System.out.println();
        System.out.print("\t\tEnter new Ad Note : ");
        newNote=box1.nextLine();
        try
        {
            dbconnection.st.executeUpdate("update car set additionalInformation = '"+newNote+"' where car_ID="+adId);
            System.out.println("\t\t\tAd Note Updated.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }




}







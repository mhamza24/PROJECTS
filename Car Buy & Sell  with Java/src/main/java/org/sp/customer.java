package org.sp;

import java.io.Console;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class customer
{
    Scanner box1 =new Scanner(System.in);
    Scanner box2 =new Scanner(System.in);

    static int customerId=0;

    void design()
    {
        System.out.println("\t\t\t*************************** Wheels For You ***************************");
        System.out.println("\t\t\t\t*************************** Buy your sawari today ***************************");
        System.out.println();
        System.out.println("\t\tCustomer Portal");
        System.out.println();
        System.out.println("\t\t\t\t\t\t*************************** ");
        System.out.println();
    }



    void loginMenu()
    {
        design();
        System.out.println("\t\t\tloginMenu");
        System.out.println();
        System.out.println("\t\t[1] Sell car     \t\t[2] Purchase car");
        System.out.println();
        System.out.println("\t\t[3] View Ads     \t\t[4] Edit Ad");
        System.out.println();
        System.out.println("\t\t[5] More options \t\t[6] Exit");
        System.out.println();
        int choice;
        System.out.print("\tYour Choice: ");
        choice = box2.nextInt();
        if(choice==1)
        {
            carAd carAd = new carAd();
            carAd.sell(customerId);
        }
        else if(choice==2)
        {
            carAd carAd = new carAd();
            carAd.buy(customerId);
        }
        else if(choice==3)
        {
            carAd carAd = new carAd();
            carAd.view(customerId);
        }
        else if(choice==4)
        {
            allFunctionsCar allFunctionsCar = new allFunctionsCar();
            allFunctionsCar.editAd(customerId);
        }
        else if(choice==5)
        {
            moreOption();
        }
        else if(choice==6)
        {
            mainScreen mainScreen = new mainScreen();
            mainScreen.mainMenu();
        }
        else
        {
            System.exit(0);
        }

    }

    void  moreOption()
    {
        System.out.println();
        System.out.println("\t\t\tMore Option");
        System.out.println();
        System.out.println("\t\t[1] Change Password          \t\t[2] Change Username");
        System.out.println();
        System.out.println("\t\t[3] Two-facotr Authentication\t\t[4] Report Account");
        System.out.println();
        System.out.println("\t\t[6] Disable Account          \t\t[7] Exit");
        System.out.println();
        System.out.println();
        int choice;
        System.out.print("\tYour Choice: ");
        choice = box2.nextInt();
        if(choice==1)
        {
            allFunctionsCustomer allFunctionsCustomer=new allFunctionsCustomer();
            allFunctionsCustomer.changePassword();
        }
        else if(choice==2)
        {
            allFunctionsCustomer allFunctionsCustomer=new allFunctionsCustomer();
            allFunctionsCustomer.changeUsername();
        }
        else if(choice==3)
        {
            allFunctionsCustomer allFunctionsCustomer=new allFunctionsCustomer();
            allFunctionsCustomer.twoFactorAuthentication(customerId);
        }
        else if(choice==4)
        {
            allFunctionsCustomer allFunctionsCustomer=new allFunctionsCustomer();
            allFunctionsCustomer.reportAccount(customerId);
        }
        else if(choice==5)
        {
            allFunctionsCustomer allFunctionsCustomer=new allFunctionsCustomer();
            allFunctionsCustomer.disableAccount(customerId);
        }
        else if(choice==6)
        {
            loginMenu();
        }
        else
        {
            System.exit(0);
        }
    }

     void signUp()
    {
        design();
        System.out.println("\t\t\tSignup");
        System.out.println();
        String fname,lname,phoneNumber,email,address,city,country,postalCode,password,username;
        int csutomerId;
        int twofactorAunthtentication,acountStatus,report;

        allFunctionsCustomer allFunctionsCustomer=new allFunctionsCustomer();

        System.out.println("\t\tEnter your details");
        System.out.println();
        System.out.print("\tFirst Name : ");
        fname=box1.nextLine();
        System.out.print("\tLast Name  :");
        lname=box1.nextLine();
        System.out.print("\tContact No :");
        phoneNumber=box1.nextLine();
        System.out.print("\tEmail      :");
        email=box1.nextLine();
        System.out.print("\tUsername   :");
        username=box1.nextLine();
        username= allFunctionsCustomer.userNameCheck(username);
        System.out.print("\tPassword   :");
        password=box1.nextLine();
        System.out.print("\tAddress    :");
        address=box1.nextLine();
        System.out.print("\tCity       :");
        city=box1.nextLine();
        System.out.print("\tCountry    :");
        country=box1.nextLine();
        System.out.print("\tPostalcode :");
        postalCode=box1.nextLine();
        twofactorAunthtentication=0;
        report=0;
        acountStatus=1;



        boolean status=allFunctionsCustomer.codeGeneratorAndSender(email,"Account Registration");
        if(status==true)
        {
            try
            {
                dbconnection dbc=new dbconnection();
                String sql = "insert INTO Customer VALUES('"+0+"','"+fname+"','"+lname+"','"+phoneNumber+"','"+email+"','"+username+"','"+password+"','"+address+"','"+city+"','"+country+"','"+postalCode+"')";
                String sql1="insert INTO Customeroption VALUES('"+0+"','"+twofactorAunthtentication+"','"+acountStatus+"','"+report+"')";
                dbc.st.executeUpdate(sql);
                dbc.st.executeUpdate(sql1);
                System.out.println("\t\tSignup successfully");
                System.out.println();
                System.out.println("\t\t Username : "+username);
                try
                {
                    Thread.sleep(5000);
                }
                catch(InterruptedException ex)
                {
                    ex.printStackTrace();
                }
                login();



            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("Invalid Code");
            signUp();
        }


    }



    void login()
    {
        design();
        System.out.println("\t\t\tLogin");
        System.out.println();
        System.out.println();
        String userName,password;
        int tries=2;
        allFunctionsCustomer allFunctionsCustomer=new allFunctionsCustomer();
        for (int i =0;i<3;i++)
        {

            System.out.print("\t\tUsername :");
            userName = box1.nextLine();
            System.out.println();
            System.out.print("\t\tPassword :");
            password = box1.nextLine();
            try {
                String fname, lname;
                dbconnection dbconnection = new dbconnection();
                ResultSet resultSet = dbconnection.st.executeQuery("select * from customer");

                while (resultSet.next()) {
                    if (userName.equals(resultSet.getString("Username")) && password.equals(resultSet.getString("password")))
                    {
                        fname = resultSet.getString("First_Name");
                        lname = resultSet.getString("Last_Name");
                        customerId=resultSet.getInt("Customer_ID");

                        boolean status=allFunctionsCustomer.accountStatus(customerId);
                        if(status==true)
                        {
                            allFunctionsCustomer.emailNotification(customerId,"Login Activity");
                            System.out.println();
                            System.out.println();
                            allFunctionsCustomer.logintwoFactorAuthentication(customerId);
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println("\t\t\t\t\t\tWelcome back " + fname + " " + lname + ",");
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            try
                            {
                                Thread.sleep(3000);
                            } catch (InterruptedException ex)
                            {
                                ex.printStackTrace();
                            }
                            loginMenu();
                        }
                        else
                        {
                            mainScreen mainScreen = new mainScreen();
                            mainScreen.mainMenu();

                        }
                    }
                }
                System.out.println("Worng username or password,tries remaining "+tries--);
                System.out.println();

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        System.out.println();
        System.out.println("\t\tForgot password or username?");
        System.out.println("\tY/N");
        String yN=box1.nextLine();
        if(yN.equals("Y") || yN.equals("y"))
        {
            allFunctionsCustomer.accountRecovery();
        }
        else
        {
            mainScreen mainScreen = new mainScreen();
            mainScreen.mainMenu();
        }

    }






    public static void main(String[] args)
    {



    }
}

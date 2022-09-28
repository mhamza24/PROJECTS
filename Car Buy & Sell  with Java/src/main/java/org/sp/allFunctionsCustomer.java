package org.sp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public  class allFunctionsCustomer
{
    Scanner box1 =new Scanner(System.in);
    Scanner box2 =new Scanner(System.in);
    static int customerId=0;
    static String comments=null;
    static  int totalRepoets=0;


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

    public  String editUserName()
    {
        System.out.print("Enter new username :");
        String userName=box1.nextLine();
        userName=userNameCheck(userName);
        return userName;
    }
    public String userNameCheck(String userName)
    {
        try {
            dbconnection dbc=new dbconnection();
            ResultSet resultSet =dbc.st.executeQuery("select * from customer");
            while (resultSet.next())
            {
                if(userName.equals(resultSet.getString("Username")))
                {
                    System.out.println("Username Exists,try something different");
                    userName=editUserName();
                    userNameCheck(userName);
                }

            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return userName;
    }

    boolean codeGeneratorAndSender(String userEmail,String task)
    {
        System.out.println();
        System.out.println();
        String waitingMessage;
        waitingMessage="\tSending verification code .";
        System.out.print(waitingMessage);
        for (int i=0;i<6;i++)
        {
            try
            {

                System.out.print(" .");
                Thread.sleep(1000);
            }
            catch(InterruptedException ex)
            {
                ex.printStackTrace();
            }
        }
        System.out.println();
        int max=999999,min=111111;
        int  code= (int)Math.floor(Math.random()*(max-min+1)+min);
        String subject;
        if(task.equals("Account Registration"))
        {
            subject="Account Verification";
        }
        else if(task.equals("Password Recovery"))
        {
            subject="Password Recovery";
        }
        else if(task.equals("Username Recovery"))
        {
            subject="Username Recovery";
        }
        else if (task.equals("twoFactorAuthentication"))
        {
            subject="Two-Factor Authentication";
        }
        else if (task.equals("Purchase Confirmation"))
        {
            subject="Purchase Conformation";
        }
        else
        {
            subject="Verification Code";
        }

        String to=userEmail;
        String message="****** Wheels For You ******\n" +
                "\t*** Buy your sawari today ***\n\n\n\n" +
                "\t\t\tC O D E : "+code+"\n\n\n\n" +
                "\tThankyou for using Wheels for you *_*";
        System.out.println();
        email.sendEmail(message,subject,to);

        for (int i=0;i<50;i++)
        {
            System.out.println();
        }
        System.out.println();
        System.out.println("\tFor the Signup Confirmation we have send a code on your given email,\n" +
                "\tPlease enter code for verification .");
        try
        {
            Thread.sleep(3000);
        }
        catch(InterruptedException ex)
        {
            ex.printStackTrace();
        }
        System.out.println();
        int tries=2;
        for (int i=0;i<3;i++)
        {
            System.out.print("\t\t\t\tCODE : ");
            int otp=box2.nextInt();
            System.out.println();
            String verificationMessage;
            verificationMessage="\tVerifying code .";
            System.out.print(verificationMessage);
            for (int j=0;j<6;j++)
            {
                try
                {

                    System.out.print(" .");
                    Thread.sleep(1000);
                }
                catch(InterruptedException ex)
                {
                    ex.printStackTrace();
                }
            }
            System.out.println();
            System.out.println();
            System.out.println();
            if(otp==code)
            {
                return true;
            }
            else
            {
                System.out.println();
                System.out.println("Invalid code, tries remaining "+tries--);
            }
        }
        return false;

    }

    void emailNotification(int reportedID,String task)
    {
        String useremail = null,subject=null,message=null;
        dbconnection dbconnection = new dbconnection();
        try
        {
            ResultSet resultSet=dbconnection.st.executeQuery("select * from customer where customer_Id="+reportedID);
            while (resultSet.next())
            {
                useremail=resultSet.getString("email");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        if(task.equals("Account Reported"))
        {

            subject="Account Reported";
            message="****** Wheels For You ******\n" +
                    "\t*** Buy your sawari today ***\n\n\n\n" +
                    "Your account has been reported by a user for the following reasons,if your reports exceeds 3 then your account will be suspended\n"+
                    "\tRepoter comments : "+comments+"\n" +
                    "\tTotal Reports    :" +totalRepoets+
                    "\n\n\nContact us : wheelsforyou100@gmail.com\n"+
                    "Thankyou for using Wheels for you *_*";
        }
        else if(task.equals("Account Disabled"))
        {
            subject="Account Disabled";
            message="****** Wheels For You ******\n" +
                    "\t*** Buy your sawari today ***\n\n\n\n" +
                    "Your account has been suspended because your accoust exceeds no of reports\n"+
                    "\tTotal Reports    :" +totalRepoets+
                    "\n\n\nContact us : wheelsforyou100@gmail.com\n"+
                    "Thankyou for using Wheels for you *_*";

        }
        else if(task.equals("Login Activity"))
        {
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String dateTime = currentDateTime.format(myFormatObj);
            subject="Login Activity";
            message="****** Wheels For You ******\n" +
                    "\t*** Buy your sawari today ***\n\n\n\n" +
                    "We notice a login activity on your account\n\n" +
                    "\tDate & Time : "+dateTime+
                    "\n\n\nContact us : wheelsforyou100@gmail.com\n"+
                    "Thankyou for using Wheels for you *_*";
        }
        else if(task.equals("Purchase Confirm"))
        {
            subject="Purchase Confirm";
            message="****** Wheels For You ******\n" +
                    "\t*** Buy your sawari today ***\n\n\n\n" +
                    "Your purchase confirm,car will be deliver in 2-5 working days\n\n" +
                    "\n\n\nContact us : wheelsforyou100@gmail.com\n"+
                    "Thankyou for using Wheels for you *_*";

        }
        else if(task.equals("Car Sold"))
        {
            subject="Car Sold";
            message="****** Wheels For You ******\n" +
                    "\t*** Buy your sawari today ***\n\n\n\n" +
                    "Your listed car is sold ,your amount will be transfer after shortly\n\n" +
                    "\n\n\nContact us : wheelsforyou100@gmail.com\n"+
                    "Thankyou for using Wheels for you *_*";

        }
        else
        {

        }

       email.sendEmail(message,subject,useremail);

    }

    void accountRecovery()
    {
        System.out.println();
        design();
        System.out.println("\t\t\tAccount Recovery");
        System.out.println();
        System.out.println("\t\t[1] Password\t\t[2] Username");
        System.out.println();
        int choice;
        boolean status;
        System.out.print("\tYour Choice: ");
        choice = box2.nextInt();
        System.out.println();
        System.out.print("\tEmail : ");
        String email = box1.nextLine();
        if(choice==1)
        {
           status= codeGeneratorAndSender(email,"Password Recovery");
           if (status==true)
           {

               changePassword();
           }
           else
           {
               System.out.println("\t\tInvalid code");
               mainScreen mainScreen = new mainScreen();
               mainScreen.mainMenu();
           }
        }
        else if(choice==2)
        {
            status=codeGeneratorAndSender(email,"Username Recovery");
            if (status==true)
            {
                changeUsername();
            }
            else
            {
                System.out.println("\t\tInvalid code");
                mainScreen mainScreen = new mainScreen();
                mainScreen.mainMenu();
            }
        }
        else
        {
            accountRecovery();
        }

    }

    void changePassword()
    {
        System.out.print("\t\tEmail : ");
        String email=box1.nextLine();
        System.out.println();
        System.out.print("\t\tUsername :");
        String userName=box1.nextLine();
        System.out.println();
        try
        {
            dbconnection dbconnection =new dbconnection();
            ResultSet resultSet=dbconnection.st.executeQuery("select * from customer");
            while (resultSet.next())
            {
                if (email.equals(resultSet.getString("email")) && userName.equals(resultSet.getString("Username")))
                {
                    int tempCustomerId=resultSet.getInt("Customer_ID");
                    System.out.print("\tEnter new Password : ");
                    String password=box1.nextLine();
                    String sql="update Customer set password = ('"+password+"') where Customer_ID = "+tempCustomerId;
                    dbconnection.st.executeUpdate(sql);
                    System.out.println();
                    System.out.println("\t\t\t Password Changed ");
                    try
                    {
                        Thread.sleep(4000);
                    }
                    catch(InterruptedException ex)
                    {
                        ex.printStackTrace();
                    }
                    customer customer=new customer();
                    customer.login();
                }

            }
            System.out.println();
            System.out.println("\t Email or Username not found !");
            System.out.println();
            try
            {
                Thread.sleep(4000);
            }
            catch(InterruptedException ex)
            {
                ex.printStackTrace();
            }
            customer customer=new customer();
            customer.login();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    void changeUsername()
    {
        System.out.print("\t\tEmail: ");
        String email=box1.nextLine();
        System.out.println();
        System.out.print("\t\tPassword:");
        String password=box1.nextLine();
        System.out.println();
        try
        {
            dbconnection dbconnection =new dbconnection();
            ResultSet resultSet=dbconnection.st.executeQuery("select * from customer");
            while (resultSet.next())
            {
                if(email.equals(resultSet.getString("email")) && password.equals(resultSet.getString("password")))
                {
                    int tempCustomerId=resultSet.getInt("Customer_ID");
                    System.out.print("\tEnter new Username : ");
                    String userName=box1.nextLine();
                    userName=userNameCheck(userName);
                    System.out.println(tempCustomerId);
                    System.out.println(userName);
                    String sql="update Customer set Username = ('"+userName+"') where Customer_ID ="+tempCustomerId;
                    dbconnection.st.executeUpdate(sql);
                    System.out.println();
                    System.out.println("\t\t\t Username Changed ");
                    try
                    {
                        Thread.sleep(4000);
                    }
                    catch(InterruptedException ex)
                    {
                        ex.printStackTrace();
                    }
                    customer customer=new customer();
                    customer.login();
                }

            }
            System.out.println("\t Email or Password not found !");
            System.out.println();
            try
            {
                Thread.sleep(4000);
            }
            catch(InterruptedException ex)
            {
                ex.printStackTrace();
            }
            customer customer=new customer();
            customer.login();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    void twoFactorAuthentication(int customerId)
    {
        dbconnection dbconnection =new dbconnection();
        boolean status = false;
        try
        {
            ResultSet resultSet1=dbconnection.st.executeQuery("select * from Customeroption where CustomerOptionID="+customerId);
            while (resultSet1.next())
            {
                status=resultSet1.getBoolean("2factorauthentication");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        System.out.println();
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
        if(choice==1)
        {
            if (status == true)
            {
                System.out.println();
                System.out.println("\t\tTwo-Factor Authentication is already Enabled ");
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                customer.loginMenu();
            }
            else
            {


                try {
                    String email = null;

                    ResultSet resultSet = dbconnection.st.executeQuery("select * from customer where Customer_ID=" + customerId);
                    while (resultSet.next()) {
                        email = resultSet.getString("email");
                    }
                    System.out.println();
                    System.out.println("\t\tEmail: " + email);
                    System.out.println("\tYou want to continuew with this email ?");
                    System.out.println("\tY/N");
                    String yN = box1.nextLine();
                    if (yN.equals("Y") || yN.equals("y"))
                    {

                        System.out.println(customerId);

                        String sql = "update Customeroption set 2factorauthentication = 1 where CustomerOptionID=" + customerId;
                        dbconnection.st.executeUpdate(sql);
                        System.out.println("\t\t\tTwo-Factor Authentication Enabled ");
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        customer.loginMenu();
                    }
                    else if (yN.equals("n") || yN.equals("N"))
                    {
                        System.out.println();
                        System.out.println("\tYour Previous email will be replace with  new eamil,do you want to continue ?");
                        System.out.println("\tY/N");
                        String yN1 = box1.nextLine();
                        if (yN1.equals("Y") || yN1.equals("y")) {
                            System.out.print("\t\tEmail: ");
                            String userEmail = box1.nextLine();
                            System.out.println();
                            String sql = "update customer set email ='" + userEmail + "' where Customer_ID =" + customerId;
                            dbconnection.st.executeUpdate(sql);
                            String sql1 = "update Customeroption set 2factorauthentication = 1 where CustomerOptionID=" + customerId;
                            dbconnection.st.executeUpdate(sql1);
                            System.out.println("\t\t\tTwo-Factor Authentication Enabled ");
                            try {
                                Thread.sleep(4000);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                            customer.loginMenu();

                        } else {
                            customer.moreOption();
                        }

                    } else {
                        customer.loginMenu();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
        else if(choice==2)
        {
            if (status == false)
            {
                System.out.println();
                System.out.println("\t\tTwo-Factor Authentication is already Disabled ");
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                customer.loginMenu();
            } else {


                try {
                    {
                        System.out.println();
                        String sql = "update Customeroption set 2factorauthentication = 0 where CustomerOptionID=" + customerId;
                        dbconnection.st.executeUpdate(sql);
                        System.out.println("\t\t\tTwo-Factor Authentication Disabled ");
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        customer.loginMenu();

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else if(choice==3)
        {
           customer.loginMenu();
        }
        else
        {
            mainScreen mainScreen = new mainScreen();
            mainScreen.mainMenu();
        }

    }

    void logintwoFactorAuthentication(int customerId)
    {
        dbconnection dbconnection = new dbconnection();
        try {

            ResultSet resultSet= dbconnection.st.executeQuery(" select * from Customeroption where CustomerOptionID="+customerId);
            while (resultSet.next())
            {
                if(resultSet.getBoolean("2factorauthentication")==true)
                {

                    try
                    {
                        String email = null;
                        ResultSet resultSet1=dbconnection.st.executeQuery("select * from customer where Customer_Id="+customerId);
                        while (resultSet1.next())
                        {
                            email=resultSet1.getString("email");
                        }
                        boolean status = codeGeneratorAndSender(email,"twoFactorAuthentication");
                        if(status==true)
                        {
                            customer customer = new customer();
                            customer.loginMenu();

                        }
                        else
                        {
                            System.out.println("\t\tInvalid Code");
                            try {
                                Thread.sleep(3000);
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                            mainScreen mainScreen =new mainScreen();
                            mainScreen.mainMenu();
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }
                else
                {
                    customer customer = new customer();
                    customer.loginMenu();
                }



            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }


    boolean accountStatus(int customerId)
    {
        dbconnection dbconnection = new dbconnection();

        ResultSet resultSet= null;
        try
        {
            resultSet = dbconnection.st.executeQuery("select accountStatus from Customeroption where CustomerOptionID="+customerId);
            while (resultSet.next())
            {
                if (resultSet.getBoolean("accountStatus")==true)
                {
                    return true;
                }
                else
                {
                    System.out.println();
                    System.out.println();
                    System.out.println("\t\t\tAccount is Disabled");
                    System.out.println();
                    System.out.println("\t\tContact us : wheelsforyou100@gmail.com");
                    System.out.println();
                    System.out.println();
                    System.out.println();
                }
                try {
                    Thread.sleep(3000);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }


    void reportAccount(int customerId)
    {
        dbconnection dbconnection = new dbconnection();
        int reportedID;
        String reporterComments;
        System.out.println();
        System.out.println();
        System.out.print("\t\tAccount id whom do you want to report : ");
        reportedID = box2.nextInt();
        System.out.println();
        System.out.print("Why do you want to report this account (150 words): ");
        reporterComments = box1.nextLine();
        if (customerId==reportedID)
        {
            System.out.println();
            System.out.println();
            System.out.println("\t\tYou can not report your own account.");
            try
            {
                Thread.sleep(4000);
            }
            catch(InterruptedException ex)
            {
                ex.printStackTrace();
            }
            customer customer = new customer();
            customer.loginMenu();

        }
        try
        {
            int reporterIDCheck=0,reports=0;
            //int[] reportedIDs=new int[];
            ResultSet resultSet =dbconnection.st.executeQuery("select reporterID from reportedAccounts where reporterID="+customerId);

            while (resultSet.next())
            {
                 reporterIDCheck=resultSet.getInt("reporterID");
            }
            ResultSet resultSet1 = dbconnection.st.executeQuery("select report from Customeroption where CustomerOptionID="+reportedID);

            while (resultSet1.next())
            {
                reports=resultSet1.getInt("report");
            }

            if (reporterIDCheck==0)
            {
                reports++;
                dbconnection.st.executeUpdate("insert into reportedAccounts values('"+0+"','"+customerId+"','"+reportedID+"','"+reporterComments+"')");
                dbconnection.st.executeUpdate("update Customeroption set report ="+reports+" where CustomerOptionID="+reportedID);
                System.out.println();
                System.out.println("\t\tYour report is submitted");
                try
                {
                    Thread.sleep(4000);
                }
                catch(InterruptedException ex)
                {
                    ex.printStackTrace();
                }
                totalRepoets=reports;
                comments=reporterComments;
                reports=0;
                emailNotification(reportedID,"Account Reported");

            }
            else
            {
                System.out.println();
                System.out.println("\t\tYou can not report same account twice.");
                try
                {
                    Thread.sleep(4000);
                }
                catch(InterruptedException ex)
                {
                    ex.printStackTrace();
                }
                customer customer = new customer();
                customer.loginMenu();

            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        try
        {
            int customerOptionID;
            ResultSet resultSet=dbconnection.st.executeQuery("select * from Customeroption where CustomerOptionID="+reportedID);
            while(resultSet.next())
            {
                if(resultSet.getInt("report") == 3)
                {
                    totalRepoets= resultSet.getInt("report");
                    customerOptionID=resultSet.getInt("CustomerOptionID");
                    dbconnection.st.executeUpdate("update Customeroption set accountStatus = 0 where CustomerOptionID="+customerOptionID);
                    emailNotification(reportedID,"Account Disabled");

                }
                break;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            Thread.sleep(4000);
        }
        catch(InterruptedException ex)
        {
            ex.printStackTrace();
        }
        customer customer = new customer();
        customer.loginMenu();

    }

    void disableAccount(int customerId)
    {
        dbconnection dbconnection = new dbconnection();
        ResultSet resultSet= null;
        try
        {
            resultSet = dbconnection.st.executeQuery("select * from Customeroption where CustomerOptionID="+customerId);
            while (resultSet.next())
            {
                if(resultSet.getBoolean("accountStatus")==true)
                {
                    dbconnection.st.executeUpdate("update Customeroption set accountStatus = 0 where CustomerOptionID="+customerId);
                    System.out.println();
                    System.out.println("\t\t Account Disabled");
                    try
                    {
                        Thread.sleep(4000);
                    }
                    catch(InterruptedException ex)
                    {
                        ex.printStackTrace();
                    }
                    mainScreen mainScreen = new mainScreen();
                    mainScreen.mainMenu();
                }


            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }




}

package org.sp;

import java.util.Scanner;

public class mainScreen
{
    Scanner box1 =new Scanner(System.in);
    Scanner box2 =new Scanner(System.in);



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

    void mainMenu()
    {
        design();
        System.out.println("\t\t\tMainMenu");
        System.out.println();
        System.out.println("\t\t[1] Login   \t\t[2] Signup");
        System.out.println();
        System.out.println("\t\t[3] View Ads\t\t[4] Exit");
        System.out.println();
        int choice;
        System.out.print("\tYour Choice: ");
        choice = box2.nextInt();
        customer customer = new customer();
        if(choice==1)
        {
            customer.login();
        }
        else if(choice==2)
        {
            customer.signUp();
        }
        else if(choice==3)
        {
            carAd carAd = new carAd();
            carAd.view(0);
        }
        else if(choice==4)
        {
            System.exit(0);
        }
        else
        {
            mainMenu();
        }

    }

    public static void main(String[] args) {
        mainScreen mainScreen = new mainScreen();
        mainScreen.mainMenu();
    }
}

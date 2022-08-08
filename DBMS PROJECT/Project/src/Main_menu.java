import java.sql.SQLException;
import java.util.Scanner;

public class Main_menu
{
    void design()
    {
        System.out.println("\t**********************************************************************************");
        System.out.println("\t\t\t******  Auto Dealership  ******");
        System.out.println("\t\t\t\t\t\t\t\t\tBuy your wheels");
        System.out.println("\n");
        System.out.println("\t\t\t\t\t\t\tMain Menu");
        System.out.println("\n\n");

    }


    void mainMenu()
    {
        design();
        System.out.println("\t\t[1] Car\t\t\t\t\t\t[2] Service");
        System.out.println("\t\t[3] Customer\t\t\t\t[4]Logs");
        System.out.println("\t\t[5] Exit");
        System.out.println();
        Scanner box = new Scanner(System.in);
        int choice;
        System.out.print("\t\tEnter Choice:");
        choice = box.nextInt();
        if (choice==1)
        {
            Car car =new Car();
            car.carMenu();

        }
        else if (choice==2)
        {
            Service service = new Service();
            service.service();
        }
        else if (choice==3)
        {
            Customer customer = new Customer();
            try {
                customer.customer();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        else if (choice==4)
        {
            Logs log = new Logs();
            log.log();
        }
        else if (choice==5)
        {
            System.out.println("\t\t\t\t******Thankyou For visiting******");
            System.out.println("\t\t\t\t\t\t\t******  Auto Dealership  ******");
            System.exit(0);
        }
        else
        {
            System.out.println("Wrong Input,try again");
            mainMenu();
        }
    }

    public static void main(String[] args) {
        Main_menu main_menu = new Main_menu();
        main_menu.mainMenu();
    }

}

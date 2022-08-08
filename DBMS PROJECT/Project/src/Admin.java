import java.util.Scanner;

public class Admin
{
    void design()
    {
        System.out.println("\t**********************************************************************************");
        System.out.println("\t\t\t******  Auto Dealership  ******");
        System.out.println("\t\t\t\t\t\t\t\t\tBuy your wheels");
        System.out.println("\n");
        System.out.println("\t\t\t\t\t\t\tAdmin Portal");
        System.out.println("\n\n");
    }
    void admin() {
        String username = null;
        int password;
        Scanner box = new Scanner(System.in);
        System.out.print("\t\tUsername:");
        username = box.nextLine();
        System.out.println();
        System.out.print("\t\tPassword:");
        password = box.nextInt();

        if (username.equals("ford") && password==248)
        {

            System.out.println("\t\t\t\t\tAccess Permitted");
            Main_menu main_menu =new Main_menu();
            main_menu.mainMenu();

        }
        else
        {

            System.out.println("\t\t\t\t\tAccess Denied");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        Admin admin =new Admin();
        admin.design();
        admin.admin();
    }
}

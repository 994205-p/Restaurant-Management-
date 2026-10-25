import com.rest.CLI.PaymentCli;
import com.rest.CLI.TableCli;
import com.rest.service.TableService;
import com.rest.service.OrderService;   // <-- ADD THIS IMPORT
import com.rest.service.PaymentService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        TableService serv = new TableService();
        OrderService orderServ = new OrderService(); 
        PaymentService paymentServ = new PaymentService();

        TableCli tableCli = new TableCli(serv, orderServ); 
        PaymentCli paymentCli = new PaymentCli(orderServ, paymentServ); ;

        int choice;
        do {
            System.out.println("== Restaurant Management System ==");
            System.out.println("1. Table Management Menu");
            System.out.println("2. Payment Management Menu");
            System.out.println("0. Exit System");
            System.out.print("Enter choice: ");
            
            choice = scan.nextInt();

            switch (choice) {
                case 1:
                    tableCli.start(); 
                    break;
                case 2:
                    paymentCli.start();
                    break;
                case 0:
                    System.out.println("Shutting down system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid Option!");
                    break;
            }
        } while (choice != 0);
    }
}

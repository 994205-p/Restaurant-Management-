package com.rest.CLI;

import com.rest.models.Table;
import com.rest.models.Order;
import com.rest.service.TableService;
import com.rest.service.OrderService;
import java.util.Scanner;

public class TableCli {
    private final TableService service;
    private final OrderService orderService; 
    Scanner scan = new Scanner(System.in);

    public TableCli(TableService service, OrderService orderService) {
        this.service = service;
        this.orderService = orderService;
    }

    public void start() {
        int choice;
        do {
            System.out.println("\nTable Management");
            System.out.println("1. All Tables");
            System.out.println("2. Edit Table");
            System.out.println("3. Delete Table");
            System.out.println("4. Book Table & Take Order"); 
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            
            choice = scan.nextInt(); 

            switch (choice) {
                case 1:
                    service.getAll();
                    break;
                case 2:
                    System.out.print("Enter Table ID to edit: ");
                    int editId = scan.nextInt();
                    service.editTable(editId); 
                    break;
                case 3:
                    System.out.print("Enter Table ID to delete: ");
                    int deleteId = scan.nextInt();
                    service.deleteTable(deleteId); 
                    break;
                case 4:
                    takeOrderWorkflow();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Balance ur Wish");
                    break;
            }
        } while (choice != 0);
    }

    private void takeOrderWorkflow() {
        System.out.print("Choose a Table ID to book: ");
        int tableId = scan.nextInt();
        scan.nextLine(); 

        Order newOrder = new Order(tableId);

        int orderChoice;
        do {
            System.out.println("\n--- Take Order Menu ---");
            System.out.println("1. Add Pizza ($250)");
            System.out.println("2. Add Burger ($150)");
            System.out.println("3. Add Cold Drink ($50)");
            System.out.println("0. Finish Order & Send to Kitchen");
            System.out.print("Select Food Item: ");
            orderChoice = scan.nextInt();

            switch (orderChoice) {
                case 1 -> newOrder.addItem("Pizza", 250);
                case 2 -> newOrder.addItem("Burger", 150);
                case 3 -> newOrder.addItem("Cold Drink", 50);
                case 0 -> System.out.println("Order finalized!");
            }
        } while (orderChoice != 0);

        orderService.createOrder(newOrder);
        System.out.println("Order assigned to Table " + tableId + ". Total Bill: ₹" + newOrder.getTotalAmount());
    }
}

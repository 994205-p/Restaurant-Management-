package com.rest.CLI;

import com.rest.models.Payment;
import com.rest.models.Order;
import com.rest.payment.*;
import com.rest.service.PaymentService;
import com.rest.service.OrderService;

import java.util.Scanner;
import java.util.InputMismatchException;

public class PaymentCli {

    private final PaymentService service;
    private final OrderService orderService; 
    private final Scanner scanner = new Scanner(System.in);

    
    public PaymentCli(OrderService orderService, PaymentService paymentServ) {
        this.service = new PaymentService();
        this.orderService = orderService;
    }

    public void start() {
        int choice;

        do {
            System.out.println("\n=== RESTAURANT PAYMENT SYSTEM ===");
            System.out.println("1. Make Payment for a Table");
            System.out.println("2. View All Payments");
            System.out.println("3. Find Payment by ID");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        makePayment();
                        break;

                    case 2:
                        viewAllPayments();
                        break;

                    case 3:
                        findPayment();
                        break;

                    case 0:
                        System.out.println("Thank you!");
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (InputMismatchException e) { 
                System.out.println("Please enter a valid number.");
                scanner.nextLine(); 
                choice = -1;

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                choice = -1;
            }

        } while (choice != 0);
    }

    private void makePayment() {
        System.out.print("Enter Table ID for billing: ");
        int tableId = scanner.nextInt();
        scanner.nextLine(); 

        Order activeOrder = orderService.getOrderByTableId(tableId);

        if (activeOrder == null) {
            System.out.println("No active orders found for Table " + tableId);
            return;
        }

        float amount = (float) activeOrder.getTotalAmount();
        System.out.println("Items Ordered: " + activeOrder.getItems());
        System.out.println("Total Amount Due: Rs. " + amount);

        System.out.println("\nSelect Payment Method");
        System.out.println("1. Cash");
        System.out.println("2. Card");
        System.out.println("3. UPI");
        System.out.print("Enter choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); 

        PaymentMethod method;

        switch (choice) {
            case 1:
                method = new CashPayment();
                break;

            case 2:
                method = new CardPayment();
                break;

            case 3:
                method = new UPIPayment();
                break;

            default:
                System.out.println("Invalid payment method.");
                return;
        }

        Payment payment = service.processPayment(method, amount);
        
        orderService.removeOrder(tableId);

        System.out.println("Payment record saved.");
        System.out.println(payment);
    }

    private void viewAllPayments() {
        if (service.getAllPayments().isEmpty()) {
            System.out.println("No payment records found.");
            return;
        }

        for (Payment payment : service.getAllPayments()) {
            System.out.println(payment);
        }
    }

    private void findPayment() {
        System.out.print("Enter payment ID: ");

        int id = Integer.parseInt(scanner.nextLine());

        Payment payment = service.getPaymentById(id);

        if (payment == null) {
            System.out.println("No transaction found with ID: " + id);
        } else {
            System.out.println(payment);
        }
    }
}

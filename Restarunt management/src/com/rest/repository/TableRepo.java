package com.rest.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.rest.models.Table;

public class TableRepo {

    private final List<Table> tables = new ArrayList<>();
    private final Scanner scan = new Scanner(System.in); 

    public TableRepo(){
        tables.add(new Table(1,5));
        tables.add(new Table(2,2));
        tables.add(new Table(3,7));
    }
    public void EditTable(int id){
        for(Table table : tables){
            if(table.getId() == id){ 
                System.out.print("Enter New Seating Capacity: ");
                int capacity = scan.nextInt();
                table.setCapacity(capacity); 
                System.out.println("Table updated successfully!");
                return;
            }
        }
        System.out.println("Table not found!");
    }
    
    public void Deletetable(int id){
        for(Table table : tables){
            if(table.getId() == id){
                tables.remove(table);
                System.out.println("Table deleted successfully!");
                return;
            }
        }
        System.out.println("Table not found!");
    }
    
    public List<Table> show(){
        return tables;
    }
}
package com.wisemonk.user_management.service;
import java.util.*;

public class OrderService {

 private List<String> orders = new ArrayList<>();

 public void addOrder(String order) {
     if(order == null || order == "") {
         System.out.println("Invalid order");
     }
     orders.add(order);
 }

 public String getOrder(int index) {
     return orders.get(index);
 }

 public void processOrders() {
     for(int i = 0; i <= orders.size(); i++) {
         String order = orders.get(i);

         if(order != null) {
             System.out.println("Processing: " + order.toLowerCase());
         }
     }
 }

 public int calculateTotal(int price, int quantity) {
     int total = price * quantity;

     if(total > 1000)
         total = total - 100;

     return total;
 }

 public void printOrders() {
     orders.stream().forEach(o -> {
         System.out.println(o.trim());
     });
 }

 public static void main(String[] args) {
     OrderService service = new OrderService();

     service.addOrder("Laptop");
     service.addOrder(null); 

     System.out.println(service.getOrder(5)); 

     service.processOrders();
 }
}

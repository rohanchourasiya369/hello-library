package Task_2;

import java.util.ArrayList;
import java.util.Scanner;

record  Book(int serialNo , String Book_Name, double price, BookType bookType){} // 1st Record Class

record Member(int id,String name) implements User {} // 2nd Record Class

enum BookType{ FICTION,NONFICTION,SCIENCE } // Enum BookType

sealed interface User permits Admin,Member {} // Sealed interface User

final class Admin implements User{} // Admin class and it's Empty Nothing is given

public class Book_Menu_Driven {

    static Scanner sc = new Scanner(System.in);

    static ArrayList<Book> books = new ArrayList<>();

    public static void main(String[] args) {
        char ch;
        do{
            System.out.println("!!!!! This is a BooK Library !!!!!");

            System.out.println("1.Add Book");
            System.out.println("2.View Book");
            System.out.println("0.Exit");
            System.out.print("Enter the Choice : ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> addBooks();

                case 2 -> viewBooks();

                case 0-> { System.out.println("Existing......!!!");
                    System.exit(0);}
                default -> System.out.println("Please Select the Valid Option .");
            }
            System.out.println("Do You Want to Continue ? 'Y' or 'N' ");
            ch = sc.next().charAt(0);
        }while(ch=='Y' || ch=='y');
    }

    private static void addBooks() {
        System.out.println("Enter the Serial_Number : ");
        int no = sc.nextInt();

        System.out.println("Enter the Book_Name : ");
        String name = sc.next();
        sc.nextLine();

        System.out.println("Enter the Book_Price : ");
        double price = sc.nextDouble();

        System.out.println("Choose the Categories \n 1) FICTION  \n 2) NONFICTION \n 3) SCIENCE.");
        int cat = sc.nextInt();
        BookType bookType;

        if(cat == 1){
            bookType = BookType.FICTION;}
        else if(cat == 2){
            bookType = BookType.NONFICTION;}
        else if(cat == 3){
          bookType = BookType.SCIENCE;}
        else {
            System.out.println("By Default FICTION");
            bookType =BookType.FICTION;
        }
        books.add(new Book(no,name,price,bookType));
        System.out.println("Books is Successfully Added in the List.");

    }

    private static void viewBooks() {
        if(books.isEmpty()){
            System.out.println("Books List is Empty..");
        }else{
            for(Book b : books)
                System.out.println(b);
        }
    }
}

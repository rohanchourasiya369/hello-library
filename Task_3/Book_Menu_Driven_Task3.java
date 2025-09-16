package Task_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

record Book(int bookId, String bookTitle,String bookAuthor,BookCategory bookCategory){} // This is Our Main Records
record Member() implements User{}                                                      // This is Optional Record
enum BookCategory{FICTION,NONFICTION,SCIENCE}                                         // Enum
sealed interface User permits Member,Admin{}                                          // Sealed Interface
final class Admin implements User{}

class BookNotFoundException extends Exception{                              // Custom Exception
    public BookNotFoundException(String str){
        super(str);
    }
}

public class Book_Menu_Driven_Task3 {
    static Scanner sc = new Scanner(System.in);
    static HashMap<Integer,Book> books = loadFile();

    public static void main(String[] args) throws BookNotFoundException {
        
        char ch;
        do{
            System.out.println("!!! This is a Book Library !!!");
            System.out.println("1.Add Books");
            System.out.println("2.View Books");
            System.out.println("3.Search Books");
            System.out.println("4.Delete Books");
            System.out.println("5.Updates Books");
            System.out.println("0.Exit");
            System.out.print("Enter the Choice : ");
            int choice = sc.nextInt();

            switch (choice){
                case 1 -> addBooks();
                case 2 -> viewBooks();
                case 3 -> searchBooks();
                case 4 -> deleteBooks();
                case 5 -> updateBooks();
                case 0 -> {
                    System.out.println("Existing.....");
                    System.exit(0);
                }
                default -> System.out.println("Please Enter Valid Input.");
            }
            System.out.println("Do you Want to continue ? 'Y' OR 'N' ");
            ch = sc.next().charAt(0);
        }while (ch=='y' || ch=='Y');

    }

    private static void updateBooks() {
        System.out.println("Enter the BookId : ");
        int bookId =sc.nextInt();

        if(!books.containsKey(bookId)){
            System.out.println("New Records is Inserted");
        }

        if(books.containsKey(bookId)){
            System.out.println("Already Existing ? Do You want to Override these data ? 'Y' Or 'N' ");
            char ch1 = sc.next().charAt(0);
            if (ch1!='Y' && ch1!='y'){
                System.out.println("Keep Existing....");
               return;
            }
        }

        sc.nextLine();
        System.out.print("Enter the BookTitle : ");
        String bookTitle = sc.nextLine();

        System.out.print("Enter the BookAuthor : ");
        String bookAuthor = sc.nextLine();

        System.out.println("Choose Book Category : \n 1) FICTION \n 2) NONFICTION \n 3) SCIENCE");
        int cat = sc.nextInt();

        BookCategory bookCategory ;
        if (cat == 1){
            bookCategory = BookCategory.FICTION;
        }else if(cat == 2){
            bookCategory = BookCategory.NONFICTION;
        }else if(cat == 3){
            bookCategory = BookCategory.SCIENCE;
        }else{
            System.out.println("BY - DEFAULT FICTION.");
            bookCategory = BookCategory.FICTION;
        }
        books.put( bookId,new Book(bookId,bookTitle,bookAuthor,bookCategory));
        saveFile(books);
        System.out.println("Book is Updated Successfully...");
    }

    private static HashMap<Integer, Book> loadFile() {  // we are create file and write those data

        HashMap<Integer,Book> bookHashMap = new HashMap<>();
        File file = new File("books.txt");

        if(!file.exists()) {
            return bookHashMap;
        }

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()){
                String line = sc.nextLine();
                String [] parts = line.split(",");

                int id = Integer.parseInt(parts[0].trim());
                String title = parts[1];
                String author = parts[2];
                BookCategory category = BookCategory.valueOf(parts[3].trim());

                Book book = new Book(id,title,author,category);
                bookHashMap.put(id,book);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return  bookHashMap;

    }

    private static void addBooks() {
        System.out.print("Enter the BookId : ");
        int bookId = sc.nextInt();

        if(books.containsKey(bookId)){
            System.out.println("BookId No "+ bookId+" is Already Existing....");
            return;
        }

        sc.nextLine();
        System.out.print("Enter the BookTitle : ");
        String bookTitle = sc.nextLine();

        System.out.print("Enter the BookAuthor : ");
        String bookAuthor = sc.nextLine();

        System.out.println("Choose Book Category : \n 1) FICTION \n 2) NONFICTION \n 3) SCIENCE");
        int cat = sc.nextInt();

        BookCategory bookCategory ;
        if (cat == 1){
            bookCategory = BookCategory.FICTION;
        }else if(cat == 2){
            bookCategory = BookCategory.NONFICTION;
        }else if(cat == 3){
            bookCategory = BookCategory.SCIENCE;
        }else{
            System.out.println("BY - DEFAULT FICTION.");
            bookCategory = BookCategory.FICTION;
        }
        books.put( bookId,new Book(bookId,bookTitle,bookAuthor,bookCategory));
        saveFile(books);
        System.out.println("Book is Added Successfully...");
    }

    private static void saveFile(HashMap<Integer, Book> books) {   // we are create file and read those data entire
        try {
            PrintWriter writer = new PrintWriter("books.txt");
            for(Book book : books.values()){
                writer.println(book.bookId() + " , " + book.bookAuthor() +" , "+book.bookTitle()
                        +" , "+book.bookCategory());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private static void viewBooks() {
        if(books.isEmpty()){
            System.out.println("Books List is Empty...");
        }else {
            System.out.println(" --- The Book List --- ");
            for (Book b : books.values()) {
                System.out.println(b);
            }
        }

    }
    private static void searchBooks() throws BookNotFoundException {
        System.out.print("Enter the BookId for Searching : ");
        int bookId = sc.nextInt();

        if(!books.containsKey(bookId)){
            throw  new BookNotFoundException("Book Number "+bookId+" is not Found.");
           // System.out.println("Book Number "+bookId +" is not found.");
        }
        System.out.println(books.get(bookId));
    }
    private static void deleteBooks() throws BookNotFoundException {
        System.out.print("Enter the BookId : ");
        int bookId = sc.nextInt();

        if(books.remove(bookId) == null){
            throw  new BookNotFoundException("Book Number "+bookId+" is not Found.");
            //System.out.println("Book Number "+bookId +" is not found.");
        }else {
            saveFile(books);
            System.out.println("Deleted Successfully....");
        }
    }
}

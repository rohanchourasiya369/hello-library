package Task_4;

import java.util.LinkedList;
import java.util.List;
import java.util.SequencedCollection;
import java.util.concurrent.Executors;

record Book (int id, String title, String author){} // Main Book records
sealed interface User permits Admin,Member{}   // Sealed interface Use
record Admin(String name) implements User{}   // record use
record Member(String name) implements  User{}  // record use

public class LibrarySimulation {

    static final SequencedCollection<Book> books = new LinkedList<>(); //SequencedCollection Use

    public static void main(String[] args) {

        books.add(new Book(1,"Harry Porter","J.K. Rolling"));
        books.add(new Book(2,"Clean Code","Robert C.Martin"));
        books.add(new Book(3,"Java Programming","James Gosling"));
        books.add(new Book(4,"C language","Dennis Richie"));

        List<User> users = List.of(
                new Admin("Rohan"),
                new Member("Mayur"),
                new Admin("Abhishek"));

        System.out.println("!!! Before Book List !!!");
        books.forEach(b-> System.out.printf("BookId :%d || Title : %s || Author : %s%n",b.id(),b.title(),b.author()));

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {  // Virtual Thread Use
            for(User user : users) {
                executor.submit(() -> issueBook(user));
            }
        }

        System.out.println("!!! After Book List !!!");
        if(books.isEmpty()){
            System.out.println("!!! No More Books Left !!!");
            return;
        }
        books.forEach(b-> System.out.printf("BookId :%d || Title : %s || Author : %s%n",b.id(),b.title(),b.author()));

    }

    private static void issueBook(User user) {    // Pattern Matching Use
        synchronized (books){
            if(books.isEmpty()){
                System.out.println("No Books Found for."+user);
                return;
            }
        }

        String note = String.valueOf(books.removeFirst());

        switch (user){  // Pattern MATCHING
            case Admin a->
                System.out.printf("Admin %s got %s%n",a.name(),note);
            case Member m->
                    System.out.printf("Member %s got %s%n",m.name(),note);
        }
    }
}

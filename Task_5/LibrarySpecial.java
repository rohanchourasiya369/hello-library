package Task_5;

//import static java.lang.StringTemplate.STR;

class Book {
    String title;
    public Book(String title) {
       // System.out.println(STR."Book Created: \{title}");
        System.out.println(String.format("Book is Created Title : %s ",title));
    }
}

class SpecialBook extends Book {
    String author;
    SpecialBook(String title, String author) {
        super(title);
        this.author = author;
      // System.out.println(STR."Special Book Creating with \{title} and Author \{author}");
      // System.out.println(STR."Special Book Initialized with \{title} and Author \{author}");
        System.out.println(String.format("Special Book Creating with Title %s and Author %s.",title,author));
        System.out.printf("Special Book Initialized with Title %s and Author %s.%n",title,author);
    }
}

public class LibrarySpecial {
    static public String libraryMethod(String title) {
        return switch (title) {
            case "features" -> "This is a Featurastics";
            case "nonfeatures" -> "This is Non Featurastics";
            case null -> "Null Values.";
            default -> "Random Values";
        };
    }

    public static void main(String[] args) {
        SpecialBook specialBook = new SpecialBook("Harry Potter", "J.K. Rowling");
        System.out.println(libraryMethod("features"));
        System.out.println(libraryMethod(null));
    }
}

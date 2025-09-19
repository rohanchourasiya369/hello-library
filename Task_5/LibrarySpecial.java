package Task_5;

//import static java.lang.StringTemplate.STR;

import java.util.logging.Logger;

class Book {
    String title;
    public Book(String title) {

        Logger logger = Logger.getLogger(Book.class.getName());
       // System.out.println(STR."Book Created: \{title}");
        logger.info(String.format("Book is Created Title : %s ",title));
    }
}

class SpecialBook extends Book {
    Logger logger = Logger.getLogger(SpecialBook.class.getName());
    String author;
    SpecialBook(String title, String author) {
        super(title);
        this.author = author;
      // System.out.println(STR."Special Book Creating with \{title} and Author \{author}");
      // System.out.println(STR."Special Book Initialized with \{title} and Author \{author}");
        logger.info(String.format("Special Book Creating with Title %s and Author %s.",title,author));
            logger.info(String.format("Special Book Initialized with Title %s and Author %s.%n",title,author));
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
        Logger logger = Logger.getLogger(LibrarySpecial.class.getName());
        SpecialBook specialBook = new SpecialBook("Harry Potter", "J.K. Rowling");
        logger.info(libraryMethod("features"));
        logger.warning(libraryMethod(null));
    }
}

package Task_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileDemo {
    public static void main(String[] args) {

//         Create File
//        File file = new File("books.txt");
//        try {
//            file.createNewFile();
//        } catch (IOException e) {
//
//            System.out.println("File Not Found");
//            e.printStackTrace();
//        }

// Write File
//        try {
//            FileWriter  fileWriter = new FileWriter("books.txt");
//            fileWriter.write("This iS Our First Java File");
//            fileWriter.close();
//        } catch (IOException e) {
//            System.out.println("This is Error");
//            e.printStackTrace();
//        }

// Read File
//        File file = new File("books.txt");
//
//        try {
//            Scanner sc = new Scanner(file);
//            while (sc.hasNext()){
//                String line = sc.nextLine();
//                System.out.println(line);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }



//        Delete File

        File file = new File("books.txt");
        if(file.delete()){
            System.out.println("This is Deleted Successfully : "+file.getName());
        }else{
            System.out.println("Something we got Error.");
        }

    }
}

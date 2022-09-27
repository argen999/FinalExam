package libraryProject;

import libraryProject.dao.Dao;
import libraryProject.model.Book;
import libraryProject.model.Library;
import libraryProject.model.LibraryMember;
import libraryProject.service.impl.LibraryServiceImpl;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.spi.AbstractResourceBundleProvider;

public class Main {
    static Scanner scannerN = new Scanner(System.in);
    static Scanner scannerS = new Scanner(System.in);
    public static void main(String[] args) {
        Dao dao = new Dao(new Library());
        LibraryServiceImpl libraryService = new LibraryServiceImpl(dao);
        while(true){
            buttons();
            System.out.print("\nВыберите функцию: ");
            String button = scannerS.nextLine();
            switch (button) {
                case "1" -> {
                    LibraryMember libraryMember = new LibraryMember();
                    System.out.print("Введите ID участника: ");
                    libraryMember.setMemberId(scannerN.nextLong() );
                    scannerN.nextLine();
                    System.out.print("Введите имя участника: ");
                    libraryMember.setName(scannerN.nextLine() );
                    libraryService.addLibraryMember(libraryMember);
                }
                case "2" -> libraryService.getLibraryMembers();
                case "3" -> libraryService.findLibraryMemberById();
                case "4" -> {
                    System.out.print("Введите ID: ");
                    long membersID = scannerN.nextLong();
                    libraryService.deleteLibraryMemberByID(membersID);
                }
                case "5" -> {
                    Book book = new Book();
                    System.out.print("Введите ID книги: ");
                    book.setBookId(scannerN.nextLong() );
                    scannerN.nextLine();
                    System.out.print("Введите тайтл книги: ");
                    book.setTitle(scannerN.nextLine() );
                    libraryService.addBookToLibrary(book);
                }
                case "6" -> libraryService.getLibraryBooks();
                case "7" -> libraryService.findLibraryBookById();
                case "8" -> {
                    System.out.print("Введите ID книги чтобы удалить: ");
                    long bookID = scannerN.nextLong();
                    libraryService.deleteLibraryBookByID(bookID);
                }
                case "9" -> libraryService.addBookToMember();
                case "10" -> libraryService.removeBookFromReading();
                default -> System.out.println("Андай метод жок!");
            }
            if (button.equals("x") ) {
                break;
            }
        }
    }

    public static void  buttons(){
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><><><>");
        System.out.println("Нажмите 1, чтобы добавить нового участника в библиотеку.");
        System.out.println("Нажмите 2, чтобы увидеть всех участников библиотеки.");
        System.out.println("Нажмите 3, чтобы найти участника по ID и увидеть данные участника, читаемая книга и прочитанные.");
        System.out.println("Нажмите 4, чтобы удалить участника по ID.");
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><><><>");
        System.out.println("Нажмите 5, чтобы добавить книгу в библиотеку.");
        System.out.println("Нажмите 6, чтобы увидеть все книги в библиотеке.");
        System.out.println("Нажмите 7, чтобы найти книгу по ID.");
        System.out.println("Нажмите 8, чтобы удалить книгу по ID.");
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><><><>");
        System.out.println("Нажмите 9, чтобы ввести memberId участника и bookId книги, добавить в читаемые");
        System.out.println("Нажмите 10, чтобы ввести memberId участника и bookId книги, добавить в прочитанные");
        System.out.println("Нажмите x, чтобы завершить программу.");
    }
}

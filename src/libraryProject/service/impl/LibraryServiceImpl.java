package libraryProject.service.impl;


import libraryProject.dao.Dao;
import libraryProject.model.Book;
import libraryProject.model.LibraryMember;
import libraryProject.service.LibraryService;


import java.util.*;


public class LibraryServiceImpl implements LibraryService {
    private static final Scanner scannerN = new Scanner(System.in);
    private Dao dao;

    public LibraryServiceImpl(Dao dao) {
        this.dao = dao;
    }

    @Override
    public void getLibraryMembers() {
        this.dao.getLibrary().getLibraryMembers().stream().filter(x -> x.getName() != null).forEach(x->
        {
            System.out.println("ID "+x.getMemberId());
            System.out.println("Name "+x.getName());
            if (x.getCurrentlyReading() == null) {
                System.out.println("Currently reading: пусто");
            } else {
                System.out.println("Currently reading: "+x.getCurrentlyReading().getTitle());
            }
            System.out.println("Finished books: ");
            if (x.getFinishedBooks() != null) {
                for (Book i: x.getFinishedBooks()) {
                    System.out.println("ID: "+i.getBookId());
                    System.out.println("Title: "+i.getTitle());
                }
            }

        });
    }

    @Override
    public void addLibraryMember(LibraryMember libraryMember) {

        this.dao.getLibrary().getLibraryMembers().add(libraryMember);
    }

    @Override
    public void findLibraryMemberById() {
        System.out.print("Введите ID: ");
        long membersID = scannerN.nextLong();
        Optional<LibraryMember> optionalMembers =
                dao.getLibrary().getLibraryMembers().
                        stream().filter(x -> x.getMemberId() == membersID).findFirst();

        scannerN.nextLine();
        optionalMembers.ifPresent(System.out::println);
    }

    @Override
    public void deleteLibraryMemberByID(Long membersID) {

        dao.getLibrary().getLibraryMembers()
                        .stream().filter(x -> x.getMemberId() == membersID)
                        .findFirst()
                        .ifPresent(x -> dao.getLibrary().getLibraryMembers().remove(x));
    }

    @Override
    public void addBookToLibrary(Book book) {
        dao.getLibrary().getBooks().add(book);
    }

    @Override
    public void getLibraryBooks() {
        this.dao.getLibrary().getBooks().stream().filter(x -> x.getTitle() != null).forEach(x->
        {
            System.out.println("ID "+x.getBookId());
            System.out.println("Name "+x.getTitle());
            if (x.getCurrentHolder() == null) {
                System.out.println("Holder: пусто");
            } else {
                System.out.println("Holder: "+x.getCurrentHolder().getName());
            }
        });
    }

    @Override
    public void deleteLibraryBookByID(Long bookId) {

        dao.getLibrary().getBooks().
                stream().filter(x -> x.getBookId() == bookId)
                .findFirst()
                .ifPresent(x -> dao.getLibrary().getBooks().remove(x));
    }

    @Override
    public void findLibraryBookById() {
        System.out.print("Введите ID книги чтобы найти: ");
        long bookID = scannerN.nextLong();
        Optional<Book> optionalMembers =
                dao.getLibrary().getBooks().
                        stream().filter(x -> x.getBookId() == bookID).findFirst();

        scannerN.nextLine();
        optionalMembers.ifPresent(System.out::println);
    }

    @Override
    public void addBookToMember() {
        System.out.print("Введите ID участника чтобы добавить книгу: ");
        long membersID = scannerN.nextLong();
        Optional<LibraryMember> optionalMember =
                dao.getLibrary().getLibraryMembers().stream()
                        .filter(x -> x.getMemberId() == membersID)
                        .filter(x -> x.getCurrentlyReading() == null).findFirst();

        System.out.print("Введите ID книги чтобы добавить к участнику: ");
        long bookID = scannerN.nextLong();
        Optional<Book> optionalBook =
                dao.getLibrary().getBooks().stream()
                        .filter(x -> x.getBookId() == bookID)
                        .filter(x -> x.getCurrentHolder() == null).findFirst();

        if (optionalMember.isPresent() && optionalBook.isPresent() ) {
            LibraryMember member = optionalMember.get();
            Book book = optionalBook.get();

            if(member.getCurrentlyReading() == null && book.getCurrentHolder() == null){
                deleteLibraryMemberByID(membersID);
                deleteLibraryBookByID(bookID);
                book.setCurrentHolder(member);
                member.setCurrentlyReading(book);
                addLibraryMember(member);
                addBookToLibrary(book);
            }
        }
    }

    @Override
    public void removeBookFromReading(){
        System.out.print("Введите ID участника чтобы убрать книгу: ");
        long membersID = scannerN.nextLong();
        Optional<LibraryMember> optionalMember =
                dao.getLibrary().getLibraryMembers().stream()
                        .filter(x -> x.getMemberId() == membersID && x.getCurrentlyReading()!=null)
                        .findFirst();

        System.out.print("Введите ID книги чтобы удлтиь из участника: ");
        long bookID = scannerN.nextLong();
        Optional<Book> optionalBook =
                dao.getLibrary().getBooks().stream()
                        .filter(x -> x.getBookId() == bookID)
                        .filter(x -> x.getCurrentHolder() != null).findFirst();

        if(optionalMember.isEmpty()) System.out.println("Бул адам базада жок же китеби жок");
        else{
            if (optionalBook.isEmpty()) System.out.println("Бул китеп базада жок же колдонуучусу жок");
            else {
                LibraryMember member = optionalMember.get();
                Book book = optionalBook.get();

                member.getFinishedBooks().add(member.getCurrentlyReading());
                member.setCurrentlyReading(null);

                book.setCurrentHolder(null);

                deleteLibraryMemberByID(membersID);
                deleteLibraryBookByID(bookID);
                addLibraryMember(member);
                addBookToLibrary(book);
            }
        }


    }
}

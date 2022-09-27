package libraryProject.service;

import libraryProject.model.Book;
import libraryProject.model.LibraryMember;

import java.util.List;

public interface LibraryService {
    void getLibraryMembers();
    void addLibraryMember(LibraryMember libraryMember);

    void findLibraryMemberById();
    void deleteLibraryMemberByID(Long id);

    void addBookToLibrary(Book book);

    void getLibraryBooks();

    void findLibraryBookById();

    void deleteLibraryBookByID(Long id);

    void addBookToMember();

    void removeBookFromReading();
}

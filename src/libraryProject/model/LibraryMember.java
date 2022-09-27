package libraryProject.model;

import java.util.ArrayList;
import java.util.List;

public class LibraryMember {
    private Long memberId;
    private String name;
    private Book currentlyReading;
    private List<Book> finishedBooks = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Book getCurrentlyReading() {
        return currentlyReading;
    }

    public void setCurrentlyReading(Book currentlyReading) {
        this.currentlyReading = currentlyReading;
    }

    public List<Book> getFinishedBooks() {
        return finishedBooks;
    }

    public void setFinishedBooks(List<Book> finishedBooks) {
        this.finishedBooks = finishedBooks;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

}



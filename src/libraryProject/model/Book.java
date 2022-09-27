package libraryProject.model;


public class Book {
    private Long bookId;
    private String title;
    private LibraryMember currentHolder;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LibraryMember getCurrentHolder() {
        return currentHolder;
    }

    public void setCurrentHolder(LibraryMember currentHolder) {
        this.currentHolder = currentHolder;
    }
}

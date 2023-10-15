public class Book
{
    private String bookTitle;
    private String bookAuthor;
    private String id;

    public Book(String bookTitle, String bookAuthor, String id) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.id = id;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getId() {
        return id;
    }
}

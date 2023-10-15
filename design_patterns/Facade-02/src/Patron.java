import java.util.ArrayList;

public class Patron
{
    private String id;
    private ArrayList<Book> books;

    public Patron(String id, ArrayList<Book> books) {
        this.id = id;
        this.books = books;
    }

    public Book getBookWithTitle(String bookTitle)
    {
        for(Book book : books)
        {
            if(book.getBookTitle().equals(bookTitle))
            {
                return book;
            }
        }
        return null;
    }

    public String getId() {
        return id;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }
}

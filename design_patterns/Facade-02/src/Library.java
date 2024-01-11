import java.util.ArrayList;

public class Library
{
    String location;
    ArrayList<Librarian> librarians;
    ArrayList<Patron> patrons;

    public Library() {}

    public Library(ArrayList<Librarian> librarians, String location) {
        this.librarians = librarians;
        this.location = location;
    }

    private Patron getPatronWithId(String patronId)
    {
        for(Patron patron : patrons)
        {
            if(patron.getId().equals(patronId))
            {
                return patron;
            }
        }
        return null;
    }

    public void borrowBook(String patronId, String bookTitle)
    {
        Patron patron = getPatronWithId(patronId);
        if(patron != null && patron.getBookWithTitle(bookTitle) == null && BooksDatabase.getBookWithTitle(bookTitle) != null)
        {
            patron.getBooks().add(BooksDatabase.getBookWithTitle(bookTitle));
        }
    }

    public void returnBook(String patronId, String bookTitle)
    {
        Patron patron = getPatronWithId(patronId);
        if(patron != null)
        {
            patron.getBooks().remove(BooksDatabase.getBookWithTitle(bookTitle));
        }
    }

    public void removePatron(String patronId)
    {
        Patron patron = getPatronWithId(patronId);
        patrons.remove(patron);
    }
}

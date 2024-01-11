import java.util.ArrayList;

public class BooksDatabase
{
    static ArrayList<Book> books = new ArrayList<>();

    public static void initBookDatabase()
    {
        books.add(new Book("To Kill a Mockingbird", "Harper Lee", "1"));
        books.add(new Book("1984", "George Orwell", "2"));
        books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", "3"));
        books.add(new Book("Pride and Prejudice", "Jane Austen", "4"));
        books.add(new Book("The Catcher in the Rye", "J.D. Salinger", "5"));
        books.add(new Book("The Lord of the Rings", "J.R.R. Tolkien", "6"));
        books.add(new Book("The Hunger Games", "Suzanne Collins", "7"));
        books.add(new Book("The Da Vinci Code", "Dan Brown", "8"));
        books.add(new Book("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", "9"));
        books.add(new Book("The Shining", "Stephen King", "10"));
        books.add(new Book("The Girl with the Dragon Tattoo", "Stieg Larsson", "11"));
        books.add(new Book("The Hobbit", "J.R.R. Tolkien", "12"));
        books.add(new Book("Brave New World", "Aldous Huxley", "13"));
        books.add(new Book("The Chronicles of Narnia", "C.S. Lewis", "14"));
        books.add(new Book("War and Peace", "Leo Tolstoy", "15"));
        books.add(new Book("The Alchemist", "Paulo Coelho", "16"));
        books.add(new Book("The Secret Garden", "Frances Hodgson Burnett", "17"));
        books.add(new Book("Moby-Dick", "Herman Melville", "18"));
        books.add(new Book("The Picture of Dorian Gray", "Oscar Wilde", "19"));
        books.add(new Book("Frankenstein", "Mary Shelley", "20"));
    }

    public static Book getBookWithTitle(String bookTitle)
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

    public static Book getBookWithId(String bookId)
    {
        for(Book book : books)
        {
            if(book.getBookTitle().equals(bookId))
            {
                return book;
            }
        }
        return null;
    }
}


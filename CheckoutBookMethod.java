import java.util.Random;
import java.util.Scanner;

class Main {
    
    static class Asset
    {
        int Id;
        String Title;
        int Quantity;
        int Available;
        String PublishDate;
    }
    
    static class Book extends Asset
    {
        String Author;
        String Publisher;
        int PageCount;
    }
    
    static class User
    {
        int Id;
        String Name;
        int Age;
    }
    
    public static void main(String[] args) {
        
        Scanner scnr = new Scanner(System.in);
        Book book = new Book();
        User user = new User();
        book.Available = 2;

        
        int checkoutId2 = checkOutBookItem(book, user);

        if (checkoutId2 > -1)
        {
            System.out.println("Checkout Id is : " + checkoutId2);
        }
    }
    
    
    private static int checkOutBookItem(Book book, User user)
    {
        Random rand = new Random();
        int checkoutId = -1;
        if (book.Available > 0)
        {
            checkoutId = rand.nextInt(1000000000);
        }
        else if (book.Available <= 0)
        {
            System.out.println("Book is unavailable");
        }
        return checkoutId;
    }
}

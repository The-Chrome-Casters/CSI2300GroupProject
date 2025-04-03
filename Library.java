import java.util.Random;
import java.time.LocalDate;
import java.util.ArrayList;

class Library
{

  private ArrayList<Users> userList = new ArrayList<>();
  private ArrayList<Books> bookList = new ArrayList<>();
  private ArrayList<CDs> cdList = new ArrayList<>();

  private static int checkOutBook(int userId, int bookId)
  {  
    return 0;

  }

  private static int createUser(String userName, int userBirthYear)
  {
    return 0;

  }

  private static void storeData()
  {

  }

  private static void readData()
  {

  }

  private static void removeUser(int userID)
  {

  }

  private static int addBook(String title, String author, int pages, int quantity, String publishDate)
  {
    return 0;

  }

  private static void removeBook(int bookID)
  {

  }

  private static int addCompactDisc(String name, int timeLength, String producingCompany, String releaseDate, int quantity)
  {
    return 0;
  }
  
  private static void removeCompactDisc(int compactDiscID)
  {

  }
public class Asset
  {
    public int ID;
    public String Title;
    public int Quantity;
    public int Available;
    public String publishDate;
  }
  
  public static String createID()
  {
    Random rand = new Random();
    StringBuilder new_ID = new StringBuilder();

    for (int i = 0; i < 8; ++i)
      {
        new_ID.append(rand.nextInt(10));
      }
    String ID = new_ID.toString();
    return ID;
  }

  class Users
    {
      private int birthYear;
      private String Name;
    }

  class Books extends Asset
    {
      private String Author;
      private String Publisher;
      private int Pages;
    }

  class CDs extends Asset
    {
      private int timeLength;
      private String producingCompany;
      private String Publisher;
    }

  class Checkout
    {
      private int checkoutID;
      private int userID;
      private int assetID;
      private LocalDate checkoutTime;
      private LocalDate returnTime;
      private boolean isReturned;
      private boolean isReturnedLate;

      private static void returnBook()
      {
        
      }

      private static void returnCompactDisc()
      {

      }
      
    }
}

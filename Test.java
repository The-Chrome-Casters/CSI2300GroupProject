
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    static Library lib;

    static void saveLibrary() throws IOException {
        FileOutputStream libOut = new FileOutputStream("library");
        ObjectOutputStream libOOS = new ObjectOutputStream(libOut);
        libOOS.writeObject(lib);
        libOut.close();
    }

    static void loadLibrary() throws IOException, ClassNotFoundException {
        FileInputStream libIn = new FileInputStream ("library");
        ObjectInputStream checkOIS = new ObjectInputStream(libIn);
        lib = (Library)checkOIS.readObject();
        libIn.close();
    }

    public static void main(String[] args) throws Exception {
        lib = new Library();
        
        
        loadLibrary();
        
        // Library.Item newItem;

        // newItem = lib.items.addItem(Library.ItemType.BOOK, "Book1");
        // newItem.setBookData("Author5");
        // newItem.setQuantity(1);
        // newItem = lib.items.addItem(Library.ItemType.BOOK, "Book1");
        // newItem.setBookData("Author2");
        // newItem.setQuantity(2);

        // newItem = lib.items.addItem(Library.ItemType.CD, "BestAlbum");
        // newItem.setCDData(73, "producer1");
        // newItem.setQuantity(4);
        // newItem = lib.items.addItem(Library.ItemType.CD, "Worst Album");
        // newItem.setCDData(55, "producer2");
        // newItem.setQuantity(2);

        // Library.User JohnPork;
        // Library.User SamJohnson;
        // Library.User JonBanks;
        // Library.User JonPork;

        // JohnPork = lib.users.addUser("John Pork", 1990);
        // SamJohnson = lib.users.addUser("Samuel Johnson", 1784);
        // JonBanks = lib.users.addUser("Jonathan Banks", 1947);
        // lib.users.removeUser(JonBanks);
        // JonBanks = null;
        // JonPork = lib.users.addUser("Jonathan Pork", 1972);



        // //////////
        // ////////// CHECKOUTS
        // //////////
        // // Check out an item and do something if you can't checkout
        // boolean success;
        // success = lib.checkouts.checkoutItem(JonPork, newItem);
        // if (!success) {
        //     System.out.println("Could not checkout item. No more left");
        // }
        
        // newItem = lib.items.addItem(Library.ItemType.CD, "Most Mediocre Album").setQuantity(4);
        // success = lib.checkouts.checkoutItem(JonPork, newItem);
        // if (!success) {
        //     System.out.println("Could not checkout item. No more left");
        // }


        // // multiple checkouts
        // Library.Item book1 = lib.items.addItem(Library.ItemType.BOOK, "how to not get huzz").setQuantity(1);
        // Library.User JP = lib.users.findUser("Jonathan Pork");
        
        // try {
        //     lib.checkouts.checkoutItem(JP, book1);    
        //     lib.checkouts.findCheckout(JP, book1).returnItem();
        //     lib.checkouts.checkoutItem(JP, book1);    
        //     lib.checkouts.checkoutItem(JP, book1);
        //     lib.checkouts.findCheckout(JP, book1).returnItem();
        //     lib.checkouts.findCheckout(JP, book1).returnItem();
        // } catch (NullPointerException e) {
        //     System.out.println("issue returning item");
        // }

        // //////////
        // //////////
        // //////////
        Library.User jp = lib.users.findUser("Jonathan Pork");

        System.out.println(lib.checkouts.findUserCheckouts(jp).size());
        System.out.println(lib.items.itemList.size());
        System.out.println(lib.users.userList.size());
        System.out.println(lib.checkouts.checkouts.size());

        for (Library.Checkout check : lib.checkouts.checkouts) {
            System.out.println(check.user.name.equals(jp.name));
        }

        // Find user's checkouts
        ArrayList<Library.Checkout> checkouts = lib.checkouts.findUserCheckouts(lib.users.findUser("Jonathan Pork"));
        
        // Iterate through checkouts
        for (Library.Checkout checkout : checkouts) {
            // Check if checkout's item is a book
            if (checkout.item.type == Library.ItemType.BOOK) {

            }
            // Getting data from checkout
            String name = checkout.user.name;
            String type = checkout.item.type.toString();
            String itemTitle = checkout.item.title;
            String dateTime = checkout.checkoutTime.format(DateTimeFormatter.ofPattern("EEE, MMM d, yyyy hh:mma"));
            System.out.println(name + " Checked out " + type + " '" + itemTitle + "' on " + dateTime);
        }

        System.out.println();

        // Print info on each user's checkouts
        for (Library.User user : lib.users.userList) {
            // User info
            System.out.println(String.format("%s was born in %d, has id %d", user.name, user.birthYear, user.id));
            
            // Checkout info
            for (Library.Checkout check : lib.checkouts.findUserCheckouts(user)) {
                String dateTime = check.checkoutTime.format(DateTimeFormatter.ofPattern("MM/d/yyyy"));
                String returned = "";
                // was item returned??
                if (check.isReturned) {
                        String returnDT = check.returnTime.format(DateTimeFormatter.ofPattern("MM/d/yyyy"));
                        if (check.isReturnedLate) {
                        returned = "returned late on " + returnDT;
                    } else {
                        returned = "returned on " + returnDT;

                    }
                } else {
                    returned = "did not return";
                }
                System.out.println(check.user.name + " checked out '" + check.item.title + "' on " + dateTime + " and " + returned);
            }
            System.out.println();
        }

        // saveLibrary();
    }    
}

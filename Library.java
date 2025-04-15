import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

class Library {
    UserHandler users;
    ItemHandler items;

    Library() {
        users = new UserHandler();
        items = new ItemHandler();
    }
  

    class UserHandler {
        ArrayList<User> userList = new ArrayList<>();
        int lastUserID = 0;

        public User findUser(int id) {
            for (User user : userList) {
                if (user.id == id) {
                    return user;
                }
            }
            return null;
        }

        public User findUser(String name) {
            for (User user : userList) {
                if (user.name == name) {
                    return user;
                }
            }
            return null;
        }

        public int createUser(String name, int birthYear) {
            User newUser = new User(lastUserID++, name, birthYear);
            userList.add(newUser);
            return lastUserID;
        }
    }

    class ItemHandler {
        ArrayList<Item> itemList = new ArrayList<>();
        int lastItemID = 0;

        public Item findItem(int id) {
            for (Item item : itemList) {
                if (item.id == id) {
                    return item;
                }
            }
            return null;
        }

        public Item findItem(String title) {
            for (Item item : itemList) {
                if (item.title == title) {
                    return item;
                }
            }
            return null;
        }

        public int addItem(ItemType type, String title, String author) {
            Item newItem = new Item(null, lastItemID++, title, author);
            itemList.add(newItem);
            return lastItemID;
        }

        public void deleteItem(int id) {
            for (Item item : itemList) {
                if (item.id == id && itemList.contains(item)) {
                    itemList.remove(item);
                }
            }
        }
    }

    class CheckoutHandler {
        ArrayList<Checkout> checkouts = new ArrayList<>();
        int lastCheckoutID = 0;

        public Checkout findCheckout(int id) {
            for (Checkout checkout : checkouts) {
                if (checkout.id == id) {
                    return checkout;
                }
            }
            return null;
        }
    }

    enum ItemType {
        DEFAULT,
        BOOK,
        CD
    }
    
    class Item {
        // Generic data for all items:
        public ItemType type = ItemType.DEFAULT;
        public int id;
        public String title;
        public int quantity;
        public int available;
        public LocalDate publishDate;

        // Book-specific info:
        public String author;

        // CD/DVD specific info:
        public int timeLength; // minutes
        public String productionCompany;

        Item(ItemType type, int id, String title, String author) {
            this.id = id;
            this.type = type;
            this.title = title;
            this.author = author;
        }

        void setBookData() {

        }
    }

    class User {
        public int id;
        public LocalDateTime createdDate;
        public String name;
        public int birthYear;
        

        User(int id, String name, int birthYear) {
            this.id = id;
            this.name = name;
            this.birthYear = birthYear;
            this.createdDate = LocalDateTime.now();
        }
    }


    class Checkout {
        public int id;
        public int userID;
        public int assetID;
        public LocalDate checkoutTime;
        public LocalDate returnTime;
        public boolean isReturned;
        public boolean isReturnedLate;

        Checkout(int id, int userID, int assetID) {

        }

        void returnBook() {

        }

        void returnCompactDisc() {

        }

    }
}
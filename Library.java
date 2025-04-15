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

        public int addUser(String name, int birthYear) {
            int newid = lastUserID++;
            User newUser = new User(newid, name, birthYear);
            userList.add(newUser);
            return newid;
        }

        public void removeUser(int id) {
            for (User user : userList) {
                System.out.println(user.id);
                System.out.println(id);
                if (user.id == id && userList.contains(user)) {
                    userList.remove(user);
                    System.out.println("Removed user");
                    break; // IMPORTANT
                }
            }
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
            int newid = lastItemID++;
            Item newItem = new Item(null, newid, title, author);
            itemList.add(newItem);
            return newid;
        }

        public void removeItem(int id) {
            for (Item item : itemList) {
                System.out.println(item.id);
                System.out.println(id);
                if (item.id == id && itemList.contains(item)) {
                    itemList.remove(item);
                    System.out.println("Removed item");
                    break; // IMPORTANT
                }
            }
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

    class CheckoutHandler {
        ArrayList<Checkout> checkouts = new ArrayList<>();
        // int lastCheckoutID = 0;

        public ArrayList<Checkout> findUserCheckouts(int userID) {
            ArrayList<Checkout> userCheckouts = new ArrayList<>();
            for (Checkout checkout : checkouts) {
                if (checkout.userID == userID) {
                    userCheckouts.add(checkout);
                }
            }
            return userCheckouts;
        }

        public void checkoutItem(int userID, int itemID) {
            // int newid = lastItemID++;
            Checkout newCheckout = new Checkout(userID, itemID);
            checkouts.add(newCheckout);
            // return newid;
        }
    }

    class Checkout {
        public int userID;
        public int itemID;
        public LocalDateTime checkoutTime;
        public LocalDateTime returnTime;
        public boolean isReturned = false;
        public boolean isReturnedLate = false;

        Checkout(int userID, int itemID) {
            this.userID = userID;
            this.itemID = itemID;
            this.checkoutTime = LocalDateTime.now();
        }

        // Run when user returned item
        void returnBook() {
            this.returnTime = LocalDateTime.now();
            this.isReturned = true;
        }


    }
}
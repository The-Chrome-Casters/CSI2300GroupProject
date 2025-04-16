import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

class Library implements Serializable{
    UserHandler users;
    ItemHandler items;
    CheckoutHandler checkouts;

    Library() {
        users = new UserHandler();
        items = new ItemHandler();
        checkouts = new CheckoutHandler();
    }

    

    // THESE DONT WORK
    // void saveDatabase() throws IOException {
    //     FileOutputStream usersFout = new FileOutputStream ("users");
    //     FileOutputStream itemsFout = new FileOutputStream ("items");
    //     FileOutputStream checkFout = new FileOutputStream ("checkouts");
    //     ObjectOutputStream usersOOS = new ObjectOutputStream(usersFout);
    //     ObjectOutputStream itemsOOS = new ObjectOutputStream(itemsFout);
    //     ObjectOutputStream checkOOS = new ObjectOutputStream(checkFout);
    //     usersOOS.writeObject(this.users);
    //     itemsOOS.writeObject(this.items);
    //     checkOOS.writeObject(this.checkouts);
    //     usersFout.close();
    //     itemsFout.close();
    //     checkOOS.close();
    // }

    // void loadDatabase() throws IOException, ClassNotFoundException {
    //     FileInputStream usersFin = new FileInputStream ("users");
    //     FileInputStream itemsFin = new FileInputStream ("items");
    //     FileInputStream checkFin = new FileInputStream ("checkouts");
    //     ObjectInputStream usersOIS = new ObjectInputStream(usersFin);
    //     ObjectInputStream itemsOIS = new ObjectInputStream(itemsFin);
    //     ObjectInputStream checkOIS = new ObjectInputStream(checkFin);
    //     this.users = (UserHandler)usersOIS.readObject();
    //     this.items = (ItemHandler)itemsOIS.readObject();
    //     this.checkouts = (CheckoutHandler)checkOIS.readObject();
    //     usersFin.close();
    //     itemsFin.close();
    //     checkFin.close();
    // }


  
    class UserHandler implements Serializable {
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
                if (user.name.equals(name)) {
                    return user;
                }
            }
            return null;
        }

        public User addUser(String name, int birthYear) {
            int newid = lastUserID++;
            User newUser = new User(newid, name, birthYear);
            userList.add(newUser);
            return newUser;
        }

        // Remove user by id
        public boolean removeUser(int id) {
            User user = findUser(id);
            if (userList.contains(user)) {
                System.out.println("Removed user " + user.name);
                userList.remove(user);
                return true;
            } else {
                return false;
            }
        }

        // Remove user by User instance
        public boolean removeUser(User user) {
            if (userList.contains(user)) {
                System.out.println("Removed user " + user.name);
                userList.remove(user);
                return true;
            } else {
                return false;
            }
        }
    }

    class ItemHandler implements Serializable {
        ArrayList<Item> itemList = new ArrayList<>();
        int lastItemID = 0;

        // Find item by item id
        public Item findItem(int id) {
            for (Item item : itemList) {
                if (item.id == id) {
                    return item;
                }
            }
            return null;
        }

        // Find item by title
        public Item findItem(String title) {
            for (Item item : itemList) {
                if (item.title.equals(title)) {
                    return item;
                }
            }
            return null;
        }

        public Item addItem(ItemType type, String title) {
            int newid = lastItemID++;
            Item newItem = new Item(type, newid, title);
            itemList.add(newItem);
            return newItem;
        }

        // Remove item by id
        public boolean removeItem(int id) {
            Item item = findItem(id);
            if (itemList.contains(item)) {
                itemList.remove(item);
                System.out.println("Removed item");
                return true;
            } else {
                return false;
            }
        }

        // Remove item by Item instance
        public boolean removeItem(Item item) {
            if (itemList.contains(item)) {
                itemList.remove(item);
                System.out.println("Removed item");
                return true;
            } else {
                return false;
            }
        }

        public ArrayList<Item> getAllOfType(ItemType type) {
            ArrayList<Item> out = new ArrayList<>();
            for (Item item : itemList) {
                if (item.type.equals(type)) {
                    out.add(item);
                }
            }
            return out;
        }
    }

    

    enum ItemType {
        DEFAULT,
        BOOK,
        CD
    }
    
    class Item implements Serializable {
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

        Item(ItemType type, int id, String title) {
            this.type = type;
            this.id = id;
            this.title = title;
        }

        Item setCDData(int timeLength, String productionCompany) {
            this.timeLength = timeLength;
            this.productionCompany = productionCompany;
            return this;
        }

        Item setBookData(String author) {
            this.author = author;
            return this;
        }

        // WILL RESET AVAILABILITY
        Item setQuantity(int quantity) {
            this.quantity = quantity;
            this.available = quantity;
            return this;
        }
    }

    class User implements Serializable {
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

    class CheckoutHandler implements Serializable {
        ArrayList<Checkout> checkouts = new ArrayList<>();
        // int lastCheckoutID = 0;

        public ArrayList<Checkout> findUserCheckouts(User user) {
            ArrayList<Checkout> userCheckouts = new ArrayList<>();
            for (Checkout checkout : checkouts) {
                if (checkout.user.equals(user)) {
                    userCheckouts.add(checkout);
                }
            }
            return userCheckouts;
        }

        public Checkout findCheckout(User user, Item item) {
            ArrayList<Checkout> checkouts = new ArrayList<>();
            // Find all user's checkouts for the specified item
            for (Checkout checkout : findUserCheckouts(user)) {
                if (checkout.item.equals(item)) {
                    checkouts.add(checkout);
                }
            }
            // if there are multiple checkouts, only find the current/most recent one
            if (checkouts.size() > 1) {
                for (Checkout checkout : checkouts) {
                    if (!checkout.isReturned) {
                        return checkout;
                    }
                }
            } else {
                if (checkouts.size() == 1) {
                    return checkouts.get(0);
                } else {
                    System.out.println("could not find checkout for user");
                    return null;
                }
            }

            return null;
        }

        // returns true if item was available to checkout, false if not
        public boolean checkoutItem(User user, Item item) {
            // only allow user to checkout if item is available
            if (item.available > 0) {
                Checkout newCheckout = new Checkout(user, item);
                checkouts.add(newCheckout);
                item.available--;
                System.out.println("Checked out '"+item.title+"' for "+ user.name);
                return true;
            } else {
                System.out.println("Unable to checkout item: none available");
                return false;
            }
        }


    }

    class Checkout implements Serializable {
        public User user;
        public Item item;
        public LocalDateTime checkoutTime;
        public LocalDateTime returnTime;
        public Duration checkoutTimeLimit;
        public boolean isReturned = false;
        public boolean isReturnedLate = false;

        Checkout(User user, Item item) {
            this.user = user;
            this.item = item;
            this.checkoutTime = LocalDateTime.now();
            this.checkoutTimeLimit = Duration.ofDays(10);
        }

        // Run when user returned item
        void returnItem() {
            this.returnTime = LocalDateTime.now();
            this.isReturned = true;

            this.item.available++;

            // Was it late?
            if (this.returnTime.isAfter(this.checkoutTime.plus(this.checkoutTimeLimit))) {
                this.isReturnedLate = true;
            }

            System.out.println("Successfully returned \"" + this.item.title + "\" for " + this.user.name);
        }


    }
}
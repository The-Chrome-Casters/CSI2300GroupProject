
import java.util.Scanner;

public class Test {

    public static void main(String[] args) throws Exception {
        Library lib = new Library();

        Scanner scanner = new Scanner(System.in);
        String inp = "";

        boolean run = true;
        while (run) {
            inp = scanner.nextLine();
            int n = 0;
            switch (inp) {
                case "ex":
                    System.out.println("abcdadwad");
                    run = false;
                break;
                case "add":
                    n = lib.users.addUser("test", 1708);
                    n = lib.items.addItem(Library.ItemType.BOOK, "title", "author");
                    n = lib.items.addItem(Library.ItemType.CD, "ti2tle", "autho1r");
                    n = lib.items.addItem(Library.ItemType.CD, "ti42tle", "autho1r");
                    lib.items.removeItem(n);
                break;
                case "find":
                    System.out.println(lib.items.findItem(n).title);
                break;
            }

        }
        scanner.close();
    }    
}

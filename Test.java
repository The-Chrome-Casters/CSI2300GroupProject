
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
                    n = lib.users.createUser("test", 1708);
                    lib.items.addItem(Library.ItemType.BOOK, "title", "author");
                break;
                case "find":
                    System.out.println(lib.users.findUser(n).name);
                break;
            }

        }
        scanner.close();
    }    
}

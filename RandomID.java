import java.util.Random;

public class RandomID {
    public static void main(String[] args) {
        Random rand = new Random();
        StringBuilder new_ID = new StringBuilder();
        
        for (int i = 0; i < 8; i++) {
            new_ID.append(rand.nextInt(10));
        }
        String ID = new_ID.toString();
        System.out.println(ID);
    }
}
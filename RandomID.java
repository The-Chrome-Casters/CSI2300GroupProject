import java.util.Random;

public class RandomID {

    public static String create()
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
}

//Can you see this?

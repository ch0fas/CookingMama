// Temporary file for specific small testing, can be ignored
import cm.User;

public class Playground
{
    public static void main(String[] args)
    {
        String[] allergies = {"Chocolate", "Peanuts"};
        User user = new User("Sofia", 21, -1, -1, -1, -1, allergies);
        System.out.println(user);
    }
}
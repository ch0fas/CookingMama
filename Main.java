import java.io.*;
import java.util.Scanner;
import cm.users.*;
import cm.recipes.*;

public class Main
{
    // Welcome protocol for when there isn't a user yet
    public User welcomeProtocol()
    
    public static void main(String[] args) throws Exception
    {
        // Attributes
        // Open user information from file
        File file = new File("user.txt");
        Scanner sc = new Scanner(file);

        int user_counter = 0;
        while (sc.hasNextLine())
        {
            switch (user_counter)
            {
                case 0:
                System.out.println(sc.nextLine());
                break;
                case 1:
                System.out.println(Integer.parseInt(sc.nextLine()) * 2);
                break;
                case 2:
                System.out.println(Integer.parseInt(sc.nextLine()) * 3);
                break;
            }
            user_counter++;
        }


        sc.close();
    }
}
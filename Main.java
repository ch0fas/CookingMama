import java.io.*;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.IOException;

import cm.users.*;
import cm.recipes.*;

public class Main
{
    // Creates a User object in memory
    public static User createUser(String name, int age, int protein_goal, int carbs_limit, int cholesterol_limit, int calories_limit, int sodium_limit, int sugar_limit, String is_vegan, String is_gluten, String is_muslim, ArrayList<String> allergies)
    {
        // Booleans
        boolean is_vegan_boolean = false;
        boolean is_gluten_boolean = false;
        boolean is_muslim_boolean = false;
        
        is_vegan_boolean = is_vegan.equalsIgnoreCase("true");
        is_gluten_boolean = is_gluten.equalsIgnoreCase("true");
        is_muslim_boolean = is_muslim.equalsIgnoreCase("true");

        return new User(name, age, protein_goal, carbs_limit, cholesterol_limit, calories_limit, sodium_limit, sugar_limit, is_vegan_boolean, is_gluten_boolean, is_muslim_boolean, allergies);
    }
    
    // Welcome protocol for when there isn't a user yet
    public static void welcomeProtocol() throws IOException
    {
        String name = "";
        int age = 0;
        int protein_goal = 0;
        int carbs_limit = 0;
        int cholesterol_limit = 0;
        int calories_limit = 0;
        int sodium_limit = 0;
        int sugar_limit = 0;
        String is_vegan = "no";
        String is_gluten_allergic = "no";
        String is_muslim = "no";
        String current_allergy = "pass";
        User new_user;
        ArrayList<String> allergies = new ArrayList<>();
        
        Scanner scanner = new Scanner(System.in);
        
        // Creating file object and the file itself
        try
        {
            File file = new File("user.txt");
            file.createNewFile();
        } catch (IOException ex)
        {
            System.out.println("An error occurred creating the file: ");
            ex.printStackTrace();
        }

        try
        {
            System.out.printf("Welcome to Cooking Mama 3.0\nWhat is your name? ");
            name = scanner.nextLine();
            System.out.printf("How old are you? ");
            age = scanner.nextInt();
            scanner.nextLine();
            System.out.printf("What is your daily protein goal (in grams)? ");
            protein_goal = scanner.nextInt();
            scanner.nextLine();
            System.out.printf("What is your daily carbs limit (in grams)? ");
            carbs_limit = scanner.nextInt();
            scanner.nextLine();
            System.out.printf("What is your daily cholesterol limit? (in mg) ");
            cholesterol_limit = scanner.nextInt();
            scanner.nextLine();
            System.out.printf("What is your daily calorie limit? ");
            calories_limit = scanner.nextInt();
            scanner.nextLine();
            System.out.printf("What is your daily sodium limit (in mg)? ");
            sodium_limit = scanner.nextInt();
            scanner.nextLine();
            System.out.printf("What is your daily sugar limit (in grams)? ");
            sugar_limit = scanner.nextInt();
            scanner.nextLine();
            System.out.printf("Are you vegan? (true/false) ");
            is_vegan = scanner.nextLine();
            System.out.printf("Do you have a gluten allergy? (true/false) ");
            is_gluten_allergic = scanner.nextLine();
            System.out.printf("Are you a muslim? (true/false) ");
            is_muslim = scanner.nextLine();
            
            do
            {
                System.out.println("If you have any allergies, write them here. Type done when finished:");
                current_allergy = scanner.nextLine();

                if (!current_allergy.equalsIgnoreCase("done"))
                {
                    allergies.add(current_allergy.toLowerCase());
                }

            } while (!current_allergy.equalsIgnoreCase("done"));

        } catch (InputMismatchException ex)
        {
            System.out.println("Input Mismatch Exception! Try again");
            ex.printStackTrace();
        }

        scanner.close();

        // With the data, the User can now be created be created and the file written into
        new_user = createUser(name, age, protein_goal, carbs_limit, cholesterol_limit, calories_limit, sodium_limit, sugar_limit, is_vegan, is_gluten_allergic, is_muslim, allergies);

        try
        {
            FileWriter fwr = new FileWriter("user.txt", true);
            fwr.write(name);
            fwr.write("\n");
            fwr.write(String.valueOf(age));
            fwr.write("\n");
            fwr.write(String.valueOf(protein_goal));
            fwr.write("\n");
            fwr.write(String.valueOf(carbs_limit));
            fwr.write("\n");
            fwr.write(String.valueOf(cholesterol_limit));
            fwr.write("\n");
            fwr.write(String.valueOf(calories_limit));
            fwr.write("\n");
            fwr.write(String.valueOf(sodium_limit));
            fwr.write("\n");
            fwr.write(String.valueOf(sugar_limit));
            fwr.write("\n");
            fwr.write(is_vegan);
            fwr.write("\n");
            fwr.write(is_gluten_allergic);
            fwr.write("\n");
            fwr.write(is_muslim);
            fwr.write("\n");
            for (String e:allergies)
            {
                fwr.write(e);
                fwr.write(" ");
            }
            fwr.close();
        } catch (IOException ex)
        {
            System.out.println("There was an IOException! ");
            ex.printStackTrace();
        }

        System.out.println("User creation done! Take a look");
        System.out.println(new_user.toString());
        System.out.println();
        System.out.println();
    }
    
    // Main "menu" (get it?) of options for the user
    public static void mainMenu()
    {
        // Re-initializing the user
        String name;
        int age;
        int protein_goal;
        int carbs;
        int cholesterol;
        int calories;
        int sodium;
        int sugar;
        boolean vegan;
        boolean gluten;
        boolean muslim;
        String[] written_allergies;
        ArrayList<String> allergies = new ArrayList<>();
        List<String> user_info = null;

        try
        {
            user_info = Files.readAllLines(Paths.get("user.txt"));
        } catch (IOException ex)
        {
            System.out.println("IO Exception: ");
            ex.printStackTrace();
        } catch (SecurityException ex)
        {
            System.out.println("You cannot access the file: ");
            ex.printStackTrace();
        }

        name = user_info.get(0);
        age = Integer.parseInt(user_info.get(1));
        protein_goal = Integer.parseInt(user_info.get(2));
        carbs = Integer.parseInt(user_info.get(3));
        cholesterol = Integer.parseInt(user_info.get(4));
        calories = Integer.parseInt(user_info.get(5));
        sodium = Integer.parseInt(user_info.get(6));
        sugar = Integer.parseInt(user_info.get(7));
        vegan = Boolean.parseBoolean(user_info.get(8));
        gluten = Boolean.parseBoolean(user_info.get(9));
        muslim = Boolean.parseBoolean(user_info.get(10));
        if (user_info.size() > 11)
        {
            written_allergies = user_info.get(11).split(" ");
            for (String e:written_allergies)
            {
                allergies.add(e);
            }
        }

        User user = new User(name, age, protein_goal, carbs, cholesterol, calories, sodium, sugar, vegan, gluten, muslim, allergies);

        
        System.out.println("Welcome back to Cooking Mama 3.0! Choose an option below!");
        System.out.println("1) Recommend meal plan"); // Get an automatic meal plan for breakfast, lunch and dinner based on your user info
        System.out.println("2) Verify meal plan"); // Choose a meal plan yourself and see if it's compliant to your needs and allergies
        System.out.println("3) Create new recipe"); // Create a new custom recipe
        System.out.println("4) Re-do user"); // If the user wrote their information wrong or wants to change their goals, they can re-do their user
        System.out.println("5) Exit Program");
        System.out.printf("Choose: ");
    }


    public static void main(String[] args) throws Exception
    {
        // Attributes
        // Open user information from file
        File file = new File("user.txt");

        if (!file.isFile()) // Checks if a user already exists. If it doesnt, it prompts you to create one. If it exists, it takes you to the main menu
        {
            welcomeProtocol();
            mainMenu();
        } else
        {
            mainMenu();
        }
    }
}
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.IOException;

import cm.users.*;
import cm.recipes.*;

public class Main
{
    public static final String RECIPE_DIRECTORY = System.getProperty("user.dir") + "/user_recipes/";
    
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
            // Prompts the user to enter their information
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

        System.out.println("User creation done! Take a look\n");
        System.out.println(new_user.toString());
        System.out.println();
        System.out.println();
    }

    // Generates a new Recipe object based on the information on a specific file
    public static Recipe generateRecipeObject(String file_name)
    {        
        String recipe_name;
        int cooking_time;
        double recipe_yield;
        String time_of_day;

        ArrayList<String> ingredients = new ArrayList<>();
        ArrayList<String> instructions = new ArrayList<>();
        NutrientProfile np1;
        
        // Reads info about a recipe from the file where it is located
        List<String> recipe_info = null;
        try
        {
            recipe_info = Files.readAllLines(Paths.get(file_name));
        } catch (IOException ex)
        {
            System.out.printf("IO Exception opening %s: \n", file_name);
            ex.printStackTrace();
        } catch (SecurityException ex)
        {
            System.out.println("Cannot access the file!");
            ex.printStackTrace();
        }

        recipe_name = recipe_info.get(0);
        cooking_time = Integer.parseInt(recipe_info.get(1));
        recipe_yield = Double.parseDouble(recipe_info.get(2));
        time_of_day = recipe_info.get(3);
        np1 = new NutrientProfile
        (
            Integer.parseInt(recipe_info.get(4)), 
            Integer.parseInt(recipe_info.get(5)), 
            Integer.parseInt(recipe_info.get(6)), 
            Integer.parseInt(recipe_info.get(7)), 
            Integer.parseInt(recipe_info.get(8)), 
            Integer.parseInt(recipe_info.get(9))
        );

        for (String e:recipe_info.get(10).split(","))
        {
            // The method createNewRecipe() includes an empty string at the end of the ingredients. I felt this was the easiest fix
            if (!e.isBlank())
            {
                ingredients.add(e);
            }
        }

        for (int i=11; i < recipe_info.size(); i++)
        {
            instructions.add(recipe_info.get(i)); // Since the last lines of the file will *always* be the instructions, I can iterate until the end of the file
        }

        Recipe recipe = new Recipe
        (
            recipe_name, 
            cooking_time, 
            recipe_yield,
            time_of_day, 
            ingredients, 
            instructions, 
            np1
        );

        return recipe;
    }

    // This function basically just iterates the folder with all the recipes and runs the generateRecipeObject() method for all of them, and returns an ArrayList with all of them
    public static ArrayList<Recipe> generateRecipeObjects()
    {
        ArrayList<Recipe> recipes = new ArrayList<>();
        Recipe current_recipe = null;
        String current_filename = null;
        
        File dir = new File(RECIPE_DIRECTORY);
        File[] dir_listing = dir.listFiles();
        if (dir_listing != null)
        {
            for (File e:dir_listing)
            {
                current_filename = e.getName();
                current_recipe = generateRecipeObject(RECIPE_DIRECTORY + current_filename);
                recipes.add(current_recipe);
            }
        }

        return recipes;
        
    }

    // Allows the user to write an ingredient. Then, the program searches that ingredient in all recipes, and returns recipes that have it
    public static void checkRecipesByIngredient(ArrayList<Recipe> recipes)
    {
        Scanner scanner = new Scanner(System.in);
        String user_ingredient;
        
        System.out.printf("Write down a specific ingredient: ");
        user_ingredient = scanner.nextLine();
        scanner.close();

        System.out.printf("\nWith %s you can cook the following meals: \n", user_ingredient);
        for (Recipe r:recipes)
        {
            for (String e:r.getIngredients())
            {
                if (e.contains(user_ingredient))
                {
                    System.out.println(r.getRecipeName());
                }
            }
        }
    }

    // Very simply allows the user to see a list of all recipes (regardless of allergies or restrictions), and prints all info about said recipe to the terminal
    public static void checkParticularRecipe(ArrayList<Recipe> recipes)
    {
        int chosen_recipe;
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nWhich recipe do you want to check out? ");
        for (int i = 0; i < recipes.size(); i++)
        {
            System.out.printf("%d %s\n", i, recipes.get(i).getRecipeName());
        }
        System.out.printf("Choose a recipe by the number to its left: ");
        chosen_recipe = scanner.nextInt();
        scanner.nextLine();
        scanner.close();
        System.out.printf("\n%s", recipes.get(chosen_recipe).toString());
    }

    // Chooses a random breakfast-lunch-dinner meal plan, making the user aware of any important limits being passed or goals not met
    public static void recommendRecipes(User user, ArrayList<Recipe> valid_recipes)
    {
        ArrayList<Recipe> chosen_recipes = new ArrayList<>();
        HashSet<String> seen = new HashSet<>();
        boolean ok_by_user = false;
        String user_choice;
        Scanner scanner = new Scanner(System.in);

        System.out.println("The program proposes the following meal plan: ");

        do
        {
            // A set is used here because it will not allow duplicates. Useful to only get one of each meal tipe
            chosen_recipes.clear();
            seen.clear();
            Collections.shuffle(valid_recipes); // Shuffling so that the order changes each time
            for (Recipe r:valid_recipes)
            {
                if (seen.add(r.getTimeOfDay()))
                {
                    chosen_recipes.add(r);
                    System.out.printf("%s: %s\n", r.getTimeOfDay(), r.getRecipeName());

                }

                if (chosen_recipes.size() == 3)
                {
                    break;
                }
            }

            // Calls a method in user that prints the nutritional considerations of the meal plan
            user.getNutritionalConsiderations(chosen_recipes);
            System.out.printf("Do you still want this meal plan? (yes/no) ");
            user_choice = scanner.nextLine();

            // Allows the user to request a different, randomized meal plan
            if (user_choice.equals("yes"))
            {
                ok_by_user = true;
            }

            

        } while (!ok_by_user);

        scanner.close();
    }

    // Allows the user to themselves choose three different dishes, and also check nutritional considerations like it's done above
    public static void checkCompliance(User user, ArrayList<Recipe> valid_recipes)
    {
        ArrayList<Recipe> chosen_recipes = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int user_choice;

        System.out.println("Choose three of the following recipes by the number on the left: ");
        for (int i = 0; i < valid_recipes.size(); i++)
        {
            System.out.printf("%d) %s (%s)\n", i, valid_recipes.get(i).getRecipeName(), valid_recipes.get(i).getTimeOfDay());
        }

        do
        {
            System.out.printf("Choice %d: ", chosen_recipes.size() + 1);
            user_choice = scanner.nextInt();
            scanner.nextLine();
            chosen_recipes.add(valid_recipes.get(user_choice));
        } while (chosen_recipes.size() < 3);

        System.out.println("You chose: ");
        for (Recipe r:chosen_recipes)
        {
            System.out.println(r.getRecipeName());
        }
        user.getNutritionalConsiderations(chosen_recipes);

        scanner.close();
    }
    
    // Allows the user to create a new recipe and save it to a file to be loaded later
    public static void createNewRecipe()
    {
        String recipe_name;
        String recipe_username;
        int cooking_time;
        double recipe_yield;
        String time_of_day;
        int protein;
        int carbs;
        int cholesterol;
        int calories;
        int sodium;
        int sugar;

        ArrayList<String> ingredients = new ArrayList<>();
        String current_ingredient;
        ArrayList<String> instructions = new ArrayList<>();
        String current_instruction;
        Scanner scanner = new Scanner(System.in);

        // Taking information about the recipe
        System.out.println("Let's get started creating the new recipe");
        System.out.printf("First, what is the name for your recipe? ");
        recipe_name = scanner.nextLine();
        System.out.printf("How long does the recipe take to prepare (in minutes)? ");
        cooking_time = scanner.nextInt();
        scanner.nextLine();
        System.out.printf("How many portions does the recipe normally yield? ");
        recipe_yield = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Is this a breakfast, lunch, or dinner recipe?");
        time_of_day = scanner.nextLine().toLowerCase();
        System.out.printf("How many grams of protein are there in this recipe? ");
        protein = scanner.nextInt();
        scanner.nextLine();
        System.out.printf("How many carbs are there in this recipe? ");
        carbs = scanner.nextInt();
        scanner.nextLine();
        System.out.printf("How many mg of cholesterol are there in this recipe? ");
        cholesterol = scanner.nextInt();
        scanner.nextLine();
        System.out.printf("How many calories are there in this recipe? ");
        calories = scanner.nextInt();
        scanner.nextLine();
        System.out.printf("How many mg of sodium are there in this recipe? ");
        sodium = scanner.nextInt();
        scanner.nextLine();
        System.out.printf("How many grams of sugar are there in this recipe? ");
        sugar = scanner.nextInt();
        scanner.nextLine();
        
        do
        {
            System.out.println("Write the ingredients for the meal. Type done when finished:");
            current_ingredient = scanner.nextLine();

            if (!current_ingredient.equalsIgnoreCase("done"))
            {
                ingredients.add(current_ingredient.toLowerCase());
            }
        } while (!current_ingredient.equalsIgnoreCase("done"));

        do
        {
            System.out.println("Write the instructions for the meal. Type done when finished:");
            current_instruction = scanner.nextLine();

            if (!current_instruction.equalsIgnoreCase("done"))
            {
                instructions.add(current_instruction.toLowerCase());
            }
        } while (!current_instruction.equalsIgnoreCase("done"));

        scanner.close();

        // The "recipe_username" is the name of the file that will be saved. This could just be the name of the recipe, but I personally don't like it when files have uppercase letters and (particularly) spaces in their names
        recipe_username = RECIPE_DIRECTORY + recipe_name.toLowerCase().replace(" ", "_") + ".txt";

        // Creating new file to write into
        try
        {
            File new_recipe_file = new File(recipe_username);
            new_recipe_file.createNewFile();

        } catch (IOException ex)
        {
            System.out.println("There was an error creating new recipe file: ");
            ex.printStackTrace();
        }

        try
        {
            FileWriter fw1 = new FileWriter(recipe_username, true);
            fw1.write(recipe_name);
            fw1.write("\n");
            fw1.write(String.valueOf(cooking_time));
            fw1.write("\n");
            fw1.write(String.valueOf(recipe_yield));
            fw1.write("\n");
            fw1.write(time_of_day);
            fw1.write("\n");
            fw1.write(String.valueOf(protein));
            fw1.write("\n");
            fw1.write(String.valueOf(carbs));
            fw1.write("\n");
            fw1.write(String.valueOf(cholesterol));
            fw1.write("\n");
            fw1.write(String.valueOf(calories));
            fw1.write("\n");
            fw1.write(String.valueOf(sodium));
            fw1.write("\n");
            fw1.write(String.valueOf(sugar));
            fw1.write("\n");
            for (String e:ingredients)
            {
                fw1.write(e);
                fw1.write(",");
            }

            fw1.write("\n");

            for (String e:instructions)
            {
                fw1.write(e);
                fw1.write("\n");
            }

            fw1.close();

        } catch (IOException ex)
        {
            System.out.println("There was an error writing into the new recipe file: ");
            ex.printStackTrace();
        }

        System.out.println("Recipe created!");

    }
    
    // Main "menu" (get it?) of options for the user
    // This function also creates the User object from the info on the user.txt file
    public static void mainMenu()
    {
        int user_choice = 0;
        
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
        Scanner scanner = new Scanner(System.in);
        ArrayList<Recipe> recipes = new ArrayList<>();
        ArrayList<Recipe> valid_recipes = new ArrayList<>();

        // Creates an array with all Recipe objects
        recipes = generateRecipeObjects();

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
        // If a user does not have any additional allergies, the twelvth line in the user.txt will be empty. Because of this, it will only be read if it actually exists, ie, if the user actually has allergies. Otherwise, it will be an empty Array in their User object
        if (user_info.size() > 11)
        {
            written_allergies = user_info.get(11).split(" ");
            for (String e:written_allergies)
            {
                allergies.add(e);
            }
        }

        User user = new User(name, age, protein_goal, carbs, cholesterol, calories, sodium, sugar, vegan, gluten, muslim, allergies);

        // Checks for "valid recipes", ie recipes that dont trigger a user's allergies or dietary restrictions. This sub-array is useful for the "recommend meal plan" option in the menu
        for (Recipe e:recipes)
        {
            if (user.canEat(e))
            {
                valid_recipes.add(e);
            }
        }

        System.out.printf("Welcome back to Cooking Mama 3.0 %s! Choose an option below!\n", user.getName());
        System.out.println("1) Recommend meal plan"); // Get an automatic meal plan for breakfast, lunch and dinner based on your user info
        System.out.println("2) Verify meal plan"); // Choose a meal plan yourself and check compliance with goals
        System.out.println("3) Create new recipe"); // Create a new custom recipe
        System.out.println("4) Check Recipe"); // Allows the user to check a particular recipe
        System.out.println("5) Search recipe by ingredient"); // Allows the user to see which recipes have a certain ingredient
        System.out.println("6) Exit Program");
        System.out.printf("Choose: ");
        user_choice = scanner.nextInt();
        scanner.nextLine();

        switch (user_choice) {
            case 1:
                recommendRecipes(user, valid_recipes);
                break;
            case 2:
                checkCompliance(user, valid_recipes);
                break;
            case 3:
                createNewRecipe();
                break;
            case 4:
                checkParticularRecipe(recipes);
                break;
            case 5:
                checkRecipesByIngredient(valid_recipes);
                break;
            case 6:
                System.exit(0);
                break;
            default:
                break;
        }

        scanner.close();
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
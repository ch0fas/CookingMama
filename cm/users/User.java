package cm.users;
import java.util.ArrayList;

public class User
{
    // Atributes
    protected String name; // The name of the user
    protected int age; // Their age
    protected int daily_protein_goal; // A daily protein goal, in grams
    protected int daily_carb_limit; // Daily carbs limit, in grams
    protected int daily_cholesterol_limit; // Daily cholesterol limit, useful if the user has special needs, in mg
    protected int daily_calorie_limit; // Maximum daily calorie intake
    protected int daily_sodium_limit; // Daily sodium limit, in mg
    protected int daily_sugar_limit; // Daily sugar limit, in grams
    protected boolean vegan; // If the user is vegan or not
    protected boolean gluten; // If the user can consume gluten, could be included in allergies but this is more specific
    protected ArrayList<String> allergies; // Any ingredient a user could be allergic to, to avoid recommending said recipes

    public User(String name, int age, int protein_goal, int carbs, int cholesterol, int calories, String[] allergies)
    {
        setName(name);
        setAge(age);
        setProtein(protein_goal);
        setCarbs(carbs);
        setCholesterol(cholesterol);
        setCalories(calories);
        setAllergies(allergies);
    }

    // Setters
    public void setName(String name)
    {
        this.name = name;
    }

    public void setAge(int age)
    {
        if (age >= 18)
        {
            this.age = age;
        } else this.age = 21;
    }

    public void setProtein(int protein)
    {
        if (protein >= 0)
        {
            this.daily_protein_goal = protein;
        } else this.daily_protein_goal = 49;
    }

    public void setCarbs(int carbs)
    {
        if (carbs >= 0)
        {
            this.daily_carb_limit = carbs;
        } else this.daily_carb_limit = 300;
    }

    public void setCholesterol(int chol)
    {
        if (chol >= 0)
        {
            this.daily_cholesterol_limit = chol;
        } else this.daily_cholesterol_limit = 150; // TEMP
    }

    public void setCalories(int cals)
    {
        if (cals >= 0)
        {
            this.daily_calorie_limit = cals;
        } else this.daily_calorie_limit = 2200;
    }

    public void setSodium(int sodium)
    {
        if (sodium >= 0)
        {
            this.daily_sodium_limit = sodium;
        } else this.daily_sodium_limit = 2300;
    }

    public void setAllergies(String[] allergies)
    {
        this.allergies = allergies;
    }

    public String getAllergyString()
    {
        String final_allergy_string = "";
        for (String e:allergies)
        {
            final_allergy_string += String.format("%s, ", e);
        }
        return final_allergy_string;
    }

    public String toString()
    {
        return String.format("About You\nName: %s, Age: %d\nDaily Protein Goal: %d\nDaily Carb Limit: %d\nDaily Cholesterol Limit: %d\nDaily Calorie Limit: %d\nDaily Sodium Limit: %d\nDaily Sugar Limit: %d\nAllergies: %s", this.name, this.age, this.daily_protein_goal, this.daily_carb_limit, this.daily_cholesterol_limit, this.daily_calorie_limit, this.daily_sodium_limit, this.daily_sugar_limit, getAllergyString());
    }
}
package cm.users;
import cm.recipes.Recipe;
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
    protected boolean muslim; // If the user is muslim, and must therefore adhere to islamic dietary laws
    protected ArrayList<String> allergies; // Any ingredient a user could be allergic to, to avoid recommending said recipes

    public User(String name, int age, int protein_goal, int carbs, int cholesterol, int calories, int sodium, int sugar, boolean vegan, boolean gluten, boolean muslim, ArrayList<String> allergies)
    {
        setName(name);
        setAge(age);
        setProtein(protein_goal);
        setCarbs(carbs);
        setCholesterol(cholesterol);
        setCalories(calories);
        setSodium(sodium);
        setSugar(sugar);
        setVegan(vegan);
        setGluten(gluten);
        setMuslim(muslim);
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

    public void setSugar(int sugar)
    {
        if (sugar >= 0)
        {
            this.daily_sugar_limit = sugar;
        } else this.daily_sugar_limit = 48;
    }

    public void setVegan(boolean vegan) { this.vegan = vegan; }
    public void setGluten(boolean gluten) { this.gluten = gluten; }
    public void setMuslim(boolean muslim) { this.muslim = muslim; }


    public void setAllergies(ArrayList<String> allergies)
    {
        this.allergies = allergies;
    }

    // Creates new DietaryFilter-derivative objects for every allergy the user has
    public ArrayList<DietaryFilter> getUserFilters()
    {
        ArrayList<DietaryFilter> filters = new ArrayList<>();

        if (vegan)
        {
            filters.add(new VeganFilter());
        }
        if (gluten)
        {
            filters.add(new GlutenFilter());
        }
        if (muslim)
        {
            filters.add(new HalalFilter());
        }

        if (getAllergies() != null)
        {
            for (String allergen: getAllergies())
            {
                filters.add(new AllergyFilter(allergen));
            }
        }

        return filters;
    }

    // Uses the filters to check if the user can consume a specific recipe
    public boolean canEat(Recipe recipe)
    {
        for (DietaryFilter df: getUserFilters())
        {
            if (df.matches(recipe))
            {
                return false;
            }
        }

        return true;
    }

    // Checks if nutritional requirements are met
    public void getNutritionalConsiderations(ArrayList<Recipe> recipes)
    {
        int total_protein = 0;
        int total_carbs = 0;
        int total_cholesterol = 0;
        int total_calories = 0;
        int total_sodium = 0;
        int total_sugar = 0;

        for (Recipe r:recipes)
        {
            total_protein += r.getNutrientProfile().getProtein();
            total_carbs += r.getNutrientProfile().getCarbs();
            total_cholesterol += r.getNutrientProfile().getCholesterol();
            total_calories += r.getNutrientProfile().getCalories();
            total_sodium += r.getNutrientProfile().getSodium();
            total_sugar += r.getNutrientProfile().getSugar();
        }

        System.out.println("Some considerations: ");
        if (total_protein < getProteinGoal()) { System.out.printf("NOTE: This meal plan does not meet your protein goals (%d < %d)\n", total_protein, getProteinGoal()); }
        if (total_carbs > getCarbLimit()) { System.out.printf("NOTE: This meal plan exceeds your carbs limit (%d > %d)\n", total_carbs, getCarbLimit()); }
        if (total_cholesterol > getCholesterolLimit()) { System.out.printf("NOTE: This meal plan exceeds your cholesterol limit (%d > %d)\n", total_cholesterol, getCholesterolLimit()); }
        if (total_calories > getCalorieLimit()) { System.out.printf("NOTE: This meal plan exceeds your calorie limit (%d > %d)\n", total_calories, getCalorieLimit()); }
        if (total_sodium > getSodiumLimit()) { System.out.printf("NOTE: This meal plan exceeds your sodium limit (%d > %d)\n", total_sodium, getSodiumLimit()); }
        if (total_sugar > getSugarLimit()) { System.out.printf("NOTE: This meal plan exceeds your sugar limit (%d > %d)\n", total_sugar, getSugarLimit()); }
        
    }

    //Getters
    public String getName() { return this.name; }
    public int getAge() { return this.age; }
    public int getProteinGoal() { return this.daily_protein_goal; }
    public int getCarbLimit() { return this.daily_carb_limit; }
    public int getCholesterolLimit() { return this.daily_cholesterol_limit; }
    public int getCalorieLimit() { return this.daily_calorie_limit; }
    public int getSodiumLimit() { return this.daily_sodium_limit; }
    public int getSugarLimit() { return this.daily_sodium_limit; }
    public boolean isVegan() { return this.vegan; }
    public boolean noGluten() { return this.gluten; }
    public boolean isMuslim() { return this.muslim;}
    public ArrayList<String> getAllergies() { return this.allergies; }

    public String getAllergyString()
    {
        String final_allergy_string = "";
        for (String e:allergies)
        {
            final_allergy_string += String.format(" ", e);
        }
        return final_allergy_string;
    }

    public String toString()
    {
        return String.format("About You\nName: %s, Age: %d\nDaily Protein Goal: %d\nDaily Carb Limit: %d\nDaily Cholesterol Limit: %d\nDaily Calorie Limit: %d\nDaily Sodium Limit: %d\nDaily Sugar Limit: %d\nIs Vegan? %s\nIs Gluten Allergic? %s\nIs Muslim? %s\nAllergies: %s", getName(), getAge(), getProteinGoal(), getCarbLimit(), getCholesterolLimit(), getCalorieLimit(), getSodiumLimit(), getSugarLimit(), isVegan(), noGluten(), isMuslim(), getAllergyString());
    }
}
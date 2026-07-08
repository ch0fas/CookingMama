package cm.recipes;

// Class that organizes all nutritional info from a recipe in one place
public class NutrientProfile
{
    // Atributes
    protected int protein; // Grams
    protected int carbs; // Grams
    protected int cholesterol; // mg
    protected int calories;
    protected int sodium; // mg
    protected int sugar; // Grams

    public NutrientProfile(int protein, int carbs, int cholesterol, int calories, int sodium, int sugar)
    {
        this.protein = protein;
        this.carbs = carbs;
        this.cholesterol = cholesterol;
        this.calories = calories;
        this.sodium = sodium;
        this.sugar = sugar;
    }

    // Getters
    public int getProtein() { return this.protein;}
    public int getCarbs() { return this.carbs;}
    public int getCholesterol() { return this.cholesterol;}
    public int getCalories() { return this.calories;}
    public int getSodium() { return this.sodium;}
    public int getSugar() { return this.sugar;}

    public String toString()
    {
        return String.format("-- Nutritional Information--\nProtein: %d gr\nCarbs: %d mg\nCholesterol: %d mg\nCalories: %d\nSodium: %d mg\nSugar: %d gr", getProtein(), getCarbs(), getCholesterol(), getCalories(), getSodium(), getSugar());
    }
}
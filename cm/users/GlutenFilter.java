package cm.users;
import cm.recipes.Recipe;

public class GlutenFilter implements DietaryFilter
{
    private final String[] GLUTEN_FOODS = {"wheat", "flour", "bread", "pasta", "grain", "cereal", "cereals","barley", "rye", "oat", "oats"};

    @Override
    public boolean matches(Recipe recipe)
    {
        for (String ingredient: recipe.getIngredients())
        {
            for (String keyword: GLUTEN_FOODS)
            {
                if (ingredient.contains(keyword))
                {
                    return false;
                }
            }
        }

        return true;
    }
}
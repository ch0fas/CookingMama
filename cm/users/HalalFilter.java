package cm.users;
import cm.recipes.Recipe;

public class HalalFilter implements DietaryFilter
{
    private final String[] HALAL_KEYWORDS = {"pork", "wine", "alcohol"};

    public boolean matches(Recipe recipe)
    {
        for (String ingredient: recipe.getIngredients())
        {
            for (String keyword: HALAL_KEYWORDS)
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
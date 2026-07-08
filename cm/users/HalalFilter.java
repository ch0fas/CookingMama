package cm.users;
import cm.recipes.Recipe;

public class HalalFilter implements DietaryFilter
{
    // The main things I found that muslims cant eat
    // Unsurprisingly I am not a muslim so this may be incomplete. Feel free to add more
    private final String[] HALAL_KEYWORDS = {"pork", "wine", "alcohol", "beer"};

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
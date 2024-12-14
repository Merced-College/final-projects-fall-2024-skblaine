import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

// class uses HashMap from manager to find recipes using ingregients and/or categories
class RecipeSearcher {
    private HashMap<String, Recipe> recipeManager;

    public RecipeSearcher(HashMap<String, Recipe> recipeManager) {
        this.recipeManager = recipeManager;
    }
    
    // Find recipes by the given user ingredients
    public List<Recipe> findRecipesByIngredients(HashSet<String> userIngredients) {
        List<Recipe> matchingRecipes = new ArrayList<Recipe>();

        // Will loop through all the recipes and check if they match
        for (Object recipeObj : recipeManager.values()) {
            Recipe recipe = (Recipe) recipeObj; 

            // Check for an  intersection 
            HashSet<String> recipeIngredients = recipe.getIngredients();

            //  retainAll to find common ingredients btwn recipe ingredients and user ingrefients
            HashSet<String> commonIngredients = new HashSet<String>(recipeIngredients);
            commonIngredients.retainAll(userIngredients);

            // if there is at least one matching ingredients, it should count as  a match
            if (!commonIngredients.isEmpty()) {
                matchingRecipes.add(recipe);
            }
        }

        return matchingRecipes;//returns matching recipes
    }


    // Find recipes by the category
    public List<Recipe> findRecipesByCategory(String category) {
        List<Recipe> matchingRecipes = new ArrayList<Recipe>();

        for (Object recipeObj : recipeManager.values()) {
            Recipe recipe = (Recipe) recipeObj;
            if (recipe.getCategory().equalsIgnoreCase(category)) {
                matchingRecipes.add(recipe);
            }
        }

        return matchingRecipes; //returns matching recipes
    }
}



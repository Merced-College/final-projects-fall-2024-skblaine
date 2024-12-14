import java.util.Scanner;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
//import java.util.ArrayList;

//main functionalities of the 'app'. user interactions, searching for matching recipes, and output. 
class RecipeApp {
    private HashMap<String, Recipe> recipeManager;  
    private RecipeSearcher recipeSearcher;

    // Initialize the recipe manager
    public RecipeApp() {
        recipeManager = new HashMap<String, Recipe>();
        recipeSearcher = new RecipeSearcher(recipeManager);
    }

    // User interaction/input
    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter ingredients (comma separated): ");
        String ingredientsInput = scanner.nextLine();
        String[] ingredientsArray = ingredientsInput.split(",");
        HashSet<String> userIngredients = new HashSet<String>();

        // Turn inputred ingredients into a HashSet
        for (String ingredient : ingredientsArray) {
            userIngredients.add(ingredient.trim().toLowerCase());
        }

        System.out.println("Enter a category (or leave blank for no category): ");
        String category = scanner.nextLine().trim();

        //move onto matching,
        List<Recipe> foundRecipes;

        // Search by ingredients/ category 
        if (category.isEmpty()) {
            foundRecipes = recipeSearcher.findRecipesByIngredients(userIngredients);
        } else {
            foundRecipes = recipeSearcher.findRecipesByCategory(category);
        }

        // Display results 
        if (foundRecipes.isEmpty()) {
            System.out.println("No matching recipes found.");//if no recipes are added
        } else {
            for (Recipe recipe : foundRecipes) {
                System.out.println(recipe);
                System.out.println();
            }
        }
    }

    // Main method to run the app 
    public static void main(String[] args) {
        RecipeApp app = new RecipeApp();
        app.start();
    }
}


package paper.projetowebpaper.viewmodels.initial;

import paper.projetowebpaper.viewmodels.recipe.RecipeViewModel;

import java.util.List;

public class InitialInformationViewModel {
    public List<RecipeViewModel> recipes;
    public Integer UsersTotal;
    public Integer ImageTotal;
    public Integer CommentsTotal;
    public Integer RecipesTotal;

    public InitialInformationViewModel() {
    }

    public InitialInformationViewModel(List<RecipeViewModel> recipes, Integer usersTotal, Integer imageTotal, Integer commentsTotal, Integer recipesTotal) {
        this.recipes = recipes;
        UsersTotal = usersTotal;
        ImageTotal = imageTotal;
        CommentsTotal = commentsTotal;
        RecipesTotal = recipesTotal;
    }

    public List<RecipeViewModel> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<RecipeViewModel> recipes) {
        this.recipes = recipes;
    }

    public Integer getUsersTotal() {
        return UsersTotal;
    }

    public void setUsersTotal(Integer usersTotal) {
        UsersTotal = usersTotal;
    }

    public Integer getImageTotal() {
        return ImageTotal;
    }

    public void setImageTotal(Integer imageTotal) {
        ImageTotal = imageTotal;
    }

    public Integer getCommentsTotal() {
        return CommentsTotal;
    }

    public void setCommentsTotal(Integer commentsTotal) {
        CommentsTotal = commentsTotal;
    }

    public Integer getRecipesTotal() {
        return RecipesTotal;
    }

    public void setRecipesTotal(Integer recipesTotal) {
        RecipesTotal = recipesTotal;
    }
}

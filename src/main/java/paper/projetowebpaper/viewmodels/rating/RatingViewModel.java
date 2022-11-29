package paper.projetowebpaper.viewmodels.rating;

public class RatingViewModel {
    public Integer id;
    public Integer rate;
    public Integer recipeId;
    public Integer authorId;

    public RatingViewModel() {
    }

    public RatingViewModel(Integer id, Integer rate, Integer recipeId, Integer authorId) {
        this.id = id;
        this.rate = rate;
        this.recipeId = recipeId;
        this.authorId = authorId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }
}

package paper.projetowebpaper.models;

import javax.persistence.*;

@Entity(name = "rating")
public class RatingModel {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="rating_seq_gen")
    @SequenceGenerator(name="rating_seq_gen", sequenceName="RATING_SEQ")
    public Integer id;

    @Column(name = "rate")
    public Integer rate;

    @ManyToOne
    @JoinColumn(name = "recipeId")
    public RecipeModel recipe;

    @ManyToOne
    @JoinColumn(name = "authorId")
    public UserModel author;

    public RatingModel() {
    }

    public RatingModel(Integer id, Integer rate, RecipeModel recipe, UserModel author) {
        this.id = id;
        this.rate = rate;
        this.recipe = recipe;
        this.author = author;
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

    public RecipeModel getRecipe() {
        return recipe;
    }

    public void setRecipe(RecipeModel recipe) {
        this.recipe = recipe;
    }

    public UserModel getAuthor() {
        return author;
    }

    public void setAuthor(UserModel author) {
        this.author = author;
    }
}

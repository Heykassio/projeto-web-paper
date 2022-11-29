package paper.projetowebpaper.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name="recipe")
public class RecipeModel {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="recipe_seq_gen")
    @SequenceGenerator(name="recipe_seq_gen", sequenceName="RECIPE_SEQ")
    public Integer id;

    @Column(name = "name", nullable = false)
    public String name;
    @Column(name = "steps", nullable = false)
    public String steps;

    @Column(name = "ingredients", nullable = false)
    public String ingredients;

    @Column(name = "timeInMinutes", nullable = false)
    public Integer timeInMinutes;

    @Column(name = "createdAt", nullable = false)
    public LocalDateTime createdAt;

    @Column(name = "updateAt", nullable = false)
    public LocalDateTime updateAt;


    @ManyToOne
    @JoinColumn(name = "authorId")
    public UserModel author;

    @OneToMany(mappedBy = "recipe")
    public List<CommentModel> comments = new ArrayList<>();

    @OneToMany(mappedBy = "recipe")
    public List<RatingModel> ratings = new ArrayList<>();

    @OneToMany(mappedBy = "recipe")
    public List<ImagemModel> images = new ArrayList<>();

    public RecipeModel() {
    }

    public RecipeModel( String name, String steps, String ingredients, Integer timeInMinutes,
                       LocalDateTime createdAt, LocalDateTime updateAt,
                       UserModel author, List<CommentModel> comments, List<RatingModel> ratings,
                       List<ImagemModel> images) {
        this.name = name;
        this.steps = steps;
        this.ingredients = ingredients;
        this.timeInMinutes = timeInMinutes;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.author = author;
        this.comments = comments;
        this.ratings = ratings;
        this.images = images;
    }
}

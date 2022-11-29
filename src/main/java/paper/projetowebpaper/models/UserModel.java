package paper.projetowebpaper.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name="user")
public class UserModel {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="user_seq_gen")
    @SequenceGenerator(name="user_seq_gen", sequenceName="USER_SEQ")
    public Integer id;
    @Column(name ="name", nullable = false, length = 80)
    public String name;
    @Column(name = "email", nullable = false, unique = true)
    public String email;
    @Column(name = "password", nullable = false)
    public String password;

    @Column(name = "createdAt", nullable = false)
    public LocalDateTime createdAt;

    @Column(name = "updatedAt", nullable = false)
    public LocalDateTime updatedAt;

    @OneToMany(mappedBy = "author")
    public List<RecipeModel> recipes = new ArrayList<>();

    @OneToMany(mappedBy = "author")
    public List<CommentModel> comments = new ArrayList<>();

    @OneToMany(mappedBy = "author")
    public List<RatingModel> ratings = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RecipeModel> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<RecipeModel> recipes) {
        this.recipes = recipes;
    }

    public List<CommentModel> getComments() {
        return comments;
    }

    public void setComments(List<CommentModel> comments) {
        this.comments = comments;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<RatingModel> getRatings() {
        return ratings;
    }

    public void setRatings(List<RatingModel> ratings) {
        this.ratings = ratings;
    }
}

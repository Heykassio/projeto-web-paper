package paper.projetowebpaper.viewmodels.user;

public class UserLoginViewModel {
    public String email;
    public String password;

    public UserLoginViewModel() {
    }

    public UserLoginViewModel(String email, String password) {
        this.email = email;
        this.password = password;
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
}

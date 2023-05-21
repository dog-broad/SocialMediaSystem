import java.util.ArrayList;
import java.util.List;


/*
This class represents a user on the social media system.
It has a username, password, and list of posts.
*/
class User {
    private String username;
    private String password;
    private List<String> posts;

    /*
    Constructs a new user with the given username and password.
    */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.posts = new ArrayList<>();
    }

    /*
    Returns the username of this user.
    */
    public String getUsername() {
        return username;
    }

    /*
    Authenticates the user with the given password.
    Returns true if the password is correct, false otherwise.
    */
    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    /*
    Add a post to this user's list of posts.
    */
    public void addPost(String post) {
        posts.add(post);
    }

    /*
    Returns a list of this user's posts.
    */
    public List<String> getPosts() {
        return posts;
    }
}
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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

/*
This class represents the social media system.
It has a map of users and a method for creating, logging in, and creating posts.
*/
class SocialMediaSystem {

    private Map<String, User> users;

    /*
    Constructs a new social media system.
    */
    public SocialMediaSystem() {
        this.users = new HashMap<>();
    }

    /*
    Creates a new user with the given username and password.
    If the username already exists, an error message is printed.
    */
    public void createUser(String username, String password) {
        if (!users.containsKey(username)) {
            User newUser = new User(username, password);
            users.put(username, newUser);
            System.out.println("User created successfully.");
        } else {
            System.out.println("Username already exists. Please choose a different username.");
        }
    }

    /*
    Logs in a user with the given username and password.
    If the username or password is incorrect, an error message is printed.
    */
    public void login(String username, String password) {
        if (users.containsKey(username)) {
            User user = users.get(username);
            if (user.authenticate(password)) {
                System.out.println("Login successful!");
                showUserPosts(String.valueOf(user));
            } else {
                System.out.println("Incorrect password. Please try again.");
            }
        } else {
            System.out.println("Username does not exist. Please sign up for a new account.");
        }
    }

    /*
    Creates a new post for the user with the given username.
    If the username does not exist, an error message is printed.
    */
    public void createPost(String username, String post) {
        if (users.containsKey(username)) {
            User user = users.get(username);
            user.addPost(post);
            System.out.println("Post created successfully.");
        } else {
            System.out.println("Username does not exist. Please sign up for a new account.");
        }
    }

    /*
    Shows the posts for the user with the given username.
    If the username does not exist, an error message is printed.
    */
    public void showUserPosts(String username) {
        if (users.containsKey(username)) {
            User user = users.get(username);
            List<String> posts = user.getPosts();
            if (posts.isEmpty()) {
                System.out.println("No posts to show.");
            } else {
                System.out.println("Posts for user " + user.getUsername() + ":");
                for (String post : posts) {
                    System.out.println(post);
                }
            }
        }
    }
}
public class SocialMediaApp {
    public static void main(String[] args) {
        SocialMediaSystem socialMediaSystem = new SocialMediaSystem();
        Scanner scanner = new Scanner(System.in);

        boolean isRunning = true;
        boolean isLoggedIn = false;

        while (isRunning) {
            if(!isLoggedIn) {
                System.out.println("Welcome to the Social Media System!");
                System.out.println("1. Create an account");
                System.out.println("2. Log in");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        System.out.print("Enter a username: ");
                        String username = scanner.nextLine();
                        System.out.print("Enter a password: ");
                        String password = scanner.nextLine();
                        socialMediaSystem.createUser(username, password);
                        break;
                    case 2:
                        System.out.print("Enter your username: ");
                        String loginUsername = scanner.nextLine();
                        System.out.print("Enter your password: ");
                        String loginPassword = scanner.nextLine();
                        socialMediaSystem.login(loginUsername, loginPassword);
                        isLoggedIn = true;
                        break;
                    case 3:
                        isRunning = false;
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
            else {
                // TODO: Add a menu for creating posts
                // Take user to new menu
                // 1. Create a post
                // 2. Show posts
                // 3. Log out
                // 4. Exit
                // Start writing code here
                System.out.println("1. Create a post");
                System.out.println("2. Show posts");
                System.out.println("3. Log out");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                switch (choice){
                    case 1:
                        System.out.print("Enter your post: ");
                        String post = scanner.nextLine();
                        // Create post
                        socialMediaSystem.createPost(String.valueOf(socialMediaSystem), post);
                        break;
                    case 2:
                        System.out.print("Enter username to check posts: ");
                        String username2 = scanner.nextLine();
                        socialMediaSystem.showUserPosts(username2);
                        break;
                    case 3:
                        isLoggedIn = false;
                        break;
                    case 4:
                        isRunning = false;
                        System.out.println("Goodbye!");
                        break;
                }
            }
            System.out.println();
        }
        scanner.close();
    }
}



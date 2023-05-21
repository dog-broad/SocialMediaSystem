package Utilities;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
This class represents the social media system.
It has a map of users and a method for creating, logging in, and creating posts.
*/
public class SocialMediaSystem {

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
            JOptionPane.showMessageDialog(null, "User created successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Username already exists. Please choose a different username.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /*
    Logs in a user with the given username and password.
    If the username or password is incorrect, an error message is printed.
    */
    public boolean login(String username, String password) {
        if (users.containsKey(username)) {
            User user = users.get(username);
            if (user.authenticate(password)) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Username does not exist. Please sign up for a new account.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    /*
    Creates a new post for the user with the given username.
    If the username does not exist, an error message is printed.
    */
    public void createPost(String username, String post) {
        if (users.containsKey(username)) {
            User user = users.get(username);
            user.addPost(post);
            JOptionPane.showMessageDialog(null, "Post created successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Username does not exist. Please sign up for a new account.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /*
    Shows the posts for the user with the given username.
    If the username does not exist, an error message is printed.
    */
    public void showUserPosts(User user) {
        List<String> posts = user.getPosts();
        if (posts.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No posts to show.", "No Posts", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder message = new StringBuilder();
            message.append("Posts for user ").append(user.getUsername()).append(":\n");
            for (String post : posts) {
                message.append(post).append("\n");
            }
            JOptionPane.showMessageDialog(null, message.toString(), "Utilities.User Posts", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Create a method to get the Utilities.User object for a given username.
    public User getUser(String username) {
        return users.get(username);
    }
}
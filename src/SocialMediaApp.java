import Utilities.SocialMediaSystem;
import Utilities.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SocialMediaApp {
    // Create GUI to utilize Utilities.SocialMediaSystem
    // Create main method to run GUI
    private JFrame frame;
    private JPanel cardPanel;
    private final SocialMediaSystem system;

    private User currentUser;

    public SocialMediaApp() {
        this.system = new SocialMediaSystem();
        initialize();
    }

    private void initialize(){
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardPanel = new JPanel(new CardLayout());
        frame.getContentPane().add(cardPanel, BorderLayout.CENTER);

        createLoginCard();
        createPostCard();

        showCard("Login");

        frame.setVisible(true);

    }

    private void createLoginCard(){
        // Create Login JPanel which also contains the Create Account button
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);

        // Create Social Media App label
        JLabel lblSocialMediaApp = new JLabel("Social Media App");
        lblSocialMediaApp.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblSocialMediaApp.setBounds(130, 10, 200, 40);
        loginPanel.add(lblSocialMediaApp);


        // Create Account button
        JButton btnCreateAccount = new JButton("Create Account");
        // apply styling to button
        btnCreateAccount.setBounds(125, 80, 200, 40);
        loginPanel.add(btnCreateAccount);
        btnCreateAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = JOptionPane.showInputDialog(null, "Enter username:", "Create Account", JOptionPane.QUESTION_MESSAGE);
                String password = JOptionPane.showInputDialog(null, "Enter password:", "Create Account", JOptionPane.QUESTION_MESSAGE);
                // Make sure to add the user to the system
                // check if cancel button is pressed
                if (username != null && password != null) {
                    system.createUser(username, password);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Please enter a username and password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Login button
        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(125, 140, 200, 40);
        loginPanel.add(btnLogin);
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = JOptionPane.showInputDialog(null, "Enter username:", "Login", JOptionPane.QUESTION_MESSAGE);
                String password = JOptionPane.showInputDialog(null, "Enter password:", "Login", JOptionPane.QUESTION_MESSAGE);
                // check if cancel button is pressed
                if (username != null && password != null) {
                    if (system.login(username, password)) {
                        // create a public variable to store username
                        currentUser = system.getUser(username);
                        showCard("Post");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Please enter a username and password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cardPanel.add(loginPanel, "Login");

    }

    private void createPostCard(){
        // Create Post JPanel which also contains the Create Post button
        JPanel postPanel = new JPanel();
        postPanel.setLayout(null);

        // Create Post button
        JButton btnCreatePost = new JButton("Create Post");
        btnCreatePost.setBounds(125, 70, 200, 40);
        postPanel.add(btnCreatePost);
        btnCreatePost.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String post = JOptionPane.showInputDialog(null, "Enter post:", "Create Post", JOptionPane.QUESTION_MESSAGE);
                // Get current username from login card
                system.createPost(currentUser.getUsername(), post);
            }
        });

        // View Posts button
        JButton btnViewPosts = new JButton("View Posts");
        btnViewPosts.setBounds(125, 130, 200, 40);
        postPanel.add(btnViewPosts);
        btnViewPosts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                system.showUserPosts(currentUser);
            }
        });

        // View other user's posts
        // Take text entry for username
        JTextField txtUsername = new JTextField();
        txtUsername.setBounds(125, 190, 200, 25);
        postPanel.add(txtUsername);
        txtUsername.setColumns(10);
        JButton btnViewOtherPosts = new JButton("View Other Utilities.User's Posts");
        btnViewOtherPosts.setBounds(125, 220, 200, 40);
        postPanel.add(btnViewOtherPosts);
        btnViewOtherPosts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // If text entry is empty, show error message
                if (txtUsername.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a username", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String username = txtUsername.getText();
                system.showUserPosts(system.getUser(username));
            }
        });

        // Logout button
        JButton btnLogout = new JButton("Logout");
        btnLogout.setBounds(125, 270, 200, 40);
        postPanel.add(btnLogout);
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showCard("Login");
            }
        });

        cardPanel.add(postPanel, "Post");
    }
    private void showCard(String cardName) {
        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
        cardLayout.show(cardPanel, cardName);
    }

    // Create the main method to run the GUI
    public static void main(String[] args) {
        SocialMediaApp app = new SocialMediaApp();
        app.frame.setVisible(true);
    }
}
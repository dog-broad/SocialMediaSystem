import Utilities.SocialMediaSystem;
import Utilities.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SocialMediaApp {
    private JFrame frame;
    private JPanel cardPanel;
    private final SocialMediaSystem system;

    private User currentUser;

    public SocialMediaApp() {
        this.system = new SocialMediaSystem();
        initialize();
    }

    private void initialize() {
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

    private void createLoginCard() {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setBackground(Color.WHITE);

        // Label for the app title
        JLabel lblSocialMediaApp = new JLabel("Social Media App");
        lblSocialMediaApp.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblSocialMediaApp.setBounds(125, 50, 200, 40);
        loginPanel.add(lblSocialMediaApp);

        // Button to create a new account
        JButton btnCreateAccount = new JButton("Create Account");
        btnCreateAccount.setBounds(125, 120, 200, 40);
        btnCreateAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Prompt user to enter username and password
                String username = JOptionPane.showInputDialog(null, "Enter username:", "Create Account", JOptionPane.QUESTION_MESSAGE);
                String password = JOptionPane.showInputDialog(null, "Enter password:", "Create Account", JOptionPane.QUESTION_MESSAGE);
                if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
                    // Create a new user account
                    system.createUser(username, password);
                    // Show success message
                    JOptionPane.showMessageDialog(null, "Account created successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Show error message if username or password is not entered
                    JOptionPane.showMessageDialog(null, "Please enter a valid username and password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        loginPanel.add(btnCreateAccount);

        // Button to log in
        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(125, 180, 200, 40);
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Prompt user to enter username and password
                String username = JOptionPane.showInputDialog(null, "Enter username:", "Login", JOptionPane.QUESTION_MESSAGE);
                String password = JOptionPane.showInputDialog(null, "Enter password:", "Login", JOptionPane.QUESTION_MESSAGE);
                if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
                    if (system.login(username, password)) {
                        // Successful login, set the current user and show the post card
                        currentUser = system.getUser(username);
                        showCard("Post");
                    } else {
                        // Show error message for invalid username or password
                        JOptionPane.showMessageDialog(null, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    // Show error message if username or password is not entered
                    JOptionPane.showMessageDialog(null, "Please enter a valid username and password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        loginPanel.add(btnLogin);

        cardPanel.add(loginPanel, "Login");
    }

    private void createPostCard() {
        JPanel postPanel = new JPanel();
        postPanel.setLayout(null);
        postPanel.setBackground(Color.WHITE);

        // Button to create a new post
        JButton btnCreatePost = new JButton("Create Post");
        btnCreatePost.setBounds(125, 70, 200, 40);
        btnCreatePost.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Prompt user to enter a post
                String post = JOptionPane.showInputDialog(null, "Enter post:", "Create Post", JOptionPane.QUESTION_MESSAGE);
                if (post != null && !post.isEmpty()) {
                    // Create a new post for the current user
                    system.createPost(currentUser.getUsername(), post);
                    // Show success message
                    JOptionPane.showMessageDialog(null, "Post created successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Show error message if the post is not entered
                    JOptionPane.showMessageDialog(null, "Please enter a valid post.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        postPanel.add(btnCreatePost);

        // Button to view posts of the current user
        JButton btnViewPosts = new JButton("View Posts");
        btnViewPosts.setBounds(125, 130, 200, 40);
        btnViewPosts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Show the posts of the current user
                system.showUserPosts(currentUser);
            }
        });
        postPanel.add(btnViewPosts);

        // Panel to view other user's posts
        JTextField txtUsername = new JTextField();
        txtUsername.setBounds(125, 190, 200, 40);
        postPanel.add(txtUsername);
        JButton btnViewOtherPosts = new JButton("View Other User's Posts");
        btnViewOtherPosts.setBounds(125, 250, 200, 40);
        postPanel.add(btnViewOtherPosts);
        btnViewOtherPosts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the username entered by the user
                String username = txtUsername.getText();
                if (username.isEmpty()) {
                    // Show error message if no username is entered
                    JOptionPane.showMessageDialog(null, "Please enter a username", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Show the posts of the specified user
                    system.showUserPosts(system.getUser(username));
                }
            }
        });

        // Button to logout
        JButton btnLogout = new JButton("Logout");
        btnLogout.setBounds(125, 330, 200, 40);
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Show the login card
                showCard("Login");
            }
        });
        postPanel.add(btnLogout);

        cardPanel.add(postPanel, "Post");
    }

    private void showCard(String cardName) {
        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
        cardLayout.show(cardPanel, cardName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Set UI to system style
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                SocialMediaApp app = new SocialMediaApp();
                app.frame.setVisible(true);
            }
        });
    }
}

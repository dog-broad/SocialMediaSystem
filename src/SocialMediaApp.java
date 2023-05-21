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

        JLabel lblSocialMediaApp = new JLabel("Social Media App");
        lblSocialMediaApp.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblSocialMediaApp.setBounds(125, 50, 200, 40);
        loginPanel.add(lblSocialMediaApp);

        JButton btnCreateAccount = new JButton("Create Account");
        btnCreateAccount.setBounds(125, 120, 200, 40);
        loginPanel.add(btnCreateAccount);
        btnCreateAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = JOptionPane.showInputDialog(null, "Enter username:", "Create Account", JOptionPane.QUESTION_MESSAGE);
                String password = JOptionPane.showInputDialog(null, "Enter password:", "Create Account", JOptionPane.QUESTION_MESSAGE);
                if (username != null && password != null) {
                    system.createUser(username, password);
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a username and password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(125, 180, 200, 40);
        loginPanel.add(btnLogin);
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = JOptionPane.showInputDialog(null, "Enter username:", "Login", JOptionPane.QUESTION_MESSAGE);
                String password = JOptionPane.showInputDialog(null, "Enter password:", "Login", JOptionPane.QUESTION_MESSAGE);
                if (username != null && password != null) {
                    if (system.login(username, password)) {
                        currentUser = system.getUser(username);
                        showCard("Post");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a username and password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cardPanel.add(loginPanel, "Login");
    }

    private void createPostCard() {
        JPanel postPanel = new JPanel();
        postPanel.setLayout(null);
        postPanel.setBackground(Color.WHITE);

        JButton btnCreatePost = new JButton("Create Post");
        btnCreatePost.setBounds(125, 70, 200, 40);
        postPanel.add(btnCreatePost);
        btnCreatePost.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String post = JOptionPane.showInputDialog(null, "Enter post:", "Create Post", JOptionPane.QUESTION_MESSAGE);
                system.createPost(currentUser.getUsername(), post);
            }
        });

        JButton btnViewPosts = new JButton("View Posts");
        btnViewPosts.setBounds(125, 130, 200, 40);
        postPanel.add(btnViewPosts);
        btnViewPosts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                system.showUserPosts(currentUser);
            }
        });

        JTextField txtUsername = new JTextField();
        txtUsername.setBounds(125, 190, 200, 25);
        postPanel.add(txtUsername);
        txtUsername.setColumns(10);
        JButton btnViewOtherPosts = new JButton("View Other User's Posts");
        btnViewOtherPosts.setBounds(125, 220, 200, 40);
        postPanel.add(btnViewOtherPosts);
        btnViewOtherPosts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtUsername.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a username", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String username = txtUsername.getText();
                system.showUserPosts(system.getUser(username));
            }
        });

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Change the look and feel to the black
                try {
                    UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                SocialMediaApp app = new SocialMediaApp();
                app.frame.setVisible(true);
            }
        });
    }
}

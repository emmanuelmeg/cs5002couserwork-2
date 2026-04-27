import javax.swing.*;
import  java.awt.*;

// this class create the login window for the system
public class loginFrame extends JFrame {
    private loginservice logins;
    private  service services;

    public  loginFrame(loginservice login,service services ) {
        this.logins= login;
        this.services = services;

        setTitle("login page");
        setSize(360,220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel loginpanel = new JPanel(new GridLayout(3,2,10,10));
        JTextField usernameField = new JTextField();
        JTextField passwordField= new JTextField();
        JButton loginButton = new JButton("LOGIN");

        loginpanel.add(new JLabel("Username"));
        loginpanel.add(usernameField);
        loginpanel.add(new JLabel("password"));
        loginpanel.add(passwordField);
        loginpanel.add(new JLabel());
        loginpanel.add(loginButton);



        // action Listener for login button
        loginButton.addActionListener(e ->
        {
            // get the input values
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();

            // attempt login using LoginService
            user users = logins.login(username,password);
            if (users == null)
            {
                JOptionPane.showMessageDialog(this,"wrong username or password");
                return;
            }


            // if the login was successful close the login frame
            dispose();
            // open the main gui with logged-in user
            new UrbanPulsegui(users, services);
        });

        add(loginpanel);

        setVisible(true);

    }



}

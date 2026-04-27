import javax.swing.*;


public  class Main{

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() ->
        {
            service services = new service();
            loginservice login = new loginservice();
            new loginFrame(login,services).setVisible(true);
        });

    }
}
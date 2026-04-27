import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.time.*;
import java.util.List;


public class UrbanPulsegui implements ActionListener
{
    // main window
    private JFrame frame;
    // Service class that handles  all the service in the system
    private service bookingService;

    // member input fields
    private  JTextField first_name;
    private  JTextField Last_name;
    private  JTextField email;
    private JTextField M_ID;
    // Booking input fields
    private JTextField dateText;
    private  JTextField pitchText;
    private  JTextField siteText;
    private  JTextField slotText;
    private  JTextField bookingIDText;
    // the logged in user
    private user users;
    //staff/management fields
    private  JTextField reasonText;
    private JTextField fromField;
    private  JTextField ToField;
    private  JComboBox<String> MembershipsCombobox;


    public UrbanPulsegui(user users, service bookingService)
    {
         this.users =users;
         this.bookingService = bookingService;
        frame();


    }
    // creates the main frame
    private void frame()
    {
        frame = new JFrame("UrbanPulse Leisure Centre");

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900,500);
        frame.pack();
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("members", Memberspanle());

        // a tab only staff and management can see
        if (users.getRole() == Role.staff || users.getRole() == Role.management)
        {
            tabs.addTab("Staff", Staffpanel());
        }

        // only management can see management tab
        if (users.getRole() == Role.management) {
            tabs.addTab("management", createManagementPanel());
        }
        JPanel root = new JPanel(new BorderLayout());
        root.add(tabs,BorderLayout.CENTER);
        frame.setContentPane(tabs);



    }
    // create the members panel
    private JPanel Memberspanle()
    {
        JPanel mainPanel = new JPanel(new BorderLayout(10,10));

        JPanel formpanel = new JPanel(new GridLayout(0,4,10,10));
        JLabel First_name = new JLabel("First name");
        first_name =new JTextField(15);

        JLabel last_name = new JLabel("Last name");
        Last_name = new JTextField(15);

        JLabel Email = new JLabel("Email");
        email = new JTextField(15);
        JLabel tiers = new JLabel( "memebership tier");
        String [] membershipsTier = {"junior","Senior","Weekday","full"};
        MembershipsCombobox = new JComboBox<>(membershipsTier);


        JLabel ID = new JLabel("memebership ID");
        M_ID = new JTextField(15);

        JLabel date = new JLabel("booking date");
        dateText =new JTextField(15);
        pitchText =new JTextField(15);
        slotText = new JTextField(15);
        siteText = new JTextField(15);
        bookingIDText = new JTextField(15);





        formpanel.add(First_name);
        formpanel.add(first_name);
        formpanel.add(last_name);
        formpanel.add(Last_name);
        formpanel.add(Email);
        formpanel.add(email);
        formpanel.add(tiers);
        formpanel.add(MembershipsCombobox);
        formpanel.add(date);
        formpanel.add(dateText);
        formpanel.add(ID);
        formpanel.add(M_ID);

        formpanel.add(new JLabel("PITCH ID"));
        formpanel.add(pitchText);
        formpanel.add(new JLabel("LOCATION"));
        formpanel.add(siteText);
        formpanel.add(new JLabel("SLOT"));
        formpanel.add(slotText);
        formpanel.add(new JLabel("BOOKING ID"));
        formpanel.add(bookingIDText);


        JPanel buttonPanel = new JPanel(new GridLayout(0,3,10,10));

        JButton ApplyButton = new JButton("apply Membership");
        JButton renewButton = new JButton( "Renew membership");
        JButton updateButton = new JButton("Update personal details");
        JButton CheckButton = new JButton("Check Availability");
        JButton BookButton = new JButton("Booking");
        JButton cancelButton = new JButton("Cancel bookings");
        JButton displayButton = new JButton("display members details");


        buttonPanel.add(ApplyButton);
        buttonPanel.add(renewButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(CheckButton);
        buttonPanel.add(BookButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(displayButton);

        ApplyButton.addActionListener(this);
        renewButton.addActionListener(this);
        updateButton.addActionListener(this);
        CheckButton.addActionListener(this);
        BookButton.addActionListener(this);
        cancelButton.addActionListener(this);
        displayButton.addActionListener(this);


        mainPanel.add(formpanel,BorderLayout.CENTER);
        mainPanel.add(buttonPanel,BorderLayout.SOUTH);
        
        return mainPanel;
    }
    // applies for a new memberships
    public void addmember()
    {

            String selected = (String) MembershipsCombobox.getSelectedItem();
            members membership = bookingService.applyMemberships(
                    first_name.getText().trim(),
                    Last_name.getText().trim(),
                    email.getText().trim(),
                    selected,
                    users.getUsername()


            );
            JOptionPane.showMessageDialog(frame,
                    " Membership CREATED. ID" +
                            membership.getID(),
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);



    }
    // updates member details
    public void update()
    {
             members updated= bookingService.UpdateDetails(
                     M_ID.getText().trim(),
                    first_name.getText().trim(),
                    Last_name.getText().trim(),
                    email.getText().trim(),
                    users.getUsername()

            );

             if (updated != null) {
                 JOptionPane.showMessageDialog(frame, "details has been update");
             }else{
                 JOptionPane.showMessageDialog(frame,"MEMBER NOT FOUND ");
             }






    }

    // BOOKS A pitch
    public void Booking()
    {

            LocalDateTime date = LocalDateTime.parse(dateText.getText().trim());
            booking booking =bookingService.bookPitch(
                    M_ID.getText().trim(),
                    pitchText.getText().trim(),
                    date,
                    slotText.getText().trim(),
                    users.getUsername()

            );
            if (booking != null)
            {
                JOptionPane.showMessageDialog(frame,
                        "Booking created. ID: " + booking.getB_ID(),
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);


            }
            else {
                JOptionPane.showMessageDialog(frame,"Booking could not be created"
                  );


            }



        
    } // Cancels a booking
    public void cancel()
    {

            String CancelMessage= bookingService.Cancelbooking(
                    bookingIDText.getText().trim(),
                    users.getUsername());
            
            JOptionPane.showMessageDialog(frame,CancelMessage);



    }
    // Checks pitch availability
    public void checkAvailability()
    {

        {
            LocalDateTime dateTime = LocalDateTime.parse(dateText.getText().trim());
            List<pitch> available = bookingService.checkavalitity(
                    siteText.getText().trim(),
                    dateTime,
                    slotText.getText().trim()
            );
            StringBuilder sb = new StringBuilder("Available pitches:\n");
            for (pitch Pitches : available) {
                sb.append(Pitches).append("\n");

            }
            JOptionPane.showMessageDialog(frame, sb.toString(), "availability", JOptionPane.INFORMATION_MESSAGE);


        }
    }
    // issues a refund
    public void refund()
    {
            String msg = bookingService.issuearefund(
                    bookingIDText.getText().trim(),
                    reasonText.getText().trim(),
                    users.getUsername()
            );
            JOptionPane.showMessageDialog(frame, msg);




    }
    // schedules pitch maintenance
    public void maintenance ()
    {
         bookingService.scheduleMaintenance(
                pitchText.getText().trim(),
                LocalDateTime.parse(fromField.getText().trim()),
                LocalDateTime.parse(ToField.getText().trim()),
                users.getUsername()
        );
         JOptionPane.showMessageDialog(frame, "Maintenance scheduled.");


    }
    // displays member details
    public  void display()
    {
        String membersID = M_ID.getText().trim();

        members member = bookingService.searchmemebrs(membersID);
        if (member == null) {
            JOptionPane.showMessageDialog(frame, "member not found");
            return;

        }
        StringBuilder details = new StringBuilder();
        details.append("members details");
        details.append("ID: ").append(member.getID()).append("\n");
        details.append("First Name: ").append(member.getFirst_name()).append("\n");
        details.append("Last Name: ").append(member.getLast_name()).append("\n");
        details.append("Email: ").append(member.getEmail()).append("\n");
        details.append("Tier: ").append(member.getMembership_tier()).append("\n");


        JTextArea textArea = new JTextArea(details.toString());
        textArea.setEditable(false);

        JFrame memebrframe = new JFrame("members detail");
        memebrframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        memebrframe.add(new JScrollPane(textArea));
        memebrframe.pack();
        memebrframe.setLocationRelativeTo(frame);
        memebrframe.setVisible(true);

    }
    // Display the operations report
    public  void generateReport()
    {
        bookingService.generteReport();

        String report=
                "Operations Report\n\n"+
                        "Bookings: \n"+
                        bookingService.getSummaryforbooking()+
                        "\nAudit Trail: \n"+
                        bookingService.getAudit();

        JOptionPane.showMessageDialog(frame,report,"Operations Report",JOptionPane.INFORMATION_MESSAGE);
    }
    public  void actionPerformed(ActionEvent event)
    {
        String command = event.getActionCommand();

        if(command.equalsIgnoreCase("apply Membership"))
        {
            addmember();
        }
        if(command.equalsIgnoreCase("Update personal details"))
        {
            update();
        }
        if(command.equalsIgnoreCase("refund"))
        {
            refund();
        }
        if(command.equalsIgnoreCase("book pitch"))
        {
            Booking();
        }
        if(command.equalsIgnoreCase("cancel booking"))
        {
            cancel();
        }
        if (command.equalsIgnoreCase("Check Availability"))
        {
            checkAvailability();
        }
        if (command.equalsIgnoreCase("Schedule Maintenance"))
        {
            maintenance();
        }
        if (command.equalsIgnoreCase("display members details"))
        {
            display();
        }
        if (command.equalsIgnoreCase("Operations Reports"))
        {
            generateReport();
        }

    }
    // creates the management panel
    private  JPanel Staffpanel()
    {JPanel spanel = new JPanel(new GridLayout(0,2,8,8));

         reasonText = new JTextField();

        JButton refundButton = new JButton("Refund");
        JButton auditButton = new JButton("view audit");

        spanel.add(new JLabel("Booking ID"));
        spanel.add(bookingIDText);

        spanel.add(new JLabel("reasons"));
        spanel.add(reasonText);
        spanel.add(refundButton);
        spanel.add(auditButton);


        return spanel;
    }
    // Creates the management panel
    private  JPanel createManagementPanel()
    {
        JPanel mpanel = new JPanel(new GridLayout(0,2,8,8));

         fromField =new JTextField();
         ToField = new JTextField();

        JButton MaintenanceButton = new JButton("schedule maintenance");
        JButton reportButton = new JButton("Operations Reports ");

        MaintenanceButton.addActionListener(this);
        reportButton.addActionListener(this);

        mpanel.add(new JLabel("PItch ID:"));
        mpanel.add(pitchText);
        mpanel.add(new JLabel("From (yyyy-MM-dd):"));
        mpanel.add(fromField);
        mpanel.add(new JLabel("To (yyyy-MM-dd):"));
        mpanel.add(ToField);
        mpanel.add(MaintenanceButton);
        mpanel.add(reportButton);
      return mpanel;
    }






}

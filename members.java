import java.time.*;

enum membersStatues
{
    Active, // memberships is active
    grace_time,  // member in the grace period
    expired // memberships has expired
}
public class members
{
    private  String ID;
    private String  first_name;
    private String last_name;
    private String Membership_tier;
    private LocalDateTime startDate;
    private LocalDateTime renewalDue;
    private membersStatues status;
    private String email;

    public members(String ID,String first_name,String last_name,
                   String membership_tier,LocalDateTime startDate,
                   LocalDateTime renewalDue,membersStatues status,String email)
    {
        this.ID = ID;
        this.first_name = first_name;
        this.last_name = last_name;
        this.Membership_tier = membership_tier;
        this.startDate = startDate;
        this.renewalDue = renewalDue;
        this.status = status;
        this.email= email;

    }
//--- setter methods----
    public void setID(String ID) {
        this.ID = ID;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setMembership_tier(String membership_tier) {
        Membership_tier = membership_tier;
    }

    public void setRenewalDue(LocalDateTime renewalDue) {
        this.renewalDue = renewalDue;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }


//---- getter---
    public LocalDateTime getRenewalDue() {
        return renewalDue;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getID() {
        return ID;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getMembership_tier() {
        return Membership_tier;
    }


    public String getEmail()
    {
        return email;
    }

    //-- toString method to display members details
    public  String toString()
    {
        return ID + " | " + first_name+" | " + last_name+"|"+ Membership_tier +"|";
    }
}

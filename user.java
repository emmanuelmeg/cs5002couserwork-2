
//Enum to define different types of user roles in the system
enum Role{
    customer,staff,management
}

public class user {
    // INstance variables to store user details
    private  String username;
    private  String password;
    private  Role role;

    public user(String username,String password ,Role role){
        this.username = username;
        this.password = password;
        this. role = role;

    }
    //---getter method---
    public String getUsername()
    {
        return username;
    }
    public String getPassword()
    {
        return  password;
    }
    public Role getRole()
    {
        return role;

    }

}
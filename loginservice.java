import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class loginservice {
    // map to store users(username )
    private Map<String, user> users = new HashMap<>();




    // constructor initializes some default users
    public  loginservice()
    {

        // adding sample user with different roles
        users.put("John23",new user("John23","12345",Role.customer));
        users.put("staff", new user("staff","pas1234",Role.staff));
        users.put("manager", new user("manager", "man2332",Role.management));

    }
    // a method ro authenticate user login
    public  user login(String username, String password)
    {
        user founduser = users.get(username);
        if(founduser != null && founduser.getPassword().equals(password))
        {
            return founduser;
        }
        return  null;
    }

}

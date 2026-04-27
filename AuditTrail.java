import java.time.*;
// this is the auditTrail is used to log system actions
public class AuditTrail {
    private LocalDateTime TimeStamp;
    private  String actor;
    private  String actions;

    public AuditTrail(String actor, String actions )
    {
        this.actions =actions; // to stores the action performed
        this.TimeStamp = LocalDateTime.now();// stores the date and time the actions occur
        this.actor = actor; // the user or system performing the actions
    }
    // method to return audit detail in readable format
    public  String display()
    {
        return TimeStamp+ "  |"+actions+"  |"+ actor;
    }
}

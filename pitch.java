import java.time.*;

public class pitch {
    private  String P_ID;
    private  String  location;
    private  String facilityType;
    private LocalDateTime maintenanceFrom;
    private  LocalDateTime maintenanceTo;


    public pitch(String p_ID,String location,String facilityType)
    {
        this.P_ID= p_ID;
        this.location =location;
        this.facilityType = facilityType;

    }
//----getter methods----
    public String getP_ID() {
        return P_ID;
    }

    public String getFacilityType() {
        return facilityType;
    }

    public String getLocation() {
        return location;
    }
// method to set maintenance period
    public void setMaintenance(LocalDateTime from, LocalDateTime TO) {
        this.maintenanceFrom = from;
        this.maintenanceTo = TO;
    }
    // method to check if a pitch is under maintenance
    public boolean isunderMaintenance(LocalDateTime date)
    {
        if(maintenanceFrom == null || maintenanceTo == null)
        {
            return false;
        }
        return !(date.isBefore(maintenanceFrom) || date.isAfter(maintenanceTo));
    }


    // to display pitch details
    public  String toString()
    {
        return P_ID + " | " + location + " | " + facilityType;
    }

}

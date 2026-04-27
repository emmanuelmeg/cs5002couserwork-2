import java.time.*;

public class booking
{
    //instance variables to store booking details
    private String b_ID;
    private String member_ID;
    private String pitch_ID;
    private  String locations;
    private LocalDateTime date;
    private  String slot;
    private boolean cancelled;

    public booking(String b_ID,String member_ID, String pitch_ID,
                   String locations,LocalDateTime date, String slot)
    {
        this.b_ID = b_ID;
        this.member_ID = member_ID;
        this.date =date;
        this.slot = slot;
        this.pitch_ID = pitch_ID;
        this.locations = locations;

    }
    //---getter method----

    public String getB_ID() {
        return b_ID;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getLocations() {
        return locations;
    }

    public String getSlot() {
        return slot;
    }

    public String getMember_ID() {
        return member_ID;
    }
// --- a method to check if booking is cancelled
    public boolean isCancelled() {
        return cancelled;
    }
//---setter method to update cancellation status
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
// to display booking details
    public String toString()
    {
        return b_ID + "| member " + member_ID + "| pitch" + pitch_ID +"| "+ date +"| "+ slot;
    }
}

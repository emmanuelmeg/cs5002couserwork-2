
import java.time.*;
import java.util.*;


public class service {
    // list used to store system data
    private List<members> MEMEBERS = new ArrayList<>();
    private List<pitch> pitches = new ArrayList<>();
    private List<booking>Bookings = new ArrayList<>();
    private List<AuditTrail> auditTRAIL = new ArrayList<>();


    public service()
    {// constructor adds default pitches and records system start
        pitches.add(new pitch("P1","north"," basketball"));
        pitches.add(new pitch("P2","SOUTH","TENNIS"));
        audit("System", "SYSTEM has started");

    }
    // adds a new actions to audit trail
    public void audit(String actor,String actions)
    {
        auditTRAIL.add(new AuditTrail(actor, actions));
    }

    // the method create a new member and adds them to the members list
    public members applyMemberships(String first_name, String last_name,
                                    String email,String tier,String actor)
    {

        // generate random members ID
        String ID = "M" +UUID.randomUUID().toString().substring(0,4).toUpperCase();

        // Create new member object
        members member = new members(ID,
                first_name,
                last_name,
                tier,
                LocalDateTime.now(),
                LocalDateTime.now().plusYears(1),
                membersStatues.Active,
                email


                );

        // add member to list
        MEMEBERS.add(member);
        // record actions
        audit(actor,"memberships applied:" + member.getID());
        System.out.println("created member ID: "+ member.getID());
        System.out.println("Members count:" +MEMEBERS.size());

        return member;

    }

    // renews an existing members memberships
    public  members renewMembership (String memberID,String actor)

    {
        members members = searchmemebrs(memberID);
        if(members ==null){
            System.out.println(" Memebers not found");
        }
        members.setRenewalDue(LocalDateTime.now().plusYears(1));
        audit(actor,"Memberships renewed:" + memberID);
        return members;

    }

    // to searches for a member by ID
    public members searchmemebrs(String ID)
    {
        for (members members :MEMEBERS){
            if(members.getID().equalsIgnoreCase(ID)){
                return  members;
            }
        }
        return  null;

    }

// checks which pitches are available at a location, date, time slot
    public List<pitch> checkavalitity(String locations,LocalDateTime dateTime, String slot)
    {
        List<pitch> available = new ArrayList<>();
        for(pitch pitches : pitches)
        {
            if(!pitches.getLocation().equalsIgnoreCase(locations))
            {
                continue;
            }
            if(pitches.isunderMaintenance(dateTime))
            {
                continue;
            }
            if (!isBooked(pitches.getP_ID(),dateTime,slot))
            {
                available.add(pitches);
            }

        }
        return available;

    }


    //  a method used to update member details
    public members UpdateDetails(String memberID, String first_name, String Last_name,String email,String actor)
    {
        System.out.println("update ID received: ("+memberID+")");
        members member = searchmemebrs(memberID);
        if (member == null)
        {
            System.out.println("member not in the database");
            return null;
        }
        if (first_name != null && !first_name.isBlank()){
            member.setFirst_name(first_name);
        }
        if (Last_name != null && !Last_name.isBlank()){
            member.setLast_name(Last_name);
        }
        if (email != null && !email.isBlank())
        {
            member.setEmail(email);
        }
        audit(actor, "Member details updated: "+ memberID);

        System.out.println("member details are update"+ member.getEmail()+member.getFirst_name()+member.getLast_name());
        return member;
    }
// a method to searches for a pitch by its ID
    private pitch searchpitch(String ID)
    {
        for (pitch pitchs :pitches)
        {
            if (pitchs.getP_ID().equalsIgnoreCase(ID)){
                return pitchs;
            }


        }
        return null;
    }



    // a methods used to create a booking of a pitch by memeber
    public booking bookPitch(String memberID, String pitchID,
                             LocalDateTime date , String slot,String actor)
    {
        members members = searchmemebrs(memberID);
        if(members == null){
            System.out.println("member not found");
        }

        pitch pitch = searchpitch(pitchID);
        if (pitch == null){
            System.out.println("pitch not found");
        }

        // create a new booking object
        booking Booking = new booking(
                "B"+ UUID.randomUUID().toString().substring(0,6).toUpperCase(),
                memberID,
                pitchID,pitch.getLocation(),
                date,
                slot);
        Bookings.add(Booking);
        audit(actor,"Booking created: "+Booking.getB_ID());
        return Booking;
    }
    // finds a booking by the booking ID
    public booking findbooking(String ID)
    {
        for (booking Booking : Bookings)
        {
            if(Booking.getB_ID().equalsIgnoreCase(ID)){
                return Booking;
            }
        }
        return null;

    }

    // Cancels an existing booking
    public String Cancelbooking (String BookingID, String actor)
    {
        booking Booking = findbooking(BookingID);
        if(Booking == null)
        {
            System.out.println("booking not found");
        }
        Booking.setCancelled(true);
        audit(actor,"Booking cancelled: "+ Booking.getB_ID());
        return "booking" + BookingID + "cancelled. ";
    }


    // records a refunds of booking
    public String issuearefund(String BookingID, String Reason,String actor)
    {
        booking booking = findbooking( BookingID);
        if (booking == null){
            System.out.println("booking not found");
        }
        System.out.println( "the refund recorded for" + BookingID);
        audit(actor,"refund issued for bookingid");
        return "a special can of refund for this" + BookingID;
    }

    // schedules maintenance for a pitch
    public void scheduleMaintenance(String pitchID, LocalDateTime from, LocalDateTime to, String actor)
    {
        pitch Pitch = searchpitch(pitchID);
        if (Pitch == null)
        {
            System.out.println("pitch not found");
        }
        Pitch.setMaintenance(from, to);
        audit(actor,"this pitchID "+pitchID + "is scheduled for maintenance "
                +"from "+from +to+"to" );

    }
    // GENERATES A Simple report
    public void generteReport()
    {
        System.out.println("total members: "+MEMEBERS.size());
        System.out.println("total pitches: " + pitches.size());
        System.out.println("total bookings: "+ Bookings.size());

    }
    // returns all audit records
    public String getAudit()
    {
        StringBuilder sb = new StringBuilder();
        for (AuditTrail event: auditTRAIL)
        {
            sb.append(event).append("\n");

        }
        return sb.toString();
    }
    // returns all booking records
    public String getSummaryforbooking()
    {
        StringBuilder sb = new StringBuilder();
        for ( booking bookes:Bookings)
        {
            sb.append(bookes).append("\n");
        }
        return sb.toString();
    }

    // checks is a pitch is already booked for the same date and slot
    private boolean isBooked(String pitchID,LocalDateTime date, String slot)
    {
        for(booking bookes:Bookings)
        {
            if(!bookes.isCancelled()
                    && bookes.getB_ID().equalsIgnoreCase(pitchID)
                    && bookes.getDate().equals(date)
                    &&bookes.getSlot().equalsIgnoreCase(slot))
            {
                return  true;
            }
        }
        return false;
    }

}
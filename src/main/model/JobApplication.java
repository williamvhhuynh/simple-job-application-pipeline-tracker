package model;

import java.time.LocalDate;
import java.util.List;

// Represents a single job application with identiying information, current application status,
// application date, and user notes.  
public class JobApplication {
    
    
    private String company;
    private String jobTitle;
    private String location;
    
    private int id;

    private Status status;
    private List<Status> statusHistory;
    private List<LocalDate> statusDates;

    private LocalDate dateApplied;

    private List<String> notes;
    
    // REQUIRES: id > 0 & company, jobTitle, and location != null
    // EFFECTS: creates a JobApplication object with a given id, company, jobTitle and location,
    //          initializes status to NOT_APPLIED
    //          initializes statusHistory with one entry containing:
    //              - status = NOT_APPLIED
    //              - date = date of object creation
    //          initializes notes to an empty list 
    public JobApplication(int id, String company, String jobTitle, String location) {
        // stub
    }

    // REQUIRES: newStatus != null
    // MODIFIES: this
    // EFFECTS: updates the status of this application to newStatus
    //          records the newStatus and current  date in statusHistory and statusDates
    //          if newStatus is APPLIED and dateApplied is null, dateApplied to set to
    //          the current date, otherwise, dateApplied is unchanged. 
    public void updateStatus(Status newStatus) {
        // stub
    }

    // EFFECTS: returns true if the application was ever in the given state,
    //          otherwise, return false
    public boolean wasEverInStatus(Status queryStatus) {
        return false;
    }

    // NOTES methods

    // REQUIRES: note != null
    // MODIFIES: this
    // EFFECTS: adds a note to the end of the notes list
    public void addNote(String note) {
        // stub
    }

    // REQUIRES: 0 <= index < notes.size()
    // MODIFIES: this
    // EFFECTS: removes the note from the notes list at a given index
    public void removeNote(int index) {
        // stub
    }

    // EFFECTS: returns true if this application has at least one note, returns false otherwise
    public boolean hasNotes() {
        return false;
    }

    // EFFECTS: returns the number of notes stored in this application
    public int getNoteCount() {
        return -1;
    }
    
    // MODIFIES: this
    // EFFECTS: removes all notes from this application
    public void clearNotes() {
        // stub
    }

    public boolean hasNoteContaining(String keyword) {
        return false; // stub
    }

    
    // Getter and Setter methods
    public int getId() {
        return -1;
    }

    public String getCompany() {
        return "";
    }

    public String getJobTitle() {
        return "";
    }

    public String getLocation() {
        return "";
    }

    public Status getStatus() {
        return null;
    }

    public List<Status> getStatusHistory() {
        return null;
    }

    public List<LocalDate> getStatusDates() {
        return null;
    }


    public LocalDate getDateApplied() {
        return null;
    }

    public List<String> getNotes() {
        return null;
    }
}

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

    private LocalDate dateApplied;
    private LocalDate lastUpdatedDate;

    private List<String> notes;
    
    // REQUIRES: company, jobTitle, and location != null
    // EFFECTS: creates a JobApplication object with a given company, jobTitle and location,
    //          assigns a unique positive integer id 
    //          initializes status to NOT_APPLIED
    //          initializes dateApplied to null
    //          initializes notes to an empty list 
    public JobApplication(String company, String jobTitle, String location) {
        // stub
    }

    // REQUIRES: newStatus != null
    // MODIFIES: this
    // EFFECTS: updates the status of this application to newStatus
    //          if newStatus is APPLIED and dateApplied is null, dateApplied to set to
    //          the current date, otherwise, dateApplied is unchanged. 
    public void updateStatus(Status newStatus) {
        // stub
    }

    // EFFECTS: returns date when application was last modified
    public LocalDate getLastUpdatedDate() {
        return null;
    }

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

    // EFFECTS: returns true if this application has ever been applied to,
    //          otherwise, returns false
    public boolean wasEverApplied() {
        return false;
    } 

    // MODIFIES: this
    // EFFECTS: removes all notes from this application
    public void clearNotes() {
        // stub
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

    public LocalDate getDateApplied() {
        return null;
    }

    public List<String> getNotes() {
        return null;
    }
}

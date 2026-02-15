package model;

import java.time.LocalDate;
import java.util.ArrayList;
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
        this.id = id;
        this.company = company;
        this.jobTitle = jobTitle;
        this.location = location;

        status = Status.NOT_APPLIED;
        statusHistory = new ArrayList<>();
        statusDates = new ArrayList<>();
        
        LocalDate today = LocalDate.now();
        statusHistory.add(Status.NOT_APPLIED);
        statusDates.add(today);

        dateApplied = null;

        notes = new ArrayList<>();
    }

    // REQUIRES: newStatus != null
    // MODIFIES: this
    // EFFECTS: updates the status of this application to newStatus
    //          records the newStatus and current  date in statusHistory and statusDates
    //          if newStatus is APPLIED and dateApplied is null, dateApplied to set to
    //          the current date, otherwise, dateApplied is unchanged. 
    public void updateStatus(Status newStatus) {
        status = newStatus;

        LocalDate today = LocalDate.now();
        statusHistory.add(newStatus);
        statusDates.add(today);

        if (newStatus == Status.APPLIED && dateApplied == null) {
            dateApplied = today;
        }
    }

    // EFFECTS: returns true if the application was ever in the given state,
    //          otherwise, return false
    public boolean wasEverInStatus(Status queryStatus) {
        return statusHistory.contains(queryStatus);
    }

    // NOTES methods

    // REQUIRES: note != null
    // MODIFIES: this
    // EFFECTS: adds a note to the end of the notes list
    public void addNote(String note) {
        notes.add(note);
    }

    // REQUIRES: 0 <= index < notes.size()
    // MODIFIES: this
    // EFFECTS: removes the note from the notes list at a given index
    public void removeNote(int index) {
        notes.remove(index);
    }

    // EFFECTS: returns true if this application has at least one note, returns false otherwise
    public boolean hasNotes() {
        return !notes.isEmpty();
    }

    // EFFECTS: returns the number of notes stored in this application
    public int getNoteCount() {
        return notes.size();
    }
    
    // MODIFIES: this
    // EFFECTS: removes all notes from this application
    public void clearNotes() {
        notes.clear();
    }

    public boolean hasNoteContaining(String keyword) {
        for (String note : notes) {
            if (note.contains(keyword)) {
                return true;
            }
        }

        return false;
    }

    
    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public String getCompany() {
        return company;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getLocation() {
        return location;
    }

    public Status getStatus() {
        return status;
    }

    public List<Status> getStatusHistory() {
        return statusHistory;
    }

    public List<LocalDate> getStatusDates() {
        return statusDates;
    }


    public LocalDate getDateApplied() {
        return dateApplied;
    }

    public List<String> getNotes() {
        return notes;
    }
}

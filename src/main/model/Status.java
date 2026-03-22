package model;

// Represents the possible states of a job application
public enum Status {
    NOT_APPLIED("NOT_APPLIED"),        
    APPLIED("APPLIED"),            
    ONLINE_ASSESSMENT("ONLINE_ASSESSMENT"),  
    INTERVIEWING("INTERVIEWING"),       
    OFFERED("OFFERED"),            
    REJECTED("REJECTED"),
    WITHDREW("WITHDREW");
    
    private String name;

    // EFFECTS: constructs the enum status with a name
    Status(String status) {
        this.name = status;
    }

    // EFFECTS: returns name of status enum
    public String getNameValue() {
        return name;
    }
}


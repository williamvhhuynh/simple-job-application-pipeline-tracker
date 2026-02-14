package model;

import java.util.List;

// Represents a collection of job applications 
public class ApplicationPipeline {

    private List<JobApplication> applications;
    private int nextId;

    // EFFECTS: creates an empty application pipeline, initializing nextId to 1
    public ApplicationPipeline() {
        // stub
    }

    // REQUIRES: company, jobTitle, location != null
    // MODIFIES: this
    // EFFECTS: creates a new JobApplication with a unique Id, adds it to the pipeline,
    //          incrementes nextId, and returns the created application
    public JobApplication addApplication(String company, String jobTitle, String location) {
        return null;
    }


    // REQUIRES: application with id exists in the pipeline
    // MODIFIES: this
    // EFFECTS: removes the JobApplication with the given id from the pipeline
    public void removeApplication(int id) {
        // stub
    }

    // REQUIRES: application with id exists in the pipeline
    // EFFECTS: returns the JobApplication with the given id
    public JobApplication getApplicationById(int id) {
        return null;
    }

    // EFFECTS: returns a copy of all applications in this pipeline
    public List<JobApplication> getAllApplications() {
        return null;
    }

    // EFFECTS: returns number of applications in the pipeline
    public int getApplicationCount() {
        return -1;
    }

    // EFFECTS: returns true if the application with a given id exists in the pipeline,
    //          otherwise returns false
    public boolean containsApplication(int id) {
        return false;
    }

    // EFFECTS: returns the next id that will be assigned to the new application
    public int getNextId() {
        return -1;
    }


}

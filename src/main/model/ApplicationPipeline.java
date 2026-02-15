package model;

import java.util.ArrayList;
import java.util.List;

// Represents a collection of job applications 
public class ApplicationPipeline {

    private List<JobApplication> applications;
    private int nextId;

    // EFFECTS: creates an empty application pipeline, initializing nextId to 1
    public ApplicationPipeline() {
        applications = new ArrayList<>();
        nextId = 1; 
    }

    // REQUIRES: company, jobTitle, location != null
    // MODIFIES: this
    // EFFECTS: creates a new JobApplication with a unique Id, adds it to the pipeline,
    //          incrementes nextId, and returns the created application
    public JobApplication addApplication(String company, String jobTitle, String location) {
        JobApplication newApplication = new JobApplication(nextId, company, jobTitle, location);
        applications.add(newApplication);
        nextId++;

        return newApplication;
    }


    // REQUIRES: application with id exists in the pipeline
    // MODIFIES: this
    // EFFECTS: removes the JobApplication with the given id from the pipeline
    public void removeApplication(int id) {
        applications.remove(getApplicationById(id));
    }

    // REQUIRES: application with id exists in the pipeline
    // EFFECTS: returns the JobApplication with the given id
    public JobApplication getApplicationById(int id) {
        for (JobApplication app : applications) {
            if (app.getId() == id) {
                return app;
            }
        }
        return null;
    }

    // EFFECTS: returns a copy of all applications in this pipeline
    public List<JobApplication> getAllApplications() {
        return new ArrayList<>(applications);
    }

    // EFFECTS: returns number of applications in the pipeline
    public int getApplicationCount() {
        return applications.size();
    }

    // EFFECTS: returns true if the application with a given id exists in the pipeline,
    //          otherwise returns false
    public boolean containsApplication(int id) {
        return getApplicationById(id) != null;
    }

    // EFFECTS: returns the next id that will be assigned to the new application
    public int getNextId() {
        return nextId;
    }


}

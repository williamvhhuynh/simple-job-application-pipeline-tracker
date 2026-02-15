package ui;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import model.ApplicationPipeline;
import model.JobApplication;
import model.Status;

// Job Application Pipeline application
public class ApplicationPipelineApp {

    private ApplicationPipeline pipeline;
    private Scanner input;

    // EFFECTS: runs the job application application
    public ApplicationPipelineApp() {
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input 
    private void runApp() {
        boolean keepGoing = true;
        String command;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\n Goodbye!");
    }

    // MODIFIES: this
    // EFFECTS: initializes pipeline and scanner
    private void init() {
        pipeline = new ApplicationPipeline();
        input = new Scanner(System.in);
        input.useDelimiter("\r?\n|\r");
    }

    // EFFECTS: displays menu
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add application");
        System.out.println("\tr -> remove application");
        System.out.println("\tv -> view all applications");
        System.out.println("\tf -> find application by id");
        System.out.println("\tu -> update application status");
        System.out.println("\tn -> add note");
        System.out.println("\td -> delete note");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: process commands
    private void processCommand(String command) {
        switch (command) {
            case "a": addApplication(); 
            break;
            case "r": removeApplication(); 
            break;
            case "v": viewAllApplications(); 
            break;
            case "f": findApplication(); 
            break;
            case "u": updateStatus(); 
            break;
            case "n": addNote(); 
            break;
            case "d": removeNote(); 
            break;
            default:
                System.out.println("Invalid selection.");
        }
    }

    // MODIFIES: this
    // EFFECTS: creates and adds an application to the pipeline
    private void addApplication() {
        System.out.print("Company: ");
        String company = input.next();

        System.out.print("Job Title: ");
        String title = input.next();

        System.out.print("Location: ");
        String location = input.next();

        JobApplication app = pipeline.addApplication(company, title, location);

        System.out.println("Added application with ID: " + app.getId());
    }

    // MODIFIES: this
    // EFFECTS: removes a given application based on ID from the pipeline
    private void removeApplication() {
        int id = promptForId();

        if (pipeline.containsApplication(id)) {
            pipeline.removeApplication(id);
            System.out.println("Removed.");
        } else {
            System.out.println("Application not found.");
        }
    }

    // EFFECTS: prints all applications in the pipeline with all details
    private void viewAllApplications() {
        List<JobApplication> apps = pipeline.getAllApplications();

        if (apps.isEmpty()) {
            System.out.println("No applications.");
            return;
        }

        for (JobApplication app : apps) {
            printApplication(app);
        }
    }

    // EFFECTS: finds the application and prints all details including notes 
    //          If no application with given ID exists, says "No application found" and returns nothing
    private void findApplication() {
        int id = promptForId();

        if (!pipeline.containsApplication(id)) {
            System.out.println("No application found with ID " + id);
            return;
        } else {
            printApplication(pipeline.getApplicationById(id));
            
            if (pipeline.getApplicationById(id).hasNotes()) {
                System.out.println("Notes:");
                List<String> notes = pipeline.getApplicationById(id).getNotes();
                for (int i = 0; i < notes.size(); i++) {
                    System.out.println((i + 1) + ". " + notes.get(i));
                }
            } else {
                System.out.println("No notes for this application.");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: changes the status of a job application on ID
    private void updateStatus() {
        int id = promptForId();

        JobApplication app = pipeline.getApplicationById(id);
        if (app == null) {
            System.out.println("Not found.");
            return;
        }

        System.out.println("Available statuses:");
        for (Status s : Status.values()) {
            System.out.println("- " + s);
        }

        System.out.print("Enter new status: ");
        String statusString = input.next().toUpperCase();

        try {
            Status newStatus = Status.valueOf(statusString);
            app.updateStatus(newStatus);
            System.out.println("Updated.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid status.");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds notes to a given job application based on ID
    private void addNote() {
        int id = promptForId();

        JobApplication app = pipeline.getApplicationById(id);
        if (app == null) {
            System.out.println("Not found.");
            return;
        }

        System.out.print("Enter note: ");
        String note = input.next();

        app.addNote(note);
        System.out.println("Note added.");
    }

    // MODIFIES: this
    // EFFECTS: removes notes from a given job application based on ID and note number
    private void removeNote() {
        int id = promptForId();

        JobApplication app = pipeline.getApplicationById(id);
        if (app == null) {
            System.out.println("Not found.");
            return;
        }

        if (!app.hasNotes()) {
            System.out.println("No notes.");
            return;
        }

        if (app.hasNotes()) {
            System.out.println("Notes:");
            List<String> notes = app.getNotes();
            for (int i = 0; i < notes.size(); i++) {
                System.out.println((i + 1) + ". " + notes.get(i));
            }
        } else {
            System.out.println("No notes for this application.");
        }

        System.out.print("Enter note number: ");
        int index = input.nextInt() - 1;

        app.removeNote(index);
        System.out.println("Note removed.");
    }

    // EFFECTS: prints all details for a given Job Application
    private void printApplication(JobApplication app) {
        System.out.println("----------------");
        System.out.println("ID: " + app.getId());
        System.out.println("Company: " + app.getCompany());
        System.out.println("Title: " + app.getJobTitle());
        System.out.println("Location: " + app.getLocation());
        System.out.println("Status: " + app.getStatus());
        System.out.println("Date Applied: " + app.getDateApplied());
        System.out.println("Notes: " + app.getNoteCount());
    }

    // EFFECTS: prompt user for a positive integer ID, reprompts if invalid, and returns it 
    private int promptForId() {
        int id = -1;

        while (true) {
            try {
                System.out.print("Enter application ID: ");
                id = input.nextInt();
                input.nextLine();

                if (id <= 0) {
                    System.out.println("ID must be a positive integer. Please try again.");
                } else {
                    return id;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a postive integer.");
                input.nextLine();
            }
        }
    }
}


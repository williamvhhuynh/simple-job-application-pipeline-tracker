package ui;

import model.ApplicationPipeline;
import model.JobApplication;
import model.Status;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

// Job Application Graphical User Interface
public class PipelineGUI extends JFrame {
    private static final String JSON_STORE = "./data/applicationpipeline.json";
    private ApplicationPipeline pipeline;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: Creates an instance of the pipline GUI application
    public PipelineGUI() {
        super("Job Application Tracker");
        
        pipeline = new ApplicationPipeline();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        
        initializeLayout();

        setupDisplayArea();
        setupButtons();
        addFileMenu();

        refreshDisplay();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: configures the main window settings
    private void initializeLayout() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: configures the display area
    private void setupDisplayArea() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: initializes the primary action buttons
    private void setupButtons() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: updates displayArea and visualComponent with current pipeline data
    private void refreshDisplay() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: adds the file menu for Save/Loaf functionality
    private void addFileMenu() {
        // stub
    }

    // EFFECTS: Saves pipeline to file.
    private void savePipeline() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: Loads pipeline from file
    private void loadPipeline() {
        // stub
    }

    // EFFECTS: Launches the GUI
    public static void main(String[] args) {
        new PipelineGUI();
    }
}
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

    private JTextArea displayArea;
    private PipelineVisual visualComponent;
    private JLabel statusLabel;

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
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        
        statusLabel = new JLabel("Welcome to your Job Application Tracker");
        add(statusLabel, BorderLayout.NORTH);
    }

    // MODIFIES: this
    // EFFECTS: configures the display area
    private void setupDisplayArea() {
        displayArea = new JTextArea(20, 60);
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: initializes the sidebar visual component
    private void setupVisualComponent() {
        visualComponent = new PipelineVisual(pipeline);
        add(visualComponent, BorderLayout.EAST);
    } 

    // MODIFIES: this
    // EFFECTS: initializes the primary action buttons
    private void setupButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 10, 10));

        JButton addButton = new JButton("Add Application");
        addButton.addActionListener(e -> handleAddApplication());
        buttonPanel.add(addButton);

        JButton updateButton = new JButton("Update Status");
        updateButton.addActionListener(e -> handleUpdateStatus());
        buttonPanel.add(updateButton);

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> System.exit(0));
        buttonPanel.add(quitButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: updates displayArea and visualComponent with current pipeline data
    private void refreshDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append("All Job Applications\n");
        sb.append("------------------------------------------\n\n");

        if (pipeline.getAllApplications().isEmpty()) {
            sb.append("No applications added yet.");
        } else {
            for (JobApplication ja : pipeline.getAllApplications()) {
                sb.append(String.format("%-2d | %-15s | %-20s | %-10s | %-15s%n",
                        ja.getId(), ja.getCompany(), ja.getJobTitle(), 
                        ja.getLocation(), ja.getStatus()));
            }
        }

        displayArea.setText(sb.toString());
        if (visualComponent != null) {
            visualComponent.repaint();
        }

        revalidate();
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: Prompts user with inputs to add a new JobApplication to the pipeline
    private void handleAddApplication() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: Prompts user for an ID and provides a dropdown to update the status
    private void handleUpdateStatus() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: adds the file menu for Save/Loaf functionality
    private void addFileMenu() {
        // stub
    }

    // EFFECTS: Saves pipeline to file.
    private void savePipeline() {
        try {
            jsonWriter.open();
            jsonWriter.write(pipeline);
            jsonWriter.close();
            statusLabel.setText("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            statusLabel.setText("Unable to write to file " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: Loads pipeline from file
    private void loadPipeline() {
        try {
            pipeline = jsonReader.read();
            visualComponent.setPipeline(pipeline);
            refreshDisplay();
            statusLabel.setText("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            statusLabel.setText("Error: Unable to read from file" + JSON_STORE);
        }
    }

    // EFFECTS: Launches the GUI
    public static void main(String[] args) {
        new PipelineGUI();
    }
}
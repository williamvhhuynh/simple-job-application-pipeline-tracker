package persistence;

import java.io.IOException;

import org.json.JSONObject;

import model.ApplicationPipeline;

// Sourced from CPSC 210 Team
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a reader that reads ApplicationPipeline from JSON data stored in file
public class JsonReader {

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        // stub
    }

    // EFFECTS: reads applicationpipeline from file and returns it;
    //          throws IOException if an error occurs reading data from file
    public ApplicationPipeline read() throws IOException {
        return null; // stub
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        return null; // stub
    }

    // EFFECTS: parses ApplicationPipeline from JSON object and returns it
    private ApplicationPipeline parseApplicationPipeline(JSONObject jsonObject) {
        return null; // stub
    }

    // MODIFIES: ap
    // EFFECTS: parses JobApplications from JSON object and adds them to ApplicationPipeline
    private void addJobApplications(ApplicationPipeline ap, JSONObject jsonObject) {
        // stub
    }

    // MODIFIES: ap
    // EFFECTS: parses JobApplication from JSON object and adds it to ApplicationPipeline
    private void addJobApplication(ApplicationPipeline ap, JSONObject jsonObject) {
        // stub
    }
}

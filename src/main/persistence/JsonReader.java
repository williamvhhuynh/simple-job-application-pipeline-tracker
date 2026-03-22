package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

import model.ApplicationPipeline;
import model.JobApplication;
import model.Status;

// Sourced from CPSC 210 Team
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a reader that reads ApplicationPipeline from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads applicationpipeline from file and returns it;
    //          throws IOException if an error occurs reading data from file
    public ApplicationPipeline read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseApplicationPipeline(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses ApplicationPipeline from JSON object and returns it
    private ApplicationPipeline parseApplicationPipeline(JSONObject jsonObject) {
        ApplicationPipeline ap = new ApplicationPipeline();
        addJobApplications(ap, jsonObject);
        setNextId(ap, jsonObject);

        return ap;
    }

    // MODIFIES: ap
    // EFFECTS: parses JobApplications from JSON object and adds them to ApplicationPipeline
    private void addJobApplications(ApplicationPipeline ap, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("jobApplications");
        for (Object json : jsonArray) {
            JSONObject nextJobApplication = (JSONObject) json;
            addJobApplication(ap, nextJobApplication);
        }
    }

    // MODIFIES: ap
    // EFFECTS: parses JobApplication from JSON object and adds it to ApplicationPipeline
    private void addJobApplication(ApplicationPipeline ap, JSONObject jsonObject) {
        String company = jsonObject.getString("company");
        String jobTitle = jsonObject.getString("jobTitle");
        String location = jsonObject.getString("location");
        int id = jsonObject.getInt("id");
        String statusStr = jsonObject.getString("status");
        Status status = Status.valueOf(statusStr);

        List<Status> statusHistory = parseStatusHistory(jsonObject.getJSONArray("statusHistory"));
        List<LocalDate> statusDates = parseStatusDates(jsonObject.getJSONArray("statusDates"));
        List<String> notes = parseNotes(jsonObject.getJSONArray("notes"));

        // dateApplied
        LocalDate dateApplied = null;
        if (!jsonObject.isNull("dateApplied")) {
            dateApplied = LocalDate.parse(jsonObject.getString("dateApplied"));
        }

        JobApplication application = new JobApplication(id, company, jobTitle, location, status,
                                               statusHistory, statusDates, dateApplied, notes);
        ap.fullJobApplicationToPipeline(application);
    }

    // EFFECTS: sets next id for application pipeline from jsonObject
    private void setNextId(ApplicationPipeline ap, JSONObject jsonObject) {
        if (jsonObject.has("nextId")) {
            int nextId = jsonObject.getInt("nextId");
            ap.setNextId(nextId);
        }
    }

    // EFFECTS: helper method for addJobApplication to parse through StatusHistory
    private List<Status> parseStatusHistory(JSONArray jsonArray) {
        List<Status> history = new ArrayList<>();
        for (Object obj : jsonArray) {
            history.add(Status.valueOf((String) obj));
        }
        return history;
    }

    // EFFECTS: helper method for addJobApplication to parse through statusDates
    private List<LocalDate> parseStatusDates(JSONArray jsonArray) {
        List<LocalDate> dates = new ArrayList<>();
        for (Object obj : jsonArray) {
            dates.add(LocalDate.parse((String) obj));
        }
        return dates;
    }

    // EFFECTS: helper method for addJobApplication to parse through notes
    private List<String> parseNotes(JSONArray jsonArray) {
        List<String> notes = new ArrayList<>();
        for (Object obj : jsonArray) {
            notes.add((String) obj);
        }
        return notes;
    }
}

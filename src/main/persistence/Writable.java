package persistence;

// Sourced from CPSC 210 Team
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONOBject to Json();
}

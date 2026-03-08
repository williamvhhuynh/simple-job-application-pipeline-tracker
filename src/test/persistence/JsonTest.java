package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import model.Status;
import java.time.LocalDate;
import java.util.List;

import model.JobApplication;

// Sourced from CPSC 210 Team
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

@ExcludeFromJacocoGeneratedReport
public class JsonTest {
    protected void checkApplication(String company, String jobTitle, String location, 
                                int id, Status status, List<Status> statusHistory, 
                                List<LocalDate> statusDates, LocalDate dateApplied,
                                List<String> notes, JobApplication jobApplication) {
        assertEquals(company, jobApplication.getCompany());
        assertEquals(jobTitle, jobApplication.getJobTitle());
        assertEquals(location, jobApplication.getLocation());
        assertEquals(id, jobApplication.getId());
        assertEquals(status, jobApplication.getStatus());
        assertEquals(statusHistory, jobApplication.getStatusHistory());
        assertEquals(statusDates, jobApplication.getStatusDates());
        assertEquals(dateApplied, jobApplication.getDateApplied());
        assertEquals(notes, jobApplication.getNotes());
    }
}

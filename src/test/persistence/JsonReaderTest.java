package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.ApplicationPipeline;
import model.JobApplication;
import model.Status;

// Sourced from CPSC 210 Team
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

@ExcludeFromJacocoGeneratedReport
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ApplicationPipeline ap = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyApplicationPipeline() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyApplicationPipeline.json");
        try {
            ApplicationPipeline ap = reader.read();
            assertEquals(0, ap.getAllApplications().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralApplicationPipeline() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralApplicationPipeline.json");
        try {
            ApplicationPipeline ap = reader.read();
            List<JobApplication> applications = ap.getAllApplications();
            assertEquals(2, applications.size());
            List<Status> statusHistory1 = new ArrayList<Status>();
            statusHistory1.add(Status.NOT_APPLIED);
            statusHistory1.add(Status.APPLIED);
            List<LocalDate> statusDates1 = new ArrayList<LocalDate>();
            statusDates1.add(LocalDate.of(2026, 03, 01));
            statusDates1.add(LocalDate.of(2026, 03, 06));
            List<String> notes1 = new ArrayList<String>();
            notes1.add("Submitted through company portal.");
            notes1.add("Connected with hiring manager");
            checkApplication("Connor, Clark & Lunn Investment Management Ltd.",
                            "Product Specialist Analyst, Fundamental Equity",
                            "Vancouver, BC",
                            1,
                            Status.APPLIED,
                            statusHistory1,
                            statusDates1,
                            LocalDate.of(2026, 03, 06),
                            notes1,
                            applications.get(0));
            List<Status> statusHistory2 = new ArrayList<Status>();
            statusHistory2.add(Status.NOT_APPLIED);
            List<LocalDate> statusDates2 = new ArrayList<LocalDate>();
            statusDates2.add(LocalDate.of(2026, 03, 07));
            List<String> notes2 = new ArrayList<String>();
            notes2.add("Recommended by previous manager");            
            checkApplication("Alberta Investment Management Company",
                            "Student, Security Operations & Pricing",
                            "Edmonton, AB",
                            2,
                            Status.NOT_APPLIED,
                            statusHistory2,
                            statusDates2,
                            LocalDate.of(2026, 03, 07),
                            notes2,
                            applications.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

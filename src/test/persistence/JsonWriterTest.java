package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

import model.ApplicationPipeline;
import model.JobApplication;
import model.Status;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Sourced from CPSC 210 Team
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyPortfolio() {
        try {
            ApplicationPipeline ap = new ApplicationPipeline();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyApplicationPipeline.json");
            writer.open();
            writer.write(ap);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyApplicationPipeline.json");
            ap = reader.read();
            assertEquals(0, ap.getApplicationCount());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralPortfolio() {
        try {
            ApplicationPipeline ap = new ApplicationPipeline();
            ap.addApplication("Connor, Clark & Lunn Investment Management Ltd.",
                            "Product Specialist Analyst, Fundamental Equity",
                            "Vancouver, BC");
            ap.addApplication("Alberta Investment Management Company",
                            "Student, Security Operations & Pricing",
                            "Edmonton, AB");
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralApplicationPipeline.json");
            writer.open();
            writer.write(ap);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralApplicationPipeline.json");
            ap = reader.read();
            List<JobApplication> jobApplications = ap.getAllApplications();
            assertEquals(2, jobApplications.size());
            List<Status> statusHistory = new ArrayList<Status>();
            statusHistory.add(Status.NOT_APPLIED);
            List<LocalDate> statusDates = new ArrayList<LocalDate>();
            statusDates.add(LocalDate.now());
            checkApplication("Connor, Clark & Lunn Investment Management Ltd.",
                            "Product Specialist Analyst, Fundamental Equity",
                            "Vancouver, BC",
                            1,
                            Status.NOT_APPLIED,
                            statusHistory,
                            statusDates,
                            null,
                            null,
                            jobApplications.get(0));
            checkApplication("Alberta Investment Management Company",
                            "Student, Security Operations & Pricing",
                            "Edmonton, AB",
                            2,
                            Status.NOT_APPLIED,
                            statusHistory,
                            statusDates,
                            null,
                            null,
                            jobApplications.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class TestJobApplication {

    private JobApplication testApplication1;
    
    @BeforeEach
    void runBefore() {
        testApplication1 = new JobApplication(1, "CCLIM", 
                                            "Product Specialist Analyst",   
                                            "Vancouver");
    }

    @Test
    void testConstructor() {
        assertEquals(1, testApplication1.getId());
        assertEquals("CCLIM", testApplication1.getCompany());
        assertEquals("Product Specialist Analyst", testApplication1.getJobTitle());
        assertEquals("Vancouver", testApplication1.getLocation());

        assertEquals(Status.NOT_APPLIED, testApplication1.getStatus());
        assertEquals(Status.NOT_APPLIED, testApplication1.getStatusHistory().get(0));
        assertEquals(LocalDate.now(), testApplication1.getStatusDates().get(0));

        assertNotNull(testApplication1.getNotes());
        assertEquals(0, testApplication1.getNoteCount());
        assertFalse(testApplication1.hasNotes());

    }

    @Test
    void testUpdateStatus() {
        testApplication1.updateStatus(Status.APPLIED);

        assertEquals(Status.APPLIED, testApplication1.getStatus());
        assertEquals(Status.APPLIED, testApplication1.getStatusHistory().get(1));
        assertEquals(LocalDate.now(), testApplication1.getStatusDates().get(1));
        assertEquals(LocalDate.now(), testApplication1.getDateApplied());

        LocalDate originalDateApplied = testApplication1.getDateApplied();
        testApplication1.updateStatus(Status.ONLINE_ASSESSMENT);

        assertEquals(Status.ONLINE_ASSESSMENT, testApplication1.getStatus());
        assertEquals(Status.ONLINE_ASSESSMENT, testApplication1.getStatusHistory().get(2));

        testApplication1.updateStatus(Status.APPLIED);

        assertEquals(Status.APPLIED, testApplication1.getStatus());
        assertEquals(originalDateApplied, testApplication1.getDateApplied());

    }

    @Test
    void testWasEverInStatus() {
        assertTrue(testApplication1.wasEverInStatus(Status.NOT_APPLIED));
        assertFalse(testApplication1.wasEverInStatus(Status.APPLIED));

        testApplication1.updateStatus(Status.APPLIED);

        assertTrue(testApplication1.wasEverInStatus(Status.APPLIED));
    }

    @Test
    void testAddNote() {
        testApplication1.addNote("Applied through referral");

        assertTrue(testApplication1.hasNotes());
        assertEquals(1, testApplication1.getNoteCount());
        assertEquals("Applied through referral", testApplication1.getNotes().get(0));
    }

    @Test
    void testRemoveNote() {
        testApplication1.addNote("Note 1");
        testApplication1.addNote("Note 2");

        testApplication1.removeNote(0);

        assertEquals(1, testApplication1.getNoteCount());
        assertEquals("Note 2", testApplication1.getNotes().get(0));
    }

    @Test
    void testHasNote() {
        assertFalse(testApplication1.hasNotes());

        testApplication1.addNote("Test note");

        assertTrue(testApplication1.hasNotes());
    }

    @Test
    void testGetNoteCount() {
        assertEquals(0, testApplication1.getNoteCount());

        testApplication1.addNote("A");
        testApplication1.addNote("B");

        assertEquals(2, testApplication1.getNoteCount());
    }

    @Test
    void testClearNotes() {
        testApplication1.addNote("A");
        testApplication1.addNote("B");

        testApplication1.clearNotes();

        assertEquals(0, testApplication1.getNoteCount());
        assertFalse(testApplication1.hasNotes());
    }

    @Test
    void testHasNoteContaining() {
        testApplication1.addNote("Applied via LinkedIn");

        assertTrue(testApplication1.hasNoteContaining("LinkedIn"));
        assertFalse(testApplication1.hasNoteContaining("Referral"));
    }

    @Test
    void testToJsonWithDateApplied() {
        JSONObject jsonNull = testApplication1.toJson();
        assertEquals(JSONObject.NULL, jsonNull.get("dateApplied"));

        testApplication1.updateStatus(Status.APPLIED);
        JSONObject jsonWithDate = testApplication1.toJson();
        
        assertEquals(LocalDate.now().toString(), jsonWithDate.getString("dateApplied"));
    }

    @Test
    void testToJsonWithNotes() {
        testApplication1.addNote("First note");
        testApplication1.addNote("Second note");

        JSONObject json = testApplication1.toJson();
        JSONArray notesArray = json.getJSONArray("notes");

        assertEquals(2, notesArray.length());
        assertEquals("First note", notesArray.getString(0));
        assertEquals("Second note", notesArray.getString(1));
}
}

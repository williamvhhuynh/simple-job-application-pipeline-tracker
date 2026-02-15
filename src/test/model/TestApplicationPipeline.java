package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class TestApplicationPipeline {

    private ApplicationPipeline pipeline;

    @BeforeEach
    void runBefore() {
        pipeline = new ApplicationPipeline();
    }
    
    @Test
    void testConstructor() {
        assertEquals(0, pipeline.getApplicationCount());
        assertEquals(1, pipeline.getNextId());
        assertTrue(pipeline.getAllApplications().isEmpty());
    }
    
    @Test
    void testAddApplication() {
        pipeline.addApplication("CCLIM", 
                                "Product Specialist Analyst", 
                                "Vancouver");

        assertEquals(1, pipeline.getApplicationCount());
        assertEquals(1, pipeline.getAllApplications().get(0).getId());
        assertEquals(2, pipeline.getNextId());

        pipeline.addApplication("AIMCo", 
                                "Student, Securities Operations & Pricing (Summer 2026)", 
                                "Edmonton");
        
        assertEquals(2, pipeline.getApplicationCount());
        assertEquals(2, pipeline.getAllApplications().get(1).getId());
        assertEquals(3, pipeline.getNextId());
    }

    @Test
    void testRemoveApplication() {
        pipeline.addApplication("CCLIM", 
                                "Product Specialist Analyst", 
                                "Vancouver"); 

        assertTrue(pipeline.containsApplication(1));

        pipeline.removeApplication(1);
        
        assertFalse(pipeline.containsApplication(1));
        assertEquals(0, pipeline.getApplicationCount());
    }

    @Test
    void testGetApplicationById() {
        pipeline.addApplication("CCLIM", 
                                "Product Specialist Analyst", 
                                "Vancouver");
        pipeline.addApplication("AIMCo", 
                                "Student, Securities Operations & Pricing (Summer 2026)", 
                                "Edmonton");

        JobApplication testApplication = pipeline.getApplicationById(2);
        assertEquals("AIMCo", testApplication.getCompany());
    }

    @Test
    void testGetAllApplications() {
        JobApplication testApplication = pipeline.addApplication("CCLIM", 
                                "Product Specialist Analyst", 
                                "Vancouver");

        List<JobApplication> applications = pipeline.getAllApplications();

        assertEquals(1, applications.size());
        assertEquals(testApplication.getId(), applications.get(0).getId());

        applications.clear();

        assertEquals(1, pipeline.getApplicationCount());
    }

    @Test
    void testGetApplicationCount() {
        pipeline.addApplication("AIMCo", 
                                "Student, Securities Operations & Pricing (Summer 2026)", 
                                "Edmonton");

        assertEquals(1, pipeline.getApplicationCount());

        pipeline.removeApplication(1);

        assertEquals(0, pipeline.getApplicationCount());
    }

    @Test
    void testContainsApplication() {
        pipeline.addApplication("CCLIM", 
                                "Product Specialist Analyst", 
                                "Vancouver");

        assertTrue(pipeline.containsApplication(1));
        assertFalse(pipeline.containsApplication(2));

        pipeline.addApplication("AIMCo", 
                                "Student, Securities Operations & Pricing (Summer 2026)", 
                                "Edmonton");

        assertTrue(pipeline.containsApplication(2));
        assertFalse(pipeline.containsApplication(999));
    }

    @Test
    void testGetNextId() {
        assertEquals(1, pipeline.getNextId());

        pipeline.addApplication("AIMCo", 
                                "Student, Securities Operations & Pricing (Summer 2026)", 
                                "Edmonton");

        assertEquals(2, pipeline.getNextId());

        pipeline.addApplication("CCLIM", 
                                "Product Specialist Analyst", 
                                "Vancouver");
        assertEquals(3, pipeline.getNextId());
    }
}

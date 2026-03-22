package ui;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import model.ApplicationPipeline;
import model.JobApplication;
import model.Status;

// Graph Visual Component
public class PipelineVisual extends JPanel {

    private ApplicationPipeline pipeline;

    private static final int LABEL_WIDTH = 100;
    private static final int BAR_HEIGHT = 20;
    private static final int ROW_SPACING = 35;
    private static final int LEFT_MARGIN = 10;
    private static final int TOP_MARGIN = 50;

    public PipelineVisual(ApplicationPipeline pipeline) {
        this.pipeline = pipeline;

        setPreferredSize(new Dimension(250, 600));
        setBackground(new Color(245, 245, 245));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawChart(g);
    }

    private void drawChart(Graphics g) {
        Map<Status, Integer> counts = getStatusCounts();
        
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.setColor(Color.BLACK);
        g.drawString("Pipeline Overview", LEFT_MARGIN, 30);

        int y = TOP_MARGIN;

        for (Status s : Status.values()) {
            int count = counts.getOrDefault(s, 0);
            
            g.setFont(new Font("Arial", Font.PLAIN, 11));
            g.setColor(Color.BLACK);
            g.drawString(s.toString(), LEFT_MARGIN, y + 15);

            int barWidth = count * 15;
            int barStart = LEFT_MARGIN + LABEL_WIDTH;

            // draw bar
            if (count > 0) {
                g.setColor(getStatusColor(s));
                g.fillRect(barStart, y, barWidth, BAR_HEIGHT);
                
                g.setColor(new Color(0, 0, 0, 50));
                g.drawRect(barStart, y, barWidth, BAR_HEIGHT);
            }

            // number count
            g.setColor(Color.DARK_GRAY);
            g.drawString("(" + count + ")", barStart + barWidth + 5, y + 15);

            y += ROW_SPACING;
        } 
    }

    // Helper to count applications by status
    private Map<Status, Integer> getStatusCounts() {
        Map<Status, Integer> counts = new HashMap<>();
        for (JobApplication ja : pipeline.getAllApplications()) {
            counts.put(ja.getStatus(), counts.getOrDefault(ja.getStatus(), 0) + 1);
        }
        return counts;
    }

    // Helper to give each status a distinct color
    private Color getStatusColor(Status s) {
        switch (s) {
            case APPLIED: return Color.BLUE;
            case ONLINE_ASSESSMENT: return Color.ORANGE;
            case INTERVIEWING: return Color.PINK;
            case OFFERED: return Color.GREEN;
            case REJECTED: return Color.RED;
            default: return Color.LIGHT_GRAY;
        }
    }

    // MODIFIES: this
    // EFFECTS: updates the pipeline reference to a new one and redraws
    public void setPipeline(ApplicationPipeline pipeline) {
        this.pipeline = pipeline;
        repaint();
    }

}

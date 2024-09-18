package org.walsh;

import java.util.HashMap;
import java.util.Map;

public class OnlineStudent extends Student {
    private final int _forumPosts;
    private final int _videoLecturesCompleted;
    private final Map<IGradable, Boolean> _assignmentTimeliness; // Track if each assignment was submitted on time

    public OnlineStudent(String name, String id) {
        super(name, id);
        _forumPosts = 0;
        _videoLecturesCompleted = 0;
        _assignmentTimeliness = new HashMap<>();
    }

    // Methods for tracking forum participation
    public void incrementForumPosts() {
        // TODO
    }

    public int getForumPosts() {
        // TODO
    }

    // Methods for tracking video lecture completion
    public void completeVideoLecture() {
        // TODO
    }

    public int getVideoLecturesCompleted() {
        // TODO
    }

    // Methods for tracking assignment submission timeliness
    public void submitAssignment(IGradable gradable, int points, boolean onTime) {
        // TODO
    }

    public boolean wasAssignmentSubmittedOnTime(IGradable gradable) {
        // TODO
    }


    // DO NOT MODIFY THIS METHOD:
    @Override
    public void simulateAPIPost() {
        super.simulateAPIPost();
        System.out.println("Forum Posts: " + _forumPosts);
        System.out.println("Video Lectures Completed: " + _videoLecturesCompleted);
        for (Map.Entry<IGradable, Boolean> entry : _assignmentTimeliness.entrySet()) {
            System.out.println("Assignment: " + entry.getKey().getName() + " submitted on time: " + entry.getValue());
        }
    }
}
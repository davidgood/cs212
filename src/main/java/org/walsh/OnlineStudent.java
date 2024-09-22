package org.walsh;

import java.util.HashMap;
import java.util.Map;

public class OnlineStudent extends Student {
    private int _forumPosts;
    private int _videoLecturesCompleted;
    private Map<IGradable, Boolean> _assignmentTimeliness; // Track if each assignment was submitted on time

    public OnlineStudent(String name, String id) {
        super(name, id);
        _forumPosts = 0;
        _videoLecturesCompleted = 0;
        _assignmentTimeliness = new HashMap<>();
    }

    // Methods for tracking forum participation
    public void incrementForumPosts() {
        _forumPosts++;
    }

    public int get_forumPosts() {
        return _forumPosts;
    }

    // Methods for tracking video lecture completion
    public void completeVideoLecture() {
        _videoLecturesCompleted++;
    }

    public int get_videoLecturesCompleted() {
        return _videoLecturesCompleted;
    }

    // Methods for tracking assignment submission timeliness
    public void submitAssignment(IGradable gradable, int points, boolean onTime) {
        addGrade(gradable, points);
        _assignmentTimeliness.put(gradable, onTime);
    }

    public boolean wasAssignmentSubmittedOnTime(IGradable gradable) {
        return _assignmentTimeliness.getOrDefault(gradable, false);
    }

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
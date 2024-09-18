package org.walsh;

import java.util.HashMap;
import java.util.Map;

public class OnlineStudent extends Student {
    private int _forumPosts;
    private int _videoLecturesCompleted;
    private Map<IGradeable, Boolean> _assignmentTimeliness; // Track if each assignment was submitted on time

    public OnlineStudent(String name, String id) {
        super(name, id);
        _forumPosts = 0;
        _videoLecturesCompleted = 0;
        _assignmentTimeliness = new HashMap<>();
    }

    // Methods for tracking forum participation
    public void incrementForumPosts() {
        this._forumPosts++;
    }

    public int get_forumPosts() {
        return _forumPosts;
    }

    // Methods for tracking video lecture completion
    public void completeVideoLecture() {
        this._videoLecturesCompleted++;
    }

    public int get_videoLecturesCompleted() {
        return _videoLecturesCompleted;
    }

    // Methods for tracking assignment submission timeliness
    public void submitAssignment(IGradeable gradeable, int points, boolean onTime) {
        addGrade(gradeable, points);
        _assignmentTimeliness.put(gradeable, onTime);
    }

    public boolean wasAssignmentSubmittedOnTime(IGradeable gradable) {
        return _assignmentTimeliness.getOrDefault(gradable, false);
    }

    @Override
    public void simulateAPIPost() {
        super.simulateAPIPost();
        System.out.println("Forum Posts: " + _forumPosts);
        System.out.println("Video Lectures Completed: " + _videoLecturesCompleted);
        for (Map.Entry<IGradeable, Boolean> entry : _assignmentTimeliness.entrySet()) {
            System.out.println("Assignment: " + entry.getKey().getName() + " submitted on time: " + entry.getValue());
        }
    }
}
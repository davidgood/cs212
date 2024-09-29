package org.walsh;

public class OnlineStudent extends Student {
    private int _forumPosts;
    private int _videoLecturesCompleted;

    public OnlineStudent(String name, String id) {
        super(name, id, StudentType.ONLINE);  // Use enum to specify student type
        _forumPosts = 0;
        _videoLecturesCompleted = 0;
    }

    public void incrementForumPosts() {
        _forumPosts++;
    }

    public void completeVideoLecture() {
        _videoLecturesCompleted++;
    }

    @Override
    public void simulateAPIPost() {
        super.simulateAPIPost();
        System.out.println("Forum Posts: " + _forumPosts);
        System.out.println("Video Lectures Completed: " + _videoLecturesCompleted);
    }
}
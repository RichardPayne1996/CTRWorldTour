package com.sparta.rp.ctrworldtour.dtos;

public class CourseDTO {
    private int courseId;
    private String courseName;
    private String coursePhotoURL;

    public CourseDTO() {}

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCoursePhotoURL() {
        return coursePhotoURL;
    }

    public void setCoursePhotoURL(String coursePhotoURL) {
        this.coursePhotoURL = coursePhotoURL;
    }
}

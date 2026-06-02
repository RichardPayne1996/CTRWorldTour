package com.sparta.rp.ctrworldtour.entities;

import jakarta.persistence.*;

@Entity
@Table(name="course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="courseId")
    private int courseId;

    @Column(name="courseName")
    private String courseName;

    @Column(name="coursePhotoURL")
    private String coursePhotoURL;

    public Course() {}

    public Course(String courseName, String coursePhotoURL) {
        this.courseName = courseName;
        this.coursePhotoURL = coursePhotoURL;
    }

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

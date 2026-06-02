package com.sparta.rp.ctrworldtour.repositories;

import com.sparta.rp.ctrworldtour.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}

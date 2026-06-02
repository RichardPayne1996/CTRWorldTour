package com.sparta.rp.ctrworldtour.services;

import com.sparta.rp.ctrworldtour.dtos.CourseDTO;
import com.sparta.rp.ctrworldtour.dtos.CourseMapper;
import com.sparta.rp.ctrworldtour.entities.Course;
import com.sparta.rp.ctrworldtour.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseMapper courseMapper;
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseMapper courseMapper, CourseRepository courseRepository) {
        if (courseMapper == null || courseRepository == null) {
            throw new IllegalArgumentException("courseMapper or courseRepository null");
        }
        this.courseMapper = courseMapper;
        this.courseRepository = courseRepository;
    }

    public List<CourseDTO> getCourses(){
        return courseRepository.findAll().stream()
                .map(courseMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CourseDTO getCourseById(int id){
        Course course = courseRepository.findById(id).orElse(null);
        return courseMapper.toDTO(course);
    }

    public CourseDTO updateCourseById(CourseDTO courseDTO, int id){
        Course course = courseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("course not found"));
        course.setCourseName(courseDTO.getCourseName());
        course.setCoursePhotoURL(courseDTO.getCoursePhotoURL());
        courseRepository.save(course);
        return courseMapper.toDTO(course);
    }

    public CourseDTO addCourse(CourseDTO courseDTO){
        Course course = courseMapper.toEntity(courseDTO);
        courseRepository.save(course);
        return courseMapper.toDTO(course);
    }

    public boolean deleteCourseById(int id){
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public CourseDTO saveCourse(CourseDTO courseDTO){
        Course course = courseMapper.toEntity(courseDTO);
        courseRepository.save(course);
        return courseMapper.toDTO(course);
    }
}

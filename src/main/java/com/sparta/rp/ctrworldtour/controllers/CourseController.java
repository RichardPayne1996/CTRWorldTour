package com.sparta.rp.ctrworldtour.controllers;

import com.sparta.rp.ctrworldtour.dtos.CourseDTO;
import com.sparta.rp.ctrworldtour.dtos.CourseMapper;
import com.sparta.rp.ctrworldtour.entities.Course;
import com.sparta.rp.ctrworldtour.repositories.CourseRepository;
import com.sparta.rp.ctrworldtour.services.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final CourseService courseService;

    public CourseController(CourseRepository courseRepository, CourseMapper courseMapper, CourseService courseService) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
        this.courseService = courseService;
    }

    @GetMapping("/")
    public ResponseEntity<List<CourseDTO>> getCourses(){
        var courses = courseService.getCourses();
        return ResponseEntity.ok().body(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable int id){
        var courseDTO = courseService.getCourseById(id);
        return ResponseEntity.ok().body(courseDTO);
    }

    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO){
        Course course = courseMapper.toEntity(courseDTO);
        Course saved = courseRepository.save(course);
        return ResponseEntity.ok().body(courseMapper.toDTO(saved));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable int id, @RequestBody CourseDTO courseDTO){
        CourseDTO oldCourse = courseService.getCourseById(id);
        if(oldCourse == null){
            return ResponseEntity.notFound().build();
        }
        if(courseDTO.getCourseName() == null || courseDTO.getCourseName().equals("string")) {
            courseDTO.setCourseName(oldCourse.getCourseName());
        }
        if (courseDTO.getCoursePhotoURL() == null || courseDTO.getCoursePhotoURL().equals("string")) {
            courseDTO.setCoursePhotoURL(oldCourse.getCoursePhotoURL());
        }
        CourseDTO updatedCourse = courseService.updateCourseById(courseDTO, id);
        return ResponseEntity.ok().body(updatedCourse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CourseDTO> deleteCourseById(@PathVariable int id){
        boolean deleted = courseService.deleteCourseById(id);
        if(deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}

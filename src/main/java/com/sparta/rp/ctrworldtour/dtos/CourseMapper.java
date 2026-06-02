package com.sparta.rp.ctrworldtour.dtos;

import com.sparta.rp.ctrworldtour.entities.Course;
import org.mapstruct.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    Course toEntity(CourseDTO dto);
    CourseDTO toDTO(Course entity);
}

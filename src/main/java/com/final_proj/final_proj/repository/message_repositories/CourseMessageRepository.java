package com.final_proj.final_proj.repository.message_repositories;

import com.final_proj.final_proj.entity.Course;
import com.final_proj.final_proj.entity.message_types.CourseMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseMessageRepository extends JpaRepository<CourseMessage, Long> {

    List<CourseMessage> getAllByCourse(Course course);
}

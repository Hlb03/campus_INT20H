package com.final_proj.final_proj.repository.message_repositories;

import com.final_proj.final_proj.entity.message_types.FacultyMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyMessageRepository extends JpaRepository<FacultyMessage, Long> {

    List<FacultyMessage> getAllByFaculty_Id(Long facultyId);
}

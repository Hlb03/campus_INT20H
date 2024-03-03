package com.final_proj.final_proj.repository.message_repositories;

import com.final_proj.final_proj.entity.message_types.GroupMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupMessageRepository extends JpaRepository<GroupMessage, Long> {

    List<GroupMessage> getAllByGroup_Id(Long groupId);
}

package com.final_proj.final_proj.repository.message_repositories;

import com.final_proj.final_proj.entity.message_types.IndividualMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndividualMessageRepository extends JpaRepository<IndividualMessage, Long> {

    List<IndividualMessage> getAllByReceiverId(Long receiverId);
}

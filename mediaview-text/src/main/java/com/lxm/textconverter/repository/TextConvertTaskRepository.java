package com.lxm.textconverter.repository;

import com.lxm.textconverter.domain.TextConvertTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data SQL repository for the TaskDTO entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TextConvertTaskRepository extends JpaRepository<TextConvertTask, Long> {
}

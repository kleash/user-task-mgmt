package com.test.deloitte.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.test.deloitte.entities.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

	List<Task> findByName(String name);

	List<Task> findByUser(String user);

}

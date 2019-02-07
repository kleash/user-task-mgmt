package com.test.deloitte;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.deloitte.entities.Task;
import com.test.deloitte.repositories.TaskRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TaskTest {
	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private TaskRepository taskRepository;

	@Test
	public void testFindByName() {
		entityManager.persist(new Task("task1", "hello", "user1"));
		List<Task> task = taskRepository.findByName("task1");
		assertEquals("task1", task.get(0).getName());
	}

	@Test
	public void testFindByUser() {
		entityManager.persist(new Task("task1", "hello", "user1"));
		List<Task> task = taskRepository.findByUser("user1");
		assertEquals("task1", task.get(0).getName());
	}
}

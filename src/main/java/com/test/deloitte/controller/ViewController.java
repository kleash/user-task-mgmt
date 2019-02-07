package com.test.deloitte.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.deloitte.entities.Task;
import com.test.deloitte.repositories.TaskRepository;

@Controller
public class ViewController {

	@Autowired
	private TaskRepository taskRepository;

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping({ "/index", "/" })
	public String index(Model model) {

		model.addAttribute("tasks", this.getUserTasks(getUser()));
		return "index";
	}

	@GetMapping("/addtask")
	public String showAddTaskForm(Task task) {
		return "add-task";
	}

	@PostMapping("/addtask")
	public String addTask(@Valid Task task, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-task";
		}

		// Set the user to current logged on user
		task.setUser(getUser());
		taskRepository.save(task);
		model.addAttribute("tasks", this.getUserTasks(getUser()));
		return "index";
	}

	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Task task = taskRepository.findById(id).filter(e -> e.getUser().equals(getUser()))
				.orElseThrow(() -> new IllegalArgumentException("Invalid task Id:" + id));
		model.addAttribute("task", task);
		return "update-task";
	}

	@PostMapping("/update/{id}")
	public String updateTask(@PathVariable("id") long id, @Valid Task task, BindingResult result, Model model) {
		if (result.hasErrors()) {
			task.setId(id);
			return "update-task";
		}

		task.setUser(getUser());
		taskRepository.save(task);
		model.addAttribute("tasks", this.getUserTasks(getUser()));
		return "index";
	}

	@GetMapping("/delete/{id}")
	public String deleteTask(@PathVariable("id") long id, Model model) {
		Task task = taskRepository.findById(id).filter(e -> e.getUser().equals(getUser()))
				.orElseThrow(() -> new IllegalArgumentException("Invalid task Id:" + id));
		taskRepository.delete(task);
		model.addAttribute("tasks", this.getUserTasks(getUser()));
		return "index";
	}

	private String getUser() {
		//Get current logged on user's username
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

	private Iterable<Task> getUserTasks(String user) {
		try {
			//Get all tasks for a user
			Iterable<Task> tasks = taskRepository.findByUser(user);
			return tasks;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

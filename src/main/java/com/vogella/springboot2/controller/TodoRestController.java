package com.vogella.springboot2.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vogella.springboot2.domain.Todo;
import com.vogella.springboot2.service.TodoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
class TodoRestController {

	private TodoService todoService;

	public TodoRestController(TodoService todoService) {
		this.todoService = todoService;
	}

	@GetMapping("/getTodos")
	public Flux<Todo> getTodos(@RequestParam(name = "limit", required = false, defaultValue = "-1") long limit) {
		return todoService.getTodos(limit);
	}

	@GetMapping("/getTodoById")
	public Mono<Todo> getTodoById(long id) {
		return todoService.getTodoById(id);
	}

	@GetMapping("/getBySummary")
	public Flux<Todo> getTodoBySummary(
			@RequestParam(name = "summary", required = false, defaultValue = " ") String summary) {
		return todoService.findBySummaryContainingIgnoreCase(summary);
	}

	@GetMapping("/getSummaryAndDescription")
	public Flux<Todo> getSummaryAndDescription(
			@RequestParam(name = "summary", required = false, defaultValue = " ") String summary,
			@RequestParam(name = "description", required = false, defaultValue = " ") String description) {
		return todoService.findBySummaryContainingAndDescriptionContainingAllIgnoreCaseAndDoneIsTrue(summary,
				description);
	}

	@PostMapping("/newTodo")
	public Mono<Todo> newTodo(@RequestBody Todo todo) {
		return todoService.newTodo(todo);
	}

	@DeleteMapping("/deleteTodo/{id}")
	public Mono<Void> deleteTodo(@PathVariable("id") int id) {
		return todoService.deleteTodo(id);
	}

}

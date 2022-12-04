package com.example.practic_boot.api;

import com.example.practic_boot.model.Task;
import com.example.practic_boot.service.LessonService;
import com.example.practic_boot.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TaskController {


    private final TaskService taskService;
    private final LessonService lessonService;

    @Autowired
    public TaskController(TaskService taskService, LessonService lessonService) {
        this.taskService = taskService;
        this.lessonService = lessonService;
    }


    @GetMapping("/tasks/{id}")
    public String getAllTasks(@PathVariable Long id, Model model) {
        model.addAttribute("tasks", taskService.getAlTasks(id));
        model.addAttribute("lessons",lessonService.getAllLessons(id));
        model.addAttribute("lessonId", id);
        return "/task/tasks";
    }

    @GetMapping("/tasks/{id}/addTask")
    public String addTask(@PathVariable Long id, Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("lessonId", id);
        return "/task/addTask";

    }

    @PostMapping("/{id}/saveTask")
    public String saveTask(@ModelAttribute("task") Task task, @PathVariable Long id) {
        taskService.addTask(id, task);
        return "redirect:/tasks/" + id;
    }

    @GetMapping("/updateTask/{id}")
    public String updateTask(@PathVariable("id") Long id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        model.addAttribute("lessonId", task.getLesson().getId());
        return "task/updateTasks";
    }

    @PostMapping("{lessonId}/{id}/saveUpdateTask")
    public String saveUpdateTask(@PathVariable("lessonId") Long lessonId,
                                 @PathVariable("id") Long id,
                                 @ModelAttribute("task") Task task) {
        taskService.updateTask(task, id);
        return "redirect:/tasks/" + lessonId;
    }

    @GetMapping("/{lessonId}/{id}/deleteTask")
    public String deleteTask(@PathVariable("id") Long id, @PathVariable("lessonId") Long lessonId) {
        taskService.deleteTask(id);

        return "redirect:/tasks/" + lessonId;
    }

}

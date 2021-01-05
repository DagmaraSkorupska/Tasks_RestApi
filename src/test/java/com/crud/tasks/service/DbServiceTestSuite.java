package com.crud.tasks.service;


import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTestSuite {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void shouldGetAllTask() {
        //Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "title1", "text"));
        tasks.add(new Task(2L, "title2", "text2"));
        when(taskRepository.findAll()).thenReturn(tasks);
        //When
        List<Task> taskList = dbService.getAllTask();
        //Then
        Assert.assertEquals(2, taskList.size());
    }

    @Test
    public void shouldGetEmptyTask() {
        //Given
        List<Task> tasks = new ArrayList<>();
        when(taskRepository.findAll()).thenReturn(tasks);
        //When
        List<Task> taskList = dbService.getAllTask();
        //Then
        Assert.assertNotNull(taskList);
    }

    @Test
    public void shouldSaveTask() {
        //Given
        Task task = new Task(1l, "title1", "text");
        when(taskRepository.save(task)).thenReturn(task);
        //When
        Task task1 = dbService.saveTask(task);
        String name = task1.getTitle();
        //Then
        Assert.assertEquals("title1", name);
    }

    @Test
    public void shouldSaveEmptyTask() {
        //Given
        Task task = new Task();
        when(taskRepository.save(task)).thenReturn(task);
        //When
        Task task1 = dbService.saveTask(task);
        String name = task1.getTitle();
        //Then
        Assert.assertNotNull(task1);
    }


}
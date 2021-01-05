package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTestSuite {

    @Autowired
    TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "name1", "test");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        String name = task.getTitle();
        String content = task.getContent();
        //Then
        Assert.assertEquals("name1", name);
        Assert.assertEquals("test", content);
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task(1L, "title1", "test1");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        String title = taskDto.getTitle();
        String content = taskDto.getContent();
        //Then
        Assert.assertEquals("title1", title);
        Assert.assertEquals("test1", content);
    }

    @Test
    public void mapToTaskDtoList() {
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L, "title1", "test1"));
        taskList.add(new Task(2L, "title2", "test2"));
        //When
        List<TaskDto> taskDtos = taskMapper.mapToTaskDtoList(taskList);
        int size = taskDtos.size();
        String list1 = taskDtos.get(0).getTitle();
        //Then
        Assert.assertEquals(2, size);
        Assert.assertEquals("title1", list1);
    }

}
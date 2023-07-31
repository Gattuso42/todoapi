package com.gattuso.todoapi;


import com.gattuso.todoapi.model.Task;
import com.gattuso.todoapi.repository.TaskRepository;
import com.gattuso.todoapi.service.TaskService;
import com.gattuso.todoapi.service.TaskServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {
    @Mock
    private TaskRepository taskRepository;
    @InjectMocks
    private TaskServiceImpl taskService;


    LocalDate dateTest = LocalDate.of(2023,7,30);

    @Test
    public void getAllTaskTest(){
        when(taskRepository.findAll()).thenReturn(Arrays.asList(
                new Task(1L,"test1","testing1","pending",LocalDate.of(2023,7,30)),
                new Task(2L,"test2","testing2","pending",LocalDate.of(2023,7,30))
        ));

        assertEquals("test1",taskService.getAllTask().get(0).getTitle());
        assertEquals("testing1",taskService.getAllTask().get(0).getDescription());
        assertEquals("pending",taskService.getAllTask().get(0).getStatus());
        assertEquals(LocalDate.of(2023,7,30),taskService.getAllTask().get(0).getDueDate());

        assertEquals("test2",taskService.getAllTask().get(1).getTitle());
        assertEquals("testing2",taskService.getAllTask().get(1).getDescription());
        assertEquals("pending",taskService.getAllTask().get(1).getStatus());
        assertEquals(LocalDate.of(2023,7,30),taskService.getAllTask().get(1).getDueDate());

        when(taskRepository.findAll()).thenReturn(new ArrayList<>());

        List<Task> tasksResult = taskService.getAllTask();

        assertEquals(new ArrayList<Task>(),tasksResult);

    }

    @Test(expected = EntityNotFoundException.class)
    public void getTaskTest(){
        when(taskRepository.findTaskById(1L)).thenReturn(
                Optional.of(new Task(1L, "test1", "testing1", "pending", LocalDate.of(2023, 7, 30)))
        );
        when(taskRepository.findTaskById(2L)).thenReturn(
                Optional.empty()
        );

        Task taskResult = taskService.getTask(1L);

        Assertions.assertNotNull(taskResult);
        assertEquals("test1",taskResult.getTitle());
        assertEquals("testing1",taskResult.getDescription());
        assertEquals("pending",taskResult.getStatus());
        assertEquals(dateTest,taskResult.getDueDate());


        Task taskResult2 = taskService.getTask(2L);
    }

    @Test
    public void saveTaskTestSuccess(){
        Task taskSample = new Task(1L,"test1","testing1","pending",LocalDate.of(2023,7,30));
        taskService.saveTask(taskSample);
        verify(taskRepository,times(1)).save(taskSample);
    }

    @Test
    public void updateTaskSuccessTest(){
//      Sample
        Task taskSample = new Task();
        taskSample.setTitle("test1");
        taskSample.setDescription("testing1");
        taskSample.setStatus("pending");
        taskSample.setDueDate(LocalDate.of(2023,7,30));
//      Mock
        when(taskRepository.findTaskById(1L)).thenReturn(Optional.of(taskSample));

//      Result
        Task testResult = taskService.upgradeTask(taskSample,1L);
//      Verifications
        verify(taskRepository,times(1)).findTaskById(1L);
        verify(taskRepository,times(1)).save(taskSample);

//      Assertions
        assertNotNull(testResult);
        assertEquals(taskSample.getTitle(),testResult.getTitle());
        assertEquals(taskSample.getDescription(),testResult.getDescription());
        assertEquals(taskSample.getStatus(),testResult.getStatus());
        assertEquals(taskSample.getDueDate(),testResult.getDueDate());

    }

    @Test()
    public void updateTaskTestFailed(){
//      Sample
        Task taskSample = new Task();
//      Mocks
        when(taskRepository.findTaskById(1L)).thenReturn(Optional.empty());
//      Result
        try{
            taskService.upgradeTask(taskSample,1L);
        }
        catch(EntityNotFoundException ignored){


        }
//      Verifications
        verify(taskRepository,times(1)).findTaskById(1L);
        verify(taskRepository,never()).save(taskSample);
//      Assertions

    }

    @Test
    public void deleteTaskTestSuccess(){
//        Sample
          Task taskSample = new Task();
          taskSample.setTitle("test1");
          taskSample.setDescription("testing1");
          taskSample.setStatus("pending");
          taskSample.setDueDate(LocalDate.of(2023,7,30));
//        Mocks
          when(taskRepository.findTaskById(1L)).thenReturn(Optional.of(taskSample));
//        Results
          taskService.deleteTask(1L);
//        Verifications
          verify(taskRepository,times(1)).deleteById(1L);
//        Assertions
    }

    @Test
    public void deleteTaskTestFailed(){
//        Sample
//        Mocks
//        Results
          try{
              taskService.deleteTask(1L);
          }
          catch(EntityNotFoundException ex){
              System.out.println(ex.getMessage());
          }
//        Verifications
          verify(taskRepository,never()).deleteById(1L);
//        Assertions
    }
}

package org.zero.apiserver.repository;


import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zero.apiserver.domain.Todo;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
@Log4j2
public class TodoRepositoryTests {
    @Autowired
    private TodoRepository todoRepository;


    @Test
    public void testinsert(){
        Todo todo = Todo.builder()
                .title("title1")
                .content("content1")
                .duedate(LocalDate.of(2023,12,31))
                .build();
        todoRepository.save(todo);
    }
    @Test
    public void test1(){
        log.info("-------------");
        log.info(todoRepository);
    }
    @Test
    public void testInsert(){
        for(int i=1;i<=100;i++){
            Todo todo = Todo.builder()
                    .title("title:"+i)
                    .duedate(LocalDate.of(2023,12,31))
                    .content("content"+i)
                    .build();
            todoRepository.save(todo);
        }

    }
    @Test
    public void testREAD(){
        Long id=1L;
        Optional<Todo> result = todoRepository.findById(id);
        Todo todo = result.orElseThrow();
        log.info("testestetsttest");
        log.info(todo);
    }
    @Test
    public void pageupdate(){
        Long id=1L;
        Optional<Todo> result = todoRepository.findById(id);
        Todo todo = result.orElseThrow();
        todo.setTitle("updatetitle");
        todo.setComplete(true);
        todo.setContent("updatecontent");
        todoRepository.save(todo);
    }
    @Test
    public void testdelete(){
        Long id = 1L;
        todoRepository.deleteById(id);
    }
    @Test
    public void testPAGING(){
        Pageable pageable = PageRequest.of(0,10,Sort.by("id").descending());
        Page<Todo> result = todoRepository.findAll(pageable);
        log.info("paging11111111111111111111");
        log.info(result.getTotalElements());
        result.getContent().stream().forEach(todo->log.info(todo));
    }

}

package org.zero.apiserver.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.zero.apiserver.dto.PageReponseDto;
import org.zero.apiserver.dto.PageRequestDto;
import org.zero.apiserver.dto.TodoDto;

import java.time.LocalDate;

@SpringBootTest
@Log4j2
public class TodoServiceTests {

    @Autowired
    private TodoService todoService;

    @Test
    public void 테스트등록(){
        TodoDto todoDto = TodoDto.builder()
                .title("service tersts")
                .dueDate(LocalDate.of(2023,10,10))
                .content("test입니다.(테스트등록)")
                .build();
        Long id = todoService.register(todoDto);
        log.info("id : " + id);
    }
    @Test
    public void test조회(){
        Long id = 103L;
        TodoDto todoDto =todoService.get(id);
        log.info(todoDto);
    }
    @Test
    public void test리스트(){
        PageRequestDto pageRequestDto = PageRequestDto.builder()
                .page(2)
                .size(10)
                .build();

        PageReponseDto<TodoDto> reponse = todoService.list(pageRequestDto);
        log.info(reponse);

    }
}

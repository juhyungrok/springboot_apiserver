package org.zero.apiserver.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.zero.apiserver.dto.PageReponseDto;
import org.zero.apiserver.dto.PageRequestDto;
import org.zero.apiserver.dto.TodoDto;
import org.zero.apiserver.service.TodoService;

import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class TodoController {
    private final TodoService todoService;

    @GetMapping("/{id}")
    public TodoDto get(@PathVariable(name = "id") Long id){
        return todoService.get(id);
    }

    @GetMapping("/list")
    public PageReponseDto<TodoDto> list(PageRequestDto pageRequestDto){
        log.info(pageRequestDto);
        return todoService.list(pageRequestDto);
    }
    @PostMapping("/")
    public Map<String,Long> register(@RequestBody TodoDto todoDto){
        log.info("todoDto : "+todoDto );
        Long id = todoService.register(todoDto);
        return Map.of("id",id);
    }
    @PutMapping("/{id}")
    //수정 처리작업
    public Map<String, String> modify(@PathVariable(name="id") Long id, @RequestBody TodoDto todoDto){
        todoDto.setId(id);
        log.info("mofify : "+todoDto);
        todoService.modify(todoDto);
        return Map.of("result","success");
    }
    @DeleteMapping("/{id}")
    public Map<String,String> delete(@PathVariable(name = "id") Long id){
        log.info("remove : " + id);
        todoService.delete(id);
        return Map.of("RESULT","SUCCESS");
    }
}

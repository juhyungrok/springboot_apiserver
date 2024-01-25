package org.zero.apiserver.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zero.apiserver.domain.Todo;
import org.zero.apiserver.dto.PageReponseDto;
import org.zero.apiserver.dto.PageRequestDto;
import org.zero.apiserver.dto.TodoDto;
import org.zero.apiserver.repository.TodoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor  //생성자 자동 주입!!(여기서 이게 가장 중요한 듯)
public class TodoServiceImpl implements TodoService{
    private final ModelMapper modelMapper;
    private final TodoRepository todoRepository;
    @Override
    public Long register(TodoDto todoDto){
        log.info("....... .. . register");
        Todo todo = modelMapper.map(todoDto, Todo.class);
        Todo savedTodo = todoRepository.save(todo);
        return savedTodo.getId();
    }
    @Override
    public TodoDto get(Long id){
        Optional<Todo> result = todoRepository.findById(id);
        Todo todo = result.orElseThrow();
        return modelMapper.map(todo,TodoDto.class);
    }
    @Override
    public void modify(TodoDto todoDto){
        Optional<Todo> result = todoRepository.findById(todoDto.getId());

        Todo todo = result.orElseThrow();
        todo.setTitle(todoDto.getTitle());
        todo.setContent(todoDto.getContent());
        todo.setComplete(todoDto.isComplete());

        todoRepository.save(todo);

    }
    public void delete(Long id){
        todoRepository.deleteById(id);
    }
    @Override
    //목록(페이징 처리 구현)
    public PageReponseDto<TodoDto> list(PageRequestDto pageRequestDto){
        Pageable pageable =
                PageRequest.of(
                        pageRequestDto.getPage()-1,
                        pageRequestDto.getSize(),
                        Sort.by("id").descending()
                );
        Page<Todo> result = todoRepository.findAll(pageable);
        List<TodoDto> dtoList = result.getContent().stream()
                .map(todo -> modelMapper.map(todo, TodoDto.class))
                .collect(Collectors.toList());
        long totalCount = result.getTotalElements();
        return PageReponseDto.<TodoDto>withAll()
                .dtoList(dtoList)
                .pageRequestDto(pageRequestDto)
                .totalcount(totalCount)
                .build();
    }
}

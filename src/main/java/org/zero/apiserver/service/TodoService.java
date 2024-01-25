package org.zero.apiserver.service;

import org.zero.apiserver.dto.PageReponseDto;
import org.zero.apiserver.dto.PageRequestDto;
import org.zero.apiserver.dto.TodoDto;

public interface TodoService {
    Long register(TodoDto todoDto);
    //해당 id값을 가져오기
    TodoDto get(Long id);
    //수정기능
    void modify(TodoDto todoDto);
    //삭제기능
    void delete(Long id);
    //목록(페이징)퍼리 구현
    PageReponseDto<TodoDto> list(PageRequestDto pageRequestDto);

}

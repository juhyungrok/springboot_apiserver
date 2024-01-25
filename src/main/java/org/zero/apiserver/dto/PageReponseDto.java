package org.zero.apiserver.dto;

import lombok.Builder;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zero.apiserver.domain.Todo;
import org.zero.apiserver.repository.TodoRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
//TodoDto 리스트(해당 페이지의 데이터)
public class PageReponseDto<E> {
    private List<E> dtoList;
    private List<Integer> pageNumList;
    private PageRequestDto pageRequestDto;
    private boolean prev,next;
    private int totalcount,prepage,nextpage,totalpage,current;

//페이징 처리된 목록 데이터
    @Builder(builderMethodName = "withAll")
    public PageReponseDto(List<E> dtoList, PageRequestDto pageRequestDto,long totalcount) {
        this.dtoList = dtoList;
        this.pageRequestDto = pageRequestDto;
        this.totalcount=(int) totalcount;

        int end = (int)(Math.ceil(pageRequestDto.getPage()/10.0))*10;
        int start = end -9;
        int last = (int) (Math.ceil(totalcount/(double) pageRequestDto.getSize()));
        end = Math.min(end, last);
        this.prev = start > 1 ;
        this.next = totalcount > end * pageRequestDto.getSize();

        this.pageNumList = IntStream.rangeClosed(start,end).boxed().collect(Collectors.toList());

        if(prev)
            this.prepage =start -1;
        if(next)
            this.nextpage = end + 1;

        this.totalpage =this.pageNumList.size();
        this.current = pageRequestDto.getPage();

    }
}

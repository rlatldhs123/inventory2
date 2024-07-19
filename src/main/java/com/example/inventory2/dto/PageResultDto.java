package com.example.inventory2.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Data
public class PageResultDto<DTO, EN> {

  // entity 타입의 리스트를 dto 타입의 리스트로 변환 [필수 작업]

  private List<DTO> dtoList;

  // 화면에서 시작 페이지 번호 도 필요
  // 화면에서 끝 페이지 번호 필요
  private int start, end;

  // 이전/다음 이동 링크 여부
  private boolean prev, next;

  // 현제 페이지 번호
  private int page;

  // 총 페이지 번호
  private int totalPage;

  // 목록 사이즈
  private int size;

  // 페이지 번호 목록
  private List<Integer> pageList;

  // Page<EN> result : 스프링에서 페이지 나누기와 관련된 모든 정보를 가지고 있는 객체

  // Function<EN, DTO> fn : 첫번째 타입(EN)을 받아서 두번쨰 타입(DTO)로 바꿔주는
  public PageResultDto(Page<EN> result, Function<EN, DTO> fn) {
    this.dtoList = result.stream().map(fn).collect(Collectors.toList());

    this.totalPage = result.getTotalPages();
    makePageList(result.getPageable());
  }

  public void makePageList(Pageable pageable) {
    // int tempEnd=(int)(Math.ceil(페이지번호 / 10.0)) * 10;
    //
    // spring 페이지는 0 부터 시작
    this.page = pageable.getPageNumber() + 1;
    this.size = pageable.getPageSize();

    int tempEnd = (int) (Math.ceil(page / 10.0)) * 10;

    this.start = tempEnd - 9;
    this.end = totalPage > tempEnd ? tempEnd : totalPage;

    this.prev = start > 1;
    this.next = totalPage > tempEnd;
    // List<Integer>
    this.pageList =
      IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
  }
}

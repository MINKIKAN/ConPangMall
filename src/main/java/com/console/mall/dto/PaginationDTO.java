package com.console.mall.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationDTO {

    private int page;
    private Long totalPages;// 현재 페이지 번호
    private int recordSize;       // 페이지당 출력할 데이터 개수
    private int pageSize;         // 화면 하단에 출력할 페이지 사이즈
    private String keyword;       // 검색 키워드
    private String searchType;
    private int startPage;
    private int endPage;
    private int totalRecord;

    public PaginationDTO(int page, Long totalPages) {
        this.recordSize = 6;
        this.pageSize = 10;
        this.page = page;
        this.totalPages = totalPages;
        setRecord();
        setPage();
    }

    public void setRecord() {
        int tr = (int) (totalPages / recordSize);
        if (tr % recordSize != 0) {
            tr++;
        }
        this.totalRecord = tr;
    }

    public void setPage() {
        if (page == 0) {
            page = 1;
            startPage = 1;
        } else {
            if (page / pageSize == 0 || page == pageSize) {
                startPage = 1;
            } else {
                startPage = page / pageSize * pageSize + 1;
            }
        }
        if (endPage > totalRecord) {
            endPage = totalRecord;
        } else {
            endPage = startPage + pageSize - 1;
        }
    }

    public int getOffset() {
        int offset = page * recordSize - recordSize;
        return offset;
    }
}

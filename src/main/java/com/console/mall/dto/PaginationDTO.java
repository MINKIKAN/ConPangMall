package com.console.mall.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PaginationDTO {

    private int page; //현재 페이지 번호
    private int totalPages; //전체페이지
    private int recordSize;  // 6개출력
    private int pageSize;   // 최대 페이지번호 10개
    private int startIdx; // 시작인덱스
    private int endIdx;  // 끝 인덱스
    private int startPage; // 시작번호
    private int endPage; // 끝 번호
    private int totalRecord; //전체 번호개수
    private int pageNum; // 보여주는 페이지 카운트

    public PaginationDTO(int page, int totalPages) {
        this.recordSize = 6;
        this.pageSize = 10;
        this.totalPages = totalPages;

        this.totalRecord = totalPages / recordSize + 1;
        if (this.totalPages / recordSize >= 1 && totalPages % recordSize == 0) {
            this.totalRecord = totalPages / recordSize;
        }

        this.page = page;
        if (this.page >= totalRecord) {
            this.page = totalRecord;
        }

        this.pageNum = this.page / pageSize;
        if (this.page / pageSize >= 1 && this.page % pageSize == 0) {
            this.pageNum = this.page / pageSize - 1;
        }


        setStartIdx();
        setEndIdx();
        setStartPage();
        setEndPage();
    }

    public void setStartPage() {
        this.startPage = pageNum * pageSize + 1;
    }

    public void setEndPage() {
        this.endPage = startPage + pageSize - 1;
        if (this.endPage >= totalRecord) {
            this.endPage = totalRecord;
        }
    }

    public void setStartIdx() {
        this.startIdx = (page - 1) * recordSize;
    }

    public void setEndIdx() {
        this.endIdx = startIdx + recordSize - 1;
    }

}

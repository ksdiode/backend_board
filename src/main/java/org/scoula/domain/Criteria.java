package org.scoula.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Criteria {

    private int pageNum;		// 현재 페이지 번호
    private int amount;			// 페이지당 데이터 건수
    private String type;		// 검색 범위
    private String keyword;	    // 검색어


    public Criteria() {
        this(1, 10);
    }

    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }

    public int getOffset() {
        return (pageNum - 1) * amount;
    }

}

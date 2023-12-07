package org.scoula.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.util.UriComponentsBuilder;

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


    public String getLink() {
        return getLink("", pageNum);
    }

    public String getLink(String base) {

        return getLink(base, pageNum);
    }

    public String getLink(int page) {

        return getLink("", page);
    }



    public String getLink(String base, int page) {
        return UriComponentsBuilder.fromPath(base)
                .queryParam("pageNum", page)
                .queryParam("amount", amount)
                .toUriString();

    }

    public String getLink(String base, long no) {
        return UriComponentsBuilder.fromPath(base)
                .queryParam("no", no)
                .queryParam("pageNum", pageNum)
                .queryParam("amount", amount)
                .queryParam("type", type)
                .queryParam("keyword", keyword)
                .toUriString();
    }

}

}

package com.fastcampus.ch4.domain;


public class PageHandler {
    int totalCnt;   // 총 게시물 개수
    int pageSize;   // 한 페이지의 크기
    int naviSize = 10;   // 페이지 네비게이션의 크기
    int totalPage;  //전체 페이지의 개수
    int page;   // 현재 페이지
    int beginPage;  // 네이게이션의 첫 번째 페이지
    int endPage;    // 네비게이션의 마지막 페이지
    boolean showPrev;   // 이전 페이지로 이동하는 링크를 보여줄 것인지의 여부
    boolean showNext;   // 다음 페이지로 이동하는 링크를 보여줄 것인지의 여부

    public PageHandler(int totalCnt, int page) {
        this(totalCnt, 10, page);
    }

    public PageHandler(int totalCnt, int pageSize, int page) {
        this.totalCnt = totalCnt;
        this.pageSize = pageSize;
        this.page = page;

        totalPage = (int) Math.ceil((double)totalCnt / pageSize);
        beginPage = page / naviSize * naviSize + 1;
        endPage = Math.min(beginPage + naviSize - 1, totalPage);
        showPrev = beginPage != 1;
        showNext = endPage != totalPage;
    }

    void print() {
        System.out.println("page = " + page);
        System.out.println(showPrev ? "[PREV] " : "");
        for (int i = beginPage; i <= endPage; i++) {
            System.out.println(i + " ");
        }
        System.out.println(showNext ? " [NEXT]" : "");
    }

    @Override
    public String toString() {
        return "PageHandler{" +
                "totalCnt=" + totalCnt +
                ", pageSize=" + pageSize +
                ", naviSize=" + naviSize +
                ", totalPage=" + totalPage +
                ", page=" + page +
                ", beginPage=" + beginPage +
                ", endPage=" + endPage +
                ", showPrev=" + showPrev +
                ", showNext=" + showNext +
                '}';
    }
}

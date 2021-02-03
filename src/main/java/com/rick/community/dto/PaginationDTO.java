package com.rick.community.dto;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showNext;
    private boolean showFirstPage;
    private boolean showFinalPage;
    private boolean page;
    private List<Integer> pages;

    public void setPagination(Integer totalCount, Integer page, Integer size) {
        Integer totalPage;
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        pages = new ArrayList<>();
        pages.add(page);
        for (int i=1;i<=3;i++)
        {
            if (page-i > 0)
                pages.add(page-i,0);

            if (page+i <= totalCount)
                pages.add(page+i);
        }

        // 是否展示上一页
        if (page == 1)
            showPrevious = false;
        else
            showPrevious = true;
        // 是否展示下一页
        if (page == totalPage)
            showNext = false;
        else
            showNext = true;

        // 是否跳转第一页
        if (!pages.contains(1))
            showFirstPage = false;
        else
            showFirstPage = true;

        // 是否跳转最后一页
        if (!pages.contains(totalPage))
            showFinalPage = false;
        else
            showFinalPage = true;
    }
}

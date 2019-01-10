package internet.shop.model.form;

import internet.shop.model.entity.Camp;

import java.util.List;

public class CampPage {

    private Long page;
    private Long pageCount;
    private Long campCount;
    private List<Camp> campList;

    public CampPage() {
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getPageCount() {
        return pageCount;
    }

    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }

    public Long getCampCount() {
        return campCount;
    }

    public void setCampCount(Long campCount) {
        this.campCount = campCount;
    }

    public List<Camp> getCampList() {
        return campList;
    }

    public void setCampList(List<Camp> campList) {
        this.campList = campList;
    }
}

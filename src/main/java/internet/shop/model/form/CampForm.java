package internet.shop.model.form;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;

public class CampForm {

    @NotEmpty
    @Size(max = 49)
    private String name;

    private String dateStart;

    private String dateFinish;

    @PositiveOrZero
    @Max(120)
    @NotNull
    private int ageMin;

    @PositiveOrZero
    @Max(120)
    @NotNull
    private int ageMax;

    @PositiveOrZero
    @NotNull
    private int price;

    @PositiveOrZero
    @NotNull
    private int childrenCount;

    private String info;

    private MultipartFile icon;

    private Long typeId;

    private Long placeId;

    public CampForm() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(String dateFinish) {
        this.dateFinish = dateFinish;
    }

    public int getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(int ageMin) {
        this.ageMin = ageMin;
    }

    public int getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(int ageMax) {
        this.ageMax = ageMax;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getChildrenCount() {
        return childrenCount;
    }

    public void setChildrenCount(int childrenCount) {
        this.childrenCount = childrenCount;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public MultipartFile getIcon() {
        return icon;
    }

    public void setIcon(MultipartFile icon) {
        this.icon = icon;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }
}

package internet.shop.model.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "camp")
public class Camp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(max = 49)
    private String name;

    private LocalDate dateStart;

    private LocalDate dateFinish;

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

    private boolean removed;
    private String info;

    private byte[] icon;
    private byte[] smallIcon;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private CampType type;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    public Camp() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = LocalDate.parse(dateStart);
    }

    public LocalDate getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(LocalDate dateFinish) {
        this.dateFinish = dateFinish;
    }

    public void setDateFinish(String dateFinish) {
        this.dateFinish = LocalDate.parse(dateFinish);
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

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

    public byte[] getSmallIcon() {
        return smallIcon;
    }

    public void setSmallIcon(byte[] smallIcon) {
        this.smallIcon = smallIcon;
    }

    public CampType getType() {
        return type;
    }

    public void setType(CampType type) {
        this.type = type;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }


}

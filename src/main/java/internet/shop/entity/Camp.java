package internet.shop.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name="camp")
public class Camp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;
    @Future
    private LocalDate dateStart;

    @Future
    private LocalDate dateFinish;

    @Min(0)
    private int ageMin;

    @Max(120)
    private int ageMax;

    @Min(0)
    private int childrenCount;

    @AssertFalse
    private boolean removed;
    private String info;

    //TODO: с иконкой и типом хранения подумать как добавлять её
    private byte[] icon;

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

    public LocalDate getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(LocalDate dateFinish) {
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

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-M-yyyy");
        return String.format(
                "Camp[id=%d, name='%s', Start='%s', Finish='%s',ageMin='%s', ageMax='%s'," +
                        "info='%s',childrenCount='%s']\r\n",
                id, name, format.format(dateStart), format.format(dateFinish),
                ageMin, ageMax, info, childrenCount) ;
    }

}

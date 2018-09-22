package hello_web;

import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class Camp {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private LocalDate dateStart;
    private LocalDate dateFinish;

    protected Camp() {
    }

    public Camp(String name, LocalDate dateStart, LocalDate dateFinish) {
        this.name = name;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
    }

    public Camp(String name, String dateStart, String dateFinish) {
        this.name = name;
        this.dateStart = LocalDate.parse(dateStart);
        this.dateFinish = LocalDate.parse(dateFinish);
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

    public void setDateStart(LocalDate LocalDateStart) {
        this.dateStart = LocalDateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = LocalDate.parse(dateStart);
    }

    public LocalDate getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(LocalDate LocalDateFinish) {
        this.dateFinish = LocalDateFinish;
    }

    public void setDateFinish(String dateFinish) {
        this.dateFinish = LocalDate.parse(dateFinish);
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-M-yyyy");
        return String.format(
                "Camp[id=%d, name='%s', Start='%s', Finish='%s']",
                id, name, format.format(dateStart), format.format(dateFinish));
    }


}

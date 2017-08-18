package models;


import javax.persistence.*;
import java.io.Serializable;
import play.db.jpa.*;

@Entity
@Table(name="points")
public class Point implements Serializable{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name="Id")
    private Integer id;
    private Double x;
    private Double y;
    private Double r;
    private Boolean isInside;
    private String color;
    private String owner;

    public Point(Double x, Double y, Double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public Point() {}
    public Point(Double x, Double y, Double r, Boolean isInside, String color, String owner ){
        this.x = x;
        this.y = y;
        this.r = r;
        this.isInside = isInside;
        this.color = color;
        this.owner = owner;
    }


    public void setX(Double x) {
        this.x = x;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public void setR(Double r) {
        this.r = r;
    }

    public void setIsInside(boolean isInside){
        this.isInside = isInside;
    }

    public void setIsInside(String color){
        this.color = color;
    }

    public void setOwner(String owner){
        this.owner = owner;
    }


    public Double getX() {
        return this.x;
    }

    public Double getR() {
        return this.r;
    }

    public Double getY() {
        return this.y;
    }

    public Integer getId() {
        return this.id;
    }

    public Boolean getIsInside() {
        return this.isInside;
    }

    public String getColor() {
        return this.color;
    }

    public String getOwner(String owner){
        return this.owner;
    }
}

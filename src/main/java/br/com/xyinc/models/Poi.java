package br.com.xyinc.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Poi {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "O atributo nome não pode ser nulo")
    private String name;

    @NotNull(message = "O atributo X não pode ser nulo")
    @Min(value = 0, message = "O atributo \"Posição X\" não pode receber um valor negativo")
    private Integer x;

    @NotNull(message = "O atributo Y não pode ser nulo")
    @Min(value = 0, message = "O atributo \"Posição Y\" não pode receber um valor negativo")
    private Integer y;

    public Poi(Long id, String name, Integer x, Integer y) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public Poi( String name, Integer x, Integer y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public Poi(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Poi() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getX() {
        return this.x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return this.y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Poi{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}

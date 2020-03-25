package com.koro.carshomework7.model;

import org.springframework.hateoas.RepresentationModel;

public class Car extends RepresentationModel {

    private long id;
    private String mark;
    private String model;
    private String color;
    private short productionYear;

    public Car() {
    }

    public Car(String mark, String model, String color, short productionYear) {
        this.mark = mark;
        this.model = model;
        this.color = color;
        this.productionYear = productionYear;
    }

    public void modifyCar(Car newCar) {
        String newMark = newCar.getMark();
        String newModel = newCar.getModel();
        String newColor = newCar.getColor();

        if (newMark != null) {
            this.mark = newMark;
        }
        if (newModel != null) {
            this.model = newModel;
        }
        if (newColor != null) {
            this.color = newColor;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public short getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(short productionYear) {
        this.productionYear = productionYear;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", productionYear=" + productionYear +
                '}';
    }
}

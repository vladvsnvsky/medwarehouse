package com.mipapp;

public class Product {

    private int id;
    private String pzn= null;
    private String supplier= null;
    private String name= null;
    private String strength= null;
    private int packageSize;
    private String unit= null;

    public Product(String pzn, String supplier, String name, String strength, int packageSize, String unit) {
        this.pzn = pzn;
        this.supplier = supplier;
        this.name = name;
        this.strength = strength;
        this.packageSize = packageSize;
        this.unit = unit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPzn() {
        return pzn;
    }

    public void setPzn(String pzn) {
        this.pzn = pzn;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public int getPackageSize() {
        return packageSize;
    }

    public void setPackageSize(int packageSize) {
        this.packageSize = packageSize;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", pzn='" + pzn + '\'' +
                ", supplier='" + supplier + '\'' +
                ", name='" + name + '\'' +
                ", strength='" + strength + '\'' +
                ", packageSize=" + packageSize +
                ", unit='" + unit + '\'' +
                '}';
    }
}

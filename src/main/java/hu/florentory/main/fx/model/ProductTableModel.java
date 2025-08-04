package hu.florentory.main.fx.model;

import javafx.beans.property.*;

public class ProductTableModel {

    private final LongProperty id;
    private final StringProperty name;
    private final IntegerProperty quantity;
    private final DoubleProperty price;
    private final StringProperty category;

    public ProductTableModel(Long id, String name, int quantity, double price, String category) {
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.price = new SimpleDoubleProperty(price);
        this.category = new SimpleStringProperty(category);
    }

    // Getters for JavaFX binding
    public LongProperty idProperty() {
        return id;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public Long getId() {
        return id.get();
    }

    public void setId(Long value) {
        this.id.set(value);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String value) {
        this.name.set(value);
    }

    public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(int value) {
        this.quantity.set(value);
    }

    public double getPrice() {
        return price.get();
    }

    public void setPrice(double value) {
        this.price.set(value);
    }

    public String getCategory() {
        return category.get();
    }

    public void setCategory(String value) {
        this.category.set(value);
    }
}


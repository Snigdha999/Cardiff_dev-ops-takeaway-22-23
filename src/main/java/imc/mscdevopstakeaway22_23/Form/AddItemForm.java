package imc.mscdevopstakeaway22_23.Form;

public class AddItemForm {
    private String name;
    private String description;
    private int price;

    public AddItemForm(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }
}

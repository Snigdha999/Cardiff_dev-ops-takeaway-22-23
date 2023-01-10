package imc.mscdevopstakeaway22_23.DTO;

public class ItemDTO {
    private String name;
    private String description;
    private int price;
    private int iD;

    public ItemDTO(String name, String description, int price, int iD) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.iD = iD;
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
    public int getID() {
        return iD;
    }
}

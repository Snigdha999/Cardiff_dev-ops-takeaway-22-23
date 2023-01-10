package imc.mscdevopstakeaway22_23.repository;

import imc.mscdevopstakeaway22_23.DTO.ItemDTO;
import imc.mscdevopstakeaway22_23.Form.AddItemForm;


import java.util.List;

public interface ItemRepository {
    public List<ItemDTO> getAllItems();
    public boolean addItem(AddItemForm item);
    int deleteItem(int itemID);


}

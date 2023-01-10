package imc.mscdevopstakeaway22_23;

import imc.mscdevopstakeaway22_23.DTO.ItemDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class JUnitSimpleTests {
    @Test
    public void DTOTest()  {
        ItemDTO item = new ItemDTO("Item1","ItemDeccription", 1000, 1);
        assertEquals(item.getDescription(), "ItemDeccription");
        assertEquals(item.getName(), "Item1");
        assertEquals(item.getPrice(), 1000);
    }

}

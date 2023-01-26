package imc.mscdevopstakeaway22_23.repository;

import imc.mscdevopstakeaway22_23.DTO.ItemDTO;
import imc.mscdevopstakeaway22_23.Form.AddItemForm;
import imc.mscdevopstakeaway22_23.model.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepositoryJDBC implements ItemRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ItemDTO> getAllItems() {
        return jdbcTemplate.query(
                "select ID,Name,description,price from items" ,
                new ItemMapper());
    }

    @Override
    public boolean addItem(AddItemForm item) {
        int rows = jdbcTemplate.update("insert into items (name, description, price) values (?,?,?);",
                new Object[] {item.getName(),
                        item.getDescription(),
                        item.getPrice()});
        return rows>0;
    }


    @Override
    public int deleteItem(int id) {
        return jdbcTemplate.update("delete from items where id =(?);",
                new Object[] {id});
    }

}

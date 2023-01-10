package imc.mscdevopstakeaway22_23.repository;

import imc.mscdevopstakeaway22_23.DTO.ItemDTO;
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
                "select ID,Name,description,price from Items" ,
                new ItemMapper());
    }
}

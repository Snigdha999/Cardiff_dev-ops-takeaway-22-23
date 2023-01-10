package imc.mscdevopstakeaway22_23.model;

import imc.mscdevopstakeaway22_23.DTO.ItemDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ItemDTO(
                rs.getString("name"),
                rs.getString("description"),
                rs.getInt("price"),
                rs.getInt("iD")  );
    }
}

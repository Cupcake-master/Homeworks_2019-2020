package ru.bulat.repositories;

import com.j256.ormlite.dao.BaseDaoImpl;
import org.springframework.stereotype.Component;
import ru.bulat.model.AssetType;

import java.sql.SQLException;

@Component
public class AssetTypeRepository extends BaseDaoImpl<AssetType, Integer> {

    protected AssetTypeRepository(Class<AssetType> dataClass) throws SQLException {
        super(dataClass);
    }
}

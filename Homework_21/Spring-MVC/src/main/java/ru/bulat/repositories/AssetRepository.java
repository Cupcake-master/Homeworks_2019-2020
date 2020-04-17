package ru.bulat.repositories;

import com.j256.ormlite.dao.BaseDaoImpl;
import org.springframework.stereotype.Component;
import ru.bulat.model.Asset;
import ru.bulat.model.AssetType;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AssetRepository extends BaseDaoImpl<Asset, Integer> {

    protected AssetRepository(Class<Asset> dataClass) throws SQLException {
        super(dataClass);
    }

    public List<Asset> getAssetListByAssetType(AssetType assetType) throws SQLException {
        Map<String, Object> map = new HashMap<>();
        map.put("assetType_id", assetType.getId());
        return this.queryForFieldValues(map);
    }
}

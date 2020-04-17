package ru.bulat.services;

import org.springframework.stereotype.Service;
import ru.bulat.model.AssetType;
import ru.bulat.repositories.AssetTypeRepository;

import java.sql.SQLException;
import java.util.List;

@Service
public class AssetTypeServiceImpl implements AssetTypeService {

    private final AssetTypeRepository assetTypeRepository;

    public AssetTypeServiceImpl(AssetTypeRepository assetTypeRepository) {
        this.assetTypeRepository = assetTypeRepository;
    }


    @Override
    public List<AssetType> findAll() {
        List<AssetType> assetTypes = null;
        try {
            assetTypes = assetTypeRepository.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assetTypes;
    }

    @Override
    public void delete(Integer id) {
        try {
            assetTypeRepository.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AssetType add(AssetType asset) {
        try {
            assetTypeRepository.create(asset);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return asset;
    }
}

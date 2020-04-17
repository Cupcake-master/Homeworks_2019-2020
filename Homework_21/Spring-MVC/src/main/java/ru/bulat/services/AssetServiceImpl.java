package ru.bulat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bulat.model.Asset;
import ru.bulat.repositories.AssetRepository;

import java.sql.SQLException;
import java.util.List;

@Service
public class AssetServiceImpl implements AssetService{

    private final AssetRepository assetRepository;

    @Autowired
    public AssetServiceImpl(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @Override
    public List<Asset> findAll() {
        List<Asset> assets = null;
        try {
            assets = assetRepository.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assets;
    }

    @Override
    public void delete(Integer id) {
        try {
            assetRepository.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Asset add(Asset asset) {
        try {
            assetRepository.create(asset);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return asset;
    }
}

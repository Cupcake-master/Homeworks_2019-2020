package ru.bulat.services;

import ru.bulat.model.AssetType;

import java.util.List;

public interface AssetTypeService {
    List<AssetType> findAll();
    void delete(Integer id);
    AssetType add(AssetType asset);
}

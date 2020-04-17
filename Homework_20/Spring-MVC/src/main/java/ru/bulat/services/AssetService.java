package ru.bulat.services;

import ru.bulat.model.Asset;

import java.util.List;

public interface AssetService {
    List<Asset> findAll();
    void delete(Integer id);
    Asset add(Asset asset);

}

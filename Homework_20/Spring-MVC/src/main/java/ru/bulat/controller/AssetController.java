package ru.bulat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.bulat.model.Asset;
import ru.bulat.model.AssetType;
import ru.bulat.services.AssetService;
import ru.bulat.services.AssetTypeService;

import java.util.List;

@RestController
public class AssetController {

    private final AssetService assetService;
    private final AssetTypeService assetTypeService;

    @Autowired
    public AssetController(AssetService assetService, AssetTypeService assetTypeService) {
        this.assetService = assetService;
        this.assetTypeService = assetTypeService;
    }

    /**
     * Классификатор категорий активов
     * @return {@link List<AssetType>}
     */

    @RequestMapping(value = "/asset/type/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public @ResponseBody List<AssetType> getAssetTypeList(){
        return assetTypeService.findAll();
    }

    /**
     * Список активов
     * @return {@link List<Asset>}
     */
    @RequestMapping(value = "/asset/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    List<Asset> getAssetList(){
        return assetService.findAll();
    }

    @RequestMapping(value = "/asset/add", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    public @ResponseBody
    Asset addAsset(@RequestBody Asset asset){
        assetService.add(asset);
        return asset;
    }

    /**
     * Удалить актив
     * @param id идентификатор актива
     */
    @RequestMapping(value = "/asset/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteAsset(@PathVariable Integer id){
        assetService.delete(id);
    }
}

package ru.bulat.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DatabaseTable(tableName = "AssetType")
public class AssetType {

    @DatabaseField(id = true)
    private int id;

    @DatabaseField(unique = true, canBeNull = false)
    private String name;

}

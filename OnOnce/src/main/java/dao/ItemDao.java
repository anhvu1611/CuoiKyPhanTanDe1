package dao;

import entity.Item;

import java.util.List;

public interface ItemDao {
    List<Item> listItems(String supplierName);
}

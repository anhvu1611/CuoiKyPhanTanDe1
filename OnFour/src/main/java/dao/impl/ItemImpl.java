package dao.impl;

import dao.ItemDao;
import entity.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.util.List;

public class ItemImpl implements ItemDao {
    private EntityManager em;
    public ItemImpl() {
        em = Persistence
                .createEntityManagerFactory("sqlserver")
                .createEntityManager();
    }
    @Override
    public List<Item> listItems(String supplierName) {
        return em.createQuery("select i from Item i inner join Beverage b on i.id = b.id where b.supplier like :supplierName", Item.class)
                .setParameter("supplierName", supplierName)
                .getResultList();
    }
}

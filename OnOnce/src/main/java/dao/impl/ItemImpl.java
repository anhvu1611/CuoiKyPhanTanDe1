package dao.impl;

import dao.ItemDao;
import entity.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class ItemImpl implements ItemDao {
    private EntityManager em;

    public ItemImpl() {
        em = Persistence.createEntityManagerFactory("mariadb")
                .createEntityManager();
    }

    @Override
    public List<Item> listItems(String supplierName) {
        return em.createQuery("select i from Item i inner join i.ingredients ing  where ing.suplierName like :supplierName", Item.class)
                .setParameter("supplierName", "%" + supplierName + "%")
                .getResultList();
    }

}

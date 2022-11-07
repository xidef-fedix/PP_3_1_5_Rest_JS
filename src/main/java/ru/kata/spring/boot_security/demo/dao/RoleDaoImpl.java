package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean add(Role user) {
        entityManager.persist(user);
        return true;
    }

    @Override
    public Role getByIdRole(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public List<Role> getListRoles() {
        return entityManager.createQuery("select s from Role s", Role.class).getResultList();
    }

    @Override
    public Role getByName(String name) {
        return entityManager.createQuery("select u from Role u where u.role = :id", Role.class)
                .setParameter("id", name)
                .getResultList().stream().findAny().orElse(null);
    }

    @Override
    public List<Role> getListByName(List<String> name) {
        return entityManager.createQuery("select u from Role u where u.role in (:id)", Role.class)
                .setParameter("id", name)
                .getResultList();
    }
    @Override
    public List<Role> getSetOfRoles(String[] roleNames) {
        List<Role> roleSet = new ArrayList<>();
        for (String role : roleNames) {
            roleSet.add(getByName(role));
        }
        return  roleSet;
    }
}

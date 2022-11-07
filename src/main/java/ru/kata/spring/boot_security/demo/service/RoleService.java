package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.HashSet;
import java.util.List;

public interface RoleService {
    Role getByIdRole(Long id);

    List<Role> getListRoles();

    Role getByName(String name);

    List<Role> getListByName(List<String> name);

    boolean add(Role user);
    public List<Role> getSetOfRoles(String[] roleNames);
}

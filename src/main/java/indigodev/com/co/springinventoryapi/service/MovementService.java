package indigodev.com.co.springinventoryapi.service;

import indigodev.com.co.springinventoryapi.domain.Movement;

public interface MovementService {
    Movement save(String name);
    Movement fiendById(int id);
    Movement fiendByName(String name);
    void delete(int id);
}

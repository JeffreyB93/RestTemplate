package org.example.repository;

import org.example.model.Phone;

import java.util.Set;

public interface PhoneRepository extends Repository<Phone, Long> {
    Set<Phone> findAll();
}

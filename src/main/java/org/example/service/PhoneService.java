package org.example.service;

import org.example.model.Phone;

import java.util.Set;

public interface PhoneService extends Service<Phone, Long> {
    Set<Phone> findAll();
}
package org.example.service.impl;

import org.example.model.Phone;
import org.example.repository.PhoneRepository;
import org.example.repository.impl.PhoneRepositoryImpl;
import org.example.service.PhoneService;

import java.util.Set;

public class PhoneServiceImpl implements PhoneService {

    private PhoneRepository phoneRepository = new PhoneRepositoryImpl();

    @Override
    public Phone findById(Long id) {
        return phoneRepository.findById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        return phoneRepository.deleteById(id);
    }

    @Override
    public Phone save(Phone phone) {
        return phoneRepository.save(phone);
    }

    @Override
    public Set<Phone> findAll() {
        return phoneRepository.findAll();
    }
}

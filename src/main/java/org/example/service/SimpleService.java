package org.example.service;

import org.example.model.SimpleEntity;

public interface SimpleService {
    SimpleEntity save(SimpleEntity simpleEntity);

    SimpleEntity findById(Long id);
}

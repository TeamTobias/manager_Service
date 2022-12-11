package com.tobias.managerservice.inner.repository;

import com.tobias.managerservice.inner.domain.Manager;
import org.springframework.data.repository.CrudRepository;

public interface ManagerRepository extends CrudRepository<Manager, Long> {
    Manager findById(int id);
    void deleteById(int id);
}

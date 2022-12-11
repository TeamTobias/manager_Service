package com.tobias.managerservice.inner.repository;

import com.tobias.managerservice.inner.domain.Manager;
import com.tobias.managerservice.inner.domain.SaleRequest;
import org.springframework.data.repository.CrudRepository;

public interface SaleRequestRepository extends CrudRepository<SaleRequest, Long> {
    SaleRequest findById(int id);
    void deleteById(int id);
}

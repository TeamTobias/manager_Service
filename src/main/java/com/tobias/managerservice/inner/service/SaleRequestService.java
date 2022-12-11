package com.tobias.managerservice.inner.service;

import com.tobias.managerservice.inner.domain.Manager;
import com.tobias.managerservice.inner.domain.RequestManager;
import com.tobias.managerservice.inner.domain.SaleRequest;

public interface SaleRequestService {
    Iterable<SaleRequest> getSaleRequestsAll();
    SaleRequest getSaleRequest(int id);
    void deleteSaleRequest(int id);
}

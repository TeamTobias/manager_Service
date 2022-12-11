package com.tobias.managerservice.inner.service;

import com.tobias.managerservice.inner.domain.Manager;
import com.tobias.managerservice.inner.domain.SaleRequest;
import com.tobias.managerservice.inner.repository.SaleRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaleRequestServiceImpl implements SaleRequestService {

    private final SaleRequestRepository saleRequestRepository;

    public Iterable<SaleRequest> getSaleRequestsAll() {return saleRequestRepository.findAll();}

    @Override
    public SaleRequest getSaleRequest(int id) {
        return saleRequestRepository.findById(id);
    }

    @Override
    public void deleteSaleRequest(int id) {saleRequestRepository.deleteById(id);}

}
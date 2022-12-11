package com.tobias.managerservice.inner.service;

import com.tobias.managerservice.inner.domain.Manager;
import com.tobias.managerservice.inner.domain.RequestManager;
import com.tobias.managerservice.inner.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;

    public Iterable<Manager> getManagersAll() {return managerRepository.findAll();}

    @Transactional
    public void addManager(RequestManager requestManager) {

        Manager saler = managerRepository.findById(requestManager.getId());

        if (saler == null) {
            saler = Manager.createManager(requestManager);
            managerRepository.save(saler);
        }
    }

    @Transactional
    public void deleteManager(int managerId){
        managerRepository.deleteById(managerId);
    }

    public void setManager(int managerId, RequestManager requestManager){
        Manager manager = managerRepository.findById(managerId);
        if(manager!=null){
            manager.setName(requestManager.getName());
            manager.setNickname(requestManager.getNickname());
            manager.setEmail(requestManager.getEmail());
            manager.setGender(requestManager.getGender());
            manager.setBirth(requestManager.getBirth());
            manager.setPhone(requestManager.getPhone());
            managerRepository.save(manager);
        }
    }

    @Override
    public Manager getManager(int managerId) {
        return managerRepository.findById(managerId);
    }
}
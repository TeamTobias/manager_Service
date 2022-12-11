package com.tobias.managerservice.inner.service;

import com.tobias.managerservice.inner.domain.Manager;
import com.tobias.managerservice.inner.domain.RequestManager;

public interface ManagerService {
    void addManager(RequestManager client);
    Iterable<Manager> getManagersAll();
    void deleteManager(int id);
    void setManager(int clientId, RequestManager client);

    Manager getManager(int salerId);
}

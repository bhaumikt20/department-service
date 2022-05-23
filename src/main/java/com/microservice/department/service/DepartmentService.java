package com.microservice.department.service;

import com.microservice.department.domain.Department;
import com.microservice.department.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository repository;

    public Department save(Department department) {
        repository.save(department);
        return department;
    }

    @KafkaListener(topics = "DEPARTMENT_SEARCH", groupId = "group-id")
    public void consume(String message,
                        @Header(value = KafkaHeaders.RECEIVED_MESSAGE_KEY, required = false) String key) {
        System.out.println("key received while receiving message " + key);
        System.out.println("Search request came with id " + message);
    }

    public List<Department> findAll() {
        return repository.findAll();
    }

    public Department findOne(Long id) {
        return repository.findById(id).orElse(new Department());
    }
}

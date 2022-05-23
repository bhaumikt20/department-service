package com.microservice.department.resource;

import com.microservice.department.domain.Department;
import com.microservice.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentResource {

    @Autowired
    DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<Department> save(@RequestBody Department department) {
        return ResponseEntity.ok(departmentService.save(department));
    }

    @GetMapping
    public ResponseEntity<List<Department>> findAll() {
        return ResponseEntity.ok(departmentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.findOne(id));
    }
}

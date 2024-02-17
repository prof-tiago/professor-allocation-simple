package com.project.professor.allocation.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Department;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class DepartmentRepositoryTest {

    @Autowired
    DepartmentRepository departmentRepository;

    @Test
    public void findAll() {
        // Act
        List<Department> departments = departmentRepository.findAll();

        // Print
        departments.forEach(System.out::println);
    }

    @Test
    public void findById() {
        // Arrange
        Long id = 1L;

        // Act
        Department department = departmentRepository.findById(id).orElse(null);

        // Print
        System.out.println(department);
    }

    @Test
    public void findByNameContainingIgnoreCase() {
        // Arrange
        String name = "Department";

        // Act
        List<Department> departments = departmentRepository.findByNameContainingIgnoreCase(name);

        // Print
        departments.forEach(System.out::println);
    }

    @Test
    public void save_create() {
        // Arrange
        Department department = new Department();
        department.setId(null);
        department.setName("Department 1");

        // Act
        department = departmentRepository.save(department);

        // Print
        System.out.println(department);
    }

    @Test
    public void save_update() {
        // Arrange
        Department department = new Department();
        department.setId(1L);
        department.setName("Department 2");

        // Act
        department = departmentRepository.save(department);

        // Print
        System.out.println(department);
    }

    @Test
    public void deleteById() {
        // Arrange
        Long id = 1L;

        // Act
        departmentRepository.deleteById(id);
    }

    @Test
    public void deleteAll() {
        // Act
        departmentRepository.deleteAllInBatch();
    }
}

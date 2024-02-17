package com.project.professor.allocation.service;

import java.sql.Time;
import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Professor;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class AllocationServiceTest {

    @Autowired
    AllocationService allocationService;

    @Test
    public void findAll() {
        // Act
        List<Allocation> allocations = allocationService.findAll();

        // Print
		System.out.println(allocations);
    }

    @Test
    public void findByProfessor() {
        // Arrange
        Long id = 1L;

        // Act
        List<Allocation> allocations = allocationService.findByProfessor(id);

        // Print
		System.out.println(allocations);
    }

    @Test
    public void findByCourse() {
        // Arrange
        Long id = 1L;

        // Act
        List<Allocation> allocations = allocationService.findByCourse(id);

        // Print
		System.out.println(allocations);
    }

    @Test
    public void findById() {
        // Arrange
        Long id = 1L;

        // Act
        Allocation allocation = allocationService.findById(id);

        // Print
        System.out.println(allocation);
    }

    @Test
    public void save() throws ParseException {
        // Arrange
    	Professor professor = new Professor();
    	professor.setId(1L);
    	
    	Course course = new Course();
    	course.setId(1L);
    	
        Allocation allocation = new Allocation();
        allocation.setId(null);
        allocation.setDayOfWeek(DayOfWeek.WEDNESDAY);
        allocation.setStartHour(Time.valueOf(LocalTime.of(19, 00)));
        allocation.setEndHour(Time.valueOf(LocalTime.of(20, 00)));
        allocation.setProfessor(professor);
        allocation.setCourse(course);

        // Act
        allocation = allocationService.save(allocation);

        // Print
        System.out.println(allocation);
    }

    @Test
    public void update() throws ParseException {
        // Arrange
    	Professor professor = new Professor();
    	professor.setId(1L);
    	
    	Course course = new Course();
    	course.setId(1L);
    	
        Allocation allocation = new Allocation();
        allocation.setId(1L);
        allocation.setDayOfWeek(DayOfWeek.MONDAY);
        allocation.setStartHour(Time.valueOf(LocalTime.of(19, 00)));
        allocation.setEndHour(Time.valueOf(LocalTime.of(20, 00)));
        allocation.setProfessor(professor);
        allocation.setCourse(course);

        // Act
        allocation = allocationService.update(allocation);

        // Print
        System.out.println(allocation);
    }

    @Test
    public void deleteById() {
        // Arrange
        Long id = 1L;

        // Act
        allocationService.deleteById(id);
    }

    @Test
    public void deleteAll() {
        // Act
        allocationService.deleteAll();
    }
}

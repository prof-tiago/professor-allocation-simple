package com.project.professor.allocation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.service.ProfessorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Professors")
@RestController
@RequestMapping(path = "/professors")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        super();
        this.professorService = professorService;
    }

    @Operation(summary = "Find all professors")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "OK")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Professor>> findAll(@RequestParam(name = "name", required = false) String name) {
        List<Professor> professors = professorService.findAll(name);
        return new ResponseEntity<>(professors, HttpStatus.OK);
    }

    @Operation(summary = "Find a professor")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "OK"),
    	@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
    	@ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    @GetMapping(path = "/{professor_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Professor> findById(@PathVariable(name = "professor_id") Long id) {
        Professor professor = professorService.findById(id);
        if (professor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(professor, HttpStatus.OK);
        }
    }

    @Operation(summary = "Find professors by department")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "OK"),
    	@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @GetMapping(path = "/department/{department_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Professor>> findByDepartment(@PathVariable(name = "department_id") Long id) {
        List<Professor> professors = professorService.findByDepartment(id);
        return new ResponseEntity<>(professors, HttpStatus.OK);
    }

    @Operation(summary = "Save a professor")
    @ApiResponses({
    	@ApiResponse(responseCode = "201", description = "Created"),
    	@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Professor> save(@RequestBody Professor professor) {
        try {
            professor = professorService.save(professor);
            return new ResponseEntity<>(professor, HttpStatus.CREATED);
        } catch (Exception e) {
        	throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @Operation(summary = "Update a professor")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "OK"),
    	@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
    	@ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    @PutMapping(path = "/{professor_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Professor> update(@PathVariable(name = "professor_id") Long id,
                                            @RequestBody Professor professor) {
        professor.setId(id);
        try {
            professor = professorService.update(professor);
            if (professor == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(professor, HttpStatus.OK);
            }
        } catch (Exception e) {
        	throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @Operation(summary = "Delete a professor")
    @ApiResponses({
    	@ApiResponse(responseCode = "204", description = "No Content"),
    	@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @DeleteMapping(path = "/{professor_id}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "professor_id") Long id) {
        professorService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Delete all professors")
    @ApiResponses({
    	@ApiResponse(responseCode = "204", description = "No Content")
    })
    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        professorService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

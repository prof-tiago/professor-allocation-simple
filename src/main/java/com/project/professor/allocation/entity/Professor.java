package com.project.professor.allocation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
public class Professor {

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "cpf", unique = true, nullable = false, length = 11)
	private String cpf;

	@Schema(allOf = Department.class)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@ManyToOne(optional = false)
	@JoinColumn(name = "department_id", nullable = false)
	private Department department;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setDepartmentId(Long id) {
		Department department = new Department();
		department.setId(id);
		this.setDepartment(department);
	}

	@Override
	public String toString() {
		return "Professor{" + "id=" + id + ", name='" + name + '\'' + ", cpf='" + cpf + '\'' + ", department="
				+ department + '}';
	}
}

package com.example.demo;

import com.example.demo.dao.Student;
import com.example.demo.dao.StudentRepository;
import com.example.demo.dto.StudentDTO;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private StudentRepository studentRepository;

	@Test
	void test() {
		List<Student> studentList = studentRepository.findByAgeBetween(18, 20);
		System.out.println(studentList.size());

		List<Student> studentList2 = studentRepository.findByNameStartingWith("up");
		System.out.println(studentList2.size());
	}

	@Test
	void test2() {
		List<Student> studentList = studentRepository.findByEmail2("bob@bob.com");
		System.out.println(studentList.size());
	}

	@Test //动态查询
	void test3() {
		StudentDTO studentDTO = StudentDTO.builder().name("abcd").minAge(15).maxAge(20).build();

		Specification specification = (root, query, criteriaBuilder) -> {
			List<Predicate> predicateList = new ArrayList<>();
			if (studentDTO.getName() != null){
				predicateList.add(criteriaBuilder.equal(root.get("name"), studentDTO.getName()));
			}
			if (studentDTO.getMinAge() != 0){
				predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get("age"), studentDTO.getMinAge()));
			}
			if (studentDTO.getMaxAge() != 0){
				predicateList.add(criteriaBuilder.lessThanOrEqualTo(root.get("age"), studentDTO.getMaxAge()));
			}
			return query.where(predicateList.toArray(new Predicate[0])).getRestriction();
		};

		List<Student> studentList = studentRepository.findAll(specification);
		System.out.println(studentList.size());
    }

	@Test //动态查询
	void test4() {
		StudentDTO studentDTO = StudentDTO.builder().minAge(15).maxAge(20).build();

		Specification specification = (root, query, criteriaBuilder) -> {
			List<Predicate> predicateList = new ArrayList<>();
			if (studentDTO.getName() != null){
				predicateList.add(criteriaBuilder.equal(root.get("name"), studentDTO.getName()));
			}
			if (studentDTO.getMinAge() != 0){
				predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get("age"), studentDTO.getMinAge()));
			}
			if (studentDTO.getMaxAge() != 0){
				predicateList.add(criteriaBuilder.lessThanOrEqualTo(root.get("age"), studentDTO.getMaxAge()));
			}
			return query.where(predicateList.toArray(new Predicate[0])).getRestriction();
		};

		PageRequest pageRequest = PageRequest.of(1, 2);
		Page<Student> studentList = studentRepository.findAll(specification, pageRequest);
		System.out.println(studentList.getSize());
	}

	@Test //动态查询
	void test5() {
		StudentDTO studentDTO = StudentDTO.builder().minAge(15).maxAge(20).build();

		Specification specification = (root, query, criteriaBuilder) -> {
			List<Predicate> predicateList = new ArrayList<>();
			if (studentDTO.getName() != null){
				predicateList.add(criteriaBuilder.equal(root.get("name"), studentDTO.getName()));
			}
			if (studentDTO.getMinAge() != 0){
				predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get("age"), studentDTO.getMinAge()));
			}
			if (studentDTO.getMaxAge() != 0){
				predicateList.add(criteriaBuilder.lessThanOrEqualTo(root.get("age"), studentDTO.getMaxAge()));
			}
			return query.where(predicateList.toArray(new Predicate[0])).getRestriction();
		};

		List<Student> studentList = studentRepository.findAll(specification, Sort.by("id"));
		System.out.println(studentList.size());
	}
}

package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {

    List<Student> findByEmail(String email);

    List<Student> findByAgeBetween(int minAge, int maxAge);

    List<Student> findByNameStartingWith(String namePrefix);

    @Query(value = "select new com.example.demo.dao.Student(name, email) from Student  where email = :email")
    List<Student> findByEmail2(@Param("email") String email);
}

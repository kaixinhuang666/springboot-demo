package com.example.demo.dao;

import com.example.demo.converter.GenderConverter;
import com.example.demo.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.id.factory.spi.GeneratorDefinitionResolver;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity//表述需要和数据库连接
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Student {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    private  long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "age")
    private int age;

    @Column(name = "gender")
    @Convert(converter = GenderConverter.class)
    private Gender gender;

    public Student(String name, String email){
        this.name = name;
        this.email = email;
    }




//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
}

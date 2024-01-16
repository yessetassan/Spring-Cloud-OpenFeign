package com.clientb.rest;


import com.clientb.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class ClientBController {

    private static List<Student> students = new ArrayList<>();

    static {
        students.add( Student.builder()
                .id(1)
                .full_name("Tom Holland")
                .build());
        students.add( Student.builder()
                .id(1)
                .full_name("Cri Ronaldo")
                .build());
    }

    @GetMapping("/all")
    public List<Student> all() {
        return students;
    }

    @GetMapping("/all/{id}")
    public Student all_one(@PathVariable(name = "id") Integer id) throws Exception {
        Optional<Student> student = students.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst();

        return student.map(x -> Student.builder()
                        .id(x.getId())
                        .full_name(x.getFull_name())
                        .build())
                .orElse( Student.builder()
                        .id(null)
                        .full_name("Notfound")
                        .build());
    }


}

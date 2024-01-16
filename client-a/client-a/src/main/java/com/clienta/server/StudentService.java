package com.clienta.server;


import com.clienta.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "server-b", url = "http://localhost:52954")
public interface StudentService {
    @GetMapping("/all")
    List<Student> getAllStudents();

    @GetMapping("/all/{id}")
    Student getStudent(@PathVariable(name = "id") Integer id);

}

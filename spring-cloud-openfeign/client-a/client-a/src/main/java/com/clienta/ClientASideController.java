package com.clienta;


import com.clienta.server.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping

public class ClientASideController {

    private final StudentService studentService;
    private final WebClient.Builder webClient;

    @Autowired
    public ClientASideController(StudentService studentService, WebClient.Builder webClient) {
        this.studentService = studentService;
        this.webClient = webClient;
    }

    private final String base_url = "http://client-b-service/all";

    @GetMapping("/all")
    public List<Student> all() throws Exception {

        List<Student> list = List.of(Objects.requireNonNull(webClient.build().get().uri(base_url)
                .retrieve().bodyToMono(Student[].class).block()));

        System.out.println(list + " : this is list");
        if (!list.isEmpty()) return list;

        throw new Exception("Such Service Doesn't exist !");
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<Student> all(@PathVariable("id") Integer id) throws Exception {

        Student student = webClient.build().get().uri(base_url + "/" + id)
                        .retrieve().bodyToMono(Student.class).block();

        if (student != null && student.getId() != null) {
            return  ResponseEntity.ok().body(student);
        }
        return ResponseEntity.notFound().build();
    }
}

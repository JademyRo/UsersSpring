package ro.jademy.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class HelloController {

    @RequestMapping(path = "/hello", method = RequestMethod.GET) // -> localhost:8080/hello
    public String hello() {
        return "hello";
    }

    @GetMapping(path = "/names") // -> localhost:8080/names
    public List<String> getNames() {
        return Arrays.asList("Gigi", "Ion", "Maria");
    }

}

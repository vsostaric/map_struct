package app.controller;

import app.model.Person;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class PersonController {

  @RequestMapping("/getPerson")
  public Person getPerson() {
    return new Person();
  }

}

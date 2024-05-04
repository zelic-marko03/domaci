package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class  EmployeeRestController {

    @Autowired
    private EmployeeService employeeService;

    /*
     * Prilikom poziva metoda potrebno je navesti nekoliko parametara
     * unutar @GetMapping anotacije: url kao vrednost 'value' atributa (ukoliko se
     * izostavi, ruta do metode je ruta do kontrolera), u slucaju GET zahteva
     * atribut 'produce' sa naznakom tipa odgovora (u nasem slucaju JSON).
     *
     * Kao povratna vrednost moze se vracati klasa ResponseEntity koja sadrzi i telo
     * (sam podatak) i zaglavlje (metapodatke) i status kod, ili samo telo ako se
     * metoda anotira sa @ResponseBody.
     *
     * url: /api/ GET
     */
    @GetMapping(value = "/api/")
    public String welcome(){
        return "Hello from api!";
    }

    @GetMapping(value = "/api/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> getEmployees(){
        List<Employee> employeeList = employeeService.findAll();
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    /*
     * U viticastim zagradama se navodi promenljivi deo putanje.
     *
     * url: /api/employees/1 GET
     */
    @GetMapping(value = "/api/employees/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> getEmployee(@PathVariable(name = "id") Long id){
        Employee employee = employeeService.findOne(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    /*
     * Prilikom poziva metoda potrebno je navesti nekoliko parametara
     * unutar @PostMappimng anotacije: url kao vrednost 'value' atributa (ukoliko se
     * izostavi, ruta do metode je ruta do kontrolera), u slucaju POST zahteva
     * atribut 'produces' sa naznakom tipa odgovora (u nasem slucaju JSON) i atribut
     * consumes' sa naznakom oblika u kojem se salje podatak (u nasem slucaju JSON).
     *
     * Anotiranjem parametra sa @RequestBody Spring ce pokusati od prosledjenog JSON
     * podatka da napravi objekat tipa Greeting.
     *
     * url: /api/employee POST
     */
    @PostMapping(value = "/api/employee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        Employee saved = employeeService.save(employee);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    /*
     * url: /api/employee/1 PUT
     */


    /*
     * url: /api/employee/1 DELETE
     */
}

package NewSales.controllers;


import NewSales.entities.Client;
import NewSales.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clients")
@Transactional
public class ClientController {

    @Autowired
    private ClientRepository c;

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public @ResponseBody List<Client> findAll(){
        return c.findAll();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Integer id){
        return c.findById(id)
                .orElseThrow
                        (() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Cliente não encontrado"));
    }


    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Client save(@Valid @RequestBody Client cl) {
        return c.save(cl);}


    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete (@PathVariable Integer id) {
        c.findById(id)
                .map(client -> {c.delete(client);
                return client;})
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado")); }



    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void update (@PathVariable Integer id, @Valid @RequestBody Client cl) {
        c.findById(id)
                .map(existisClient -> {
                    cl.setId(existisClient.getId());
                    c.save(cl);
                    return existisClient;})
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado")); }



    @GetMapping("/find")
    public List<Client> find (Client cl) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example ex = Example.of(cl, matcher);
        return c.findAll(ex); }

    }




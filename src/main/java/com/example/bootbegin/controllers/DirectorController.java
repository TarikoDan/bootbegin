package com.example.bootbegin.controllers;

import com.example.bootbegin.entiti.Director;
import com.example.bootbegin.services.IDirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/directors")
public class DirectorController {

    @Autowired
    private IDirectorService directorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Director save(@RequestBody @Valid Director director) {
        return directorService.insert(director);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Director getById (@PathVariable int id) {
        return directorService.getById(id);
    }

    @GetMapping("movie/{movieTitle}")
    @ResponseStatus(HttpStatus.FOUND)
    public Director getByMovieTitle (@PathVariable String movieTitle) {
        return directorService.getByMovieTitle(movieTitle);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<Director> getAll() {
        return directorService.getAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Director edit(@PathVariable int id, @RequestBody @Valid Director director) {
        return directorService.edit(id, director);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clear() {
        directorService.clear();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id) {
        directorService.deleteById(id);
    }

}

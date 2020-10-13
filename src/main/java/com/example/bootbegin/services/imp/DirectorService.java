package com.example.bootbegin.services.imp;

import com.example.bootbegin.dao.DirectorDAO;
import com.example.bootbegin.entiti.Director;
import com.example.bootbegin.services.IDirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorService implements IDirectorService {

    @Autowired
    DirectorDAO directorDAO;

    @Override
    public Director insert(Director director) {
        return directorDAO.save(director);
    }

    @Override
    public List<Director> getAll() {
        return directorDAO.findAll();
    }

    @Override
    public Director getById(int id) {
        return directorDAO.findById(id).orElseThrow(() ->
                new NullPointerException("There is no such Director with id = " + id));
    }

    @Override
    public Director edit(int id, Director director) {
        if (directorDAO.existsById(id)){
            director.setId(id);
            return directorDAO.saveAndFlush(director);
        }else {
            throw new NullPointerException("There is no such Director with id = " + id);
        }
    }

    @Override
    public void deleteById(int id) {
        if (directorDAO.existsById(id)){
            directorDAO.deleteById(id);
        }else {
            throw new NullPointerException("There is no such Director with id = " + id);
        }

    }

    @Override
    public void clear() {
        directorDAO.deleteAll();
    }
}

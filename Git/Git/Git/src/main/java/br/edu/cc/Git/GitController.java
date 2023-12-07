package br.edu.cc.Git;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GitController {

    @Autowired GitDao dao;

    @PostMapping("/git")
    public Git incluir(@RequestBody Git g) throws Exception {
        return dao.incluir(g);
    }

    @GetMapping("/git")
    public Retorno listar() throws Exception{
        try{
            return new Retorno(dao.listar());
        } catch(Exception e){
            return new Retorno(e.getMessage());
        }
    }
}

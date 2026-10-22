package br.com.fiap.gamavault.controller;

import br.com.fiap.gamavault.entity.Jogo;
import br.com.fiap.gamavault.service.JogoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/jogos")
public class JogoController {

    private JogoService jogoService;

    public JogoController(JogoService jogoService) {
        this.jogoService = jogoService;
    }

    @PostMapping
    public ResponseEntity<Jogo> cadastrar(@RequestBody @Valid Jogo jogo, UriComponentsBuilder builder){
        jogoService.cadastrar(jogo);
        URI uri = builder.path("/api/jogos/{id}").buildAndExpand(jogo.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(jogo);
    }

    @GetMapping
    public List<Jogo> listar(){
        return jogoService.listar();
    }

    @GetMapping("/{id}")
    public Jogo buscar(@PathVariable Long id){
        return jogoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Jogo atualizar(@PathVariable Long id, @RequestBody @Valid Jogo jogo){
        return jogoService.atualizar(jogo, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id){
        jogoService.remover(id);
    }

}

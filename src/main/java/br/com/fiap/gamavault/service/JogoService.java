package br.com.fiap.gamavault.service;

import br.com.fiap.gamavault.entity.Jogo;
import br.com.fiap.gamavault.exception.ConflitoException;
import br.com.fiap.gamavault.exception.EntidadeNaoEncontradaException;
import br.com.fiap.gamavault.repository.JogoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JogoService {
    private JogoRepository jogoRepository;

    // Construtor que recebe o Repository por injeção de  dependencia
    public JogoService(JogoRepository jogoRepository) {
        this.jogoRepository = jogoRepository;
    }

    public Jogo cadastrar(Jogo jogo){
        if(jogoRepository.existsByTituloIgnoreCase(jogo.getTitulo()))
            throw new ConflitoException("Titulo já existe no banco");
        jogo.setCodigo(null);
        return jogoRepository.save(jogo);
    }

    public List<Jogo> listar(){
        return jogoRepository.findAll();
    }

    public Jogo buscarPorId(Long id){
//        Optional<Jogo> op = jogoRepository.findById(id);
//        if (op.isPresent())
//            return op.get();
//        throw new EntidadeNaoEncontradaException("Jogo não encontrado!");
        return jogoRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Jogo não encontrado"));
    }

    public Jogo atualizar(Jogo jogo, Long id){
        validarId(id);
        jogo.setCodigo(id);
        if (jogoRepository.existsByTituloIgnoreCaseAndCodigoNot(jogo.getTitulo(), id))
            throw new ConflitoException("Nome do jogo já existe");

        return jogoRepository.save(jogo);
    }

    public void remover(Long id){
        validarId(id);
        jogoRepository.deleteById(id);
    }

    private void validarId(Long id){
        if (!jogoRepository.existsById(id))
            throw new EntidadeNaoEncontradaException("Jogo não encontrado");
    }
}

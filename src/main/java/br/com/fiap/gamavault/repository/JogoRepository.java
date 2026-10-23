package br.com.fiap.gamavault.repository;

import br.com.fiap.gamavault.entity.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;
                                        //Tabela, Tipo da PK
public interface JogoRepository extends JpaRepository<Jogo, Long> {

    boolean existsByTituloIgnoreCase(String titulo);

    boolean existsByTituloIgnoreCaseAndCodigoNot(String titulo, Long codigo);
}



package br.com.fiap.gamavault.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name="tb_jogo")
public class Jogo {
    
    @Id
    @Column(name="cd_jogo")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "jogo")
    @SequenceGenerator(name = "jogo", sequenceName = "sq_tb_jogo", allocationSize = 1)
    private Long codigo;

    @Column(name="nm_titulo", nullable = false, length = 100)
    @NotBlank(message = "Titulo obrigatório")
    @Size(max = 100)
    private String titulo;


    @Column(name="ds_plataforma", nullable = false, length = 50)
    @NotBlank(message = "Plataforma obrigatória")
    @Size(max = 50)
    private String plataforma;

    @Column(name="nr_nota")
    @DecimalMax(value = "10")
    @DecimalMin(value = "0", message = "Nota não pode ser negativa")
    private Double nota;

    @Column(name="dt_lancamento")
    private LocalDate dataLancamento;

    @Column(name="st_zerado")
    private boolean zerado;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public boolean isZerado() {
        return zerado;
    }

    public void setZerado(boolean zerado) {
        this.zerado = zerado;
    }
}

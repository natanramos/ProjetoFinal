package com.curso.betha.projetofinal.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by NatanRamos on 30/09/2016.
 */
public class Debitos {

    private Long id;
    private Long idCompetencias;
    private Long idPessoas;
    private Date dataVencimento;
    private BigDecimal valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCompetencias() {
        return idCompetencias;
    }

    public void setIdCompetencias(Long idCompetencias) {
        this.idCompetencias = idCompetencias;
    }

    public Long getIdPessoas() {
        return idPessoas;
    }

    public void setIdPessoas(Long idPessoas) {
        this.idPessoas = idPessoas;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}

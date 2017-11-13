package com.magossi.apisimb.domain.matriz;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.magossi.apisimb.domain.bovino.Bovino;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by RafaelMq on 04/11/2016.
 */

@Entity
public class Inseminacao {


    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInseminacao;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean monta;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String touro;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date dataDaInseminacao;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String imei;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String matriz;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date previsaoParto;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date dataInclusao = new Date();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean status;

    public String getMatriz() {
        return matriz;
    }

    public void setMatriz(String matriz) {
        this.matriz = matriz;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Long getIdInseminacao() {
        return idInseminacao;
    }

    public void setIdInseminacao(Long idInseminacao) {
        this.idInseminacao = idInseminacao;
    }

    public Boolean getMonta() {
        return monta;
    }

    public void setMonta(Boolean monta) {
        this.monta = monta;
    }

    public String getTouro() {
        return touro;
    }

    public void setTouro(String touro) {
        this.touro = touro;
    }

    public Date getDataDaInseminacao() {
        return dataDaInseminacao;
    }

    public void setDataDaInseminacao(Date dataDaInseminacao) {
        this.dataDaInseminacao = dataDaInseminacao;
    }





    public Date getPrevisaoParto() {
        return previsaoParto;
    }

    public void setPrevisaoParto(Date previsaoParto) {
        this.previsaoParto = previsaoParto;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}

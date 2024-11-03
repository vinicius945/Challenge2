package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;

public class ProblemaTO {
    private Long IdProblema;
    @NotBlank private String Descricao;

    public ProblemaTO() {
    }

    public ProblemaTO(Long idProblema, String descricao) {
        IdProblema = idProblema;
        Descricao = descricao;
    }

    public Long getIdProblema() {
        return IdProblema;
    }

    public void setIdProblema(Long idProblema) {
        IdProblema = idProblema;
    }

    public @NotBlank String getDescricao() {
        return Descricao;
    }

    public void setDescricao(@NotBlank String descricao) {
        Descricao = descricao;
    }
}

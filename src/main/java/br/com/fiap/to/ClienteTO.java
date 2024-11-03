package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;

public class ClienteTO {

   private Long IdCliente;
   @NotBlank private String nome;
   @NotBlank private String cpf;
   @NotBlank private String genero;
   @NotBlank private String MotivoDeContato;

   public ClienteTO () {}

    public ClienteTO(Long IdCliente, String nome, String cpf, String genero, String motivoDeContato) {
        this.IdCliente = IdCliente;
        this.nome = nome;
        this.cpf = cpf;
        this.genero = genero;
        MotivoDeContato = motivoDeContato;
    }

    public Long getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(Long IdCliente) {
        this.IdCliente = IdCliente;
    }

    public @NotBlank String getNome() {
        return nome;
    }

    public void setNome(@NotBlank String nome) {
        this.nome = nome;
    }

    public @NotBlank String getCpf() {
        return cpf;
    }

    public void setCpf(@NotBlank String cpf) {
        this.cpf = cpf;
    }

    public @NotBlank String getGenero() {
        return genero;
    }

    public void setGenero(@NotBlank String genero) {
        this.genero = genero;
    }

    public @NotBlank String getMotivoDeContato() {
        return MotivoDeContato;
    }

    public void setMotivoDeContato(@NotBlank String motivoDeContato) {
        MotivoDeContato = motivoDeContato;
    }
}

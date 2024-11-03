package br.com.fiap.to;

public class VeiculoTO {
    private Long IdVeiculo;
    private String PlacaCarro;
    private String modelo;
    private long IdProblema;

    public VeiculoTO() {
    }

    public VeiculoTO(Long idVeiculo, String placaCarro, String modelo, long idProblema) {
        IdVeiculo = idVeiculo;
        PlacaCarro = placaCarro;
        this.modelo = modelo;
        IdProblema = idProblema;
    }

    public Long getIdVeiculo() {
        return IdVeiculo;
    }

    public void setIdVeiculo(Long idVeiculo) {
        IdVeiculo = idVeiculo;
    }

    public String getPlacaCarro() {
        return PlacaCarro;
    }

    public void setPlacaCarro(String placaCarro) {
        PlacaCarro = placaCarro;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public long getIdProblema() {
        return IdProblema;
    }

    public void setIdProblema(long idProblema) {
        IdProblema = idProblema;
    }
}

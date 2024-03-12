package app.condominio.service;

import app.condominio.domain.Ocorrencia;

import java.util.List;

public interface OcorrenciaService{

    public void criarOcorrencia(Ocorrencia entidade);

    public Ocorrencia lerOcorrencia(Long id);

    public void editarOcorrencia(Ocorrencia entidade);

    public void excluirOcorrencia(Ocorrencia entidade);

    public List<Ocorrencia> listarOcorrencias(Long id);


}

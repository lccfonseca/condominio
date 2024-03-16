package app.condominio.service;

import app.condominio.domain.Ocorrencia;

public interface OcorrenciaService extends CrudService<Ocorrencia, Long> {

    public Ocorrencia visualizar(Long id);

}

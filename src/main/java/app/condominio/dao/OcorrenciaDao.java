package app.condominio.dao;

import app.condominio.domain.Condominio;
import app.condominio.domain.Ocorrencia;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;
import java.util.List;

public interface OcorrenciaDao extends PagingAndSortingRepository<Ocorrencia, Long> {
    List<Ocorrencia> findAllByEstadoInOrderByDataRegistroDescHoraRegistroDesc(Collection<Integer> estado);

    List<Ocorrencia> findAllByIdCondominioInOrderByDataRegistroDescHoraRegistroDesc(Collection<Condominio> condominio);

}

package app.condominio.dao;

import app.condominio.domain.Condominio;
import app.condominio.domain.Ocorrencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;
import java.util.List;

public interface OcorrenciaDao extends PagingAndSortingRepository<Ocorrencia, Long> {

    Ocorrencia findOcorrenciaByIdOcorrencia(Long id);

    List<Ocorrencia> findAllByEstadoInOrderByDataRegistroDescHoraRegistroDesc(Collection<Integer> estado);

    Page<Ocorrencia> findAllByCondominioOrderByDataRegistroDescHoraRegistroDesc(Condominio condominio, Pageable pagina);

}

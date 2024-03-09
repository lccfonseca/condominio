package app.condominio.dao;

import app.condominio.domain.Condominio;
import app.condominio.domain.Ocorrencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

public interface OcorrenciaDao extends PagingAndSortingRepository<Ocorrencia, Long> {
    List<Ocorrencia> findAllByAtivoInOrderByData_registroDescHora_registroDesc(Collection<Boolean> ativo);

    List<Ocorrencia> findAllByCondominioInOrderByData_registroDescHora_registroDesc(Condominio condominio);

    Page<Ocorrencia> findAllByCondominioInOrderByData_registroDescHora_registroDesc(Condominio condominio, Pageable pagina);
}

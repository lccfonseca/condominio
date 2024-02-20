package app.condominio.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import app.condominio.domain.Condominio;
import app.condominio.domain.Visitante;

public interface VisitanteDao extends PagingAndSortingRepository<Visitante, Long>{
      
   Boolean existsByNomeAndCondominio(String nome, Condominio condominio);

	Boolean existsByNomeAndCondominioAndIdVisitanteNot(String nome, Condominio condominio, Long idVisitante);

   Boolean existsByCpfAndCondominio(String cpf, Condominio condominio);

	Boolean existsByCpfAndCondominioAndIdVisitanteNot(String cpf, Condominio condominio, Long idVisitante);

	Page<Visitante> findAllByCondominioOrderByNomeAsc(Condominio condominio, Pageable pagina);
   
}
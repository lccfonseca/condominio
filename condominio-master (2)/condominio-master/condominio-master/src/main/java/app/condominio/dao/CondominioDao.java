package app.condominio.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import app.condominio.domain.Condominio;

public interface CondominioDao extends PagingAndSortingRepository<Condominio, Long> {

	Boolean existsByCnpj(String cnpj);

	Boolean existsByCnpjAndIdCondominioNot(String cnpj, Long idCondominio);

}

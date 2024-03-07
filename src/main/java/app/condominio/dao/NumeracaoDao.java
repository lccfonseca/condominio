package app.condominio.dao;

import app.condominio.domain.Condominio;
import app.condominio.domain.Moradia;
import app.condominio.domain.Numeracao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NumeracaoDao extends PagingAndSortingRepository<Numeracao, String> {
    
    Page<Numeracao> findAllByMoradiaOrderById(Moradia moradia, Pageable pagina);
    
}

package app.condominio.dao;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import app.condominio.domain.Conta;
import app.condominio.domain.Movimento;

public interface MovimentoDao extends PagingAndSortingRepository<Movimento, Long> {

	List<Movimento> findAllByContaInOrderByDataDesc(Collection<Conta> conta);

	Page<Movimento> findAllByContaInOrderByDataDesc(Collection<Conta> conta, Pageable pageable);

	List<Movimento> findAllByContaInAndDataBetweenOrderByDataAsc(Collection<Conta> conta, LocalDate inicio,
			LocalDate fim);

}

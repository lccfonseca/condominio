package app.condominio.dao;

import app.condominio.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import app.condominio.domain.Condominio;
import app.condominio.domain.Pet;

public interface PetDao extends PagingAndSortingRepository<Pet, Long> {
    Page<Pet> findAllByCondominioOrderByNome(Condominio condominio, Pageable pagina);

}

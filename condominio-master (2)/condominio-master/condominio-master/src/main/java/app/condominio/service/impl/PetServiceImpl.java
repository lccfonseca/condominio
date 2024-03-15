package app.condominio.service.impl;

import app.condominio.dao.PessoaDao;
import app.condominio.dao.PetDao;
import app.condominio.domain.*;
import app.condominio.service.PetService;
import app.condominio.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PetServiceImpl implements PetService {

    @Autowired
    private PetDao petDao;

    @Autowired
    private PessoaDao pessoaDao;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public void salvar(Pet entidade) {
        if (entidade.getIdPet() == null) {
            padronizar(entidade);
            petDao.save(entidade);
        }
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Pet ler(Long id) {
        return petDao.findById(id).get();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Pet> listar() {
        Condominio condominio = usuarioService.lerLogado().getCondominio();
        if (condominio == null) {
            return new ArrayList<>();
        }
        return condominio.getPet();
    }

    @Override
    public Page<Pet> listarPagina(Pageable pagina) {
        Condominio condominio = usuarioService.lerLogado().getCondominio();
        if (condominio == null) {
            return Page.empty(pagina);
        }
        return petDao.findAllByCondominioOrderByNome(condominio, pagina);
    }


    @Override
    public void editar(Pet entidade) {
        padronizar(entidade);
        petDao.save(entidade);
    }

    @Override
    public void excluir(Pet entidade) {
        petDao.delete(entidade);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public void validar(Pet pet, BindingResult validacao) {
        if (pet.getIdPet() == null) {
            if (pet.getTipo_Pet() == null) {
                validacao.rejectValue("tipo", "NotNull");
            }
        }

        if (pet.getNascimento().isAfter(LocalDate.now())) {
            validacao.rejectValue("idade", "Min");
        }

        if (pet.getRaca() == null || pet.getRaca().isEmpty()) {
            validacao.rejectValue("raca", "NotEmpty");
        }

    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public void padronizar(Pet pet, Long idPessoa) {
        Pessoa pessoa = new Pessoa();
        if(pet.getDono() == null){
            pet.setDono(pessoaDao.findById(idPessoa).get());
        }
    }

    @Override
    public void padronizar(Pet entidade) {}


}

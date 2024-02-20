package app.condominio.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import app.condominio.dao.VisitanteDao;
import app.condominio.domain.Condominio;
import app.condominio.domain.Visitante;
import app.condominio.service.UsuarioService;
import app.condominio.service.VisitanteService;

@Service
@Transactional
public class VisitanteServiceImpl implements VisitanteService {
   
   @Autowired
   private VisitanteDao visitanteDao;

   @Autowired
   private UsuarioService usuarioService;

   @Override
   public void salvar(Visitante entidade){
      if(entidade.getIdvisitante()==null){
         padronizar(entidade);
         visitanteDao.save(entidade);
      }
   }

   @Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Visitante ler(Long id) {
		return visitanteDao.findById(id).get();
	}

   @Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Visitante> listar() {
		Condominio condominio = usuarioService.lerLogado().getCondominio();
		if (condominio == null) {
			return new ArrayList<>();
		}
		return condominio.getVisitantes();
	}

   @Override
	public Page<Visitante> listarPagina(Pageable pagina) {
		Condominio condominio = usuarioService.lerLogado().getCondominio();
		if (condominio == null) {
			return Page.empty(pagina);
		}
		return visitanteDao.findAllByCondominioOrderByNomeAsc(condominio, pagina);
	}

   
	@Override
	public void editar(Visitante entidade) {
		padronizar(entidade);
		visitanteDao.save(entidade);
	}

   @Override
	public void excluir(Visitante entidade) {
		visitanteDao.delete(entidade);
	}

   

   @Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public void validar(Visitante entidade, BindingResult validacao) {
		// VALIDAÇÕES NA INCLUSÃO
		if (entidade.getIdvisitante() == null) {
			// Cpf não pode repetir
			if (visitanteDao.existsByCpfAndCondominio(entidade.getCpf(), usuarioService.lerLogado().getCondominio()) && entidade.getCpf()!=null) {
				validacao.rejectValue("cpf", "Unique");
			}
		}  
		// VALIDAÇÕES NA ALTERAÇÃO
		else {
			// Cpf não pode repetir
			if (visitanteDao.existsByCpfAndCondominioAndIdVisitanteNot(entidade.getCpf(),
					usuarioService.lerLogado().getCondominio(), entidade.getIdvisitante()) && entidade.getCpf()!=null) {
				validacao.rejectValue("cpf", "Unique");
			}
		}
		// VALIDAÇÕES EM AMBOS
	}

   @Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public void padronizar(Visitante entidade) {
		if (entidade.getCondominio() == null) {
			entidade.setCondominio(usuarioService.lerLogado().getCondominio());
		}
	}
   
}

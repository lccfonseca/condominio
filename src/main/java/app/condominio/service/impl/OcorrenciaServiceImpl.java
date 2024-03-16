package app.condominio.service.impl;

import app.condominio.dao.OcorrenciaDao;
import app.condominio.domain.Condominio;
import app.condominio.domain.Ocorrencia;
import app.condominio.domain.enums.EstadoOcorrencia;
import app.condominio.service.OcorrenciaService;
import app.condominio.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OcorrenciaServiceImpl implements OcorrenciaService {

    @Autowired
    private OcorrenciaDao ocorrenciaDao;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public void salvar(Ocorrencia entidade){
        ocorrenciaDao.save(entidade);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Ocorrencia visualizar(Long id){
        Ocorrencia ocorrencia = ocorrenciaDao.findById(id).get();
        ocorrencia.setEstado(EstadoOcorrencia.A);
        ocorrenciaDao.save(ocorrencia);
        return ocorrencia;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Ocorrencia ler(Long id){
        return ocorrenciaDao.findOcorrenciaByIdOcorrencia(id);
    }

    @Override
    public void editar(Ocorrencia entidade){
        ocorrenciaDao.save(entidade);
    }

    @Override
    public void excluir(Ocorrencia entidade){
        ocorrenciaDao.delete(entidade);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Ocorrencia> listar(){
        Condominio condominio = usuarioService.lerLogado().getCondominio();
        if(condominio == null){
            return new ArrayList<>();
        }
        return condominio.getOcorrencias();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Page<Ocorrencia> listarPagina(Pageable pagina){
        Condominio condominio = usuarioService.lerLogado().getCondominio();
        if(condominio == null){
            return Page.empty(pagina);
        }
        return ocorrenciaDao.findAllByCondominioOrderByDataRegistroDescHoraRegistroDesc(condominio, pagina);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public void validar(Ocorrencia entidade, BindingResult validacao){

    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public void padronizar(Ocorrencia entidade) {

    }

}

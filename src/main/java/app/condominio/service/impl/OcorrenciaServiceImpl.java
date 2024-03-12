package app.condominio.service.impl;

import app.condominio.dao.OcorrenciaDao;
import app.condominio.domain.Condominio;
import app.condominio.domain.Ocorrencia;
import app.condominio.service.CondominioService;
import app.condominio.service.OcorrenciaService;
import app.condominio.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OcorrenciaServiceImpl implements OcorrenciaService {

    @Autowired
    private OcorrenciaDao ocorrenciaDao;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CondominioService condominioService;

    @Override
    public void criarOcorrencia(Ocorrencia entidade){
        if(entidade.getIdOcorrencia() == null){
            ocorrenciaDao.save(entidade);
        }
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Ocorrencia lerOcorrencia(Long id){
        return ocorrenciaDao.findById(id).get();
    }

    @Override
    public void editarOcorrencia(Ocorrencia entidade){
        ocorrenciaDao.save(entidade);
    }

    @Override
    public void excluirOcorrencia(Ocorrencia entidade){
        ocorrenciaDao.delete(entidade);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Ocorrencia> listarOcorrencias(Long id){
        Condominio condominio = usuarioService.lerLogado().getCondominio();
        if(condominio == null){
            return new ArrayList<>();
        }
        return ocorrenciaDao.findAllByIdCondominioInOrderByDataRegistroDescHoraRegistroDesc(condominioService.listar());
    }
}

package app.condominio.controller;

import app.condominio.service.CondominioService;
import app.condominio.service.OcorrenciaService;
import app.condominio.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sindico/ocorrencias")
public class OcorrenciaController {

    @Autowired
    private OcorrenciaService ocorrenciaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CondominioService condominioService;

    //@ModelAttribute

}

package app.condominio.controller;

import app.condominio.domain.*;
import app.condominio.domain.enums.EstadoOcorrencia;
import app.condominio.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("sindico/ocorrencias")
public class OcorrenciaController {

    @Autowired
    private BlocoService blocoService;

    @Autowired
    private MoradiaService moradiaService;

    @Autowired
    private OcorrenciaService ocorrenciaService;

    @Autowired
    private UsuarioService usuarioService;

    @ModelAttribute("ativo")
    public String[] ativo() {
        return new String[] {"condominio", "ocorrencias"};
    }

    @ModelAttribute("estados")
    public EstadoOcorrencia[] estado(){
        return EstadoOcorrencia.values();
    }

    @ModelAttribute("blocos")
    public List<Bloco> blocos(){return blocoService.listar();}

    @ModelAttribute("moradias")
    public List<Moradia> moradias() {
        return moradiaService.listar();
    }

    // Página para listar todas as ocorrências
    @GetMapping({"", "/", "lista"})
    public ModelAndView getOcorrencias(@RequestParam("pagina") Optional<Integer> pagina,
                                       @RequestParam("tamanho") Optional<Integer> tamanho, ModelMap model){
        model.addAttribute("ocorrencias", ocorrenciaService.listarPagina(PageRequest.of(pagina.orElse(1) - 1, tamanho.orElse(20))));
        model.addAttribute("conteudo", "ocorrenciaLista");
        return new ModelAndView("fragmentos/layoutSindico", model);
    }

    // Página para cadastrar nova ocorrência
    @GetMapping("/cadastro")
    public ModelAndView getOcorrenciaCadastro(@ModelAttribute("ocorrencia") Ocorrencia ocorrencia,
                                              ModelMap model){
        ocorrencia.setEstado(EstadoOcorrencia.N);
        model.addAttribute("conteudo", "ocorrenciaCadastro");
        return new ModelAndView("fragmentos/layoutSindico", model);
    }

    // Página para visualizar ocorrência
    @GetMapping("/{idOcorrencia}/view")
    public ModelAndView viewOcorrencia(@PathVariable("idOcorrencia") Long idOcorrencia, ModelMap model) {
        Ocorrencia ocorrencia = ocorrenciaService.visualizar(idOcorrencia);
        model.addAttribute("ocorrencia", ocorrencia);
        model.addAttribute("conteudo", "ocorrenciaCadastro");
        return new ModelAndView("fragmentos/layoutSindico", model);
    }

    // Página de editar ocorrência
    @GetMapping("/{idOcorrencia}/cadastro")
    public ModelAndView getOcorrenciaEditar(@PathVariable("idOcorrencia") Long idOcorrencia, ModelMap model) {
        Ocorrencia ocorrencia = ocorrenciaService.ler(idOcorrencia);
        model.addAttribute("ocorrencia", ocorrencia);
        model.addAttribute("conteudo", "ocorrenciaCadastro");
        return new ModelAndView("fragmentos/layoutSindico", model);
    }

    // Altera o estado das ocorrências entre Ativas ou Finalizadas
    @GetMapping("/{idOcorrencia}/togglecheck")
    public ModelAndView checkOcorrencia(@PathVariable("idOcorrencia") Long idOcorrencia){
        Ocorrencia ocorrencia = ocorrenciaService.ler(idOcorrencia);
        if(ocorrencia.getEstado() == EstadoOcorrencia.F){
            ocorrencia.setEstado(EstadoOcorrencia.A);
        }else{
            ocorrencia.setEstado(EstadoOcorrencia.F);
        }
        ocorrenciaService.editar(ocorrencia);
        return new ModelAndView("redirect:/sindico/ocorrencias");
    }


    // POST para cadastro de ocorrência
    @PostMapping("/cadastro")
    public ModelAndView postOcorrenciaCadastro(@Valid @ModelAttribute("ocorrencia") Ocorrencia ocorrencia,
                                               BindingResult validacao){
        ocorrencia.setCondominio(usuarioService.lerLogado().getCondominio());
        ocorrencia.setIdAutor(usuarioService.lerLogado());
        ocorrencia.setDataRegistro(LocalDate.now());
        ocorrencia.setHoraRegistro(LocalTime.now());
        ocorrencia.setEstado(EstadoOcorrencia.N);
        if((ocorrencia.getMotivo() == null) || (ocorrencia.getMotivo().length() > 45)){
            return new ModelAndView("fragmentos/layoutSindico", "conteudo", "ocorrenciaCadastro");
        }
        ocorrenciaService.salvar(ocorrencia);
        return new ModelAndView("redirect:/sindico/ocorrencias");
    }

    // POST para editar ocorrência
    @PostMapping("/editar")
    public ModelAndView postOcorrenciaEditar(@ModelAttribute("ocorrencia") Ocorrencia ocorrencia,
                                             BindingResult validacao) {
        ocorrencia.setCondominio(usuarioService.lerLogado().getCondominio());
        ocorrencia.setIdAutor(usuarioService.lerLogado());
        ocorrencia.setDataRegistro(LocalDate.now());
        ocorrencia.setHoraRegistro(LocalTime.now());
        ocorrencia.setEstado(EstadoOcorrencia.N);
        if((ocorrencia.getMotivo() == null) || (ocorrencia.getMotivo().length() > 45)){
            return new ModelAndView("fragmentos/layoutSindico", "conteudo", "ocorrenciaCadastro");
        }
        ocorrenciaService.editar(ocorrencia);
        return new ModelAndView("redirect:/sindico/ocorrencias");
    }

    // POST para deletar ocorrência
    // O deletemapping não funcionou (?)
    @PostMapping("/excluir")
    public ModelAndView deleteOcorrenciaCadastro(@RequestParam("idObj") Long idObj) {
        ocorrenciaService.excluir(ocorrenciaService.ler(idObj));
        return new ModelAndView("redirect:/sindico/ocorrencias");
    }

}

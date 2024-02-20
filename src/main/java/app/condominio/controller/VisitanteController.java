package app.condominio.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import app.condominio.domain.Visitante;
import app.condominio.domain.enums.Dias;
import app.condominio.service.VisitanteService;

@Controller
@RequestMapping("sindico/visitantes")
public class VisitanteController {
   
   @Autowired
   private VisitanteService visitanteService;

   @ModelAttribute("ativo")
	public String[] ativo() {
		return new String[] { "condominio", "visitantes" };
	}

   @ModelAttribute("dias")
	public Dias[] dias() {
		return Dias.values();
	}


   @GetMapping({ "", "/", "/lista" })
	public ModelAndView getVisitantes(@RequestParam("pagina") Optional<Integer> pagina,
			@RequestParam("tamanho") Optional<Integer> tamanho, ModelMap model) {
		model.addAttribute("visitantes",
				visitanteService.listarPagina(PageRequest.of(pagina.orElse(1) - 1, tamanho.orElse(20))));
		model.addAttribute("conteudo", "visitanteLista");
		return new ModelAndView("fragmentos/layoutSindico", model);
	}

   @GetMapping("/cadastro")
	public ModelAndView getVisitanteCadastro(@ModelAttribute("visitante") Visitante visitante) {
      
		return new ModelAndView("fragmentos/layoutSindico", "conteudo", "visitanteCadastro");
	}

   @GetMapping("/{idVisitante}/cadastro")
	public ModelAndView getVisitanteEditar(@PathVariable("idVisitante") Long idVisitante, ModelMap model) {
		model.addAttribute("visitante", visitanteService.ler(idVisitante));
		model.addAttribute("conteudo", "visitanteCadastro");
		return new ModelAndView("fragmentos/layoutSindico", model);
	}

   @PostMapping("/cadastro")
	public ModelAndView putVisitanteCadastro(@Valid @ModelAttribute("visitante") Visitante visitante, BindingResult validacao) {
		visitanteService.validar(visitante, validacao);
		if (validacao.hasErrors()) {
			visitante.setIdvisitante(null);
			return new ModelAndView("fragmentos/layoutSindico", "conteudo", "visitanteCadastro");
		}
		visitanteService.salvar(visitante);
		return new ModelAndView("redirect:/sindico/visitantes");
	}

   @PutMapping("/cadastro")
	public ModelAndView postVisitanteCadastro(@Valid @ModelAttribute("visitante") Visitante visitante, BindingResult validacao) {
		visitanteService.validar(visitante, validacao);
		if (validacao.hasErrors()) {
			return new ModelAndView("fragmentos/layoutSindico", "conteudo", "visitanteCadastro");
		}
		visitanteService.editar(visitante);
		return new ModelAndView("redirect:/sindico/visitantes");
	}

   @DeleteMapping("/excluir")
	public ModelAndView deleteVisitanteCadastro(@RequestParam("idObj") Long idObj) {
		visitanteService.excluir(visitanteService.ler(idObj));
		return new ModelAndView("redirect:/sindico/visitantes");
	}

}

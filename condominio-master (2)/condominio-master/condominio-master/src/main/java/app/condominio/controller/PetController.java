package app.condominio.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import app.condominio.dao.UsuarioDao;
import app.condominio.domain.Periodo;
import app.condominio.domain.PessoaJuridica;
import app.condominio.domain.Pet;
import app.condominio.domain.Usuario;
import app.condominio.domain.enums.TipoPessoa;
import app.condominio.service.PetService;
import app.condominio.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("sindico/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @ModelAttribute("ativo")
    public String[] ativo() {
        return new String[] { "condominio", "pet"};
    }

    @GetMapping({ "", "/", "/lista" })
    public ModelAndView getPet(@RequestParam("pagina") Optional<Integer> pagina,
                               @RequestParam("tamanho") Optional<Integer> tamanho, ModelMap model) {
        model.addAttribute("pet",
                petService.listarPagina(PageRequest.of(pagina.orElse(1) - 1, tamanho.orElse(20))));
        model.addAttribute("conteudo", "petLista");
        return new ModelAndView("fragmentos/layoutSindico", model);
    }

    @GetMapping("/cadastro")
    public ModelAndView getPetCadastro(@ModelAttribute("pet") Pet pet) {
        return new ModelAndView("fragmentos/layoutSindico", "conteudo", "petCadastro");
    }

    @GetMapping("/{idPet}/cadastro")
    public ModelAndView getPetEditar(@PathVariable("idPet") Long idPet, ModelMap model) {
        model.addAttribute("pet", petService.ler(idPet));
        model.addAttribute("conteudo", "petCadastro");
        return new ModelAndView("fragmentos/layoutSindico", model);
    }

    @PostMapping("/cadastro")
    public ModelAndView postPetCadastro(@Valid @ModelAttribute("pet") Pet pet, BindingResult validacao) {
        petService.validar(pet, validacao);
        if (validacao.hasErrors()) {
            pet.setIdPet(null);
            return new ModelAndView("fragmentos/layoutSindico", "conteudo", "petCadastro");
        }
        petService.salvar(pet);
        return new ModelAndView("redirect:/sindico/pet");
    }

    @PutMapping("/cadastro")
    public ModelAndView putPetCadastro(@Valid @ModelAttribute("pet") Pet pet, BindingResult validacao) {
        petService.validar(pet, validacao);
        if (validacao.hasErrors()) {
            return new ModelAndView("fragmentos/layoutSindico", "conteudo", "petCadastro");
        }
        petService.editar(pet);
        return new ModelAndView("redirect:/sindico/pet");
    }

    @DeleteMapping("/excluir")
    public ModelAndView deletePetCadastro(@RequestParam("idObj") Long idObj) {
        petService.excluir(petService.ler(idObj));
        return new ModelAndView("redirect:/sindico/pet");
    }
}
package dev.paulovieira.estoqueapp.controllers;

import dev.paulovieira.estoqueapp.models.Fornecedor;
import dev.paulovieira.estoqueapp.services.impl.FornecedorServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/fornecedores")
public class FornecedorController {

    private final FornecedorServiceImpl fornecedorService;

    public FornecedorController(FornecedorServiceImpl fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    /**
     * Cria um novo fornecedor.
     *
     * @return mantem o usuário na mesma página
     */
    @GetMapping("/novo")
    public ModelAndView criarNovoFornecedor(ModelMap model) {
        model.addAttribute("fornecedor", new Fornecedor());
        return new ModelAndView("fornecedor/formulario", model);
    }

    /**
     * Salva o fornecedor.
     *
     * @return para a lista de fornecedores
     */
    @PostMapping()
    public String salvarFornecedor(@Valid @ModelAttribute Fornecedor fornecedor, BindingResult result, RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "fornecedor/formulario";
        }

        if (fornecedor.getId() == null) {
            fornecedorService.salvar(fornecedor);
            attr.addFlashAttribute("feedback", "Fornecedor cadastrado com sucesso.");
        } else {
            fornecedorService.salvar(fornecedor);
            attr.addFlashAttribute("feedback", "Fornecedor atualizado com sucesso.");
        }

        return "redirect:/fornecedores";
    }
    
    /**
     * Obtem a lista com todos os fornecedores.
     *
     * @return para a lista de fornecedores
     */
    @GetMapping
    public ModelAndView obterTodosFornecedors(ModelMap model) {
        model.addAttribute("fornecedores", fornecedorService.encontrarTodos());
        return new ModelAndView("fornecedor/lista", model);
    }

    /**
     * Edita o fornecedor.
     *
     * @return para a lista de fornecedores
     */
    @GetMapping("/editar/{id}")
    public ModelAndView editarFornecedor(@PathVariable Long id, ModelMap model) {
        model.addAttribute("fornecedor", fornecedorService.encontrarPorId(id));
        return new ModelAndView("fornecedor/formulario", model);
    }

    /**
     * Inativa o fornecedor.
     *
     * @return para a lista de fornecedores
     */
    @GetMapping("inativar/{id}")
    public String inativarFornecedor(@PathVariable Long id, RedirectAttributes attr) {
        Fornecedor fornecedor = fornecedorService.encontrarPorId(id);
        fornecedor.setAtivo(false);
        fornecedorService.salvar(fornecedor);
        attr.addFlashAttribute("feedback", "Fornecedor ID: " + id + " foi inativado com sucesso.");
        return "redirect:/fornecedores";
    }

    /**
     * Ativa o fornecedor.
     *
     * @return para a lista de fornecedores
     */
    @GetMapping("ativar/{id}")
    public String ativarFornecedor(@PathVariable Long id, RedirectAttributes attr) {
        Fornecedor fornecedor = fornecedorService.encontrarPorId(id);
        fornecedor.setAtivo(true);
        fornecedorService.salvar(fornecedor);
        attr.addFlashAttribute("feedback", "Fornecedor ID: " + id + " foi ativado com sucesso.");
        return "redirect:/fornecedores";
    }

    /**
     * Faz a pergunta se o usuário realmente deseja excluir o fornecedor.
     *
     * @return para a lista de fornecedores
     */
    @GetMapping("excluir/{id}")
    public String excluirFornecedor(@PathVariable Long id, RedirectAttributes attr) {

        attr.addFlashAttribute("excluir",
                "Você tem certeza que deseja excluir o fornecedor ID: " + id + "?");

        // Criando um atributo para o ID do fornecedor para enviar para o ThymeLeaf
        attr.addFlashAttribute("id", id);

        return "redirect:/fornecedores";
    }

    /**
     * Excluir fornecedor apoś confirmação.
     *
     * @return para a lista de fornecedores
     */
    @GetMapping("excluir/sim/{id}")
    public String excluirFornecedorConfirmado(@PathVariable Long id, RedirectAttributes attr) {

        fornecedorService.excluir(id);
        attr.addFlashAttribute("feedback",
                "Fornecedor ID: " + id + " foi excluído com sucesso.");
        return "redirect:/fornecedores";
    }
}

package dev.paulovieira.estoqueapp.controllers;

import dev.paulovieira.estoqueapp.models.Fornecedor;
import dev.paulovieira.estoqueapp.models.NotaEntrada;
import dev.paulovieira.estoqueapp.models.NotaEntradaItem;
import dev.paulovieira.estoqueapp.services.impl.FornecedorServiceImpl;
import dev.paulovieira.estoqueapp.services.impl.NotaEntradaServiceImpl;
import dev.paulovieira.estoqueapp.services.impl.ProdutoServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/nota-entrada")
public class NotaEntradaController {

    NotaEntradaServiceImpl notaEntradaService;
    FornecedorServiceImpl fornecedorService;
    ProdutoServiceImpl produtoService;

    public NotaEntradaController(NotaEntradaServiceImpl notaEntradaService,
                                 FornecedorServiceImpl fornecedorService,
                                 ProdutoServiceImpl produtoService) {
        this.notaEntradaService = notaEntradaService;
        this.fornecedorService = fornecedorService;
        this.produtoService = produtoService;
    }

    @ModelAttribute(name = "notaEntrada")
    public NotaEntrada notaEntrada() {
        return new NotaEntrada();
    }

    @ModelAttribute(name = "fornecedores")
    public List<Fornecedor> fornecedores() {
        return fornecedorService.encontrarTodos();
    }

    @GetMapping
    public ModelAndView obterTodasNotas(ModelMap model) {
        model.addAttribute("notas", notaEntradaService.encontrarTodas());
        model.addAttribute("fornecedores", fornecedores());
        return new ModelAndView("nota-entrada/lista", model);
    }

    @GetMapping("/novo")
    public String novaNotaEntrada() {
        return "nota-entrada/formulario";
    }

    @PostMapping
    public String salvarNotaEntrada(@Valid @ModelAttribute NotaEntrada notaEntrada,
                                    BindingResult result,
                                    RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "nota-entrada/formulario";
        }

        if (notaEntrada.getId() == null) {
            notaEntradaService.salvar(notaEntrada);
            attr.addFlashAttribute("feedback", "Nota de entrada cadastrada com sucesso");
        } else {
            notaEntradaService.atualizar(notaEntrada);
            attr.addFlashAttribute("feedback", "Nota de entrada atualizada com sucesso");
        }

        return "redirect:/nota-entrada";
    }

    @GetMapping("excluir/{id}")
    public String excluirNota(@PathVariable Long id, RedirectAttributes attr) {

        attr.addFlashAttribute("excluir",
                "Você tem certeza que deseja excluir esta nota ?");

        // Criando um atributo para o ID da nota para enviar para o ThymeLeaf
        attr.addFlashAttribute("id", id);

        return "redirect:/nota-entrada";
    }

    /**
     * Excluir nota apoś confirmação.
     *
     * @return para a lista de notas de entrada
     */
    @GetMapping("excluir/sim/{id}")
    public String excluirNotaConfirmado(@PathVariable Long id, RedirectAttributes attr) {

        notaEntradaService.excluir(id);
        attr.addFlashAttribute("feedback",
                "A nota foi excluída com sucesso.");
        return "redirect:/nota-entrada";
    }

    @GetMapping("/{id}/item")
    public ModelAndView produto(@PathVariable("id") Long id, ModelMap model) {
        var notaEntradaItem = new NotaEntradaItem();
        var notaEntrada = notaEntradaService.encontrarPorId(id);
        notaEntradaItem.setNotaEntrada(notaEntrada);
        model.addAttribute("notaEntradaItem", notaEntradaItem);
        model.addAttribute("produtos", produtoService.encontrarTodos());

        return new ModelAndView("nota-entrada-item/formulario", model);
    }
}

package dev.paulovieira.estoqueapp.controllers;

import dev.paulovieira.estoqueapp.models.Produto;
import dev.paulovieira.estoqueapp.models.enums.Categoria;
import dev.paulovieira.estoqueapp.services.impl.ProdutoServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Arrays;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoServiceImpl produtoService;

    public ProdutoController(ProdutoServiceImpl produtoService) {
        this.produtoService = produtoService;
    }

    /**
     * Cria um novo produto.
     *
     * @return mantem o usuário na mesma página
     */
    @GetMapping("/novo")
    public ModelAndView criarNovoProduto(ModelMap model) {
        model.addAttribute("produto", new Produto());
        model.addAttribute("categorias", Arrays.asList(Categoria.values()));
        return new ModelAndView("produto/formulario", model);
    }

    /**
     * Salva o produto.
     *
     * @return para a lista de produtos
     */
    @PostMapping()
    public String salvarProduto(@Valid @ModelAttribute Produto produto, BindingResult result, RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "produto/formulario";
        }

        if (produto.getId() == null) {
            produtoService.salvar(produto);
            attr.addFlashAttribute("feedback", "Produto cadastrado com sucesso.");
        } else {
            produtoService.salvar(produto);
            attr.addFlashAttribute("feedback", "Produto atualizado com sucesso.");
        }

        return "redirect:/produtos";
    }

    /**
     * Obtem a lista com todos os produtos.
     *
     * @return para a lista de produtos
     */
    @GetMapping
    public ModelAndView obterTodosProdutos(ModelMap model) {
        model.addAttribute("produtos", produtoService.encontrarTodos());
        return new ModelAndView("produto/lista", model);
    }

    /**
     * Edita o produto.
     *
     * @return para a lista de produtos
     */
    @GetMapping("/editar/{id}")
    public ModelAndView editarProduto(@PathVariable Long id, ModelMap model) {
        model.addAttribute("produto", produtoService.encontrarPorId(id));
        model.addAttribute("categorias", Arrays.asList(Categoria.values()));
        return new ModelAndView("produto/formulario", model);
    }

    /**
     * Inativa o produto.
     *
     * @return para a lista de produtos
     */
    @GetMapping("inativar/{id}")
    public String inativarProduto(@PathVariable Long id, RedirectAttributes attr) {
        Produto produto = produtoService.encontrarPorId(id);
        produto.setAtivo(false);
        produtoService.salvar(produto);
        attr.addFlashAttribute("feedback", "Produto ID: " + id + " foi inativado com sucesso.");
        return "redirect:/produtos";
    }

    /**
     * Ativa o produto.
     *
     * @return para a lista de produtos
     */
    @GetMapping("ativar/{id}")
    public String ativarProduto(@PathVariable Long id, RedirectAttributes attr) {
        Produto produto = produtoService.encontrarPorId(id);
        produto.setAtivo(true);
        produtoService.salvar(produto);
        attr.addFlashAttribute("feedback", "Produto ID: " + id + " foi ativado com sucesso.");
        return "redirect:/produtos";
    }

    /**
     * Faz a pergunta se o usuário realmente deseja excluir o produto.
     *
     * @return para a lista de produtos
     */
    @GetMapping("excluir/{id}")
    public String excluirProduto(@PathVariable Long id, RedirectAttributes attr) {

        attr.addFlashAttribute("excluir",
                "Você tem certeza que deseja excluir o produto ID: " + id + "?");

        // Criando um atributo para o ID do produto para enviar para o ThymeLeaf
        attr.addFlashAttribute("id", id);

        return "redirect:/produtos";
    }

    /**
     * Excluir produto apoś confirmação.
     *
     * @return para a lista de produtos
     */
    @GetMapping("excluir/sim/{id}")
    public String excluirProdutoConfirmado(@PathVariable Long id, RedirectAttributes attr) {

        produtoService.excluir(id);
        attr.addFlashAttribute("feedback",
                "Produto ID: " + id + " foi excluído com sucesso.");
        return "redirect:/produtos";
    }
}

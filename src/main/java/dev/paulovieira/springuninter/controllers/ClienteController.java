package dev.paulovieira.springuninter.controllers;

import dev.paulovieira.springuninter.models.Cliente;
import dev.paulovieira.springuninter.services.impl.ClienteServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteServiceImpl clienteService;

    public ClienteController(ClienteServiceImpl clienteService) {
        this.clienteService = clienteService;
    }

    /**
     * Cria um novo cliente.
     *
     * @return mantem o usuário na mesma página
     */
    @GetMapping("/novo")
    public ModelAndView criarNovoCliente(ModelMap model) {
        model.addAttribute("cliente", new Cliente());
        return new ModelAndView("cliente/formulario", model);
    }

    /**
     * Salva o cliente.
     *
     * @return para a lista de clientes
     */
    @PostMapping()
    public String salvarCliente(@Valid @ModelAttribute Cliente cliente, BindingResult result, RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "cliente/formulario";
        }

        if (cliente.getId() == null) {
            clienteService.salvar(cliente);
            attr.addFlashAttribute("feedback", "Cliente cadastrado com sucesso.");
        } else {
            clienteService.salvar(cliente);
            attr.addFlashAttribute("feedback", "Cliente atualizado com sucesso.");
        }

        return "redirect:/clientes";
    }

    /**
     * Mostra uma pagina de confirmacao.
     *
     * @return uma pagina de confirmacao.
     */
    @GetMapping("/salvocomsucesso")
    public ModelAndView salvarComSucesso(ModelMap model) {
        return new ModelAndView("cliente/salvocomsucesso", model);
    }

    /**
     * Obtem a lista com todos os clientes.
     *
     * @return para a lista de clientes
     */
    @GetMapping
    public ModelAndView obterTodosClientes(ModelMap model) {
        model.addAttribute("clientes", clienteService.encontrarTodos());
        return new ModelAndView("cliente/lista", model);
    }

    /**
     * Edita o cliente.
     *
     * @return para a lista de clientes
     */
    @GetMapping("/editar/{id}")
    public ModelAndView editarCliente(@PathVariable Long id, ModelMap model) {
        model.addAttribute("cliente", clienteService.encontrarPorId(id));
        return new ModelAndView("cliente/formulario", model);
    }

    /**
     * Inativa o cliente.
     *
     * @return para a lista de clientes
     */
    @GetMapping("inativar/{id}")
    public String inativarCliente(@PathVariable Long id, RedirectAttributes attr) {
        Cliente cliente = clienteService.encontrarPorId(id);
        cliente.setAtivo(false);
        clienteService.salvar(cliente);
        attr.addFlashAttribute("feedback", "Cliente ID: " + id + " foi inativado com sucesso.");
        return "redirect:/clientes";
    }

    /**
     * Ativa o cliente.
     *
     * @return para a lista de clientes
     */
    @GetMapping("ativar/{id}")
    public String ativarCliente(@PathVariable Long id, RedirectAttributes attr) {
        Cliente cliente = clienteService.encontrarPorId(id);
        cliente.setAtivo(true);
        clienteService.salvar(cliente);
        attr.addFlashAttribute("feedback", "Cliente ID: " + id + " foi ativado com sucesso.");
        return "redirect:/clientes";
    }

    /**
     * Faz a pergunta se o usuário realmente deseja excluir o cliente.
     *
     * @return para a lista de clientes
     */
    @GetMapping("excluir/{id}")
    public String excluirCliente(@PathVariable Long id, RedirectAttributes attr) {

        attr.addFlashAttribute("excluir",
                "Você tem certeza que deseja excluir o cliente ID: " + id + "?");

        // Criando um atributo para o ID do cliente para enviar para o ThymeLeaf
        attr.addFlashAttribute("id", id);

        return "redirect:/clientes";
    }

    /**
     * Excluir cliente apoś confirmação.
     *
     * @return para a lista de clientes
     */
    @GetMapping("excluir/sim/{id}")
    public String excluirClienteConfirmado(@PathVariable Long id, RedirectAttributes attr) {

        clienteService.excluir(id);
        attr.addFlashAttribute("feedback",
                "Cliente ID: " + id + " foi excluído com sucesso.");
        return "redirect:/clientes";
    }
}

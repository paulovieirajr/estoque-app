package dev.paulovieira.springuninter.controllers;

import dev.paulovieira.springuninter.models.Cliente;
import dev.paulovieira.springuninter.services.impl.ClienteServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteServiceImpl clienteService;

    public ClienteController(ClienteServiceImpl clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/novo")
    public ModelAndView criarNovoCliente(ModelMap model){
        model.addAttribute("cliente", new Cliente());
        return new ModelAndView("cliente/formulario", model);
    }

    @PostMapping()
    public ModelAndView salvarCliente(@ModelAttribute Cliente cliente){
        clienteService.salvar(cliente);
        return new ModelAndView("redirect:/clientes/salvocomsucesso", "cliente", cliente);
    }

    @GetMapping("/salvocomsucesso")
    public ModelAndView salvarComSucesso(ModelMap model){
        return new ModelAndView("cliente/salvocomsucesso", model);
    }

    @GetMapping
    public ModelAndView obterTodosClientes(ModelMap model){
        model.addAttribute("clientes", clienteService.encontrarTodos());
        return new ModelAndView("cliente/lista", model);
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editarCliente(@PathVariable Long id, ModelMap model){
        model.addAttribute("cliente", clienteService.encontrarPorId(id));
        return new ModelAndView("cliente/formulario", model);
    }

    @GetMapping("inativar/{id}")
    public String inativarCliente(@PathVariable Long id){
        Cliente cliente = clienteService.encontrarPorId(id);
        cliente.setAtivo(false);
        clienteService.salvar(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("ativar/{id}")
    public String ativarCliente(@PathVariable Long id){
        Cliente cliente = clienteService.encontrarPorId(id);
        cliente.setAtivo(true);
        clienteService.salvar(cliente);
        return "redirect:/clientes";
    }
}

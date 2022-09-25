package dev.paulovieira.springuninter.controllers;

import dev.paulovieira.springuninter.models.Cliente;
import dev.paulovieira.springuninter.services.impl.ClienteServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}

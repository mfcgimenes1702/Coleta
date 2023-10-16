package br.com.fiap.gereciadorcoleta.coleta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/coleta")
public class ColetaController {

    @Autowired
    ColetaService service;

    @GetMapping
    public String index(Model model, @AuthenticationPrincipal OAuth2User user) {
        model.addAttribute("avatar_url", user.getAttribute("avatar_url"));
        model.addAttribute("coletas", service.findAll());
        return "coleta/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        if (service.delete(id)) {
            redirect.addFlashAttribute("success", "Coleta apagada com sucesso");
        } else {
            redirect.addFlashAttribute("error", "Coleta não encontrada");
        }
        return "redirect:/coleta";
    }

    @DeleteMapping
    public String deleteobject(Coleta coleta) {
        System.out.println(coleta);
        return "redirect:/coleta";
    }

    @GetMapping("new")
    public String form(Coleta coleta) {
        return "coleta/form";
    }

    @PostMapping
    public String save(@Valid Coleta coleta, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors())
            return "/coleta/form";

        service.save(coleta);
        redirect.addFlashAttribute("success", "Coleta cadastrada com sucesso");
        return "redirect:/coleta";
    }

}

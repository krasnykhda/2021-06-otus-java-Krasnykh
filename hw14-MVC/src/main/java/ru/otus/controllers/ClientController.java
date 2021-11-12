package ru.otus.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.crm.model.Adress;
import ru.otus.crm.model.Phone;
import ru.otus.crm.service.DBServiceClient;
import ru.otus.crm.model.Client;
import ru.otus.crm.service.DBServiceClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class ClientController {


    private final DBServiceClient clientService;

    public ClientController(DBServiceClient clientService) {
        this.clientService = clientService;
    }

    @GetMapping({"/", "/client/list"})
    public String clientsListView(Model model) {
        List<Client> clients = clientService.findAll();
        model.addAttribute("clients", clients);
        return "clientsList";
    }

    @GetMapping("/client/create")
    public String clientCreateView(Model model) {
        model.addAttribute("client", new Client());
        model.addAttribute("adress", new Adress());
        model.addAttribute("phone", new Phone());
        return "clientCreate";
    }

    @PostMapping("/client/save")
    public RedirectView clientSave(@ModelAttribute Client client, @ModelAttribute Adress adress, @ModelAttribute Phone phone) {
        clientService.saveClient(new Client(client.getName(), new Adress(adress.getStreet(), client.getId()), Set.of(new Phone(phone.getNumber(), client.getId()))));
        return new RedirectView("/", true);
    }

}

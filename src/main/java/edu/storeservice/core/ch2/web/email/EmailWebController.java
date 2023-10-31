package edu.storeservice.core.ch2.web.email;

import edu.storeservice.core.ch2.domain.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/emails")
@RequiredArgsConstructor
public class EmailWebController {

    private final EmailWebService emailWebService;

    @GetMapping
    public String emails(Model model){
        List<Email> emails = emailWebService.findAll();
        model.addAttribute("emails", emails);
        return "emails/emails";
    }



}

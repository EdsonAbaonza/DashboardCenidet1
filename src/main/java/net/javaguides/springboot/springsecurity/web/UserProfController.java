package net.javaguides.springboot.springsecurity.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import net.javaguides.springboot.springsecurity.model.Profesor;
import net.javaguides.springboot.springsecurity.service.ProfesorService;
import net.javaguides.springboot.springsecurity.web.dto.ProfesorRegistrationDto;


@Controller
@RequestMapping("/registrationProf")
public class UserProfController {

		@Autowired
	    private ProfesorService profesorService;
		

	    @ModelAttribute("profesor")
	    public ProfesorRegistrationDto profesorRegistrationDto() {
	        return new ProfesorRegistrationDto();
	    }

	    @GetMapping
	    public String showRegistrationForm(Model model) {

	        return "registrationProf";
	    }

	    @PostMapping
	    public String registerUserAccount(@ModelAttribute("profesor") @Valid ProfesorRegistrationDto profesorDto,
	                                      BindingResult result){

	    	
	    	
	        Profesor existing = profesorService.findByCorreo(profesorDto.getCorreo());
	        if (existing != null){
	            result.rejectValue("correo", null, "There is already an account registered with that correo");
	        }

	        if (result.hasErrors()){
	            return "registrationProf";
	        }

	        profesorService.save(profesorDto);
	        return "redirect:/registrationProf?success";
	    }
	    
	    
}

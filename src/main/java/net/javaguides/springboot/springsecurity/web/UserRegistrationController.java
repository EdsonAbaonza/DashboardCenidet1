package net.javaguides.springboot.springsecurity.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.javaguides.springboot.springsecurity.model.Alumno;
import net.javaguides.springboot.springsecurity.model.Role;
import net.javaguides.springboot.springsecurity.repository.RoleRepository;
import net.javaguides.springboot.springsecurity.service.AlumnoService;
import net.javaguides.springboot.springsecurity.web.dto.UserRegistrationDto;

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
@RequestMapping("/registration")
public class UserRegistrationController {

		@Autowired
	    private AlumnoService alumnoService;

	    @ModelAttribute("alumno")
	    public UserRegistrationDto UserRegistrationDto() {
	        return new UserRegistrationDto();
	    }

	    @GetMapping
	    public String showRegistrationForm(Model model) {

	        return "registration";
	    }

	    @PostMapping
	    public String registerUserAccount(@ModelAttribute("alumno") @Valid UserRegistrationDto userDto,
	                                      BindingResult result){

	    	
	    	
	        Alumno existing = alumnoService.findByCorreo(userDto.getCorreo());
	        if (existing != null){
	            result.rejectValue("correo", null, "There is already an account registered with that correo");
	        }

	        if (result.hasErrors()){
	            return "registration";
	        }

	        alumnoService.save(userDto);
	        return "redirect:/registration?success";
	    }
}

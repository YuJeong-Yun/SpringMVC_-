package com.fastcampus.ch2;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {

	@InitBinder
	public void toDate(WebDataBinder binder) {
		ConversionService conversionService = binder.getConversionService();
//		System.out.println("conversionService = " + conversionService);
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));
		binder.registerCustomEditor(String[].class, new StringArrayPropertyEditor("#"));
		
//		binder.setValidator(new UserValidator()); // UserValidator를 WebDataBinder의 validaoter로 등록 
//		binder.addValidators(new UserValidator()); // UserValidator를 WebDataBinder의 validaoter로 등록 
		List<Validator> validatorList = binder.getValidators();
		System.out.println("validatorList = "+validatorList);
	}

//	@RequestMapping(value = "/register/add", method = { RequestMethod.GET, RequestedMethod.POST})
	@GetMapping("/register/add")
	public String register() {
		return "registerForm"; // WEB-INF/vies/registerForm.jsp
	}

//	@RequestMapping(value="/register/save", method=RequestMethod.POST)
	@PostMapping("/register/add") // 4.3
	public String save(@Valid User user, BindingResult result, Model m) throws Exception {
		System.out.println("result = " + result);
		System.out.println("user = " + user);


//		//수동 검증 - Vlidator를 직접 생성하고, validator()를 직접 호출 
//		UserValidator userValidator = new UserValidator();
//		userValidator.validate(user, result);
		// User 객체를 검증한 결과 에러가 있으면 registerForm을 이용해서 에러를 보여줘야 함
		
		
		if(result.hasErrors()) {
			return "registerForm";
		}
		// 2.DB에 신규회원 정보를 저장
		return "registerInfo";
	}

	private boolean isValid(User user) {
		return true;
	}
}

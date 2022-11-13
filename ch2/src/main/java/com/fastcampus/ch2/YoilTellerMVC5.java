package com.fastcampus.ch2;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

// ������� �Է��ϸ� ������ �˷��ִ� ���α׷�
@Controller
public class YoilTellerMVC5 {
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex, BindingResult result) {
		System.out.println("result="+result);
		FieldError error =  result.getFieldError();
		
		System.out.println("code="+error.getCode());
		System.out.println("filed="+error.getField());
		System.out.println("msg="+error.getDefaultMessage());
		ex.printStackTrace();
		return "yoilError";
	}

//	http://localhost:8088/ch2/getYoilMVC?year=2021&month=10&day=1
	@RequestMapping("/getYoilMVC6")
//	public void main(HttpServletRequest request, HttpServletResponse response) throws IOException {
//	public String main(@ModelAttribute("myDate") MyDate date, Model model) throws IOException {
	public String main(MyDate date, BindingResult result) throws IOException {
		System.out.println("result=" + result);
//
		// 1.��ȿ�� �˻�
		if (!isValid(date)) {
			return "yoilError";
		}

		// 2. ���� ���
//		char yoil = getYoil(date);

		// 3. ����� ����� model�� ����
//		model.addAttribute("myDate", date);
//		model.addAttribute("yoil", yoil);

		return "yoil";

//		return "yoil"; // /WEB-INF/view/yoil.jsp

//		 3.���
//		response.setContentType("text/html");
//		response.setCharacterEncoding("utf-8");
//		PrintWriter out = response.getWriter(); // response��ü���� ���������� ��� ��Ʈ���� ��´�.
//		out.println(year + "�� " + month + "�� " + day + "���� ");
//		out.println(yoil + "�����Դϴ�.");

	}

	private boolean isValid(MyDate date) {
		return isValid(date.getYear(), date.getMonth(), date.getDay());
	}

	private @ModelAttribute("yoil") char getYoil(MyDate date) {
		return getYoil(date.getYear(), date.getMonth(), date.getDay());
	}

	private boolean isValid(int year, int month, int day) {
		if (year == -1 || month == -1 || day == -1)
			return false;

		return (1 <= month && month <= 12) && (1 <= day && day <= 31); // ������ üũ
	}

	private char getYoil(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);

		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1:�Ͽ���, 2:������ ..
		return " �Ͽ�ȭ�������".charAt(dayOfWeek);
	}

}

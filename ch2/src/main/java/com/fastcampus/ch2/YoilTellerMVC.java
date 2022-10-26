package com.fastcampus.ch2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// ������� �Է��ϸ� ������ �˷��ִ� ���α׷�
@Controller
public class YoilTellerMVC {

//	http://localhost:8088/ch2/getYoilMVC?year=2021&month=10&day=1
	@RequestMapping("/getYoilMVC")
//	public void main(HttpServletRequest request, HttpServletResponse response) throws IOException {
	public ModelAndView main(int year, int month, int day) throws IOException {

		ModelAndView mv = new ModelAndView();
		
		// 1.��ȿ�� �˻�
//		if (!isValid(year, month, day)) {
//			return "yoilError";
//		}

		// 2. ���� ���
		char yoil = getYoil(year, month, day);

		// 3. ����� ����� model�� ����
		mv.addObject("year", year);
		mv.addObject("month", month);
		mv.addObject("day", day);
		mv.addObject("yoil", yoil);
		
		// 4. ����� ������ view�� ����
		mv.setViewName("yoil");
		
		return mv;
		
//		return "yoil"; // /WEB-INF/view/yoil.jsp

//		 3.���
//		response.setContentType("text/html");
//		response.setCharacterEncoding("utf-8");
//		PrintWriter out = response.getWriter(); // response��ü���� ���������� ��� ��Ʈ���� ��´�.
//		out.println(year + "�� " + month + "�� " + day + "���� ");
//		out.println(yoil + "�����Դϴ�.");

	}

	private boolean isValid(int year, int month, int day) {
		return true;
	}

	private char getYoil(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);

		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1:�Ͽ���, 2:������ ..
		return " �Ͽ�ȭ�������".charAt(dayOfWeek);
	}

}

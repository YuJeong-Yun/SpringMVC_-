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

// 년월일을 입력하면 요일을 알려주는 프로그램
@Controller
public class YoilTellerMVC {

//	http://localhost:8088/ch2/getYoilMVC?year=2021&month=10&day=1
	@RequestMapping("/getYoilMVC")
//	public void main(HttpServletRequest request, HttpServletResponse response) throws IOException {
	public ModelAndView main(int year, int month, int day) throws IOException {

		ModelAndView mv = new ModelAndView();
		
		// 1.유효성 검사
//		if (!isValid(year, month, day)) {
//			return "yoilError";
//		}

		// 2. 요일 계산
		char yoil = getYoil(year, month, day);

		// 3. 계산한 결과를 model에 저장
		mv.addObject("year", year);
		mv.addObject("month", month);
		mv.addObject("day", day);
		mv.addObject("yoil", yoil);
		
		// 4. 결과를 보여줄 view를 저장
		mv.setViewName("yoil");
		
		return mv;
		
//		return "yoil"; // /WEB-INF/view/yoil.jsp

//		 3.출력
//		response.setContentType("text/html");
//		response.setCharacterEncoding("utf-8");
//		PrintWriter out = response.getWriter(); // response객체에서 브라우저로의 출력 스트림을 얻는다.
//		out.println(year + "년 " + month + "월 " + day + "일은 ");
//		out.println(yoil + "요일입니다.");

	}

	private boolean isValid(int year, int month, int day) {
		return true;
	}

	private char getYoil(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);

		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1:일요일, 2:월요일 ..
		return " 일월화수목금토".charAt(dayOfWeek);
	}

}

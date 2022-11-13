package com.fastcampus.ch2;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

// 년월일을 입력하면 요일을 알려주는 프로그램
@Controller
public class YoilTellerMVC6 {
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex) {
		ex.printStackTrace();
		return "yoilError";
	}
	
//	http://localhost:8088/ch2/getYoilMVC?year=2021&month=10&day=1
	@RequestMapping("/getYoilMVC5")
//	public void main(HttpServletRequest request, HttpServletResponse response) throws IOException {
//	public String main(@ModelAttribute("myDate") MyDate date, Model model) throws IOException {
		public String main(@ModelAttribute MyDate date, Model model) throws IOException {
		System.out.println("data="+date);
		
		// 1.유효성 검사
		if (!isValid(date)) {
			return "yoilError";
		}

		// 2. 요일 계산
//		char yoil = getYoil(date);

		// 3. 계산한 결과를 model에 저장
//		model.addAttribute("myDate", date);
//		model.addAttribute("yoil", yoil);
		
		
		return "yoil";
		
//		return "yoil"; // /WEB-INF/view/yoil.jsp

//		 3.출력
//		response.setContentType("text/html");
//		response.setCharacterEncoding("utf-8");
//		PrintWriter out = response.getWriter(); // response객체에서 브라우저로의 출력 스트림을 얻는다.
//		out.println(year + "년 " + month + "월 " + day + "일은 ");
//		out.println(yoil + "요일입니다.");

	}

	private boolean isValid(MyDate date) {
		return isValid(date.getYear(), date.getMonth(), date.getDay());
	}

	private @ModelAttribute("yoil") char getYoil(MyDate date) {
		return getYoil(date.getYear(), date.getMonth(), date.getDay());
	}

	private boolean isValid(int year, int month, int day) {
    	if(year==-1 || month==-1 || day==-1) 
    		return false;
    	
    	return (1<=month && month<=12) && (1<=day && day<=31); // 간단히 체크 
	}

	private char getYoil(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);

		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1:일요일, 2:월요일 ..
		return " 일월화수목금토".charAt(dayOfWeek);
	}

}

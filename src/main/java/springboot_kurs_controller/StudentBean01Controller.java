package springboot_kurs_controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller     // Bu anotation ile Springboot bu klasin Controller Class oldugunu anlar.
@RestController
public class StudentBean01Controller {  //Bu klasta Müsterinin istekleri kontrol edilecek. Get mi Put mu Delete mi???

//	@RequestMapping(method=RequestMethod.GET, path="/getRequest")
//	@ResponseBody  //Methodum return ettigi datayi görmek icin bu annotation kullanilir.
//	public String getMethod1() {
//		return "Get Request Method1 calistirildi...";
//	}
	
	@GetMapping(path="/getString")  // GetRequestleri bu maplerle eslestir demis olduk
	public String getMethod1() {
		return "Get Request Method1 calistirildi...";
	}
	
	
	//Tight coupling
	@GetMapping(path="/getObject")
	public StudentBean01 getMethod2() {
		return new StudentBean01("Ali Can", 13, "AC202113");
	}
	
	//Loose coupling
	@Autowired//@Autowired annotation’i IOC Container’dan objeyi alir ve
    // “s” container’inin icine koyar. StudentBean01 s = new StudentBean01() deki “=” operatorune benzer
	StudentBean01 s;
	
	@GetMapping(path="/getObjectLoose")
	public StudentBean01 getMethod3() {
		
		s.setName("Ali Can");
		s.setAge(13);
		s.setId("AC202113");
		
		return s;
	}
	
	
	// Tight Coupling
		@GetMapping(path="/getObjectParametreli1/{school}")
		public StudentBean01 getMethod4(@PathVariable String school) {
			
			return new StudentBean01("Veli Han", 35, String.format("VH2021%s35", school)) ;
								
		}
	
	
	
	
	// Loose Coupling
	@GetMapping(path="/getObjectParametreli2/{school}")
	public StudentBean01 getMethod5(@PathVariable String school) {
		s.setName("Veli Han");
		s.setAge(35);
		s.setId(String.format("VH2021%s35", school)); //%s datanin gelmesi icin yer tutucu
		
		return s;
		
	}
	
	
	//Tight Coupling
	@GetMapping(path="/getObjectList")
	public List<StudentBean01> getMethod6(){
		
		
		return List.of(
				new StudentBean01("Veli Han", 35, "VH202135"),
				new StudentBean01("Ayse Kan", 23, "VH202123"),
				new StudentBean01("Talha Tan", 46, "VH202146")
				);
	}
	
	
	//Loose coupling
	@Autowired
	StudentBean02 t;   // Class ismini yazarak IOC Containerdan istediginiz objeyi alabilirsiniz
	
	@GetMapping(path="/getStudy")
	public String getMethod7() {
		return t.study();
	}
	
	
	//Loose coupling
		@Autowired      // Buradan Java hata verir. Cünkü Interface altinda hangi class istedigimizi bilemez
		@Qualifier(value="studentBean02")  // Autowired anotation normalde data type a bakar.
											// @Qualifier(value="") yazarak isme bakmasini saglariz.
		StudentInterface u;   // =new StudentBean02();  
		
		@GetMapping(path="/getInterface") 
		public String getMethod8() {
			return t.study();
		}
	
	
}

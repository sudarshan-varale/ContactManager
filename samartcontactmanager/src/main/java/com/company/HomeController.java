package com.company;



import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.dao.ContactRepo;
import com.company.dao.InstructorDetailsRepo;
import com.company.dao.InstructorRepo;
import com.company.dao.UserRepository;
import com.company.entities.Contact;
import com.company.entities.Instructor;
import com.company.entities.InstructorDetails;
import com.company.entities.Login;
import com.company.entities.SMS;
import com.company.entities.SMSService;
import com.company.entities.User;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;




@CrossOrigin
@Controller
public class HomeController 
{
	BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
     	
	
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	DaoAuthenticationProvider daoAuthenticationProvider;
	 SMS sms=new SMS();
	 @Autowired
	 SMSService smsService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ContactRepo contactRepo;
	@Autowired
	InstructorDetailsRepo instructorDetailsRepo;
	@Autowired
	InstructorRepo instructorRepo;
	
	@Autowired
	UserDetailsService userDetailsService;
	User user=new User();
	Contact contact;
	// For home page
	   @RequestMapping("/")
	    public String home()
	    {
	   	 return"home";
			
			
		}
	   @RequestMapping("/create_order")
	   @ResponseBody
	    public String create_order()
	    {
		   System.out.println("hey success");
	   	 return"login";
	    } 
	   
	   //for signup
    @RequestMapping("/signup")
    public String home(Model model)
    {
  	model.addAttribute("user",new User());
   	 return"signup";
    }
	

     //for register
   @RequestMapping(value="/do_register" ,method=RequestMethod.POST)
  public String register(@Valid @ModelAttribute("user") User user,BindingResult bindingresult  ,@RequestParam(value="agreement" ,defaultValue="false") boolean agreement,Model model , HttpSession session)
  {
   
   try
   {
	  
	   if(bindingresult.hasErrors())
	   {
		   model.addAttribute("user",user);
		   return"signup";
	   }
	   if(!agreement)
	  {
		throw new Exception("check term and condition");
	   }
	   user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
       user.setRole("ROLE_USER");
	  user.setImageUrl("myimg.jpg");
	  user.setEnabled(agreement);
	System.out.println(user.getName());
	   userRepository.save(user);
	  //session.setAttribute("msg",new Message("sucessfully registered","alert-success"));
		return "signup";
		}
	
	catch(Exception e)
	{
		//session.setAttribute("msg",new Message("somthing went wrong\t\t" +e.getMessage(),"alert-danger"));
		return "signup";
	}
}
   @RequestMapping("/payment")
   public String payment()
   {
	   return"payment";
   }

   @RequestMapping("/signin")
   public String signup()

   {
   	return"login";
   }
   
   //  just practice one to one
   @ResponseBody
   @ RequestMapping("/prac")
   public String check()
   {
	 /*
	 //one to one 
	Instructor instructor=new Instructor();
	 instructor.setFirst_name("sudarshan");
	 instructor.setLast_name("varale");
	 instructor.setEmail("svarale98@gmail.com");
	 
	 InstructorDetails instructorDetails=new InstructorDetails();
	 instructorDetails.setYoutube_channel("www.luv2code.com");
	 instructorDetails.setHobby("luv2 code!!");

	 instructor.setInstructorDetails(instructorDetails);
	 instructorRepo.save(instructor);
	 */
	/*
	 //delete 
	 Instructor instructor= instructorRepo.findById(14).get();
	 instructorRepo.delete(instructor);
	 */
	 
	 // one to one bydirection
	/* Instructor instructor=new Instructor();
	 instructor.setFirst_name("mangesh");
	 instructor.setLast_name("varale");
	 instructor.setEmail("mvarale98@gmail.com");
	 
	 
	 InstructorDetails instructorDetails=new InstructorDetails();
	 instructorDetails.setYoutube_channel("www.youtube.com");
	 instructorDetails.setHobby("cricket!!");
	 instructor.setInstructorDetails(instructorDetails);

	 instructorDetails.setInstructor(instructor);
	 instructorDetailsRepo.save( instructorDetails);
	*/
		 InstructorDetails instructorDetail=instructorDetailsRepo.findById(1).get();
		 System.out.println("instructorDetails"+instructorDetail);
		 
		 System.out.println("Assosiate instructor:"+instructorDetail.getInstructor());
		 
		 System.out.println("delete Instructor detail and instructor:"+instructorDetail.getInstructor());
		 instructorDetailsRepo.delete(instructorDetail);
		 
			 return"add record sucessfully";
   }
   
   @RequestMapping("/otp")
   public String home1()
   {
      sms.setTo("89560563254");
      sms.setMessage("sudarshan");
      smsService.setFROM_NUMBER("8830301480");
      smsService.send(sms);
  	 return"signup";
   }
   
   @PostMapping("/do-login" )
   public String login(@ModelAttribute("user") Login login,Principal principle)
   {
	   System.out.println(login.getUsername());
	   System.out.println(login.getPassword());
	   
	   Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(),login.getPassword()));
	   daoAuthenticationProvider.authenticate(authentication);  
	   daoAuthenticationProvider.setUserDetailsService(userDetailsService);
	   daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
	   return"login";
	   
   }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
}
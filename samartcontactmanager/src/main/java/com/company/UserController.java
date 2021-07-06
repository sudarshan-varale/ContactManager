package com.company;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.dao.ContactRepo;
import com.company.dao.UserRepository;
import com.company.entities.Contact;
import com.company.entities.User;
import com.company.helper.Message1;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;


@CrossOrigin
@Controller 
@RequestMapping("/user")
public class UserController 
{
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	ContactRepo contactRepo;
	
@PostMapping("/createorder")
@ResponseBody
public  String createorder(@RequestBody Map<String,Object>data) throws RazorpayException
{
	
    System.out.println("come");
	System.out.println("payment sssssssssssssss");
	int amount=Integer.parseInt(data.get("amount").toString());
	RazorpayClient client=new RazorpayClient("rzp_test_X2hkZZ4o0cL8ht","yWf18NYpzBiFvehFqR6ZfEH1");
	JSONObject ob = new JSONObject();
	ob.put("amount", amount*100);
	ob.put("currency", "INR");
	ob.put("receipt", "txn_123456");
	Order order=client.Orders.create(ob);
	System.out.println("sucessfully run");
	System.out.println(order);
	return order.toString();
}

@RequestMapping("/index")
public String index(Model model,Principal principle)
{

	String name=principle.getName();
    User user= userRepository.findByUsername(name);
	model.addAttribute("user", user);
	 return"USER/index";
}
@RequestMapping("/fail")

public String fail()
{	return"fail";

}

@RequestMapping("/add_contact")
public String add_contact(Model m)
{
	m.addAttribute("contact",new Contact());
	return "USER/add_contact";
}
@PostMapping("/process-form")
public String process_form(@Valid @ModelAttribute("contact")Contact contact,BindingResult bindingResult,Model model,HttpSession session,Principal p)
{
	System.out.println(contact.getFname());
	System.out.println(contact.getLname());
	System.out.println(contact.getId());
	System.out.println(contact.getPhone());
	System.out.println(contact.getWork());

	try 
	{
 if(bindingResult.hasErrors())
 {
	System.out.println("error");
	model.addAttribute("contact",contact);
	return"USER/add_contact";
}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	String name=p.getName();
 User user=userRepository.findByUsername(name);
 contact.setUser(user);

 user.getContact().add(contact);
 userRepository.save(user);


	model.addAttribute("contact",contact);
	 session.setAttribute("msg",new Message1("sucessfully contact added!!","alert-success"));

	return  "USER/add_contact";

}
@GetMapping("/view-contact/{page}")
public String view_contact(@PathVariable("page") int page,Principal p,Model m)
{
        		PageRequest pageable=PageRequest.of(page ,3);
	
	String name=p.getName();
	User user=userRepository.findByUsername(name); 
	Page<Contact>contacts=contactRepo.contactfindById(user.getId(),pageable);

	m.addAttribute("contacts",contacts);
	m.addAttribute("currentpage",page);
	m.addAttribute("totalPages", contacts.getTotalPages());
	
	return "USER/view-contact";
}

@RequestMapping("/contact/{id}")
public String viewContactDetails(@PathVariable("id") int id,Model m)
{
	System.out.println(id);
  Optional<Contact> contactDetails=contactRepo.findById(id);
  Contact contact=contactDetails.get();
  m.addAttribute("contact",contact);
	return "USER/contact-details";
}


@GetMapping("/profile")
public String profile(Principal p,Model m)
{
	String name=p.getName();
User user=	userRepository.findByUsername(name);
	m.addAttribute("user",user);
	return "USER/profile";
	
}
@RequestMapping("/delete/{cid}")
public String delete(@PathVariable("cid") int id)
{
           Contact contact=contactRepo.findById(id).get();     
	           contactRepo.delete(contact);
	      System.out.println("this is delete id:"+id);
	return "redirect:/user/view-contact/0";
}


@PostMapping("/update/{cid}")
public String update(@PathVariable("cid") int id,Model m)
{    
           Contact contact=contactRepo.findById(id).get();   
           m.addAttribute("contact",contact);
	      	return "USER/update";
}

// update contact
@PostMapping("/update-form")
public String update_form(@Valid @ModelAttribute("contact")Contact contact,BindingResult result,Model m,Principal p)
{
	
	if(result.hasErrors())
	{
		m.addAttribute("contact",contact);
		return"USER/update";
	}
	
	
	
	String name=p.getName();
	User user=userRepository.findByUsername(name);
   contact.setUser(user);
   contactRepo.save(contact)	;
	
	
	return "redirect:/user/view-contact/0";
}
}
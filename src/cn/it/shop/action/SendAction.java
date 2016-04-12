package cn.it.shop.action;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 此An
 * 
 * @author THINK
 * 
 */
@Controller
public class SendAction extends ActionSupport {

	public SendAction() {
		// TODO Auto-generated constructor stub
		System.out.println("----sendAction-----");
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("---execute----");
		return "send";
	}
}

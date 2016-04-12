package cn.it.shop.model;

/**
 * Account entity. @author MyEclipse Persistence Tools
 */

public class Account implements java.io.Serializable {

	// Fields

	private Integer id;

	private String login;
	private String name;
	private String pass;
	//private Boolean system;

	// Constructors

	/** default constructor */
	public Account() {
	}

	/** full constructor */
	public Account(String login, String name, String pass, Boolean system) {
		this.login = login;
		this.name = name;
		this.pass = pass;
		//this.system = system;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

//	public Boolean getSystem() {
//		return this.system;
//	}
//
//	public void setSystem(Boolean system) {
//		this.system = system;
//	}

	
	@Override
	public String toString() {
		return "Account [id=" + id + ", login=" + login + ", name=" + name
				+ "]";
	}

}
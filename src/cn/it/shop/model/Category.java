package cn.it.shop.model;

/**
 * Category entity. @author MyEclipse Persistence Tools
 */

public class Category implements java.io.Serializable {

	// Fields

	private Integer id;
	private String type;
	private Boolean hot;
	//private Integer aid;
	
	private Account account;
	
	public void setAccount(Account account) {
		this.account = account;
	}
	public Account getAccount() {
		return account;
	}

	// Constructors

	/** default constructor */
	public Category() {
	}

	/** full constructor */
	public Category(String type, Boolean hot) {
		this.type = type;
		this.hot = hot;
		//this.aid = aid;
	}
	public Category(Integer id, String type, Boolean hot) {
		super();
		this.id = id;
		this.type = type;
		this.hot = hot;
	 	//this.aid = aid;
	}

	
	
	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getHot() {
		return this.hot;
	}

	public void setHot(Boolean hot) {
		this.hot = hot;
	}

//	public Integer getAid() {
//		return this.aid;
//	}
//
//	public void setAid(Integer aid) {
//		this.aid = aid;
//	}
	
	@Override
	public String toString() {
		return "Category [id=" + id + ", type=" + type + ", hot=" + hot
				+  "]";
	}

}
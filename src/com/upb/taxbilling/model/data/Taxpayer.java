package com.upb.taxbilling.model.data;

/**
 * Contains the information of a taxpayer
 * @author Vanessa Sanjinez
 * @author Gina Cardozo
 */

public class Taxpayer {
	
	/**
	 * Attributes to save the information about Taxpayer
	 */
	
	private String NameLastname;
	private String Address;
	private String ExpeditionPlace;
	private String Email;
	private int IdentityNumber;

	/**
	 * Constructor method with parameters
	 */
	 
	public Taxpayer(String NameLastname, String Address, String ExpeditionPlace, String Email, int IdentityNumber)
	{
		this.NameLastname = NameLastname;
		this.Address = Address;
		this.ExpeditionPlace = ExpeditionPlace;
		this.Email = Email;
		this.IdentityNumber = IdentityNumber;
	}
	
	/**
	 * Getters and Setters
	 * @return
	 */
	
	public String getNameLastname() {
		return NameLastname;
	}

	public String getAddress() {
		return Address;
	}

	public String getExpeditionPlace() {
		return ExpeditionPlace;
	}

	public int getIdentityNumber() {
		return IdentityNumber;
	}
	
	public String getEmail() 
	{
		return Email;
	}
	
	public void setNameLastname(String nameLastname) {
		NameLastname = nameLastname;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public void setExpeditionPlace(String expeditionPlace) {
		ExpeditionPlace = expeditionPlace;
	}

	public void setIdentityNumber(int identityNumber) {
		IdentityNumber = identityNumber;
	}

	public void setEmail(String email) {
		Email = email;
	}
	
	
}

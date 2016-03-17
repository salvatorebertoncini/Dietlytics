package model;

import java.io.Serializable;

public class MStorico implements Serializable {
	
	private int id;
	private int id_user;
	private int id_diet;
	private String start_diet;
	private String end_diet;

	
	public MStorico(int id, int id_user, int id_diet, String start_diet, String end_diet) {
		this.id = id;
		this.id_user = id_user;
		this.id_diet = id_diet;
		this.start_diet = start_diet;
		this.end_diet = end_diet;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public int getId_diet() {
		return id_diet;
	}
	public void setId_diet(int id_diet) {
		this.id_diet = id_diet;
	}
	public String getStart_diet() {
		return start_diet;
	}
	public void setStart_diet(String start_diet) {
		this.start_diet = start_diet;
	}
	public String getEnd_diet() {
		return end_diet;
	}
	public void setEnd_diet(String end_diet) {
		this.end_diet = end_diet;
	}
	

}

package Diet;

public class Dieta {
	boolean login;
	String Dieta, InizioDieta, FineDieta, idk, nome_pane;
	int n_pane,lifestyle;
	
	//COSTRUTTORI
	public Dieta() {}
	
	public Dieta(int lifestyle, boolean login) {
		this.login = login;
		this.lifestyle = lifestyle;
	}
	
	public Dieta(String dieta, boolean login) {
		this.login = login;
		this.Dieta = dieta;
	}

	public Dieta(String Dieta, String InizioDieta, boolean login){
		this.Dieta=Dieta;
		this.InizioDieta=InizioDieta;
		this.login=login;
	}

	public Dieta(int n_pane, String nome_pane, String idk, boolean login) {
		this.n_pane=n_pane;
		this.nome_pane=nome_pane;
		this.idk=idk;
		this.login=login;
	}

	//GETTERS E SETTERS
	public String getDieta() {
		return Dieta;
	}

	public boolean getLogin() {
		return login;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}

	public String getIdk() {
		return idk;
	}

	public void setIdk(String idk) {
		this.idk = idk;
	}

	public String getNome_pane() {
		return nome_pane;
	}

	public void setNome_pane(String nome_pane) {
		this.nome_pane = nome_pane;
	}

	public int getN_pane() {
		return n_pane;
	}

	public void setN_pane(int n_pane) {
		this.n_pane = n_pane;
	}

	public void setDieta(String dieta) {
		Dieta = dieta;
	}

	public String getInizioDieta() {
		return InizioDieta;
	}

	public void setInizioDieta(String inizioDieta) {
		InizioDieta = inizioDieta;
	}

	public String getFineDieta() {
		return FineDieta;
	}

	public void setFineDieta(String fineDieta) {
		FineDieta = fineDieta;
	}
	
}

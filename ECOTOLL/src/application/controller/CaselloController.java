package application.controller;

import java.util.List;

import application.dao.DAOCasello;
import application.dao.DAOFactory;
import application.model.Casello;

public class CaselloController {
	
	private DAOFactory mysqlfactory;
	private DAOCasello userdao;
	
	public CaselloController() {
		mysqlfactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		userdao = mysqlfactory.getDAOCasello();
	}
	
	public Casello get(Casello c) {
		return mysqlfactory.getDAOCasello().getCasello(c);
	}
	
	public List<Casello> getAllCas() {
		return mysqlfactory.getDAOCasello().getAllCasello();
	}
	
	
	public boolean delete(Casello casello) {
		return mysqlfactory.getDAOCasello().deleteCasello(casello);
	}
	
	public boolean aggiungi(Casello c) {
		return mysqlfactory.getDAOCasello().addCasello(c);
	}
	
	public static CaselloController getIstance() {
		return new CaselloController();
	}
	
	public void setCaselloGlobal(String nome_casello, String altezza_casello, int id_autostrada) {
		userdao.aggCasello(nome_casello, altezza_casello, id_autostrada);	
	}
	
	public boolean addCasello(String nome_casello, String altezza_casello, int id_autostrada ) {
		if(userdao.aggCasello(nome_casello, altezza_casello, id_autostrada)) return true;
		else return false;
	}

}
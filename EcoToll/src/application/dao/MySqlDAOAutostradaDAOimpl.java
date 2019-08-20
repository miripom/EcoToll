package application.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.model.Autostrada;
import application.dao.MySQLDAOFactory;


public class MySqlDAOAutostradaDAOimpl implements DAOAutostrada {
	
	private Connection con = null;
	private PreparedStatement prep=null;
	private ResultSet res = null;
	
	private final String INSERT_AUTOSTRADA ="INSERT INTO autostrada (nome_autostrada, inizio, fine, km, tariffa_km) values ('?,?,?,?,?)";
	private final String SELECT_INFO_AUTOSTRADA = "SELECT * FROM autostrada WHERE nome_autostrada=?";
	private final String HIGHWAY = "SELECT * FROM autostrada";

		
	
	
	@Override
	public boolean addAutostrada(Autostrada a) {
		try {
			con = MySQLDAOFactory.createConnection();
			prep = (PreparedStatement) con.prepareStatement(INSERT_AUTOSTRADA);
			prep.setString(1, a.getNomeAutostrada());
			prep.setString(2, a.getInizio());
			prep.setString(3, a.getFine());
			prep.setDouble(4, a.getKm());
			prep.setDouble(5, a.getTariffaKm());
			
			return prep.execute();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
	
	@Override
	public int getIdAutostrada (Autostrada a) {
		try {
			con = MySQLDAOFactory.createConnection();
			prep = (PreparedStatement) con.prepareStatement(SELECT_INFO_AUTOSTRADA);
			prep.setString(1, a.getNomeAutostrada());
			res = prep.executeQuery();
			if (res.next()){
			   return res.getInt(1);
			   }
		   }
		catch (SQLException e) {
			e.printStackTrace(); 
			System.out.println("Problema nel DB");
			}
		
			return 0;

	}
	
	
	
	@Override
	public List<Autostrada> getAllAutostrade(){
		List<Autostrada> list = new ArrayList<>();
		try {
			  con = MySQLDAOFactory.createConnection();
			  prep = (PreparedStatement) con.prepareStatement(HIGHWAY);
			 
			  res = prep.executeQuery();
			  while(res.next()) {
				  Autostrada autostrada = new Autostrada();
				  	autostrada.setId(res.getInt("id"));
				  	autostrada.setKm(res.getDouble("km"));
				  	autostrada.setTariffaKm(res.getDouble("tariffa_km"));
				  	autostrada.setNomeAutostrada(res.getString("nome_autostrada"));
				  	autostrada.setInizio(res.getString("inizio"));
				  	autostrada.setFine(res.getString("fine"));
					list.add(autostrada);
			  }
	}
		catch (SQLException e) {
		e.printStackTrace(); 
		System.out.println("Problema nel DB");
		}
	
		return list;
	}
	
	
	@Override
	public Autostrada getAutostrada(Autostrada a) {
		try {
			  con = MySQLDAOFactory.createConnection();
			  prep = (PreparedStatement) con.prepareStatement(SELECT_INFO_AUTOSTRADA);
			  prep.setString(1, a.getNomeAutostrada());
			  res = prep.executeQuery();
			  if(res.next()) {
				  return new Autostrada(res);
			  }
	}
		catch (SQLException e) {
		e.printStackTrace(); 
		System.out.println("Problema nel DB");
		}
	
		return null;
		}
}
		
	

package com.fosagri.model.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Demande {
	@Id
	@GeneratedValue
	private Long id;
	private Long prestationId;
	private String nom;
	private String titre;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date dateprestation;
	private String statut;
	private String type;
	private String destination;
	private String choix1;
	private String choix2;
	private String periode1;
	private String periode2;
	private String benificiare;
	private String codAg;
	private String email;
	private String gsm;
	private String passport;
	private String dateExpPassport;
	private String text1;
	private Date date1;
	private String text2;
	private Date date2;
	private String text3;
	private Date date3;
	private String text4;
	private Date date4;
	private int nombre;
	private Date datedemande;   
	private String pprconj;

	public Demande() {
	}

	public Demande(String nom, String titre, Date datedemande, String statut, String type, String destination, String benificiare, String codAg, int nombre) {
		this.nom = nom;
		this.titre = titre;
		this.datedemande = datedemande;
		this.statut = statut;
		this.type = type;
		this.destination = destination;
		this.benificiare = benificiare;
		this.codAg = codAg;
		this.nombre = nombre;
	}

}
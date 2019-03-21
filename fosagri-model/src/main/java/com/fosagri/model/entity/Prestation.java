package com.fosagri.model.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Prestation {
	@Id
	@GeneratedValue
	private Long id;
	private String nom;
	private String titre;
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
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
	private String pprconj;
	private String gsm;
	private int nombre;
	//===============================Relation conjoint adherent: Many conj to One Adherent==================
	@ManyToOne
	@JoinColumn(name = "adherentId")
	Adherent adherent;
//===========================================================



	public Prestation() {
	}

	public Prestation(String nom, String titre, Date dateprestation, String statut, String type, String destination, String benificiare, String codAg, int nombre) {
		this.nom = nom;
		this.titre = titre;
		this.dateprestation = dateprestation;
		this.statut = statut;
		this.type = type;
		this.destination = destination;
		this.benificiare = benificiare;
		this.codAg = codAg;
		this.nombre = nombre;
	}

}
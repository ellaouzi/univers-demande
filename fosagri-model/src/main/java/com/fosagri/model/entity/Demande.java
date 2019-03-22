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
	private String nom;
	private String titre;
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private Date datedemande;
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
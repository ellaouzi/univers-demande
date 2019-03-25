package com.fosagri.model.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
public class Prestation {
	@Id
	@GeneratedValue
	private Long prestationId;
	private String nom;
	private String titre;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date dateprestation;
	private String statut;
	private String type;

	@OneToMany(fetch = FetchType.LAZY,  mappedBy = "prestation")
    private Set<Demande> demandes;

	public Prestation() {
		super();
	}


	public Prestation(String nom, String titre, Date dateprestation, String statut, String type, Set<Demande> demandes) {
		this();
		this.nom = nom;
		this.titre = titre;
		this.dateprestation = dateprestation;
		this.statut = statut;
		this.type = type;
		this.demandes=demandes;
	}
}
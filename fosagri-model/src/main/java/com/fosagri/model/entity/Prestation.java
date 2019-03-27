package com.fosagri.model.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Prestation implements Serializable {
	@Id
	@Column(name = "prestation_id")
	private Long id;
  	private String statut;
	private String type;
 	private String label;
 	private String valeurs;
	private String description;
	private boolean open;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date dateDu;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date dateAu;
 	private int nombreLimit;

  	@OneToMany(mappedBy = "prestation", fetch = FetchType.LAZY)
     private List<Demande> demandes;

	public Prestation() {
		super();
	}

	public Prestation(  String statut, String type, Long id, String label, String valeurs, String description, boolean open, Date dateDu, Date dateAu, int nombreLimit, List<Demande> demandes) {
 		this.statut = statut;
		this.type = type;
		this.id = id;
		this.label = label;
		this.valeurs = valeurs;
		this.description = description;
		this.open = open;
		this.dateDu = dateDu;
		this.dateAu = dateAu;
		this.nombreLimit = nombreLimit;
		this.demandes = demandes;
	}
}
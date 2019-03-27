package com.fosagri.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class Demande implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
 	private String statut;
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

    @ManyToOne
    @JoinColumn(name="prestation_id")
    Prestation prestation;

 	public Demande() {
	    super();
	}

    public Demande(String statut, String destination, String choix1, String choix2, String periode1, String periode2, String benificiare, String codAg, String email, String gsm, String passport, String dateExpPassport, String text1, Date date1, String text2, Date date2, String text3, Date date3, String text4, Date date4, int nombre, Date datedemande, String pprconj) {
        this.statut = statut;
        this.destination = destination;
        this.choix1 = choix1;
        this.choix2 = choix2;
        this.periode1 = periode1;
        this.periode2 = periode2;
        this.benificiare = benificiare;
        this.codAg = codAg;
        this.email = email;
        this.gsm = gsm;
        this.passport = passport;
        this.dateExpPassport = dateExpPassport;
        this.text1 = text1;
        this.date1 = date1;
        this.text2 = text2;
        this.date2 = date2;
        this.text3 = text3;
        this.date3 = date3;
        this.text4 = text4;
        this.date4 = date4;
        this.nombre = nombre;
        this.datedemande = datedemande;
        this.pprconj = pprconj;
    }
}
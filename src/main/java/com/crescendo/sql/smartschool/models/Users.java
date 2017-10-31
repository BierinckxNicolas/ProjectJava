package com.crescendo.sql.smartschool.models;



import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/** User POJO
 * @author Groep 5
 */
@Entity
@Table(name="smartschoolusers", schema="crescendo")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="autoId")
    private Long Id;

    @Column(name="firstname")
    private String voornaam;

    @Column(name="lastname")
    private String naam;

    @Column(name="username")
    private String gebruikersnaam;

    @Column(name="gender")
    private String geslacht;

    @Column(name="birthdate")
    private String geboortedatum;

    @Column(name="email")
    private String emailadres;

    @Column(name="mobilenumber")
    private String mobielnummer;

    @Column(name="phonenumber")
    private String telefoonnummer;

    @Column(name="stamboeknummer")
    private String stamboeknummer;

    @Column(name="street")
    private String straat;

    @Column(name="housenumber")
    private String huisnummer;

    @Column(name="country")
    private String land;

    @Column(name="place")
    private String woonplaats;

    @Column(name="status")
    private String status;

    @Column(name="internnumber")
    private String internnummer;

    public Users(String voornaam, String naam, String gebruikersnaam, String geslacht, String geboortedatum, String emailadres, String mobielnummer, String telefoonnummer, String stamboeknummer, String straat, String huisnummer, String land, String woonplaats, String status, String internnummer) {
        this.voornaam = voornaam;
        this.naam = naam;
        this.gebruikersnaam = gebruikersnaam;
        this.geslacht = geslacht;
        this.geboortedatum = geboortedatum;
        this.emailadres = emailadres;
        this.mobielnummer = mobielnummer;
        this.telefoonnummer = telefoonnummer;
        this.stamboeknummer = stamboeknummer;
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.land = land;
        this.woonplaats = woonplaats;
        this.status = status;
        this.internnummer=internnummer;
    }

    public Users() {
    }

    public String getInternnummer() {
        return internnummer;
    }

    public void setInternnummer(String internnummer) {
        this.internnummer = internnummer;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }


    public String getStraat() {

        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public String getGeslacht() {
        return geslacht;
    }

    public void setGeslacht(String geslacht) {
        this.geslacht = geslacht;
    }

    public String getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(String geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public String getEmailadres() {
        return emailadres;
    }

    public void setEmailadres(String emailadres) {
        this.emailadres = emailadres;
    }

    public String getMobielnummer() {
        return mobielnummer;
    }

    public void setMobielnummer(String mobielnummer) {
        this.mobielnummer = mobielnummer;
    }

    public String getTelefoonnummer() {
        return telefoonnummer;
    }

    public void setTelefoonnummer(String telefoonnummer) {
        this.telefoonnummer = telefoonnummer;
    }

    public String getStamboeknummer() {
        return stamboeknummer;
    }

    public void setStamboeknummer(String stamboeknummer) {
        this.stamboeknummer = stamboeknummer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "voornaam='" + voornaam + '\'' +
                ", naam='" + naam + '\'' +
                ", gebruikersnaam='" + gebruikersnaam + '\'' +
                ", geslacht='" + geslacht + '\'' +
                ", geboortedatum='" + geboortedatum + '\'' +
                ", emailadres='" + emailadres + '\'' +
                ", mobielnummer='" + mobielnummer + '\'' +
                ", telefoonnummer='" + telefoonnummer + '\'' +
                ", stamboeknummer='" + stamboeknummer + '\'' +
                ", straat='" + straat + '\'' +
                ", huisnummer='" + huisnummer + '\'' +
                ", land='" + land + '\'' +
                ", woonplaats='" + woonplaats + '\'' +
                ", status='" + status + '\'' +
                ", internnummer='" + internnummer + '\'' +
                '}';
    }
}


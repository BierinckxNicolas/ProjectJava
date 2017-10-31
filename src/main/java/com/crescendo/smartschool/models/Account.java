package com.crescendo.smartschool.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/** Account POJO
 * @author Groep 5
 */


public class Account {

    private String account;

    private String gebruikersnaam;
    private String internnummer;
    private String voornaam;
    private String naam;
    private String status;
    private String klasnummer;

    public Account() {
    }

    public Account(String gebruikersnaam, String internnummer, String voornaam, String naam, String status, String klasnummer) {
        this.gebruikersnaam = gebruikersnaam;
        this.internnummer = internnummer;
        this.voornaam = voornaam;
        this.naam = naam;
        this.status = status;
        this.klasnummer = klasnummer;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public String getInternnummer() {
        return internnummer;
    }

    public void setInternnummer(String internnummer) {
        this.internnummer = internnummer;
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

    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    public String getKlasnummer() {
        return klasnummer;
    }

    public void setKlasnummer(String klasnummer) {
        this.klasnummer = klasnummer;
    }

    @Override
    public String toString() {
        return "Account{" +
            "gebruikersnaam='" + gebruikersnaam + '\'' +
            ", internnummer='" + internnummer + '\'' +
            ", voornaam='" + voornaam + '\'' +
            ", naam='" + naam + '\'' +
            ", status='" + status + '\'' +
            ", klasnummer='" + klasnummer + '\'' +
            '}';
    }
}



package com.example.telemedecin;

public class Utilisateur {
    private int id;
    private String nom;
    private String numero;
    private String password;

    public Utilisateur(int id, String nom, String numero, String password) {
        this.id = id;
        this.nom = nom;
        this.numero = numero;
        this.password = password;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getNumero() { return numero; }
    public String getPassword() { return password; }

    public void setId(int id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setNumero(String numero) { this.numero = numero; }
    public void setPassword(String password) { this.password = password; }
}

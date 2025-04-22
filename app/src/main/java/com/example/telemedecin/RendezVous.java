package com.example.telemedecin;

public class RendezVous {
    private int id;
    private String date;
    private String heure;
    private String motif;

    public RendezVous(int id, String date, String heure, String motif) {
        this.id = id;
        this.date = date;
        this.heure = heure;
        this.motif = motif;
    }

    // Getters (obligatoires pour l'Adapter)
    public int getId() { return id; }
    public String getDate() { return date; }
    public String getHeure() { return heure; }
    public String getMotif() { return motif; }
}
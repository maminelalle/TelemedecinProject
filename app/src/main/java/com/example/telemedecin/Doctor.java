package com.example.telemedecin;

public class Doctor {
    private int id;
    private String name;
    private String speciality;
    private String numero;

    public Doctor(int id, String name, String speciality, String numero) {
        this.id = id;
        this.name = name;
        this.speciality = speciality;
        this.numero = numero;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getSpeciality() { return speciality; }
    public String getnumero() { return numero; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setSpeciality(String speciality) { this.speciality = speciality; }
    public void setnumero(String numero) { this.numero = numero; }
}

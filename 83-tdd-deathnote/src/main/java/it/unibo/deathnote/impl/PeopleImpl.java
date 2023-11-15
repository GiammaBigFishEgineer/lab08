package it.unibo.deathnote.impl;

import it.unibo.deathnote.api.People;

public class PeopleImpl implements People{
    
    private String name;
    private String deathCasue;
    private String deathDetail;


    public PeopleImpl(String name, String deathCasue, String deathDetail) {
        this.name = name;
        this.deathCasue = deathCasue;
        this.deathDetail = deathDetail;
    }

    public PeopleImpl(String name, String deathCasue) {
        this.name = name;
        this.deathCasue = deathCasue;
    }

    public PeopleImpl(String name) {
        this.name = name;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeathCasue() {
        return this.deathCasue;
    }

    public void setDeathCasue(String deathCasue) {
        this.deathCasue = deathCasue;
    }

    public String getDeathDetail() {
        return this.deathDetail;
    }

    public void setDeathDetail(String deathDetail) {
        this.deathDetail = deathDetail;
    }
}

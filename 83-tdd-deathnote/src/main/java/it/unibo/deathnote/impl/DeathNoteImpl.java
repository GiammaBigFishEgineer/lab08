package it.unibo.deathnote.impl;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.api.People;

import java.util.*;

public class DeathNoteImpl implements DeathNote {

    private List<People> peopleList = new LinkedList<>();
    
    @Override
    public String getRule(int ruleNumber) {
        if(ruleNumber > 0 || ruleNumber <= DeathNoteImpl.RULES.size()){
            return DeathNoteImpl.RULES.get(ruleNumber);
        }
        throw new IllegalArgumentException("this rule dosen't exists");
        
    }

    @Override
    public void writeName(String name) {
        People human = new PeopleImpl(name);
        if(name != null){
            peopleList.add(human);
        }
        throw new NullPointerException("any name passed");
    }

    @Override
    public boolean writeDeathCause(String cause) {
        People human = peopleList.get(peopleList.size());
        if(cause != null || !peopleList.isEmpty()){
            human.setDeathCasue(cause);
        }
        throw new IllegalStateException("List is empty or this cause is not possible");
    }

    @Override
    public boolean writeDetails(String details) {
        People human = peopleList.get(peopleList.size());
        if(details != null || !peopleList.isEmpty()){
            human.setDeathDetail(details);
        }
        throw new IllegalStateException("List is empty or this cause is not possible");
    }

    @Override
    public String getDeathCause(String name) {
        
    }

    @Override
    public String getDeathDetails(String name) {
        
    }

    @Override
    public boolean isNameWritten(String name) {
        
    }
    
}

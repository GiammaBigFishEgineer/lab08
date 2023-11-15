package it.unibo.deathnote.impl;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.api.People;
import java.util.*;

public class DeathNoteImpl implements DeathNote {

    private List<People> peopleList = new LinkedList<>();
    private TimeSet timing = new TimeSet();

    public List<People> getPeopleList(){
        return this.peopleList;
    }
    
    @Override
    public String getRule(int ruleNumber) {
        if(ruleNumber < 0 || ruleNumber > DeathNoteImpl.RULES.size()){
            throw new IllegalArgumentException("this rule dosen't exists");
        }
        return DeathNoteImpl.RULES.get(ruleNumber);
        
    }

    @Override
    public void writeName(String name) {
        People human = new PeopleImpl(name);
        if(name == null || peopleList.contains(human)){
            throw new NullPointerException("any name passed or just presents");
        }
        peopleList.add(human);
        timing.setStartTime();
        
    }

    @Override
    public boolean writeDeathCause(String cause) throws IllegalStateException, IndexOutOfBoundsException {
        People human = peopleList.get(peopleList.size() - 1);
        if(cause == null){
            throw new IllegalStateException("This cause is not possible");
        }
        if(peopleList.isEmpty()){
            throw new IndexOutOfBoundsException("List is empty");
        }
        human.setDeathCasue(cause);
        timing.setEndTime();
        if(timing.calculateDifference() < 40 && timing.calculateDifference() >= 0){
            timing.setStartTime();
            return true;
        }else{
            timing.resetTime();
            return false;
        }
        
        
    }

    @Override
    public boolean writeDetails(String details) {
        People human = peopleList.get(peopleList.size() - 1);
        if(details == null){
            throw new IllegalStateException("This details is not possible");
        }
        if(peopleList.isEmpty()){
            throw new IndexOutOfBoundsException("List is empty");
        }
        human.setDeathDetail(details);
        timing.setEndTime();
        if(timing.calculateDifference() < 6000 && timing.calculateDifference() >= 0){
            timing.resetTime();
            return true;
        }else{
            timing.resetTime();
            return false;
        }
    }

    @Override
    public String getDeathCause(String name) {
        for(People i: peopleList){
            if(i.getName().equals(name)){
                return i.getDeathCasue();
            }
        }
        throw new IllegalArgumentException("Name's not presents in list");
    }

    @Override
    public String getDeathDetails(String name) {
        for(People i: peopleList){
            if(i.getName().equals(name)){
                return i.getDeathDetail();
            }
        }
        throw new IllegalArgumentException("Name's not presents in list");
    }

    @Override
    public boolean isNameWritten(String name) {
        for(People i: peopleList){
            if(i.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
    
    public class TimeSet{
        private double startTime;
        private double endTime;

        public void setStartTime(){
            this.startTime = System.currentTimeMillis();
        }

        public void setEndTime(){
            this.endTime = System.currentTimeMillis();
        }

        public double getStartTime(){
            return this.startTime;
        }

        public double getEndTime(){
            return this.endTime;
        }

        public double calculateDifference(){
            return this.getStartTime()-this.getEndTime();
        }

        public void resetTime(){
            this.endTime = 0;
            this.startTime = 0;
        }
        
    }
}

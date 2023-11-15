package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.api.People;

class TestDeathNote {

    private static List<String> RULES = DeathNote.RULES;
    private People person;
    
    @Test
    public void testGetRule(){
        for(String i: RULES){
            assertNotNull(i);
        }
    }

    @Test
    public void testWriteName(){
        person.setName("test");
        assertEquals(person.getName(), "test");
    }
    
    @Test
    public void testWriteDeathCause(){
        person.setDeathCasue("test");
        assertEquals(person.getDeathCasue(), "test");
    }

    @Test
    public void testWriteDetails(){
        person.setDeathDetail("test");
        assertEquals(person.getDeathDetail(), "test");
    }

    @Test
    public void testIsNameWritten(){
        assertNotNull(person.getName());
    }
}
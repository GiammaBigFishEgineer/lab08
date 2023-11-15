package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.deathnote.api.People;
import it.unibo.deathnote.impl.PeopleImpl;

public class PeopleTest {
    private People person;

    @BeforeEach
    public void setUp() {
        this.person = new PeopleImpl("test");
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

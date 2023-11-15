package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import org.junit.jupiter.api.Test;
import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.api.People;
import it.unibo.deathnote.impl.DeathNoteImpl;

class TestDeathNote {

    private static List<String> RULES = DeathNote.RULES;
    private static final String nameTest = "Pluto";
    private DeathNote note = new DeathNoteImpl();
    
    @Test
    public void testGetRule(){
        for(String i: RULES){
            assertNotNull(i);
            assertFalse(i.isBlank());
        }
    }

    @Test
    public void testNotExistsRule(){
        try{
            note.getRule(0);
            note.getRule(-1);
        }catch(IllegalArgumentException e){
            assertNotNull(e);
            assertFalse(e.getMessage().isBlank());
            assertTrue(e.getMessage().length() > 10);
        }
    }

    @Test
    public void testIsNotWritten(){
        try{
            note.writeName("test");
            note.writeName("test");
        }catch(NullPointerException e){
            assertNotNull(e);
            assertFalse(e.getMessage().isBlank());
            assertTrue(e.getMessage().length() > 10);
            int temp = 0;
            for(People i: note.getPeopleList()){
                if(i.getName() == "test"){
                    temp++;
                }
            }
            assertEquals(1, temp);
        }
    }

    @Test
    public void testTimeDeathCause(){
        note.writeName("test");
        Boolean writtenInTime = note.writeDeathCause("Killed");
        Boolean writtenInTime2 = note.writeDetails("Murdered");
        assertTrue(writtenInTime);
        assertTrue(writtenInTime2);
    }

    @Test
    public void testNoNameSetted(){
        try{
            note.writeDeathCause("Killed");
        }catch(IndexOutOfBoundsException e){
            assertNotNull(e);
            assertFalse(e.getMessage().isBlank());
            assertTrue(e.getMessage().length() > 10);
        }
        
    }
    @Test
    public void testHeartAttack() throws InterruptedException{
        note.writeName(nameTest);
        note.writeDeathCause("Heart attack");
        assertEquals("Heart attack", note.getDeathCause(nameTest));
        note.getDeathCause(nameTest);
        note.writeName("Gigi");
        Boolean inTime = note.writeDeathCause("karting accident");
        assertTrue(inTime);
        Thread.sleep(100);
        inTime = note.writeDeathCause("falled from mountain");
        assertFalse(inTime);
        
    }

    @Test
    public void testRanTooLong() throws InterruptedException{
        note.writeName(nameTest);
        assertNull(note.getDeathDetails(nameTest));
        note.writeDeathCause("run for too long");
        assertEquals("run for too long", note.getDeathCause(nameTest));
        note.writeName("Gigi");
        Thread.sleep(6100);
        Boolean inTime = note.writeDetails("killed by girlfriend");
        assertFalse(inTime);

    }

    @Test
    public void testNotWriteName(){
        try{
            note.writeName(null);
            fail("Name null passed");
        }catch(NullPointerException e){
            assertNotNull(e);
        }
    }

    @Test
    public void testNulltWriteDeathCause(){
        try{
            note.writeDeathCause(null);
        }catch(IllegalStateException e){
            assertNotNull(e);
        }catch(IndexOutOfBoundsException a){
            assertNotNull(a);
        }
    }

    @Test
    public void testEmptytWriteDeathCause(){
        try{
            note.writeDeathCause("test");
        }catch(IllegalStateException e){
            assertNotNull(e);
        }catch(IndexOutOfBoundsException a){
            assertNotNull(a);
        }
    }

    @Test
    public void testNullWriteDeathDetail(){
        try{
            note.writeDetails(null);
        }catch(IllegalStateException e){
            assertNotNull(e);
        }catch(IndexOutOfBoundsException a){
            assertNotNull(a);
        }
    }

    @Test
    public void testEmptyWriteDeathDetail(){
        try{
            note.writeDetails("test");
        }catch(IllegalStateException e){
            assertNotNull(e);
        }catch(IndexOutOfBoundsException a){
            assertNotNull(a);
        }
    }

}
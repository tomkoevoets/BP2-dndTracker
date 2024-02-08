package com.dndtracker.bp2dndtracker.classes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


class ControllerTest {
    Controller cl = new Controller();

// item section

    // Test for creating an item
    @Test
    void createItem() {
        cl.createItem("peter", "this is peter", "scroll", "common", 1, 1, "extra test" );
        // Check if items list is not empty after creation
        assert !cl.getItems().isEmpty();
    }

    // Test for deleting an item
    @Test
    void deleteItem() {
        cl.deleteItem(27);
        // Check if the last item's ID is not 27 after deletion
        assert cl.getItems().getLast().getId() != 27;
    }

    // Test for updating an item
    @Test
    void updateItem() {
        Item item = new Item(30, "Obsidian Crown", "none", "amulet.jpg", "amulet", "common",
                100, 50, "tiara made of obsidian");
        cl.updateItem(item);
        // Check if the item with ID 30 has its name updated to "Obsidian Crown"
        for (Item i: cl.getItems()) {
            if(i.getId() == 30) {
                assert i.getName().equals("Obsidian Crown");
            }
        }
    }

    // Test for getting items
    @Test
    void getItems() {
        // Check if the items list is not empty
        assert!cl.getItems().isEmpty();
    }

// session section

    // Test for getting sessions
    @Test
    void getSessions() {
        // Check if the sessions list is not empty
        assert!cl.getSessions().isEmpty();
    }

    // Test for adding a session
    @Test
    void addSession() {
        cl.createSession( "test session", "test session", "test session");
        // Check if the sessions list is not empty after addition
        assert !cl.getSessions().isEmpty();
    }

    // Test for deleting a session
    @Test
    void deleteSession() {
        cl.deleteSession(60);
        // Check if the last session's ID is not 60 after deletion
        assert cl.getSessions().getLast().getId() != 60;
    }

    // Test for updating a session
    @Test
    void updateSession() {
        Session s = new Session(60, "test", "test", "test");
        cl.updateSession(s);
        // Check if the session with ID 30 has its name updated to "test"
        for (Session session: cl.getSessions()) {
            if(session.getId() == 30) {
                assert session.getName().equals("test");
            }
        }
    }

// character section

    // Test for getting monsters
    @Test
    void getMonsters() {
        // Check if the monsters list is not empty
        assert!cl.getMonsters().isEmpty();
    }

    // Test for getting NPCs
    @Test
    void getNpcs() {
        // Check if the NPCs list is not empty
        assert!cl.getNpcs().isEmpty();
    }

    // Test for adding a monster
    @Test
    void addMonster() {
        cl.createCharacter( "Monster", "test", "test", "test", "test",
                "test", "test", "test", "test", "test", "test",
                "test", "test", "test", "test", "test", "test");
        // Check if the monsters list is not empty after addition
        assert !cl.getMonsters().isEmpty();
    }

    // Test for adding an NPC
    @Test
    void addNpc() {
        cl.createCharacter( "Npc", "test", "test", "test", "test",
                "test", "test", "test", "test", "test", "test",
                "test", "test", "test", "test", "test", "test");
        // Check if the NPCs list is not empty after addition
        assert !cl.getNpcs().isEmpty();
    }

    // Test for deleting a monster
    @Test
    void deleteMonster() {
        cl.deleteCharacter(23);
        // Check if the last monster's ID is not 23 after deletion
        assert cl.getMonsters().getLast().getId()!= 23;
    }

    // Test for updating a character
    @Test
    void updateCharacter() {
        CharacterSuperclass cs = new CharacterSuperclass(23, "test1", "test", "test", "test",
                "test", "test", "test", "test", "test", "test",
                "test", "test", "test", "test", "test", "test", "test");
        cl.updateCharacter(cs);
        // Check if the character with ID 23 has its name updated to "test1"
        for (CharacterSuperclass character: cl.getMonsters()) {
            if(character.getId() == 23) {
                assert character.getName().equals("test1");
            }
        }
    }

    // Test for linking a character to a session
    @Test
    void linkCharacterToSession() {
        cl.linkCharacterToSession(58, 19);
        for (Session s : cl.getSessions()) {
            if(s.getId() == 58) {
                boolean characterFound = false;
                for (CharacterSuperclass c : cl.getNpcs()) {
                    if (c.getId() == 19) {
                        characterFound = true;
                        break;
                    }
                }
            }
        }
    }

    // Test for getting characters by session ID
    @Test
    void getCharacterBySessionId() {
        // Check if the list of characters for session ID 58 is not empty
        assert!cl.getCharacterBySessionId(58).isEmpty();
    }

}
package com.dndtracker.bp2dndtracker.classes;

import org.junit.jupiter.api.Test;


class ControllerTest {
    Controller cl = new Controller();

    @Test
    void createItem() {
        cl.createItem("peter", "this is peter", "scroll", "common", 1, 1, "extra test" );
        assert !cl.getItems().isEmpty();
    }

    @Test
    void deleteItem() {
        cl.deleteItem(27);
        assert cl.getItems().getLast().getId() != 27;
    }
}
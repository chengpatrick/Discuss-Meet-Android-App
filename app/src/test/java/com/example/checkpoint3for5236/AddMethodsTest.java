package com.example.checkpoint3for5236;

import static org.junit.Assert.*;

import org.junit.Test;

public class AddMethodsTest {

    @Test
    public void addClassClassnameTest() {
        String input = "CSE 1223";
        Class output;
        String expected = "CSE 1223";

        AddMethods addMethods = new AddMethods();
        output = addMethods.addClass(input);
        assertEquals(expected, output.getClassname());
    }

    @Test
    public void addClassEmptyClassnameTest() {
        String input = "";
        Class output;
        Class expected = null;

        AddMethods addMethods = new AddMethods();
        output = addMethods.addClass(input);
        assertEquals(expected, output);
    }

    @Test
    public void addDiscussionTitleTest() {
        String inputTitle = "Question1";
        String inputMainText = "solution for question 1";
        String inputUserID = "11223344";
        Discussion output;
        String expected = "Question1";

        AddMethods addMethods = new AddMethods();
        output = addMethods.addDiscussion(inputTitle, inputMainText, inputUserID);
        assertEquals(expected, output.getTitle());
    }

    @Test
    public void addDiscussionEmptyTitleTest() {
        String inputTitle = "";
        String inputMainText = "solution for question 1";
        String inputUserID = "11223344";
        Discussion output;
        Discussion expected = null;

        AddMethods addMethods = new AddMethods();
        output = addMethods.addDiscussion(inputTitle, inputMainText, inputUserID);
        assertEquals(expected, output);
    }

    @Test
    public void addDiscussionMainTextTest() {
        String inputTitle = "Question1";
        String inputMainText = "solution for question 1";
        String inputUserID = "11223344";
        Discussion output;
        String expected = "solution for question 1";

        AddMethods addMethods = new AddMethods();
        output = addMethods.addDiscussion(inputTitle, inputMainText, inputUserID);
        assertEquals(expected, output.getMainText());
    }

    @Test
    public void addDiscussionUserIDTest() {
        String inputTitle = "Question1";
        String inputMainText = "solution for question 1";
        String inputUserID = "11223344";
        Discussion output;
        String expected = "11223344";

        AddMethods addMethods = new AddMethods();
        output = addMethods.addDiscussion(inputTitle, inputMainText, inputUserID);
        assertEquals(expected, output.getUserID());
    }
}
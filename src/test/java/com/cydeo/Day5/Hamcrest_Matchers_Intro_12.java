package com.cydeo.Day5;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Hamcrest_Matchers_Intro_12 {

    @Test
    public void simpleTest1() {

        System.out.println("---------------------------is(  equalTo()) Method --------------------------------------------");
        assertThat(5 + 5, is(10));
        //Here is checking the equation, it's like a sentence.

        // assertThat(5 + 5, is(11));
        //Expected: is <11>
        //     but: was <10>
        //Expected :is <11>
        //Actual   :<10>

        assertThat(5 + 5, equalTo(10));
        //equalTo() method has the same functionality as is() method
        //OR;
        assertThat(5 + 5, is(equalTo(10)));
        //This method only increases the readability, it is just that.
        /*
        Matchers has 2 overloaded versions
         First that accept actual value
            Second that accept another matcher
                Above example is() method accept another matchers equalTo() make it readable
         */


        System.out.println("---------------------------is( not( equalTo())) Method --------------------------------------------");


        assertThat(5 + 5, not(9));
        assertThat(5 + 5, is(not(9)));
        assertThat(5 + 5, is(not(equalTo(9))));
        //All of them gives the same result. There is a web site for matcher methods.

        System.out.println("---------------------------Number comparison Methods --------------------------------------------");

        //greaterThan()
        assertThat(5 + 5, greaterThan(9));

        //greaterThanOrEqualTo()
        assertThat(5 + 5, greaterThanOrEqualTo(10));

        //lessThan()
        assertThat(5 + 5, lessThan(11));

        //lessThanOrEqualTo()
        assertThat(5 + 5, lessThanOrEqualTo(11));
    }

    @DisplayName("Assertion With String")
    @Test
    public void stringHamcrest() {

        System.out.println("===================================== Assertion With String =====================================");
        String text = "EU_9 is learning HamCrest";

        System.out.println("---------------------------is(equalTo()) --------------------------------------------");
        assertThat(text, is("EU_9 is learning HamCrest"));
        assertThat(text, equalTo("EU_9 is learning HamCrest"));
        assertThat(text, is(equalTo("EU_9 is learning HamCrest")));

        System.out.println("---------------------------startsWith() --------------------------------------------");
        //check if the text starts with EU_9
        assertThat(text, startsWith("EU_9"));
        assertThat(text, startsWithIgnoringCase("eu_9"));

        System.out.println("--------------------------- endsWith() --------------------------------------------");
        //endsWith
        assertThat(text, endsWith("rest"));

        System.out.println("--------------------------- contains() --------------------------------------------");
        //contains ?
        assertThat(text, containsString("learning"));
        assertThat(text, containsStringIgnoringCase("LEarning"));

        System.out.println("--------------------------- isBlank()/isEmpty --------------------------------------------");
        //check if string is blank
        String str = "   ";
        assertThat(str, blankString());

        //check if the trimmed string is empty
        assertThat(str.trim(), emptyString());
    }

    @DisplayName("HamCrest Fpr Collection")
    @Test
    public void testCollection() {
        System.out.println("============================ HamCrest Fpr Collection ============================");

        System.out.println("--------------------------- hasSize() --------------------------------------------");
        List<Integer> listOfNumbers = Arrays.asList(1, 2, 4, 5, 6, 32, 55, 67, 38);
        //Check size of the list
        assertThat(listOfNumbers,hasSize(9));

        System.out.println("--------------------------- hasItem()/hasItems() --------------------------------------------");
        //check if list has 67
        assertThat(listOfNumbers,hasItem(67));
       // assertThat(listOfNumbers,hasItem(77));
        //java.lang.AssertionError:
        //Expected: a collection containing <77>
        //     but: mismatches were: [was <1>, was <2>, was <4>, was <5>, was <6>, was <32>, was <55>, was <67>, was <38>]

        //check if list has 67, 38, 1, 4
        assertThat(listOfNumbers,hasItems(67,4,1,38));

        System.out.println("--------------------------- Iteration --------------------------------------------");
        // check if all numbers are greater than 0
        assertThat(listOfNumbers,everyItem(greaterThan(0)));
        //It automatically loops every number and check the condition.
            // We can change the inner condition as lessThan(), hasItems, startsWith, etc.




    }

}

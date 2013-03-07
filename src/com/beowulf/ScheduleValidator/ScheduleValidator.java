package com.beowulf.ScheduleValidator;

import java.io.File;

import com.beowulf.ScheduleValidator.model.*;
import com.beowulf.ScheduleValidator.test.ConsistencyTest;
import com.beowulf.ScheduleValidator.util.FileParser;

public class ScheduleValidator
{

    public static void main(String[] args)
    {

        FileParser myParser = new FileParser(new File("conf\\constraints.conf"), new File("conf\\definitions.conf"), new File("conf\\plan.conf"));

        University myUni = myParser.parseConstraintsOnly();
        University myUni2 = myParser.parse();

        if (ConsistencyTest.testDefinitions(myUni, true))
        {
            System.out.println(">>> Definitions are consistent.. ");
            
            if(ConsistencyTest.testDefinitions(myUni2, false))
            {
                System.out.println("There were conflicts in the timetable, please refer to the errormessages before.");
            }
            else
            {
                System.out.println(">>> There were no conflicts in the timetable..");
            }
        }
        else
        {
            System.out.println("Definitions are NOT consistent.");
        }

    }

}

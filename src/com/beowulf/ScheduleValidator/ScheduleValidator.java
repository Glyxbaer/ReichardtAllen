package com.beowulf.ScheduleValidator;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import com.beowulf.ScheduleValidator.model.*;
import com.beowulf.ScheduleValidator.test.ConsistencyTest;
import com.beowulf.ScheduleValidator.util.FileParser;
import java.io.Console;
import java.util.Scanner;

public class ScheduleValidator
{

    public static void main(String[] args)
    {

        FileParser myParser = new FileParser(new File("conf\\constraints.conf"), new File("conf\\definitions.conf"), new File("conf\\plan.conf"));

        University myUni = myParser.parseConstraintsOnly();
        University myUni2 = myParser.parse();
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter the opening time (Format: HH:MM): ");
        
        if (!ConsistencyTest.testOpening(myUni,input.next()))
        {
            System.out.println(">>> Opening hours do not fit the timetable. The University has to be opened BEFORE the first lecture starts.");
        }

        if (ConsistencyTest.testDefinitions(myUni, true))
        {
            System.out.println(">>> Constraints are consistent.. ");
            
            if(ConsistencyTest.testDefinitions(myUni2, false))
            {
                System.out.println("[ERROR] There were conflicts in the timetable, please refer to the errormessages before.");
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

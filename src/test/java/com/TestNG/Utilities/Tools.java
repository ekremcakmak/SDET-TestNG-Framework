package com.TestNG.Utilities;

public class Tools {

public static void sleep(int millisecond)

{
    try
    {
        Thread.sleep(millisecond);
    }
    catch (InterruptedException ex)
    {
        ex.printStackTrace();
    }

}

}

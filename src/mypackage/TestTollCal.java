package mypackage;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestTollCal {

    @Test
    public void testCostOfTrip() {
        String st = TollCal.costOfTrip("QEW", "Highway 400");
        assertEquals("Trip between QEW and Highway 400 :\n Distance : 67.75 KMs\n Cost : $16.94", st);

        String st1 = TollCal.costOfTrip("Salem Road", "QEW");
        assertEquals("Trip between Salem Road and QEW :\n Distance : 107.96 KMs\n Cost : $26.99", st1);

        String st2 = TollCal.costOfTrip("QEW", "Salem Road");
        assertEquals("Trip between QEW and Salem Road :\n Distance : 115.28 KMs\n Cost : $28.82", st2);

        String st3 = TollCal.costOfTrip("Invalid", "Interchange");
        assertEquals("Invalid From and To Interchanges. Please recheck given interchanges", st3);
    }
}

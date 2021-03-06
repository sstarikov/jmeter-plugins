package kg.apc.jmeter.functions;

import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author undera
 */
public class FifoPutTest {


    @Test
    public void testExecute() throws Exception {
        System.out.println("execute");
        SampleResult previousResult = null;
        Sampler currentSampler = null;
        FifoPut instance = new FifoPut();
        String expResult = "FifoPuttest";
        LinkedList<CompoundVariable> list = new LinkedList<CompoundVariable>();
        list.add(new CompoundVariable("FifoPuttest"));
        list.add(new CompoundVariable("FifoPuttest"));
        instance.setParameters(list);
        String result = instance.execute(previousResult, currentSampler);
        assertEquals(expResult, result);
    }

    @Test
    public void testSetParameters() throws Exception {
        System.out.println("setParameters");
        LinkedList<CompoundVariable> list = new LinkedList<CompoundVariable>();
        list.add(new CompoundVariable("FifoPuttest"));
        list.add(new CompoundVariable("FifoPuttest"));
        FifoPut instance = new FifoPut();
        instance.setParameters(list);
    }

    @Test
    public void testGetReferenceKey() {
        System.out.println("getReferenceKey");
        FifoPut instance = new FifoPut();
        String expResult = "__fifoPut";
        String result = instance.getReferenceKey();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetArgumentDesc() {
        System.out.println("getArgumentDesc");
        FifoPut instance = new FifoPut();
        List result = instance.getArgumentDesc();
        assertNotNull(result);
    }
}

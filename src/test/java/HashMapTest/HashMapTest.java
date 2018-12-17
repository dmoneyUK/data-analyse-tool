package HashMapTest;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {

    private Map<TestPerson, Integer> map = new HashMap();
    private final String john = "john";

    @Test
    public void testHashCodeAndEquals() {

        TestPerson testPerson = new TestPerson(john);
        recordVisit(john);

        Assert.assertEquals((int) map.get(testPerson), 1);

        recordVisit(john);

        Assert.assertEquals((int) map.get(testPerson), 2);

        recordVisit(john);

        Assert.assertEquals((int) map.get(testPerson), 3);


    }

    private void recordVisit(String name) {
        TestPerson p = new TestPerson(name);
        Integer count = map.get(p);
        if (count == null) {
            map.put(p, 1);
        } else {
            map.put(p, count + 1);
        }
    }
}

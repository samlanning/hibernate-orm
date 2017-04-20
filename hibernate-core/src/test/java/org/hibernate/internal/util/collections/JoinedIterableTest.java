package org.hibernate.internal.util.collections;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by sam on 19/04/17.
 */
public class JoinedIterableTest {

    @Test
    public void testReuse() {
        List<String> la = Arrays.<String>asList("foo", "bar");
        List<String> lb = Arrays.<String>asList("baz");

        Iterable<String> joined = new JoinedIterable<>(Arrays.asList(la, lb));

        int countA = testConcat(joined);
        int countB = testConcat(joined);

        assertEquals("First iteration does not have expected number of items", 3, countA);
        assertEquals("Second iteration does not have expected number of items", 3, countB);
    }

    private static int testConcat(Iterable<String> joined) {
        int count = 0;
        for (String s : joined) {
            switch(count) {
                case 0:
                    assertEquals("foo", s);
                    break;
                case 1:
                    assertEquals("bar", s);
                    break;
                case 2:
                    assertEquals("baz", s);
                    break;
            }
            count++;
        }
        return count;
    }

}

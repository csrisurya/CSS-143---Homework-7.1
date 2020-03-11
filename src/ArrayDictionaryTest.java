import org.junit.Test;

import java.lang.reflect.Array;

import static org.junit.Assert.*;

public class ArrayDictionaryTest {
    @Test
    public void demo() {
        int testSize = 5;
        ArrayDictionary dict = new ArrayDictionary(testSize);
        assertTrue(dict.add(2, 82));
        assertTrue(dict.add(4, 84));
        assertTrue(dict.add(7, 87));
        System.out.println(dict);
    }

    @Test
    public void remove() {
        ArrayDictionary emptyDict = new ArrayDictionary(0); // empty dictionary
        int testKey1 = 2;
        assertFalse(emptyDict.remove(testKey1));

        ArrayDictionary dict2 = new ArrayDictionary(2); // key exists in a dictionary that has no collision
        dict2.add(0, 101); dict2.add(1, 102);
        int testkey2 = 0;
        assertTrue(dict2.remove(testkey2));

        ArrayDictionary dict3 = new ArrayDictionary(3); // key does not exists in a dictionary that has no collision
        dict3.add(0, 101); dict3.add(1, 102);
        int testkey3 = 2;
        assertFalse(dict3.remove(testkey3));

        ArrayDictionary dict4 = new ArrayDictionary(2); // key exists in a dictionary that has collision, key is in the collision.
        dict4.add(0, 101); dict4.add(1, 102); dict4.add(2, 103);
        int testkey4 = 2;
        assertTrue(dict4.remove(testkey4));

        ArrayDictionary dict5 = new ArrayDictionary(3); //key does not exists in a dictionary that has collision.
        dict5.add(0, 101); dict5.add(1, 102); dict5.add(3, 104);
        int testkey5 = 2;
        assertFalse(dict5.remove(testkey5));
    }

    @Test
    public void contains() {
        int[] emptyListForNoKeys = {-1,0,1}; // set 1
        for(int key : emptyListForNoKeys){
            ArrayDictionary dict = new ArrayDictionary(0);
            assertFalse(dict.contains(key));
        }

        int[] listForOneKey = {2,0}; // set 2
        boolean[] expected1 = {false, true};
        for(int i = 0; i < listForOneKey.length; i++){
            ArrayDictionary dict = new ArrayDictionary(1);
            dict.add(0, 103);
            assertEquals(expected1[i], dict.contains(listForOneKey[i]));
        }

        int[] listForTwoKeys = {2,1,0,3}; // set 3
        boolean[] expected2 = {false, true, true, false};
        for(int i = 0; i < listForOneKey.length; i++){
            ArrayDictionary dict = new ArrayDictionary(2);
            dict.add(0, 103); dict.add(1, 105);
            assertEquals(expected2[i], dict.contains(listForTwoKeys[i]));
        }

        ArrayDictionary dictForThreeKeys1 = new ArrayDictionary(3); // set 4
        dictForThreeKeys1.add(0,103); dictForThreeKeys1.add(1,105);
        assertFalse(dictForThreeKeys1.contains(3));

        ArrayDictionary dictForThreeKeys2 = new ArrayDictionary(3);
        dictForThreeKeys2.add(0,103); dictForThreeKeys2.add(1,105); dictForThreeKeys2.add(2,206);
        assertTrue(dictForThreeKeys2.contains(1));

        int[] listForThreeKeys = {1,7,4,8}; // set 5
        boolean[] expected3 = {true, false, true, false};
        for(int i = 0; i < listForThreeKeys.length; i++){
            ArrayDictionary dict = new ArrayDictionary(3);
            dict.add(0, 103); dict.add(1,105); dict.add(2, 206); dict.add(4, 407);
            assertEquals(expected3[i], dict.contains(listForThreeKeys[i]));
        }
    }
}


import HashMap.MyHashMap;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class MyHashMapTest {
    private MyHashMap<String, String> map;

    @BeforeEach
    public void initMap() {
        map  = new MyHashMap<>(17);
        map.put("USA", "Washington DC");
        map.put("Nepal", "Kathmandu");
        map.put("India", "New Delhi");
        map.put("Australia", "Sydney");
    }

    @Test
    public void instantietedMapIsNotNull() {
        assertNotNull(map);
    }

    @Test
    public void puttingOnTheSameKeyReplacesValue() {
        map.put("USA", "Brooklyn");
        assertFalse(map.containsValue("Washington DC"));
    }

    @Test
    public void puttingIncreasesSize() {
        int currentSize = map.size();
        map.put("Bulgaria", "Sofia");
        assertEquals(currentSize + 1, map.size());
    }

    @Test
    public void keySetContainsAllKeys() {
    }

    @Test
    public void containsKeyWorksWithContainedKeys() {
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            assertTrue(map.containsKey(key));
        }
    }

    @Test
    public void mapDoesNotContainKeyAfterRemove() {
        map.remove("Bulgaria");
        assertFalse(map.containsKey("Bulgaria"));
    }

    @Test
    public void removeDecreasesSize() {
        int currentSize = map.size();
        map.remove("USA");
        assertEquals(currentSize - 1, map.size());
    }

    @Test
    public void putAllTest() {
        MyHashMap<String, String> tempMap = new MyHashMap<>();
        tempMap.put("Romania", "Bucharest");
        tempMap.put("Serbia", "Belgrade");
        tempMap.put("Turkey", "Ankara");
        map.putAll(tempMap);
        assertTrue(map.containsKey("Romania"));
        assertTrue(map.containsKey("Serbia"));
        assertTrue(map.containsKey("Turkey"));
    }

    @Test
    public void mapEntrySetIsEmptyAfterClear() {
        map.clear();
        assertTrue(map.entrySet().isEmpty());
    }

    @Test
    public void getHashCodeReturnSameHashForEqualKeys() {
        String firstKey = "Bulgaria";
        String secondKey = "Bulgaria";
        assertTrue(firstKey.equals(secondKey));
        assertTrue(map.getHash(firstKey) == map.getHash(secondKey));
    }

    /*TODO: Try to fix it, because it spits this out:
    java.lang.IllegalStateException:
     Could not initialize plugin: interface org.mockito.plugins.MockMaker (alternate: null)
    @SuppressWarnings("unchecked")
    @Test
    public void mapRehashesAfterLoadFactorLimitIsExceeded() {
        MyHashMap<String, String> mockMap = (MyHashMap<String, String>) Mockito.mock(MyHashMap.class);
        int currentCapacity = mockMap.getCapacity();

        Mockito.when(mockMap.getCurrentLoadFactor()).thenReturn(mockMap.getMaxLoadFactor() + 0.1);
        mockMap.put("Spain", "Madrid");
        assertEquals(currentCapacity * 2, mockMap.getCapacity());
    }*/
}

package RootCodeChallengeTest;
import RootCodeChallengeSolution.Solution;
import RootCodeChallengeSolution.Driver;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class DriversStatusTest {
    @Test
    public void getDriversStatusTest() throws Exception {
        //handle the input:
        //handle the output:
        Map<String, RootCodeChallengeSolution.Driver> driverMap = new HashMap<>();
        List<String> driver1 = Arrays.asList("Driver", "Dan");
        List<String> driver2 = Arrays.asList("Driver", "Lauren");
        List<String> driver3 = Arrays.asList("Driver", "Kumi");
        List<String> trip1 = Arrays.asList("Trip", "Kumi", "07:15", "07:45", "17.3");
        List<String> trip2 = Arrays.asList("Trip", "Dan", "06:12", "06:32", "21.8");
        List<String> trip3 = Arrays.asList("Trip", "Lauren", "12:01", "13:16", "42.0");
        List<List<String>> res = Arrays.asList(driver1, driver2, driver3, trip1, trip2, trip3);
        for (List<String> str : res) {
            Driver curDriver = new Solution().getCurDriverStatus(str, driverMap);
            driverMap.put(curDriver.getName(), curDriver);
        }
        List<String> result = new Solution().getAllResult(driverMap);
        StringBuilder sb = new StringBuilder();
        while (result.iterator().hasNext()) {
            String str = result.iterator().next();
            sb.append(str).append("\n");
            result.remove(result.iterator().next());
        }
        assertEquals("It is a successful build",
                "Lauren: 42 miles @ 34 mph" + "\n" +
                "Dan: 39 miles @ 47 mph" + "\n" +
                "Kumi: 0 miles" + "\n", sb.toString());
    }

    /*
    The other test cases which is a successful build as well.
    @Test
    public void getDriversStatusTest() throws Exception {
        //handle the input:
        //handle the output:
        Map<String, RootCodeChallengeSolution.Driver> driverMap = new HashMap<>();
        List<String> driver1 = Arrays.asList("Driver", "Dan");
        List<String> driver2 = Arrays.asList("Driver", "Lauren");
        List<String> driver3 = Arrays.asList("Driver", "Kumi");
        List<String> trip1 = Arrays.asList("Trip", "Kumi", "07:15", "07:45", "17.3");
        List<String> trip2 = Arrays.asList("Trip", "Dan", "06:12", "06:32", "21.8");
        List<String> trip3 = Arrays.asList("Trip", "Lauren", "12:01", "13:16", "42.0");
        List<String> trip4 = Arrays.asList("Trip", "Kumi", "07:15", "07:45", "17.3");
        List<String> trip5 = Arrays.asList("Trip", "Lauren", "12:01", "13:16", "42.0");
        List<List<String>> res = Arrays.asList(driver1, driver2, driver3, trip1, trip2, trip3, trip4, trip5);
        for (List<String> str : res) {
            Driver curDriver = new Solution().getCurDriverStatus(str, driverMap);
            driverMap.put(curDriver.getName(), curDriver);
        }
        List<String> result = new Solution().getAllResult(driverMap);
        StringBuilder sb = new StringBuilder();
        while (result.iterator().hasNext()) {
            String str = result.iterator().next();
            sb.append(str).append("\n");
            result.remove(result.iterator().next());
        }
        assertEquals("It is a successful build",
                "Lauren: 84 miles @ 34 mph" + "\n" +
                "Kumi: 35 miles @ 35 mph" + "\n" +
                "Dan: 22 miles @ 65 mph" + "\n", sb.toString());
    }
     */
}

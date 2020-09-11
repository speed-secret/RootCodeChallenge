package RootCodeChallengeSolution;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.util.*;

/*
High LEVEL:
1. DRIVER Class
2. Trip Class
3. Solution Class
 */

public class Solution {
    public static void main(String[] args) throws ParseException {
        //detect each drivers as well as each trip
        //put each driver as a new HashMap with key is: String
        //and its value is : Driver(class)
        /*
        StringTokenizer st;
        BufferedReader TSVFile = new BufferedReader(new FileReader(args[0]));
        List<List<String>> stringsOfDriver = new ArrayList<>();
        List<List<String>> stringsOfTrip = new ArrayList<>();
        try {
            String dataRow = TSVFile.readLine(); // Read first line.
            while (dataRow != null) {
                st = new StringTokenizer(dataRow, "\t");
                List<String> dataArray = new ArrayList<String>();
                if (dataArray.get(0).equals("Driver")) {
                    stringsOfDriver.add(dataArray);
                }
                else if (dataArray.get(0).equals("Trip")) {
                    stringsOfTrip.add(dataArray);
                }
                while (st.hasMoreElements()) {
                    dataArray.add(st.nextElement().toString());
                }
                for (String item : dataArray) {
                    System.out.print(item + "  ");
                }
                System.out.println(); // Print the data line.
                dataRow = TSVFile.readLine(); // Read next line of data.
            }
         */
        List<String> driver1 = Arrays.asList("Driver", "Dan");
        List<String> driver2 = Arrays.asList("Driver", "Lauren");
        List<String> driver3 = Arrays.asList("Driver", "Kumi");
        List<String> trip1 = Arrays.asList("Trip", "Dan", "07:15", "07:45", "17.3");
        List<String> trip2 = Arrays.asList("Trip", "Dan", "06:12", "06:32", "21.8");
        List<String> trip3 = Arrays.asList("Trip", "Lauren", "12:01", "13:16", "42.0");
        Map<String, Driver> driverMap = new HashMap<>();
        List<List<String>> res = Arrays.asList(driver1, driver2, driver3, trip1, trip2, trip3);
        for (List<String> str : res) {
            Driver curDriver = getCurDriverStatus(str, driverMap);
            driverMap.put(curDriver.getName(), curDriver);
        }
        //At that time we already processed all the lists of Strings, we will round each
        //total distance of every driver and round each average speed into integer value and
        //use a list to store all sorted entries;
        List<String> result = getAllResult(driverMap);
        while (result.iterator().hasNext()) {
            System.out.println(result.iterator().next());
            result.remove(result.iterator().next());
        }
    }

    public static Driver getCurDriverStatus(List<String> str, Map<String, Driver> driverMap) throws ParseException {
        //assume: str != null && str.size() > 0;
        //assume there is no duplicated drivers
        //case 1:
        if (str.get(0).equals("Driver")) {
            Driver newDriver = new Driver(str.get(1), 0.0 ,0.0, 0.0);
            driverMap.put(str.get(1), newDriver);
            return newDriver;
        }
        //case 2:
        Trip curTrip = new Trip(new int[]{0, 0, 0, 0}, 0);
        String owner = str.get(1);
        String startTime = str.get(2);
        String endTime = str.get(3);
        String dis = str.get(4);
        double distance = Double.parseDouble(dis);
        //then I need to calculate time and speed;
        double curTime = curTrip.getTime(startTime, endTime);
        double curSpeed = curTrip.getCurSpeed(curTime, distance);
        double preSpeed = driverMap.get(owner).getAverageSpeed();
        double preTime = driverMap.get(owner).getPreTime();
        double sumOfDistance = driverMap.get(owner).getSumOfDistance() + distance;
        curSpeed = curSpeed * curTime / (preTime + curTime) + preSpeed * preTime / (preTime + curTime);
        //creat a new Driver with updated speed and distance
        Driver newDriver = new Driver(owner, sumOfDistance, curSpeed, preTime + curTime);
        return newDriver;
    }

    public static List<String> getAllResult(Map<String, Driver> map) {
        List<String> result = new ArrayList<>();
        PriorityQueue<Driver> maxHeap = new PriorityQueue<>(
                (a, b) -> (int) (b.getSumOfDistance() - a.getSumOfDistance()));
        for (Map.Entry<String, Driver> entry: map.entrySet()) {
            maxHeap.add(entry.getValue());
        }
        while (!maxHeap.isEmpty()) {
            Driver curDriver = maxHeap.poll();
            StringBuilder sb = new StringBuilder();
            sb.append(curDriver.getName() + ':' + ' ');
            if (curDriver.getSumOfDistance() > 0) {
                sb.append((int) Math.round(curDriver.getSumOfDistance())+ " miles" + ' ' + "@ ");
            } else {
                sb.append("0 miles");
            }
            if (curDriver.getAverageSpeed() > 0) {
                sb.append((int) Math.round(curDriver.getAverageSpeed()) + " mph" );
            }
            result.add(sb.toString());
        }
        return result;
    }
}

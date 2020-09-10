package RootCodeChallengeSolution;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.*;
public class Trip {
    /*
Input:
Driver Dan
Driver Lauren
Driver Kumi
Trip Dan 07:15 07:45 17.3 -> 34.6/H * 0.6(30/(30 + 20)) +
Trip Dan 06:12 06:32 21.8 -> 65.4/H * 0.4  = 47
Trip Lauren 12:01 13:16 42.0

Output:
Lauren: 42 miles @ 34 mph
Dan: 39 miles @ 47 mph
Kumi: 0 miles

     */
    //for each trip:
    //it must has a name, startTime end time, and average speed;
    private double distance;
    private int[] time;//通过计算得出来的
    public void setDistance(double distance) {
        this.distance = distance;
    }
    public double getDistance() {
        return this.distance;
    }
    public void setTime(int[] time) {
        this.time = time;
    }
    public int[] getTime() {
        return this.time;
    }
    public Trip(int[] time, double distance) {
        this.time = time;
        this.distance = distance;
    }

    //当我们读到trip的时候，那就是要计算出当前的速度和平均速度
    //需要的值是距离（distance，时间差）
    //overload to calculate time
    public double getTime (String str1, String str2) throws ParseException {
        double curTime;
        SimpleDateFormat start = new SimpleDateFormat("HH:mm");
        SimpleDateFormat end = new SimpleDateFormat("HH:mm");
        Date startT = start.parse(str1);
        Date endT = end.parse(str2);
        Calendar calOfStart = Calendar.getInstance();
        calOfStart.setTime(startT);
        Calendar calOfEnd = Calendar.getInstance();
        calOfEnd.setTime(endT);
        int startHour = calOfStart.get(Calendar.HOUR_OF_DAY);
        int endHour = calOfEnd.get(Calendar.HOUR_OF_DAY);
        int startMins = calOfStart.get(Calendar.MINUTE);
        int endMins = calOfEnd.get(Calendar.MINUTE);
        curTime = (((endHour - startHour + 0.0) * 60 + (endMins - startMins)))/ 60;
        return curTime;
    }

    public double getCurSpeed(double curTime, double distance) {
        double speed = distance / curTime;
        //According to the constraints, we would discard the data which is out of the boundary;
        return speed >= 5 && speed <= 100 ? speed : 0;
    }
    /*
    public static void main(String[] args) throws ParseException {
        List<Double> eachTimePeriod = new ArrayList<>();
        List<Integer> eachSpeed = new ArrayList<>();
        //for each one trip in the whole list of trip;
        String startTime = "18:30";
        String endTime = "20:35";
        SimpleDateFormat start = new SimpleDateFormat("hh:mm");
        SimpleDateFormat end = new SimpleDateFormat("hh:mm");
        Date startT = start.parse(startTime);
        Date endT = end.parse(endTime);
        Calendar calOfStart = Calendar.getInstance();
        Calendar calOfEnd = Calendar.getInstance();
        //把被parse好的参数给cal赋值，然后再用Calendar的类去拿小时和分钟；
        calOfStart.setTime(startT);
        calOfEnd.setTime(endT);
        int cosOfTime = (calOfEnd.get(Calendar.HOUR_OF_DAY) - calOfStart.get(Calendar.HOUR_OF_DAY)) * 60 +
                         calOfEnd.get(Calendar.MINUTE) - calOfStart.get(Calendar.MINUTE);
        double timeInHour = (cosOfTime + 0.0) / 60;
        int distance = 20;
        int speed = (int) (distance / timeInHour);
        if (speed > 5 && speed < 100) {
            //add it into
            //eligible speed;
            eachTimePeriod.add(timeInHour);
            eachSpeed.add(speed);
        }
        //then calculate the average speed of the specific in a certain driver
        //and put the result into the report class
        //and save it later;
        /*
        try {
            Date dt = sdf.parse("90");
            sdf = new SimpleDateFormat("HH:mm");
            System.out.println(sdf.format(dt));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(format);
    }
        */
}


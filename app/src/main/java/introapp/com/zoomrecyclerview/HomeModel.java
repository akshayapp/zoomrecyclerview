package introapp.com.zoomrecyclerview;

/**
 * Created by choudhary on 3/8/2018.
 */

public class HomeModel {
    int id;
    String name,startTime,endTime,moments,timelines,weekname,startWeek,endWeek,weekmoments,weektimelines,MonthData,dayData;


    public int getid() {
        return id;
    }
    public void setid(int id) {
        this.id = id;
    }

    public String getname() {
        return name;
    }
    public void setname(String name) {
        this.name = name;
    }

    public String getstartTime() {
        return startTime;
    }
    public void setstartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getendTime() {
        return endTime;
    }
    public void setendTime(String endTime) {
        this.endTime = endTime;
    }
    public String getmoments() {
        return moments;
    }
    public void setmoments(String moments) {
        this.moments = moments;
    }
    public String gettimelines() {
        return timelines;
    }
    public void settimelines(String timelines) {
        this.timelines = timelines;
    }

    public String getweekname() {
        return weekname;
    }
    public void setweekname(String weekname) {
        this.weekname = weekname;
    }
    public String getstartWeek() {
        return startWeek;
    }
    public void setstartWeek(String startWeek) {
        this.startWeek = startWeek;
    }
    public String getendWeek() {
        return endWeek;
    }
    public void setendWeek(String endWeek) {
        this.endWeek = endWeek;
    }
    public String getweekmoments() {
        return weekmoments;
    }
    public void setweekmoments(String weekmoments) {
        this.weekmoments = weekmoments;
    }
    public String getweektimelines() {
        return weektimelines;
    }
    public void setweektimelines(String weektimelines) {
        this.weektimelines = weektimelines;
    }

    public String getMonthData() {
        return MonthData;
    }
    public void setMonthData(String MonthData) {
        this.MonthData = MonthData;
    }
    public String getdayData() {
        return dayData;
    }
    public void setdayData(String dayData) {
        this.dayData = dayData;
    }


    public HomeModel(int  id, String name, String startTime, String endTime, String moments, String timelines, String MonthData, String dayData) {
        this.id = id;
        this.name=name;
        this.startTime=startTime;
        this.endTime=endTime;
        this.moments=moments;
        this.timelines=timelines;
//        this.weekname=weekname;
//        this.startWeek=startWeek;
//        this.endWeek=endWeek;
//        this.weekmoments=weekmoments;
//        this.weektimelines=weektimelines;
        this.MonthData=MonthData;
        this.dayData=dayData;
    }
}

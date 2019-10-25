package club.zby.ScheduleQueue;

import java.net.URL;

/**
 * @Author: 赵博雅
 * @Date: 2019/10/25 15:16
 */
public interface ScheduleQueue {

    int size();

    boolean addNewURL(URL url);

    //just look don't remove
    URL lookNextURL();

    //return URL and this URL will be remove in Queue
    URL nextURL();

    boolean removeHead();

}

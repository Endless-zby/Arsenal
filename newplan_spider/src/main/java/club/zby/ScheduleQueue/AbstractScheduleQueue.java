package club.zby.ScheduleQueue;

import java.net.URL;
import java.util.Queue;

/**
 * @Author: 赵博雅
 * @Date: 2019/10/25 15:20
 */

public abstract class AbstractScheduleQueue implements ScheduleQueue {

    protected Queue<URL> queue;
    protected int maxCapacity = 10; //take care of your memory:)


    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public boolean addNewURL(URL url) {
        if(size() > maxCapacity){
           return false;
        }
        queue.add(url);
        return true;
    }

    @Override
    public URL lookNextURL() {
        return queue.peek();
    }

    @Override
    public URL nextURL() {
        return queue.poll();
    }

    @Override
    public boolean removeHead() {
        if (size() == 0){
            return false;
        }
        queue.remove();
        return true;
    }
}

package club.zby.ftp.Entity;

/**
 * @Author: 赵博雅
 * @Date: 2019/11/18 18:05
 */
public class Progress {

    private long read;//到目前為止讀取到的比特數
    private long length;//文件總大小
    private long items;//目前正在读取第几个文件

    public long getRead() {
        return read;
    }

    public void setRead(long read) {
        this.read = read;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public long getItems() {
        return items;
    }

    public void setItems(long items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Progress{" +
                "read=" + read +
                ", length=" + length +
                ", items=" + items +
                '}';
    }
}

package club.zby.Boot;

/**
 * @Author: 赵博雅
 * @Date: 2019/10/25 14:57
 */
public interface Boot {

    void beforeBoot();

    void boot();

    void afterBoot();

}

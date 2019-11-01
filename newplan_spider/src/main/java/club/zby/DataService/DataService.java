package club.zby.DataService;

import club.zby.Entity.SpiderData;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 赵博雅
 * @Date: 2019/10/25 16:06
 */
public interface DataService<E> {

    void init() throws IOException;

    void closed();

    //crud

    int add(E obj);

    void adds(List<E> obj) throws ClassNotFoundException;



    E get(int id);

    void update(E obj);

    void delete(int id);

    List<E> selectList();
}

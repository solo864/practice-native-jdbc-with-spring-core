package dao.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class FirstAspect {

    //суть его в том что мы просто проверяем ту или иную аннотацию над классом.
    @Pointcut("@within(org.springframework.stereotype.Controller)")
    public void isControllerLayer() {
    }

    //Здесь уже указываем класс, не аннотацию, а класс
    //Мы можем сказать что нам всё равно на что он начинается, главное чтобы он заканчивался постфиксом Service
    @Pointcut("within(az.online.shop.service.*Service)")
    public void isServiceLayer() {
    }

    //this и target, разница между ними в том что this обращается именно к AOP Proxy которая будет создана, а target именно к нашему
    //исходному объекту вокруг которого будет обёрнут
    //Т.к AOP Proxy и наш Spring Repository реализует одни и теже интерфейсы, следовательно мы в нашем случае можем использовать как this так и target
    @Pointcut("this(org.springframework.data.repository.Repository)")
    public void isRepositoryLayer() {
    }


    //Именно annotation проверяет аннотации над методом, в случае within проверяется аннотация над классом.
    //этот Pointcut будет проверять все методы которые помечены аннотацией GetMapping.

    /**
     * Т.к мы указали только аннотацию которую мы ищем над методом то Spring будет искать все бины, что очень не удобно, поэтому очень часто добавляют
     * несколько @Pointcut'ов в одном, чтобы ограничивать гораздо быстрее.
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void hasGetMapping() {
    }

    @Pointcut("isControllerLayer() && @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void hasGetMapping2nd() {
    }

    /*
    args - check method param type
    * - any param type
    .. - 0+ any params type
     */


    //Мы хотим только те методы которые первым параметром принимают Model
    @Pointcut("args(org.springframework.ui.Model)")
    public void hasModelParam() {

    }


    /**
     * В данном случае мы описали что будет метод ровно с одним параметром типа Model,
     * если нас интересует только первый и не важно сколько будет в последующем
     */

    @Pointcut("args(org.springframework.ui.Model,..")
    public void hasModelParam2nd() {

    }


}

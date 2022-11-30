import dao.CustomerDao;
import dao.OrderDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationRunner {
    public static void main(String[] args) {
        CustomerDao customerDao = new CustomerDao(new OrderDao());
        System.out.println(customerDao.findByIdWithAllOrders(1));
        System.out.println(customerDao.findAllWithOrders(1));

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        System.out.println();
    }
}

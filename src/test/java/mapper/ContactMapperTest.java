package mapper;

import com.mirrordust.telcodata.entity.Contact;
import com.mirrordust.telcodata.mapper.ContactMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@SpringBootTest(classes = ContactMapperTest.class)
@MapperScan("com.mirrordust.telcodata.mapper")
public class ContactMapperTest {

    @Autowired
    private ContactMapper contactMapper;

    @Test
    public void testQuery() throws Exception {
        List<Contact> contacts = contactMapper.getAll();
        System.out.println(contacts.toString());
    }

    @Test
    public void testQueryByType() throws Exception {
        List<Contact> contacts0 = contactMapper.findByType(0);
        List<Contact> contacts1 = contactMapper.findByType(1);
        System.out.println("ordinary recipient: " + contacts0.toString());
        System.out.println("CC recipient: " + contacts1.toString());
    }
}

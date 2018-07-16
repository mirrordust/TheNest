package service;

import com.mirrordust.telcodata.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MailServiceTest.class)
@ComponentScan("com.mirrordust.telcodata.service")
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Test
    public void testSimpleMail() throws Exception {
        mailService.sendSimpleMail(new String[]{"selfisuniverse@163.com"}, new String[]{"1018975858@qq.com", "liaoshanhe@gmail.com"}, "test simple mail", " hello this is simple mail!\nhttps://www.baidu.com/");
    }

}

package com.mirrordust.telcodata;

import com.mirrordust.telcodata.service.MailService;
import com.mirrordust.telcodata.storage.StorageProperties;
import com.mirrordust.telcodata.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
//@MapperScan("com.mirrordust.telcodata.mapper")
//@ComponentScan
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    private final HolderMapper holderMapper;
//
//    public Application(HolderMapper holderMapper) {
//        this.holderMapper = holderMapper;
//    }

    private final MailService mailService;

    public Application(MailService mailService) {
        this.mailService = mailService;
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
//            storageService.deleteAll();
            storageService.init();
//            System.out.println("======"+holderMapper.getAll().toString());
            mailService.sendSimpleMail(new String[]{"selfisuniverse@163.com"},
                    new String[]{"1018975858@qq.com", "liaoshanhe@gmail.com"},
                    "数据收集日报20171103", "2017年11月3日数据收集情况如下：仿佛\n第二行！");

        };
    }

}

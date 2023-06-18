package springbook.user.dao;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

/**
 * Application Context 를 적용한다.
 */

@Configuration  //Application Context가 사용할 설정정보라는 표시, 사실 XML과 같은 설정정보라 봐도 무방한 클래스
public class DaoFactory {

    @Bean   // UserDao 오브젝트 생성을 담당하는 IoC용 메소드라는 표시
    public UserDao userDao() {
        //의존관계 주입을 setter 방식으로 변경
        UserDao userDao = new UserDao();
        userDao.setDataSource(dataSource());
        return userDao;
    }

    @Bean
    public CountingAddedDataSource dataSource() {
        CountingAddedDataSource dataSource = new CountingAddedDataSource();
        dataSource.setDataSource(realDataSource());

        return dataSource;
    }

    @Bean
    public DataSource realDataSource() {
        return new SimpleDriverDataSource();
    }


}
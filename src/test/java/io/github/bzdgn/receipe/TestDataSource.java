//package io.github.bzdgn.receipe;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.jdbc.datasource.SimpleDriverDataSource;
//
//import javax.sql.DataSource;
//
//public class TestDataSource {
//    @Bean
//    public DataSource dataSource() {
//        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
//        dataSource.setDriverClass(org.h2.Driver.class);
//        dataSource.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=TRUE;DATABASE_TO_UPPER=false;MODE=MYSQL");
//        dataSource.setUsername("sa");
//        return dataSource;
//    }
//}

package com.yyh.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import lombok.Data;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;

@Data
@EnableAutoConfiguration
public class MybatisConfig {
    @Autowired
    private ComboPooledDataSource dataSource;

    @Bean
    public SqlSessionFactory getSqlSessionFactory(){
        try {
            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(dataSource);
            return sqlSessionFactoryBean.getObject();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Bean
    public SqlSessionTemplate getSqlSessionTemplate() {
        return new SqlSessionTemplate(getSqlSessionFactory());
    }
}

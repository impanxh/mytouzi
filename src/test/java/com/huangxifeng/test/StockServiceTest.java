package com.huangxifeng.test;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.huangxifeng.gupiao.dao.StockDao;
import com.huangxifeng.gupiao.domain.Stock;
import com.querydsl.jpa.impl.JPAQueryFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockServiceTest {
	
    // 注入JPAQueryFactory
    @Bean
    @Autowired
    public JPAQueryFactory jpaQuery(EntityManager entityManager) {
        return new JPAQueryFactory(entityManager);
    }

    //@Autowired
    //private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private StockDao stockDao;


    @org.junit.Test
    public void jpaQuery() {
        Stock stock = new Stock();
        stock.setName("翠微股份");
        stock.setCode("603123");
        stockDao.saveAndFlush(stock);
    }

    @org.junit.Test
    public void updateBook() {
        Stock stock = new Stock();
        stock.setName("翠微股份2");
        stock.setCode("603123");
        //jpaQueryFactory.update(path)
        //stockDao.findBy(example, queryFunction)saveAndFlush(stock);
    }

     /**
     * 多条件查询
//     */
    @org.junit.Test
    public void findAll() {
//        // select customer0_.id as id1_1_, customer0_.customer_name as customer2_1_, customer0_.customer_part as customer3_1_ from t_customer customer0_ where (customer0_.customer_name like ? escape '!' or customer0_.customer_name=?) and (customer0_.id between ? and ?)
//        QStock qc = QStock.stock;
//        BooleanBuilder builder = new BooleanBuilder();
//        builder.and(qc.name.like('%' + "微" + '%'));
//        //builder.or(qc.name.eq("老王"));
//        //builder.and(qc.id.between(3, 5));
//        List<Stock> list = jpaQueryFactory.selectFrom(qc).where(builder).fetch();
    	
    	List<Stock> list = stockDao.findAll();
        list.forEach(stock -> {
            System.out.println(stock.getName());
        });
    }

//    /**
//     * 排序
//     */
//    @Test
//    public void testDesc() {
//        QCustomer qc = QCustomer.customer;
//        List<Customer> customerList = jpaQueryFactory.selectFrom(qc).orderBy(qc.id.desc()).fetch();
//        customerList.forEach(customer -> {
//            System.out.println(customer.getName());
//        });
//    }
//
//    /**
//     * 分页
//     */
//    @Test
//    public void testPage() {
//        QCustomer qc = QCustomer.customer;
//        QEmployees qe = QEmployees.employees;
//        // 写法一
//        JPAQuery<Employees> employeesJPAQuery = jpaQueryFactory.selectFrom(qe);
//        // offset 起始页
//        List<Employees> employeesList = employeesJPAQuery.offset(3).limit(2).fetch();
//        employeesList.forEach(employees -> {
//            System.out.println(employees);
//        });
//        // 写法二
//        Long total = employeesJPAQuery.fetchCount();
//        QueryResults<Customer> customerQueryResults = jpaQueryFactory.selectFrom(qc).offset(0).limit(3).fetchResults();
//        List<Customer> results = customerQueryResults.getResults();
//        results.forEach(customer -> {
//            System.out.println(results);
//        });
//    }
}

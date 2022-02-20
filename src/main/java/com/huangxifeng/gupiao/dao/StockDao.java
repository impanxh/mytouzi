package com.huangxifeng.gupiao.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huangxifeng.gupiao.domain.Stock;

public interface StockDao extends JpaRepository<Stock, Long>
{
    Stock findStockByCode(String code);
}


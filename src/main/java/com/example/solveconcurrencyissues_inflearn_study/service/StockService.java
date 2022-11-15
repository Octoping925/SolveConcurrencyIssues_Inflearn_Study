package com.example.solveconcurrencyissues_inflearn_study.service;

import com.example.solveconcurrencyissues_inflearn_study.domain.Stock;
import com.example.solveconcurrencyissues_inflearn_study.repository.StockRepository;
import org.springframework.stereotype.Service;

@Service
public class StockService {
    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public synchronized void decrease(Long id, Long quantity) {
        Stock stock = stockRepository.findById(id).orElseThrow();
        stock.decrease(quantity);
        stockRepository.saveAndFlush(stock);
    }
}

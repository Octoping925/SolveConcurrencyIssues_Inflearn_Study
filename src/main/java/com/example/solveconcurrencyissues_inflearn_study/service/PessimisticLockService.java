package com.example.solveconcurrencyissues_inflearn_study.service;

import com.example.solveconcurrencyissues_inflearn_study.domain.Stock;
import com.example.solveconcurrencyissues_inflearn_study.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PessimisticLockService {
    private final StockRepository stockRepository;

    public PessimisticLockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional
    public void decrease(Long id, Long quantity){
        Stock stock = stockRepository.findByIdWithPessimisticLock(id);
        stock.decrease(quantity);
        stockRepository.saveAndFlush(stock);
    }
}

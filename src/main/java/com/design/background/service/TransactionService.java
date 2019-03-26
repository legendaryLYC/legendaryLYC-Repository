package com.design.background.service;

import com.design.background.entity.NewTransaction;
import com.design.background.entity.Transaction;
import com.design.background.model.TransactionModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**

 * @Author     :高红亮

 * @Date       :2019/2/14

 * @Description:这是交易记录的service

*/
@Transactional
public interface TransactionService {

    int deleteByPrimaryKey(Long id);

    int insert(Transaction record);

    int insertSelective(Transaction record);

    Transaction selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Transaction record);

    int updateByPrimaryKeyWithBLOBs(Transaction record);

    int updateByPrimaryKey(Transaction record);

    List<NewTransaction> selectRecord();
}

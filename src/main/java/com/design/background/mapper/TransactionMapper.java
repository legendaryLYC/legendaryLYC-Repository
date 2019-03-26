package com.design.background.mapper;

import com.design.background.entity.NewTransaction;
import com.design.background.entity.Transaction;
import com.design.background.form.TransactionForm;
import com.design.background.model.TransactionModel;

import java.util.List;

/**

 * @Author     :高红亮

 * @Date       :2019/2/23

 * @Description:交易记录mapper

*/

public interface TransactionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Transaction record);

    int insertSelective(Transaction record);

    Transaction selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Transaction record);

    int updateByPrimaryKey(Transaction record);

    List<NewTransaction> selectRecord();
    
    List<TransactionForm> selectTran(Long userId);
}
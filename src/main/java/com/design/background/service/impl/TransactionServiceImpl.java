package com.design.background.service.impl;

import com.design.background.entity.NewTransaction;
import com.design.background.entity.Transaction;
import com.design.background.mapper.TransactionMapper;
import com.design.background.model.TransactionModel;
import com.design.background.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**

 * @Author     :高红亮

 * @Date       :2019/2/14

 * @Description:这是交易记录的serviceimpl

*/

@Service("transactionService")
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionMapper transactionMapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return transactionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Transaction record) {
        return transactionMapper.insert(record);
    }

    @Override
    public int insertSelective(Transaction record) {
        return transactionMapper.insertSelective(record);
    }

    @Override
    public Transaction selectByPrimaryKey(Long id) {
        return transactionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Transaction record) {
        return transactionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(Transaction record) {
        return transactionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Transaction record) {
        return transactionMapper.updateByPrimaryKey(record);
    }

    /**

     * @param     :Application project

     * @return    :List

     * @Description:展示交易记录列表

     * @Author      :高红亮

     * @ Date       :2019/2/21

     */

    @Override
    public List<NewTransaction> selectRecord() {
        List<NewTransaction> result = transactionMapper.selectRecord();
        return result;
    }
}

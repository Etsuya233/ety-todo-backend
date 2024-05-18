package com.ety.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ety.auth.domain.po.Record;
import com.ety.auth.mapper.RecordMapper;
import com.ety.auth.service.IRecordService;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements IRecordService {

}

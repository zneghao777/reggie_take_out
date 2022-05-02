package com.zenghao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.zenghao.entity.AddressBook;
import com.zenghao.mapper.AddressBookMapper;
import com.zenghao.service.AddressBookService;
import org.springframework.stereotype.Service;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {

}

package com.vnptt.tms.service;

import com.vnptt.tms.dto.ListDeviceDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IListDeviceService {
    ListDeviceDTO save(ListDeviceDTO listDeviceDTO);

    ListDeviceDTO findOne(Long id);

    int totalItem();

    void delete(Long[] ids);

    List<ListDeviceDTO> findAll(Pageable pageable);

    List<ListDeviceDTO> findAll();
}
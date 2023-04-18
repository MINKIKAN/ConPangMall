package com.console.mall.service;

import com.console.mall.dto.PaginationDTO;
import com.console.mall.entitiy.Item;
import com.console.mall.respository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public Item findOneItem(Long id) {
//        List<Item> itemList = itemRepository.findOne(id);
//        return itemList.get(0);
        Item item = itemRepository.findOne(id);
        return item;
    }

    public List<Item> findAllItem(Long id, PaginationDTO paginationDTO) {
        int start = paginationDTO.getOffset();
        int recordSize = paginationDTO.getRecordSize();
        return itemRepository.getList(id, start , recordSize);
    }

    public Long allCount(Long id) {
        return itemRepository.allCount(id);
    }
}

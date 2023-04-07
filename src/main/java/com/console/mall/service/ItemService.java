package com.console.mall.service;

import com.console.mall.dto.ItemDTO;
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
        return itemRepository.findOne(id);
    }

    public List<Item> findAllItem() {
        return itemRepository.findAll();
    }


}

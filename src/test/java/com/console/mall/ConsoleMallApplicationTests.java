package com.console.mall;

import com.console.mall.entitiy.Category;
import com.console.mall.entitiy.Item;
import com.console.mall.entitiy.Member;
import com.console.mall.respository.CategoryRepository;
import com.console.mall.respository.ItemRepository;
import com.console.mall.respository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Transactional
@SpringBootTest
@Rollback(value = false)
class ConsoleMallApplicationTests {

	@Autowired
	MemberRepository memberRepository;
	@Autowired
	ItemRepository itemRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	EntityManager em;

	@Test
	void DB연동() {
		Assertions.assertNotNull(em);
	}
	@Test
	void 회원추가() {
		// 회원 추가
		Member member1 = new Member();
		member1.setName("홍길동");
		member1.setLogin_id("qwer");
		member1.setPw("qwer");


		Member member2 = new Member();
		member2.setName("김철수");
		member2.setLogin_id("asdf");
		member2.setPw("asdf");

		Member member3 = new Member();
		member3.setName("꿈나무");
		member3.setLogin_id("zxcv");
		member3.setPw("zxcv");

		// 저장
		memberRepository.save(member1);
		memberRepository.save(member2);
		memberRepository.save(member3);
	}
	@Test
	void 카테고리AND아이템추가() {
		// 카테고리 추가
		Category ps = new Category();
		Category xbox = new Category();
		Category nintendo = new Category();
		Category vr = new Category();

		Item item1 = new Item();
		item1.setName("ps4 slim jet black");
		item1.setPrice(10000);
		item1.setStockQuantity(100);
		item1.setCategory(ps);
		item1.setImage("/img/ps4 slim jetblack.jpg");

		Item item2 = new Item();
		item2.setName("ps5 vt2 호라이즌");
		item2.setPrice(20000);
		item2.setStockQuantity(50);
		item2.setCategory(ps);
		item2.setImage("/img/ps5 vt2 호라이즌.jpg");

		Item item3 = new Item();
		item3.setName("xbox series s");
		item3.setPrice(20000);
		item3.setStockQuantity(50);
		item3.setCategory(xbox);
		item3.setImage("/img/xbox series s.jpg");

		Item item4 = new Item();
		item4.setName("xbox series x");
		item4.setPrice(20000);
		item4.setStockQuantity(50);
		item4.setCategory(xbox);
		item4.setImage("/img/xbox series x.jpg");

		Item item5 = new Item();
		item5.setName("xbox series x diablo bundle");
		item5.setPrice(20000);
		item5.setStockQuantity(50);
		item5.setCategory(xbox);
		item5.setImage("/img/xbox series x diablo bundle.jpg");

		Item item6 = new Item();
		item6.setName("xbox series x forza horizon5");
		item6.setPrice(20000);
		item6.setStockQuantity(50);
		item6.setCategory(xbox);
		item6.setImage("/img/xbox series x forza horizon5.jpg");

		Item item7 = new Item();
		item7.setName("닌텐도스위치oled");
		item7.setPrice(20000);
		item7.setStockQuantity(50);
		item7.setCategory(nintendo);
		item7.setImage("/img/닌텐도스위치oled.jpg");

		Item item8 = new Item();
		item8.setName("닌텐도스위치블루레드");
		item8.setPrice(20000);
		item8.setStockQuantity(50);
		item8.setCategory(nintendo);
		item8.setImage("/img/닌텐도스위치블루레드.jpg");

		Item item9 = new Item();
		item9.setName("플스4프로7218b 1tb");
		item9.setPrice(20000);
		item9.setStockQuantity(50);
		item9.setCategory(ps);
		item9.setImage("/img/플스4프로7218b 1tb.jpg");

		Item item10 = new Item();
		item10.setName("플스5 디스크에디션");
		item10.setPrice(20000);
		item10.setStockQuantity(50);
		item10.setCategory(ps);
		item10.setImage("/img/플스5 디스크에디션.jpg");

		ps.setName("ps");
		ps.setCategory_code("ps");
		List<Item> list1 = new ArrayList<>();
		list1.add(item1);
		list1.add(item2);
		list1.add(item9);
		list1.add(item10);
		ps.setItemList(list1);

		xbox.setName("xbox");
		xbox.setCategory_code("xbox");
		List<Item> list2 = new ArrayList<>();
		list2.add(item3);
		list2.add(item4);
		list2.add(item5);
		list2.add(item6);
		xbox.setItemList(list2);

		nintendo.setName("nintendo");
		nintendo.setCategory_code("nintendo");
		List<Item> list3 = new ArrayList<>();
		list3.add(item7);
		list3.add(item8);
		nintendo.setItemList(list3);

		vr.setName("vr");
		vr.setCategory_code("vr");
		List<Item> list4 = new ArrayList<>();
		list4.add(item3);
		list4.add(item4);
		list4.add(item5);
		list4.add(item6);
		vr.setItemList(list4);


		// 저장
		categoryRepository.save(ps);
		categoryRepository.save(xbox);
		categoryRepository.save(nintendo);
		categoryRepository.save(vr);
	}

}

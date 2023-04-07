package com.console.mall;

import com.console.mall.entitiy.Category;
import com.console.mall.entitiy.Item;
import com.console.mall.entitiy.Member;
import com.console.mall.entitiy.Review;
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
		// 회원 추가
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
		item1.setItemInfo("PS4 Slim Jet Black은 소니가 제조한 게임 콘솔로, 흑색의 광택있는 디자인으로 유명합니다. 이 제품은 PS4 Pro보다 크기가 작고 가볍습니다. 그러나 그래픽 성능이나 게임 실행 속도는 더 높은 스펙을 가진 PS4 Pro에 비해 다소 낮을 수 있습니다.\n" +
				"\n" +
				"PS4 Slim Jet Black은 Wi-Fi 및 블루투스 기능을 탑재하고 있으며, 무선 인터넷 연결을 통해 온라인 게임을 즐길 수 있습니다. 또한 USB 포트를 통해 게임 컨트롤러를 연결하거나 외부 저장 장치를 사용할 수도 있습니다.\n" +
				"\n" +
				"이 제품에는 다양한 게임 제목이 미리 설치되어 있지 않으며, 게임을 즐기기 위해서는 별도의 게임 디스크나 다운로드를 해야 합니다. PS4 Slim Jet Black은 대부분의 게임을 실행할 수 있으며, PlayStation Store에서 다양한 게임을 다운로드할 수 있습니다.\n" +
				"\n" +
				"이 게임 콘솔은 가정용으로 설계되어 있으며, 여러 사람이 함께 즐길 수 있는 멀티플레이 기능을 제공합니다. 또한 PlayStation Plus 구독 서비스를 통해 월별 무료 게임을 다운로드할 수 있으며, 온라인 멀티플레이에 참여할 수 있습니다.\n" +
				"\n" +
				"PS4 Slim Jet Black은 적절한 가격대에 제공되며, 게임 콘솔을 즐기는데 필요한 기능을 충분히 갖추고 있습니다. 게임을 즐기는 새로운 방식을 경험하고 싶은 분들에게 추천드립니다.");
		item1.setItemVideo("https://www.youtube.com/embed/PsOcep7PGjs");
		List<Review> rList = new ArrayList<>();
		Review review1 = new Review();
		review1.setImage("/review_img/PS4 Slim Jet Black_review1.jpg");
		review1.setTitle("Test1");
		review1.setContent("응테스트야");
		review1.setWriter("qwer");
		review1.setItem(item1);

		Review review2 = new Review();
		review2.setImage("/review_img/PS4 Slim Jet Black_review2.jpg");
		review2.setTitle("test2");
		review2.setContent("응 이것도테스트야");
		review2.setWriter("asdf");
		review2.setItem(item1);

		Review review3 = new Review();
		review3.setImage("/review_img/PS4 Slim Jet Black_review3.jpg");
		review3.setTitle("test3");
		review3.setContent("이것도 테스튼데?");
		review3.setWriter("zxcv");
		review3.setItem(item1);

		rList.add(review1);
		rList.add(review2);
		rList.add(review3);
		item1.setList(rList);

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

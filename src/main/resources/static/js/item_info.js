function addToCart(item) {
    // 아이템 추가 로직
    // ...

    // Order 객체 생성
    const order = {
        // order의 필드들 작성 (예시)
        userId: 1234,
        itemId: item.id,
        quantity: 1,
        totalPrice: item.price
    };

    // POST 요청 전송
    fetch('/orders', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(order)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to add item to cart');
            }
            console.log('Item added to cart successfully');
        })
        .catch(error => {
            console.error(error);
        });
}

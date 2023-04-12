

function order(){
    var url = "/order";
    var paramData = { // 주문상품 id, 주문할 상품 개수 저장
        itemId : $("#itemId").val(),
        count : $("#count").val()
    };

    var param = JSON.stringify(paramData); // JSON 으로 데이터 보냄
    // JSON 으로 데이터 보냄
    $.ajax({
        url      : url,
        type     : "POST",
        contentType : "application/json",
        data     : param,
        dataType : "json",
        cache   : false,
        success  : function(result, status){
            alert("주문이 완료 되었습니다.");
            location.href='/'; // 주문 로직 호출이 성공하면은 위의 메세지가 출력되고, 메인 페이지로 이동
        },
        error : function(jqXHR, status, error){

            if(jqXHR.status == '401'){
                alert('로그인 후 이용해주세요');
                location.href='/members/login'; // 401이면(로그인 상태가 아니면) 로그인 페이지로 이동함
            } else{
                alert(jqXHR.responseText); // 주문 시 에러가 발생하면 메세지 출력됨
            }

        }
    });
}
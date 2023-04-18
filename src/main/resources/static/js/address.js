
function findAddr(){

    new daum.Postcode({
        oncomplete: function(data) {

            console.log(data);

            var roadAddr = data.roadAddress;
            var jibunAddr = data.jibunAddress;

            document.getElementById("zipcode").value = data.zonecode;
            if(roadAddr !== ''){
                document.getElementById("address").value = roadAddr;
            }
            
            else if(jibunAddr !== ''){
                document.getElementById("address").value = jibunAddr;
            }
        }
    }).open();
}
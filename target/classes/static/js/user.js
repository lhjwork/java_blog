let index = {
	init: function() {
		$("#btn-save").on("click", () => { // function(){}, ()=>{} this를 바인딩하기 위해서
			this.save();
		});

		$("#btn-update").on("click", () => { // function(){}, ()=>{} this를 바인딩하기 위해서
			this.update();
		});


	},



	//login part
	save: function() {
		//alert('user의 save함수 호출됨');

		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}

		console.log(data);

		//ajax 호출시 default가 비동기 호출
		//ajax 통신을 이용해서 3개의 데이터를 json으로 변경해서 insert 요청!!
		$.ajax({
			type: "POST",
			//url:"/blog/api/user",
			url: "/auth/joinProc",
			data: JSON.stringify(data), // http body 데이터
			contentType: 'application/json', // body데이터가 어떤 타입입지(MIME)
			dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게 json이러면) => javascript 오브젝트로 변경
		}).done(function(resp) {
			alert("회원가입이 완료되었습니다.");
			location.href = "/";
		}).fail(function(err) {
			alert(JSON.stringify(err));
		});
	},
	update: function() {
		//alert('user의 save함수 호출됨');

		let data = {
			id:$("#id").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}

		console.log(data);

	
		$.ajax({
			type: "PUT",
			url: "/user",
			data: JSON.stringify(data), // http body 데이터
			contentType: 'application/json', // body데이터가 어떤 타입입지(MIME)
			dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게 json이러면) => javascript 오브젝트로 변경
		}).done(function(resp) {
			alert("회원정보 수정이 완료되었습니다.");
			location.href = "/";
		}).fail(function(err) {
			alert(JSON.stringify(err));
		});
	}
}

index.init();
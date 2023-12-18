let index = {
	init:function(){
		$("#btn-save").on("click", ()=>{ // function(){}, ()=>{} this를 바인딩하기 위해서
			this.save();
		});
		
		
	},
	

	
	//login part
	save:function(){
		//alert('user의 save함수 호출됨');
		
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		}
		
		console.log(data);
	
		$.ajax({
			type:"POST",
			//url:"/blog/api/board",
			url:"/api/board",
			data : JSON.stringify(data), 
			contentType: 'application/json',
			dataType:"json" 
		}).done(function(resp){
			alert("글쓰기가 완료되었습니다.");
			location.href = "/";
		}).fail(function(err){
			alert(JSON.stringify(err));
		}); 
	}
}

index.init();
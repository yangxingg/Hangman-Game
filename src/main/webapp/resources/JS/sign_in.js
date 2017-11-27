/**
 * 
 */

$(document).ready(function(){
	//check if userName exists
	$("#userName").blur(function(){
		$.ajax({
			type: "POST",
			  url: "checkUserName.htm",
			  data:"userName="+$(this).val(),
			  success: function(response){
				 handleUserNameExist(response);
			  },
			   error: function(e){
				   	alert(e.status+"..."+e.statusText+".."+e.responseText,e);
			   }
	    })
		});
	
	function handleUserNameExist(val){
		if(val != 0){//exist usernamr
			$("#userName").css("background-color","red");
			$("#submit").attr("disabled",true);
		}else{
			$("#userName").css("background-color","#25a2b7");
			$("#submit").attr("disabled",false);
		}
	}
});
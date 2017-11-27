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
		if(val == 0){//exist usernamr
			$("#userName").css("background-color","red");
			$("#submit").attr("disabled",true);
		}else{
			$("#userName").css("background-color","#25a2b7");
			$("#submit").attr("disabled",false);
		}
	}
		
	
  // signup form validate
  $("form[name='signup']").validate({
    // Specify validation rules
	errorElement:"label",
    rules: {
      
      userName: "required",
      password1: "required",
      password2: "required",
    
      password1: {
        required: true,
        minlength: 6
      },
      password2: {
          required: true,
          minlength: 6,
          equalTo: "#password1"
        },
    },
    // Specify validation error messages
    messages: {
      firstname: "username required",
      password1: {
        required: "password required",
        minlength: "password at least 6 characters long"
      },
      password2: {
          required: "password required",
          minlength: "password at least 6 characters long",
        	  equalTo: "password need match"
        },
    },

    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      form.submit();
    }
    
  });
});
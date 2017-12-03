/**
 * 
 * for game_play page.
 */

/* keyboard cllik, color change, unable */
$(document).ready(function(){
	$("body").keypress(function(e){
       var letter = String.fromCharCode(event.which)
       if(letter.match(/[a-z]/i)){
    	   	   letter = letter.toUpperCase()
    	   	   $("button[value='"+letter+"']").css("background-color", "rgba(191, 191, 191,0,6)");
    	   	   $("button[value='"+letter+"']").css("color", "black");
           $("button[value='"+letter+"']").attr("disabled","disabled");
           $("button[value='"+letter+"']").addClass('no-hover');
    	   	   guess(letter)
       }
    });
	
	
	$(".letterBtn").click(function(){
        $(this).css("background-color", "rgba(191, 191, 191,0,6)");
        $(this).css("color", "black");
        $(this).attr("disabled","disabled");
        $(this).addClass('no-hover');
        
        $(".word_result").remove();
        $("#image").empty();
       
       guess($(this).val());
        
        
    });
	
	function guess(val){
		
		$.ajax({
			type: "POST",
			  url: "gameplay.htm",
			  data:"val="+val,
			  success: function(response){
				  showResult(response);
				 
			  },
			   error: function(e){
				   	alert(e.status+"..."+e.statusText,e);
			        }
	    });
	}

	function showResult(response){
		var res = response.split("||")
		var guessNum = res[0]
		var wordHtml = res[1]
		var gameStatus = res[2] // 0-ongoing, 1-success, 2- failed

		var imgSrc = "/Images/step"+guessNum+".png" 
		document.getElementById("image").innerHTML="<img alt='err' src='../Images/step"+guessNum+".png' />"
		document.getElementById("letters_result").innerHTML=res[1]
		
		if(gameStatus == 0){
			//do nothing
		}else{
			//game fail or success
			//disabled keyboard
			var btns = document.getElementsByClassName("letterBtn")
			for(var i = 0; i < btns.length; i++) {
				btns[i].disabled = true
				btns[i].classList.add("no-hover")
			}
			if(gameStatus == 1)
				 document.getElementById("winGame").style.display = "inline-block";
			else
				document.getElementById("failGame").style.display = "inline-block"
		}
		
	}

	
});


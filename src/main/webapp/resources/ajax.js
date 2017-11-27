///**
// * all ajax functions here
// */
//var xmlHttp;
//
//function GetXmlHttpObject()
//{
//	var xmlHttp=null;
//	
//	try
//	{	 // Firefox, Opera 8.0+, Safari
//	  xmlHttp=new XMLHttpRequest();
//	}
//	catch (e)
//	{    // Internet Explorer
// 	  try
//	  {
//	    xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
//	  }
//	  catch (e)
//	  {
//	    xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
//	  }
//    }
//  	return xmlHttp;
//}
//
//function guessLetter(letter){
//	    xmlHttp=GetXmlHttpObject()
//	    if (xmlHttp==null)
//	     {
//	         alert ("Browser does not support HTTP Request")
//	          return
//	     }
//
//	    var url="gameplay.htm"
//	    	var form_data = new FormData()
//	    form_data.append("val", letter)
//	    
//	    	xmlHttp.onreadystatechange = function(){
//	    		if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
//	    		{ 
//	    			 var myArr = JSON.parse(this.responseText);
//	    		     populateDiv(myArr)
//	    		} 
//	    }
//	    xmlHttp.open("post",url,true)
//	    xmlHttp.send(form_data)
//}
//
//function populateDiv(myArr){
//	alert(myArr)
//	//document.getElementById("word_result").innerHTML = "<p>"+myArr+"</p>";
//}
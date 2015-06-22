// JavaScript Document
$(document).ready(function(e) {
    $("#login").click(function(e) {
       var username=$("input[name='username']").val();
	   var password=$("input[name='password']").val();
	   var data={'name':username,'password':password}
	   $.ajax({
		   url:"app/login",
		   dataType: "json",
		   type: "post",
		   data:JSON.stringify(data),		  
		   success: function(data){
			  if(data.stateCode==200){
				 document.location.href="/"; 
			 }
		   },
		   error: function(e){
			 console.log(e);
		   }
		   });	  
    });

});
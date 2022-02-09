
	function isValidLength(element,length){
				return element.length<=length ? true : false;			
	}
	function isValidLetter(letter){		
		var pattern = /^[a-zA-Z\s]*$/; 
		return pattern.test(letter);		
	}
	
	function isValidPhoneNo(phoneNo){		
		var pattern = /^[0-9,.\-\+\s]*$/; 
		return pattern.test(phoneNo);		
	}
	
	function isValidEmail(email){
		var pattern = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		  return pattern.test(email.trim());
	}
	function isValidDateTime(dateStr){
		var pattern = /^\d{1,2}\/\d{1,2}\/\d{4}\s\d{1,2}:\d{1,2}\s([AP]M)?$/;
		 return pattern.test(dateStr);
	}
	
	function isValidImage(image)
	{	
		var checkimg = image.toLowerCase();
		if (checkimg.match(/(\.jpg|\.png|\.gif|\.GIF|\.JPG|\.PNG|\.jpeg|\.JPEG)$/)){			
		return true;
		}		
	return false;
	}
	function isInt(n) 
	{
	    return n != "" && !isNaN(n) && n.indexOf(".")<0 && Math.round(n) == n;
	}
	function isFloat(n){
	    return n != "" && !isNaN(n) && Math.round(n) != n;
	}
	
	function checkField(descValue,value,required,min,max,type){		
		if(required==true){				
			if(value==null || value=="" || typeof value == "undefined" || !value.trim()){				
				return descValue+" is required.";
			}
		}		
		if(min!=null || min>0){
			if(value.length<min){				
				return descValue+" must be greater than "+min+" characters.";
			}
		}		
		if(max!=null || max>0){
			if(value.length>max){				
				return descValue+" must be less than "+max+" characters.";
			}
		}
		
		if(type!=null){	
			if(value!=null && value.length>0){
				if(type=='l'){//for only letter
					if(!isValidLetter(value)){
						return descValue+" allow only letter";
					}
				}
				if(type=='p'){//for phone Number
					if(!isValidPhoneNo(value)){
						return descValue+" is not valid format, it allow only number, space, coma(,), (-),(+)";
					}
				}
				if(type=='e'){//for email
					if(!isValidEmail(value)){
						return descValue+" is not valid format. Use this format \'example@gmail.com\'.";
					}
				}
				
				if(type=='n'){	//for number float and integer			
					if(!$.isNumeric(value)){
						return descValue+" is not valid number.";
					}
				}
				if(type=='i'){	//for number integer only			
					if(!isInt(value)){						
						return descValue+" is not integer."+descValue+" allows only integer value.";
					}
				}
				if(type=='f'){	//for number float only			
					if(!isFloat(value)){
						return descValue+" is not decimal. "+descValue+" allows only decimal value.";
					}
				}
				
			}
			if(type=='s'){				
				if(value==null || value<=0){					
					return "Please select "+descValue;
				}
			}
		}
		return false;		
	}
	
	function clearText(formId){
		$(formId+" input[type='text']").val('');
		$(formId+" input[type='hidden']").val('-1');
		/*$(formId+" select").val('-1');*/
		$(formId+" select").each(
			    function() {
			        $(this).val($(this).children("option:first-child").val());
			    }
			);		
		$(formId+" textarea").val('');
		$(formId+" .error-msg").text('');
	}
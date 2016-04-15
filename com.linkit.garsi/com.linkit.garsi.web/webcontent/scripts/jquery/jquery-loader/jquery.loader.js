jQuery.extend( {
	
	initLoader:function(contextPath,dataFilter,success){
		jQuery.ajaxSetup({
			cache:false,
			type: "GET",
			dataFilter:dataFilter
		});
		
		jQuery(document).ajaxStart(function(){
			
		});
		
		jQuery(document).ajaxSend(function(event, request, settings){
			var div=$("#loading_div");
			if(div.length==0){
				div=$("<div id='loading_div'></div>");
			}
//		    div.html("<img src='"+contextPath+"ajax-loader.gif'/>").
//		        attr("id","loading_div").
//		        css({position:"absolute"}).
//		        appendTo(document.body).
//		        fadeOut(0);
			 div.fadeOut(0);

		    //window.onscroll=changePosition;
	        //window.onresize=changePosition;

		    //changePosition();
		    div.fadeIn();
		});
		
		jQuery(document).ajaxStop(function(){
			var div=$("#loading_div");
			if(div.length>0){
				debounce(div.fadeOut("normal",function(){
					$("body").unbind("mousemove");
				}),1000);
			}
			
		});
		
		jQuery(document).ajaxSuccess(function(event, xhr, ajaxOptions){
			if(success){
				success(event, xhr, ajaxOptions);
			}
		});
		
		jQuery(document).ajaxError(function(event, xhr, ajaxOptions, thrownError){
			$().message(xhr.statusText+" ("+xhr.status+")"+"："+xhr.responseText);
		});
		
		jQuery(document).ajaxComplete(function(event, xhr, ajaxOptions){
			if(xhr.status==500){
				$().message("http500 错误，服务器出现异常");
				return;
			}
			if(xhr.status==401){
				$().message("没有权限访问");
				return;
			}
			if(xhr.status==403){
				$().message("会话超时");
				$.cookie("garsiCookieSession", null);
				$.cookie("garsiLock", null);
				$(this).oneTime(100, function() { 
					self.location.reload();
				});
				return;
			}
		});
		
		var changePosition=function(){
			var div=$("#loading_div");
			if(div.length>0){
			    $("body").bind("mousemove", function(event){
					div.css({
						top:event.originalEvent.clientY+6,
						left:event.originalEvent.clientX+24
					});
			    });
			}
		};
		
	}
});
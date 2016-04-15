//高频执行事件or方法的防抖
function debounce(func, wait, immediate) {  
	var timeout;  
	return function() {  
	    var context = this, args = arguments;  
	    var later = function() {  
	        timeout = null;  
	        if (!immediate)func.apply(context, args);  
	    };  
	    var callNow = immediate && !timeout;  
	    clearTimeout(timeout);  
	    timeout = setTimeout(later, wait);  
	    if (callNow) func.apply(context, args);  
	};  
}

function loadView(url,region,callback){
	var load=function(region){
		region.triggerHandler("unload");
		
		$(document.body).removeData("contentPage");
		var requestParam = parse_url(url);
		if(!$.isEmptyObject(requestParam)){
			$(document.body).data("contentPage",requestParam);
		}
		
		region.load(url,function(){
			region.one("unload", function(){
				var dataTables=$.fn.dataTable.fnTables();
				if (dataTables.length>0&&region.find(dataTables).length>0){
					$(dataTables).dataTable().fnDestroy();
				}
			});
			if(callback!=null){
				region.show('slide', 'normal',callback);
			}else{
				region.show('slide', 'normal');
			}
		});
	}
	
	if(region){
		region=$("#"+region);
		load(region);
	}
}

function pageInterceptor(view){
	reviseCssInterceptor(view);
}

function ajaxInterceptor(view){
	view=$(view);
	
	permCodeInterceptor(view);
	//icheckInterceptor(view);
	tooltipInterceptor(view);
	showCriteriaInterceptor(view);
	selectDataInterceptor(view);
	select2plusInterceptor(view);
	ajaxFormSubmitInterceptor(view);
	ajaxFormSubmit2Interceptor(view);
	ajaxFormSubmit3Interceptor(view);
	ajaxFormSubmit4Interceptor(view);
	dataAjaxRefreshInterceptor(view);
	buttonActionInterceptor(view);
	dataFormEnterKeypressInterceptor(view);
	dataPopupOpenInterceptor(view);
	datepickerInterceptor(view);
	rangeSliderInterceptor(view);
	FineUploaderInterceptor(view);
	return view;
}

function permCodeInterceptor(view){
	view.find("*[data-permCode]").hide();

	if(typeof account==="object"){
		if(account.roles){
			$.each(account.roles, function (index, value) {
				view.find("*[data-permCode*='"+value.name+"']").show();
			});
		}
	}
}

function icheckInterceptor(view){
	if(view.find("input.icheck-me").length > 0){
		view.find("input.icheck-me").each(function(){
			var $el = $(this);
			var opt = {
				checkboxClass: 'icheckbox_square-blue',
				radioClass: 'iradio_square-blue',
				increaseArea: "10%"
			}

			$el.iCheck(opt);
		});
	}
}

function ajaxFormSubmitInterceptor(view){
	view.find("label.required").prepend("<span class='red'>*</span>&nbsp;&nbsp;");
	view.find("#addOrEditForm").validate({
		submitHandler:function(form){
			form=$(form);
			var validateSuccess=form.attr("data-validateSuccess");
			if(validateSuccess){
				eval(validateSuccess);
			}
			var data = JSON.stringify(getFormJson(form));
			$.ajax({
				type : form.attr("method"),
				url : document.rootURI+form.attr("action"),
				data : data,
				contentType : "application/json; charset=UTF-8",
				dataType : "json",
				success:function(data){
					if(data.success==true){
						$().message("done");
						var callBackCode=form.attr("data-callback");
						if(callBackCode){
							eval(callBackCode);
						}
					}else{
						$().message(data.message);
					}
				}
			});
		}
	});
}

function ajaxFormSubmit2Interceptor(view){
	view.find(".garsiForm").each(function(){
		$(this).validate({
			submitHandler:function(form){
				form=$(form);
				var validateSuccess=form.attr("data-validateSuccess");
				if(validateSuccess){
					eval(validateSuccess);
				}
				var data = JSON.stringify(getFormJson(form));
				$.ajax({
					type : form.attr("method"),
					url : document.rootURI+form.attr("action"),
					data : data,
					contentType : "application/json; charset=UTF-8",
					dataType : "json",
					success:function(data){
						if(data.success==true){
							$().message("done");
							var callBackCode=form.attr("data-callback");
							if(data.data){
								if(data.data.id && data.data.resourceId){
									form.removeAttr("method").attr("method","PUT");
									form.append("<input type='hidden' name='id' value='"+ data.data.id +"' />");
									form.find(":input[name='resourceId']").val(data.data.resourceId);
								}else if(data.data.askId && data.data.resourceId){
									form.removeAttr("method").attr("method","PUT");
									form.append("<input type='hidden' name='askId' value='"+ data.data.askId +"' />");
									form.find(":input[name='resourceId']").val(data.data.resourceId);
								}else if(data.data.familyHistoryId && data.data.resourceId){
									form.removeAttr("method").attr("method","PUT");
									form.append("<input type='hidden' name='familyHistoryId' value='"+ data.data.familyHistoryId +"' />");
									form.find(":input[name='resourceId']").val(data.data.resourceId);
								}
								else if(data.data.id && data.data.resourceId==null){
									form.removeAttr("method").attr("method","PUT");
									form.find(":input[name='id']").val(data.data.id);
									$(":input[name='resourceId']").val(data.data.id);
								}
							}
							if(callBackCode){
								eval(callBackCode);
								$('.page-body').animate({scrollTop: '0px'}, 800);
							}
						}else{
							$().message(data.message);
						}
					}
				});
			}
		});
	});
}

function ajaxFormSubmit3Interceptor(view){
	view.find(".garsiFileForm").each(function(){
		$(this).validate({
			submitHandler:function(form){
				form=$(form);
				var validateSuccess=form.attr("data-validateSuccess");
				if(validateSuccess){
					eval(validateSuccess);
				}
				var resourceId="";
				if(form.attr("data-resourceId")){
					resourceId = $("#"+form.attr("data-resourceId")).val();
				}
				if(form.find(":input[type='file']").val()==""){
					var callBackCode=form.attr("data-callback");
					if(callBackCode){
						eval(callBackCode);
					}
					return;
				}
				form.ajaxSubmit({
					type : form.attr("method"),
					url : document.rootURI+form.attr("action")+resourceId,
					success:function(data){
						if(data.success==true){
							$().message("done");
							var callBackCode=form.attr("data-callback");
							if(callBackCode){
								eval(callBackCode);
							}
						}else{
							$().message(data.message);
						}
					}
				});
			}
		});
	});
}

function ajaxFormSubmit4Interceptor(view){
	view.find("#customerSearchForm").each(function(){
		$(this).validate({
			submitHandler:function(form){
				form=$(form);
				var validateSuccess=form.attr("data-validateSuccess");
				if(validateSuccess){
					eval(validateSuccess);
				}
				var resourceId="";
				if(form.attr("data-resourceId")){
					resourceId = $("#"+form.attr("data-resourceId")).val();
				}
				if(form.find(":input[type='file']").val()==""){
					var callBackCode=form.attr("data-callback");
					if(callBackCode){
						eval(callBackCode);
					}
					return;
				}
				form.ajaxSubmit({
					type : form.attr("method"),
					url : document.rootURI+form.attr("action")+resourceId,
					success:function(data){
						if(data.success==true){
							$().message("done");
							var callBackCode=form.attr("data-callback");
							if(callBackCode){
								eval(callBackCode);
							}
						}else{
							$().message(data.message);
						}
					}
				});
			}
		});
	});
}

function dataAjaxRefreshInterceptor(view){
	if(view.find("a[data-refresh]").length > 0){
		view.find("a[data-refresh]").each(function(i){
			var a=$(this);
			var href=a.attr("href").replace(location.protocol+"//"+location.host,"");
			a.attr("data-href",document.rootURI+href);
			a.attr("href","javascript:void(0);");
			if(!href||href==""||href=="#"){
			}else{
				a.bind("click", function(event){
					//loadView(href,a.attr("data-refresh"));
					
					if(a.closest("#sidebarAccordion").length>0||a.attr("title")=="刷新"){//切换页面释放缓存数据
						/*var searchForm=$(".search");
						if(searchForm.length>0){
							searchForm.sisyphus({
								autoSave:false,
								autoRestore:false,
								autoRelease:false
							}).manuallyReleaseData();
						}*/
						//$.jStorage.flush();
					}
					$.address.value(a.attr("data-href")).
			        	parameter("_refresh",a.attr("data-refresh")).
			        	update();
					return false;
				});
			}
		});
	}
}

function dataFormEnterKeypressInterceptor(view){
	if(view.find("form[data-formEnterKeypress]").length > 0){
		view.find("form[data-formEnterKeypress]").each(function(i){
			var form=$(this);
			form.bind("keypress", function(event){
				if(event.keyCode==13){
					var callBackCode=form.attr("data-formEnterKeypress");
					if(callBackCode){
						eval(callBackCode);
					}
				}
			});
		});
	}
}

function buttonActionInterceptor(view){
	view.find("a[data-action]").each(function(i){
		var a=$(this);
		var href=a.attr("href");
		a.attr("data-href",document.rootURI+href);
		a.attr("href","javascript:void(0);");
		if(!href||href==""||href=="#"){
		}else{
			a.bind(a.attr("data-action"), function(actionEvent){
				var confirmMessage=a.attr("data-confirmMessage");
				if(confirmMessage==null){
					$.ajax({
						type:"GET",
						url:a.attr("data-href"),
						dataType:"json",
						success: function(data,textStatus){
							$().message(data.simpleAjaxResponse.message);
							if(data.simpleAjaxResponse.succeed==true){
								var callBackCode=a.attr("data-callback");
								if(callBackCode){
									eval(callBackCode);
								}
							}
						}
					});
				}else{
					openModal(document.rootURI+"views/commons/confirm.html","confirmView",
						function success(){
							$("#myModalLabel").html("Please confirm");
							$("#confirmMessage").html(confirmMessage);
							
							$("#confirmButton").bind("click", function(confirmEvent){
								$.ajax({
									type:a.attr("data-method")||"GET",
									url:a.attr("data-href"),
									dataType:"json",
									success: function(data,textStatus){
										$().message(data.message);
										if(data.success==true){
											var callBackCode=a.attr("data-callback");
											if(callBackCode){
												eval(callBackCode);
											}
											closeModal("confirmView");
										}else{
											closeModal("confirmView");
										}
									}
								});
							});
						}
					);
				}
				return false;
			});
		}
	});
}

function tooltipInterceptor(view){
	if(view.find("a[data-toggle]").length > 0){
		view.find("a[data-toggle]").each(function(i){
			var a=$(this);
			if(a.attr("data-toggle")=="tooltip"){
				if(a.attr("data-placement")){
					a.tooltip({placement:a.attr("data-placement")});
				}else{
					a.tooltip();
				}
				
			}
		});
	}
	if(view.find("button[data-toggle]").length > 0){
		view.find("button[data-toggle]").each(function(i){
			var a=$(this);
			if(a.attr("data-toggle")=="tooltip"){
				if(a.attr("data-placement")){
					a.tooltip({placement:a.attr("data-placement")});
				}
				a.tooltip();
			}
		});
	}
}

function dataPopupOpenInterceptor(view){
	view.find("a[data-popup]").each(function(i){
		var a=$(this);
		var href=a.attr("href");
		var ModalId = a.attr("data-modalId")||"myModal";
		a.attr("href","javascript:void(0);");
		if(!href||href==""||href=="#"){
		}else{
			if(a.attr("data-popup")=="modal"){
				a.bind("click", function(event){
					openModal(document.rootURI+href,ModalId);
				});
			}
		}
	});
}

function showCriteriaInterceptor(view){
	view.find(".showCriteria").bind("click", function(event){
		$(".moreCriteria").toggle("normal");
	});
}

function selectDataInterceptor(view){
	view.find("select[data-dataUrl]").each(function(i){
		var select=$(this);

		//获取父控件选择值
		var parentValue=null;
		var parentSelect=view.find("#"+select.attr("data-parentId"));
		if(parentSelect.length==1&&parentSelect.attr("multiple")==undefined){
			if(isSelect2(parentSelect)){
				parentValue=parentSelect.select2("val");
			}else{
				parentValue=parentSelect.attr("value");
			}
		}
		
		$.ajax({
			type:"GET",
			url:select.attr("data-dataUrl"),
			data:{"condiValue":parentValue},
			dataType:"json",
			async:false,
			success: function(data,textStatus){
				setOptions(select,data);

				//还原默认值
				var values=select.attr("data-value");
				if(select.attr("multiple")!=undefined&&values!=undefined){
					values=values.split(",");
				}
				select.val(values);
			}
		});

		//注册父空间更改事件
		if(parentSelect.length==1&&parentSelect.attr("multiple")==undefined){
			parentSelect.on("change",function(event){
				if(isSelect2(parentSelect)){
					parentValue=parentSelect.select2("val");
				}else{
					parentValue=parentSelect.val();
				}
				$.ajax({
					type:"GET",
					url:select.attr("data-dataUrl"),
					data:{"condiValue":parentValue},
					dataType:"json",
					success: function(data,textStatus){
						if(isSelect2(select)){
							select.select2("val",null);
						}else{
							select.val(null);
						}
						select.empty();
						clearChildrenOptions(view,select);
						setOptions(select,data);
					}
				});
			});
		}
		
		//生成Select2
		if(isSelect2(select)){
			select.select2({
				placeholder:select.attr("data-placeholder"),
				allowClear:true,
				dropdownAutoWidth:true
			});
		}
	});
}
function setOptions(select,data){
	if(data==null||$.isEmptyJSON(data)){
		return;
	}
	var optionArray=[];
	if(select.attr("multiple")==undefined){
		optionArray.push("<option value=''></option>");
	}
	if($.isArray(data)){
		$.each(data, function (index, obj) {
			optionArray.push("<option value='"+obj.id+"'>"+(obj.name)+"</option>");
        });
	}
	select.append(optionArray.join(""));
}
function clearChildrenOptions(view,select){
	var childrenSelects=view.find("select[data-parentId="+select.attr("id")+"]");
	childrenSelects.each(function(index,item){
		item=$(item);
		if(item.attr("multiple")==undefined){
			if(isSelect2(item)){
				item.select2("val",null);
			}else{
				item.val(null);
			}
			item.empty();
			return clearChildrenOptions(view,item);
		}
	});
}
function isSelect2(select){
	return select.hasClass("select");
}

function select2plusInterceptor(view){
	if(view.find(".select").length > 0){
		view.find(".select").each(function(i){
			var select=$(this);
			select.select2();
		});
	}
}

function datepickerInterceptor(view){
	if(view.find(".form_datetime").length > 0){
		view.find(".form_datetime").each(function(i){
			if($(this).attr("data-datepicker")=="date"){
				$(this).datetimepicker({
			        format: $(this).attr("data-date-format"),
			        showMeridian: true,
			        autoclose: true,
			        todayBtn: true,
			        minView:2
			    });
			}else if($(this).attr("data-datepicker")=="datetime"){
				$(this).datetimepicker({
			        format: $(this).attr("data-date-format"),
			        showMeridian: true,
			        autoclose: true,
			        todayBtn: true
			    });
			}
			if($(this).attr("data-callback")){
				var callback=$(this).attr("data-callback");
				$(this).datetimepicker().on('changeDate', function(ev){
					eval(callback);
				});
			}
		});
	}
}

function rangeSliderInterceptor(view){
	if(view.find(".slider-range").length > 0){
		view.find(".slider-range").each(function(i){
			var range=$(this);
			range.slider({
		        range: true,
		        min: parseInt(range.attr("data-slider-min")),
		        max: parseInt(range.attr("data-slider-max")),
		        values: [ parseInt(range.attr("data-slider-min")), parseInt(range.attr("data-slider-max")) ],
		        slide: function( event, ui ) {
		        	range.prev("input").val(ui.values[0]);
		        	range.next("input").val(ui.values[1]);
		        },
		        create:function( event, ui ) {
		        	range.prev("input").val(range.attr("data-slider-min"));
		        	range.next("input").val(range.attr("data-slider-max"));
		        }
			});
			range.prev("input").blur(function(){
				range.slider( "values", 0, range.prev("input").val());
			});
			range.next("input").blur(function(){
				range.slider( "values", 1, range.next("input").val());
			});
		});
	}
}

function reviseCssInterceptor(view){
	if(getViewportWidth()<800){
		$("#sidebar-nav").hide();
		$(".content").width("100%");
		$("#navbar-toggle-btn").show();
	}else{
		$("#sidebar-nav").css("position","static").show();
		$(".content").removeAttr("style");
		$("#navbar-toggle-btn").hide();
	}
	$("#menu-list").height(getViewportHeight()-110);
	$(".page-body").height(getViewportHeight()-120);
	$("#sidebar-nav").height(getViewportHeight());
	$("#menu-list").getNiceScroll().resize();
}

function FineUploaderInterceptor(view){
	if(view.find(".manual-fine-uploader").length > 0){
		view.find(".manual-fine-uploader").each(function(i){
			var manualuploader = new qq.FineUploader({
			  element: this,
			  request: {
			    endpoint: $(this).attr("data-upload-action")
			  },
			  autoUpload: false,
			  multiple: $(this).attr("data-upload-multiple")||false,
			  classes: {
					success: "alert alert-success",
					fail: "alert alert-error"
				},
			  messages:{
					noFilesError: "Please select a file."
				},
			  template: "qq-template",
			  thumbnails: {
			      placeholders: {
			          notAvailablePath: document.rootURI + "/images/assets/not_available-generic.png",
			          waitingPath: document.rootURI + "/images/assets/waiting-generic.png"
			      }
			  },
			  callbacks: {
				onError: function(event, id, name, errorReason, xhrOrXdr){
			    	$(this).oneTime(1000,function(){
						$(manualuploader.getItemByFileId(id)).hide("slow");
					});
			    	$().message(qq.format("Upload error {} : {}", id, name, errorReason));
				},
			    onComplete: function(id, fileName,data) {
			    	$(this).oneTime(1000,function(){
						$(manualuploader.getItemByFileId(id)).hide("slow");
					});
			    }
			  }
			});
			view.find("#triggerUpload").click(function() {
				manualuploader.uploadStoredFiles();
			});
		});
	}
}

function openModal(url,modalId,callback){
	var mymodal = $("<div id='"+ modalId + "' class='modal fade' tabindex='-1' role='dialog' data-keyboard='false' data-backdrop='static' aria-hidden='true'></div>")
		.appendTo(document.body);
	mymodal.modal();
	var requestParam = parse_url(url);
	$(document.body).removeData(modalId);
	if(!$.isEmptyObject(requestParam)){
		$(document.body).data(modalId,requestParam);
	}
	mymodal.load(url, callback); 
	mymodal.on('hide.bs.modal', function (e) {
		$(document.body).removeData(modalId);
		mymodal.empty();
		$("#"+modalId).remove();
	});
}

function closeModal(modalId){
	if(!modalId){
		modalId="myModal";
	}
	$("#"+modalId).modal('hide');
}

$(function($){
	$.address.init(function(event) {
		$.address.autoUpdate(false);
    }).change(function(event) {
    	var url=event.value;
    	if(url!="/"&&url!="/#"){
    		loadView(event.value,event.parameters["_refresh"]);
    	}
    });
	
	$._messengerDefaults = {
		extraClasses: 'messenger-fixed messenger-theme-block messenger-on-bottom'
	}
	
	$.validator.setDefaults({
		ignore:null,
		errorElement:"span",
		errorClass: "help-block has-error",
		errorPlacement:function(error, element){
			if($(error).html()!=""){
				$(element).tooltip({
					title:error,
					placement:"auto",
					html:true
				});
			}else{
				$(element).tooltip('destroy');
			}
			//element.after(error);
		},
		highlight: function(input,errorClass) {
			$(input).closest(".form-group").removeClass("has-success").addClass("has-error");
		},
		success: function(label) {
			$(label).addClass("valid").closest(".form-group").removeClass("has-error").addClass("has-success");
			$(label).closest(".tooltip").prev(":input").tooltip('destroy');
			//label.remove();
		}
	});	
	
	$.extend($.fn.dataTable.defaults,{
		bJQueryUI:true,
		sPaginationType:"full_numbers",
		bAutoWidth: false,
		bStateSave:true,
//		oLanguage:{
//            "sUrl":document.rootURI+"/scripts/jquery/jquery-datatable/js/i18n/dataTables-zh-CN.txt"
//		},
		bFilter: false,
        bSort: false,
        bDestroy: true,
		bServerSide:true,
		fnInitComplete:function(oSettings, json){
			var dataTable=this;
			var searchForm=$("form[data-bindTable='"+dataTable.attr("id")+"']").
				attr("onsubmit","return false;").
				bind("keypress", function(event){
					if(event.keyCode==13){
						dataTable.find(".btn-primary").click();
					}
				});
			searchForm.find(".btn-primary").click(function(event){
				dataTable.fnPageChange(0);
			});
		},
		fnServerData:function(sSource,aoData,fnCallback){
			var searchForm=$("form[data-bindTable='"+this.attr("id")+"']");
			var url = document.rootURI+searchForm.attr("action");
			if(searchForm.attr("action-param")!=null){
				if(searchForm.attr("action-param")=="username"){
					url += "/"+account.userName;
				}
			}
			if(searchForm.length==1){
				var requestData = getFormJson(searchForm);
				var secho = aoData[0].value;
				for (var i = 0; i < aoData.length; i++) {
					if(aoData[i].name=="iDisplayStart"){
						requestData.start=aoData[i].value;
					}
					if(aoData[i].name=="iDisplayLength"){
						requestData.limit=aoData[i].value;
					}
				}
				$.ajax({
					type : searchForm.attr("method"),
					url : url,
					data : requestData,
					contentType : "application/json; charset=UTF-8",
					dataType : "json",
					success:function(json){
						var responseData = {};
						responseData.iTotalRecords=json.total;
						responseData.iTotalDisplayRecords=json.total;
						responseData.sEcho = secho;
						responseData.aaData = json.results
						fnCallback(responseData);
					}
				});
			}
		}
    });

	$.initLoader(
		document.rootURI+"/scripts/jquery/jquery-loader/",
		function dataFilter(data, type){
			if(type=="html"){
				return ajaxInterceptor($("<div>"+data+"</div>"));
			}else{
				return data;
			}
		},
		function success(evt, request, settings){
			pageInterceptor($(document.body));
		}
	);

});

$(window).resize(function(){
	reviseCssInterceptor($("body"));
});

function dataTableDraw(dataTableId){
	var dataTable=$("#"+dataTableId).dataTable();
	dataTable.fnDraw();
}

function getViewportHeight() {
	var h=0;
	h = $(window).height();
	return h;
}
function getViewportWidth() {
	var w=0;
	w = $(window).width();
	return w;
}

function navbarToggleBtn() {
	
}

function getFormJson($form) {
	var o = {};
	var a = $form.find(":input").not("*[ignore]").serializeArray();
	$.each(a, function () {
		if (o[this.name] !== undefined) {
			if (!o[this.name].push) {
				o[this.name] = [o[this.name]];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
}

function parse_url(_url){
	var pattern = /(\w+)=(\w+)/ig;
	var params = {};
	_url.replace(pattern, function(a, b, c){
		if(b!="_refresh"){
			params[b] = c;
		}
	});
	return params;
}

function Birthdate2Age(BirthdateElement,showInElement){
	var Birthdate = $("#"+BirthdateElement).val();
	if(Birthdate==""){
		$("#"+showInElement).val("");
		return;
	}
	var Birthdatetime = Date.parse(Birthdate);
    var ageSeconds = new Date().getTime() - Birthdatetime;
    var cha=ageSeconds/86400000/365;
    var age = Math.abs(cha)+"";
    age =age.split(".")[0];
    $("#"+showInElement).val(age);
}

function computeBMI(heightElemt,weightElemt,bmiElemt){
	var height = $("#"+heightElemt).val();
	var weight = $("#"+weightElemt).val();
	if(height!="" && weight!=""){
		var mHeight = height*unit_convert[2][14][9];
		var kgWeight = weight*unit_convert[3][1][9];
		var bmi = kgWeight/(mHeight*mHeight);
		$("#"+bmiElemt).val(bmi.toFixed(2));
	}else{
		$("#"+bmiElemt).val("");
	}
}

function switchProgressBar(width){
	$("#progressBar").css("width",width);
	$("#progressBar").html(width);
}

function switchDisplayState(showId,hideId){
	$("#"+showId).show();
	$("#"+hideId).hide();
	$('.page-body').animate({scrollTop: '0px'}, 400);
}

function fillForm(json,$form){
	$form.find(":input").each(function(i){
		var input = $(this);
		var inputName = $(this).attr("name");
		var val;
		if(input.attr("data-date-format")){
			val = parseDate(json[inputName]).format("mm/dd/yyyy");
		}else{
			val = json[inputName];
		}
		if(input.attr("type")!=null){
			switch (input.attr("type")) {
			case "radio":
				if(input.val()==val){
					input.attr("checked","checked");
				}
				break;
			case "checkbox":
				if(val.indexOf("[")>=0 && val.indexOf("]")>=0){
					var vals = eval(val);
					for(var jj=0;jj<vals.length;jj++){
						if(input.val()==vals[jj]){
							input.attr("checked","checked");
						}
					}
				}else{
					if(input.val()==val){
						input.attr("checked","checked");
					}
				}
				break;
			case "text":
				input.val(val);
				break;	
			case "hidden":
				input.val(val);
				break;	
			case "password":
				input.val(val);
				break;
			}
		}else if(isSelect2($(this))){
			if(val.indexOf("[")>=0 && val.indexOf("]")>=0){
				var vals = eval(n);
				$(this).select2("val", vals); 
			}else{
				$(this).select2("val",val);
			}
		}else{
			if (val != null) {
				input.val(val);
			}
		}
	});
}

function fillDetail(json,$tab){
	$.each(json, function(i, n) {
		$tab.find("span[data-value='" + i + "']").each(function() {
			var span = $(this);
			if(span.attr("data-date-format")){
				n = parseDate(n).format("mm/dd/yyyy");
			}
			span.html(n);
		});
	});
}

function printPage(elId){
	$("#"+elId).printArea({
		mode:"popup",
		popTitle:"Print"
	});
}

function sendRequest(options) {
	var uri = options.url;
	var sync = options.sync||true;
	var method = options.method;
	var json = JSON.stringify(options.data);
	var response = $.ajax({
			type : method,
			async : false,
			url : "api" + uri,
			data : json,
			contentType : "application/json; charset=UTF-8",
			dataType : "json",
			cache : false,
			success:options.success,
			error : function(obj) {
			console.log("sendRequest failed! URI:" + uri + ", JSON:" + json);
			}
		});
	return response.responseJSON;
}
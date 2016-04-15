(function ($) {
    var counter = 0;
    var modes = { iframe: "iframe", popup: "popup" };
    var defaults = { mode: modes.iframe,
        popHt: 500,
        popWd: 400,
        popX: 200,
        popY: 200,
        popTitle: '',
        popClose: false,
        printA4 : false
    };

    var settings = {}; //global settings

    $.fn.printArea = function (options) {
        $.extend(settings, defaults, options);

        counter++;
        var idPrefix = "printArea_";
        $("[id^=" + idPrefix + "]").remove();
        var ele = getFormData($(this));

        settings.id = idPrefix + counter;

        var writeDoc;
        var printWindow;

        switch (settings.mode) {
            case modes.iframe:
                var f = new Iframe();
                writeDoc = f.doc;
                printWindow = f.contentWindow || f;
                break;
            case modes.popup:
                printWindow = new Popup();
                writeDoc = printWindow.doc;
        }

        writeDoc.open();
        writeDoc.write(docType() + " <html lang='en'>" + getHead() + getBody(ele) + "</html>");
        
        //增加功能：隐藏打印区按钮
		$(writeDoc).find(".modal-footer").attr("style","display:none;");
        
        writeDoc.close();
        
        printWindow.focus();
        printWindow.print();

        if (settings.mode == modes.popup && settings.popClose)
            printWindow.close();
    }

    function docType() {
        //if (settings.mode == modes.iframe || !settings.strict) return "";

        //var standard = settings.strict == false ? " Trasitional" : "";
        //var dtd = settings.strict == false ? "loose" : "strict";

        //return '<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01' + standard + '//EN" "http://www.w3.org/TR/html4/' + dtd + '.dtd">';
    	return '<!DOCTYPE html>';
    }

    function getHead() {
        var head = "<head><title>" + settings.popTitle + "</title>";
        head += '<meta charset="utf-8"> ';
        head += '<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">';
        $(document).find("link")
            .filter(function () {
                return $(this).attr("rel").toLowerCase() == "stylesheet";
            })
            .each(function () {
                head += '<link type="text/css" rel="stylesheet" href="' + $(this).attr("href") + '" />';
            });
        head += '<link type="text/css" rel="stylesheet" href="' + document.rootURI + 'styles/print.css" />';
        head += "</head>";
        return head;
    }
    
    function getBody(printElement) {
    	//新增方法 将input修改成span
    	var element = $(printElement).clone();
    	
    	$(element).find("h3").each(function(i){
    		var text = $(this).html();
    		$(this).before("<span style='font-weight: bold;'>"+ text +"</span>");
    		$(this).remove();
    	});
    	
    	$(element).find("span").each(function(i){
    		if($(this).attr("style")=="color:red;"){
    			$(this).remove();
    		}
    	});
    	
    	$(element).find(":text").each(function(i){
    		var text = this.value;
    		$(this).before("&nbsp;<span class='printSpan'>"+ text +"</span>");
    		$(this).remove();
    	});
    	//去掉文本编辑器
    	var keFlag = false;
    	if($(element).find(".ke-container")){
    		keFlag=true;
    		$(element).find(".ke-container").remove();
    	}
    	
    	$(element).find("input").each(function(i){
    		var type = $(this).attr("type");
            if (type == "radio") {
            	if ($(this).is(":not(:checked)")){
            		$(this).before("<img src='" + document.rootURI + "/images/3.png' width='9' height='9' />");
            		$(this).remove();
            	} else{
            		$(this).before("<img src='" + document.rootURI + "/images/4.png' width='9' height='9' />");
            		$(this).remove();
                } 
            }
            if (type == "checkbox") {
            	if ($(this).is(":not(:checked)")){
            		$(this).before("<img src='" + document.rootURI + "/images/1.png' width='9' height='9' />");
            		$(this).remove();
            	} else{
            		$(this).before("<img src='" + document.rootURI + "/images/2.png' width='9' height='9' />");
            		$(this).remove();
                } 
            }
    	});
    	
    	$(element).find("textarea").each(function(i){
    		var text = $(this).clone().html();
    		text = text.replace(/(\r)*\n/g,"<br/>");
    		text = text.replace(/\s/g,"&nbsp;");
    		if(keFlag){
    			$(this).before(decodeHtml(text));
    		}else{
    			$(this).before("<p>"+ text +"</p>");
    		}
    		$(this).remove();
    	});
    	
    	var printClass="dataTables_wrapper";
        return '<body><div class="'+ printClass +'">' + $(element).html() + '</div></body>';
    }

    function getFormData(ele) {
        $("input,select,textarea", ele).each(function () {
            // In cases where radio, checkboxes and select elements are selected and deselected, and the print
            // button is pressed between select/deselect, the print screen shows incorrectly selected elements.
            // To ensure that the correct inputs are selected, when eventually printed, we must inspect each dom element
            var type = $(this).attr("type");
            if (type == "radio" || type == "checkbox") {
                if ($(this).is(":not(:checked)")) this.removeAttribute("checked");
                else this.setAttribute("checked", true);
            }
            else if (type == "text"){
                this.setAttribute("value", $(this).val());
            }
            else if (type == "select-multiple" || type == "select-one"){
                $(this).find("option").each(function () {
                    if ($(this).is(":not(:selected)")) this.removeAttribute("selected");
                    else this.setAttribute("selected", true);
                });
            }
            else if (type == "textarea") {
                var v = $(this).attr("value");
                if ($.browser.mozilla) {
                    if (this.firstChild) this.firstChild.textContent = v;
                    else this.textContent = v;
                }
                else this.innerHTML = v;
            }
        });
        
        return ele;
    }

    function Iframe() {
        var frameId = settings.id;
        var iframeStyle = 'border:0;position:absolute;width:0px;height:0px;left:0px;top:0px;';
        var iframe;

        try {
            iframe = document.createElement('iframe');
            document.body.appendChild(iframe);
            $(iframe).attr({ style: iframeStyle, id: frameId, src: "" });
            iframe.doc = null;
            iframe.doc = iframe.contentDocument ? iframe.contentDocument : (iframe.contentWindow ? iframe.contentWindow.document : iframe.document);
        }
        catch (e) { throw e + ". iframes may not be supported in this browser."; }

        if (iframe.doc == null) throw "Cannot find document.";

        return iframe;
    }

    function Popup() {
    	//width=1003px,height=768px
        var windowAttr = "location=no,statusbar=no,directories=no,menubar=no,titlebar=no,toolbar=no,dependent=no";
        windowAttr += ",width=900px,height=700px,top=0,left=0,toolbar=no,scrollbars=yes,personalbar=no";
        windowAttr += ",resizable=yes,screenX=" + settings.popX + ",screenY=" + settings.popY + "";

        var newWin = window.open("", "_blank", windowAttr);

        newWin.doc = newWin.document;

        return newWin;
    }
    
    function decodeHtml (s){
        var HTML_DECODE = {
                "&lt;" : "<",
                "&gt;" : ">",
                "&amp;" : "&",
                "&nbsp;": " ",
                "&quot;": "\"",
                "&copy;": ""
                // Add more
            };
        s = (s != undefined) ? s : this.toString();
        return (typeof s != "string") ? s :
            s.replace(/&\w+;|&#(\d+);/g,
                      function($0, $1){
                          var c = HTML_DECODE[$0];
                          if(c == undefined){
                              // Maybe is Entity Number
                              if(!isNaN($1)){
                                  c = String.fromCharCode(($1 == 160) ? 32:$1);
                              }else{
                                  c = $0;
                              }
                          }
                          return c;
                      });
    }

})(jQuery);
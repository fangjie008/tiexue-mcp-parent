
function getCookie(c_name) {
    if (document.cookie.length > 0) {
        c_start = document.cookie.indexOf(c_name + "=");
        if (c_start != -1) {
            c_start = c_start + c_name.length + 1;
            c_end = document.cookie.indexOf(";", c_start);
            if (c_end == -1) c_end = document.cookie.length;
            return unescape(document.cookie.substring(c_start, c_end));
        }
    }
    return "";
}

function setCookie(c_name, value, expiredays) {
    var exdate = new Date();
    exdate.setDate(exdate.getDate() + expiredays);
    document.cookie = c_name + "=" + escape(value) +
    ((expiredays == null) ? "" : ";expires=" + exdate.toGMTString())+";path=/";
}

//获得url中传递的参数
function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
    var regexS = "[\\?&]" + name + "=([^&#]*)";
    var regex = new RegExp(regexS);
    var results = regex.exec(window.location.search);
    if (results == null)
        return "";
    else
        return decodeURIComponent(results[1].replace(/\+/g, " "));
}


function bookrack(bookid,chapterid){
	this.bookid=bookid;
	this.chapterid=chapterid;
}
//把书架信息添加到cookie中
function addbookrack(bookid,chapterid){
	var isupdate=false;
	var array=new Array();
	var bookracks= getCookie("defaultbookrack");
	if(bookracks==undefined||bookracks==""){
		array.push(new bookrack(bookid,chapterid));
	}else{
		var jsonobj=JSON.parse(bookracks);
		if(jsonobj.length>=20){
			//删除第一个
			jsonobj.shift();
		}
		for(var i=0;i<jsonobj.length;i++){
			if(jsonobj[i].bookid==bookid){
				isupdate=true;
				if(chapterid!="0"||chapterid!=0){
					jsonobj[i].chapterid=chapterid;
				}
			}
		}
		array=jsonobj;
		if(!isupdate){
			array.push(new bookrack(bookid,chapterid));
		}
		
	}
	
	var json=JSON.stringify(array);
    setCookie("defaultbookrack",json,365);
}

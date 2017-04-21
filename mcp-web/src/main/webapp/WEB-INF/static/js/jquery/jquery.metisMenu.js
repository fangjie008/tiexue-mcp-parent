;(function ($, window, document, undefined) {

    var pluginName = "metisMenu",
        defaults = {
            toggle: true
        };
        
    function Plugin(element, options) {
        this.element = element;
        this.settings = $.extend({}, defaults, options);
        this._defaults = defaults;
        this._name = pluginName;
        this.init();
    }

    Plugin.prototype = {
        init: function () {
            var $this = $(this.element),
                $toggle = this.settings.toggle;

            $this.find('li.active').has('ul').children('ul').addClass('collapse in');
            $this.find('li').not('.active').has('ul').children('ul').addClass('collapse');
            $this.find('li').children('a').on('click',function(e){
            	e.preventDefault();
            	var $a = $(this);
                $(this).parent('li').toggleClass('active').children('ul').collapse('toggle');
                if ($toggle) {
                    $(this).parent('li').siblings().removeClass('active').children('ul.in').collapse('hide');
                }

                // Auto Scroll document when click
                setTimeout(function(){
                    if ($.cookie('header') == 'header-fixed') {
                        // when sidebar fixed
                    } else {
                        if ($a.parent().hasClass('active')) {
                            $('html,body').animate({
                                scrollTop: ($a.offset().top-100)
                            }, 'slow');  
                        // Scroll to top when collapsed      
                        } else {
                             
                        }
                        /*$('html,body').animate({
                                scrollTop: ($a.offset().top-100)
                        }, 500); */
                    }
                }, 300);
                //增加导航
                var _id = $(this).attr("id");
                if(_id != "" && _id != null){		//没有id表示是链接，也就是叶子节点
                	var _li = $("#side-menu").children("li.active");
                	var _title ="<li><i class='fa fa-home'></i>首页</li>";
                	var _span = "";
                	var _ul = "";
                	while(true){
                		if(_li.length < 1){
                			break;
                		}
                		_span = _li.find("a").children("span.menu-title").html();
                		_title = _title + " <li ><i class='fa fa-angle-right'></i>" + _span + "</li>";
                		_ul = _li.find("ul");
                		if(_ul != null && _ul.length > 0){
                			_li = _ul.find("li.active");
                		}else{
                			break;
                		}
                	}
                	$("#page_crumb").html(_title);
                }
            });
        }
    };

    $.fn[ pluginName ] = function (options) {
        return this.each(function () {
            if (!$.data(this, "plugin_" + pluginName)) {
                $.data(this, "plugin_" + pluginName, new Plugin(this, options));
            }
        });
    };

})(jQuery, window, document);

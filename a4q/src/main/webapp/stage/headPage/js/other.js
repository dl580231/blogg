// JavaScript Document
/* 
* 创建时间：2011.03.14
* 创建原因：页面部分jquery特效
* 创 建 者：meihua
*/
$.include("/etsrc/buy/buy.js");
function slide_switch(content_container, nav_container, auto_scroll) {
	this.position = 0;
	this.wrap_position = 0;
	this.allow_scroll = true;
	this.wraping = false;	
	
	var DISPLAY_NAV_ITEM_COUNT = 3;
	var DISPLAY_SECOND = 3;
	
	var contents = $(content_container).find(".adImg li");
	var navs = $(nav_container).find(".adNum li");
	
	var total = contents.length;
	var wrap_total = total - DISPLAY_NAV_ITEM_COUNT;
	if (wrap_total < 0) {
		wrap_total = 0;
	}
	
	var nav_wrap = $(nav_container).find(".slideWrap");
	var nav_height = $(nav_container).find(".slideWrap ul li").outerHeight();
	
	this.wrap_to = function (wrap_position) {
		if (wrap_position < 0 || wrap_position > wrap_total) {
			return;
		}		
		var last_position = this.wrap_position;
		this.wrap_position = wrap_position;		
		if (last_position < wrap_position) {
			nav_wrap.animate({ top: "-=" + (wrap_position - last_position) * nav_height }, 400);
		}
		else if (last_position > wrap_position) {
			nav_wrap.animate({ top: "+=" + (last_position - wrap_position) * nav_height }, 400);
		}
		
		if (this.wrap_position == 0) {
			$(nav_container).find(".slide_prev").addClass("slide_prev_e");			
		}
		else {
			$(nav_container).find(".slide_prev").removeClass("slide_prev_e");
		}
		if (this.wrap_position == wrap_total) {
			$(nav_container).find(".slide_next").addClass("slide_next_e");	
		}
		else {			
			$(nav_container).find(".slide_next").removeClass("slide_next_e");
		}
	}
	this.wrap_to_prev = function () {		
		this.wrap_to(this.wrap_position - 1);
	}	
	this.wrap_to_next = function () {		
		this.wrap_to(this.wrap_position + 1);
	}
	this.wrap_to_first = function () {
		this.wrap_to(0);
	}
	this.wrap_init = function () {
		nav_wrap.animate({ top: "0px" }, 400);
		$(nav_container).find(".slide_prev").addClass("slide_prev_e");
	}
	
	var self = this;
	this.auto_scroll = function () {
		if (self.position == total) {
			self.position = 0;
			self.wrap_to_first();
		}
		else {
			var wrap_position = self.position - DISPLAY_NAV_ITEM_COUNT + 1;
			if (wrap_position < 0) {
				wrap_position = 0;
			}
			if (self.position > self.wrap_position && self.position < self.wrap_position + DISPLAY_NAV_ITEM_COUNT - 1) {				
			}
			else {
				self.wrap_to(wrap_position);
			}			
		}
		opacityShowImg(contents, navs, self.position);
		++self.position;		
	}
	
	this.timer = null;
	this.set_timer = function () {
		if (!auto_scroll || this.timer != null) {
			return;
		}
		this.timer = setInterval(this.auto_scroll, DISPLAY_SECOND * 1000);
	}
	this.clear_timer = function () {
		if (this.timer == null) {
			return;
		}
		clearInterval(this.timer);
		this.timer = null;
	}
	
	this.wrap_init();
}

function slideSwitch(content_container, nav_container, auto_scroll) {
	var contents = $(content_container).find(".adImg li");
	var navs = $(nav_container).find(".adNum li");
	
	var switcher = new slide_switch(content_container, nav_container, auto_scroll);
	var self = this;
	
	$(nav_container).find(".slide_prev").click(function () {
		switcher.clear_timer();
		switcher.wrap_to_prev();
		switcher.set_timer();
	}).hover(
		function(){
			$(this).addClass("slide_prev_h");
		}, function(){
			$(this).removeClass("slide_prev_h");
		});
	$(nav_container).find(".slide_next").click(function () {
		switcher.clear_timer();
		switcher.wrap_to_next();
		switcher.set_timer();
	}).hover(
		function(){
			$(this).addClass("slide_next_h");
		}, function(){
			$(this).removeClass("slide_next_h");
		});
		
	navs.click(function () {
		switcher.clear_timer();
		switcher.position = navs.index(this);	// 设置当前显示图片所在位置
		opacityShowImg(contents, navs, switcher.position);
		++switcher.position;
		switcher.set_timer();
	});
	
	navs.eq(0).click();
		
	$(contents).hover(function () {
		switcher.clear_timer();
	}, function () {
		switcher.set_timer();
	});
}

function opacityShowImg(imgItem, numItem, index) {
	$(imgItem).eq(index).addClass("currentImg").fadeIn(400).siblings().removeClass("currentImg").fadeOut(400);
	$(numItem).eq(index).addClass("currentNum").siblings().removeClass("currentNum");
}
function slideVertical(slideobj, len){  //len为框架内放置元素个数再减1
	var page_count = $(slideobj).find(".slideWrap ul li").length - len;
	var none_unit_width = $(slideobj).find(".slideWrap ul li").outerHeight(); //获取框架内容的高度,不带单位
	slideChange(slideobj, page_count, none_unit_width, "up", ".slide_prev", ".slide_next", "slide_prev_h", "slide_next_h", "slide_prev_e", "slide_next_e");
}

/*图片分屏滚动*/
function slideHorizontal_img(slideobj){
	var i = 6; //每版放6个图片
	var len =$(slideobj).find(".slideWrap ul li").length;
	var page_count = Math.ceil(len / i) ;   //只要不是整数，就往大的方向取最小的整数
	var none_unit_width = $(slideobj).find(".stu_simg").outerWidth(); //获取框架内容的宽度,不带单位
	switchImg("414px");
	slideChange(slideobj, page_count, none_unit_width, "left", ".slide_left", ".slide_right", "slide_left_h", "slide_right_h", "slide_left_e", "slide_right_e");
}
function slideHorizontal(slideobj, num, auto_scroll){
	var i = 0;
	if(num){i = num;}
	else{i = 5;}
	var len =$(slideobj).find(".slideWrap ul li").length;
	var page_count = Math.ceil(len / i) ;   //只要不是整数，就往大的方向取最小的整数
	var none_unit_width = $(slideobj).find(".m_tec_wrap").outerWidth(); //获取框架内容的宽度,不带单位
	slideChange(slideobj, page_count, none_unit_width, "left", ".slide_left", ".slide_right", "slide_left_h", "slide_right_h", "slide_left_e", "slide_right_e", auto_scroll);
}
function slideChange(element, pageCount, slideWidth, position, prevItem, nextItem, prevItemHover, nextItemHover, prevItemEnd, nextItemEnd, auto_scroll){
	var page = 1;
	var $parent = $(element).find(".slideWrap");
	var $next = $(element).find(nextItem);
	var $prev = $(element).find(prevItem);
	if( page == 1 ){
		$prev.addClass(prevItemEnd);
	}
	var $curNumObj = $parent.parent().siblings().find("#curNum");
	var switchTimer;
    //向右/下 按钮
	$next.click(function(){
		var _this = this;
	   	if( !$parent.is(":animated") ){
			if( page == pageCount ){  //已经到最后一个版面了,如果再向前,停止动画。
				$parent.stop();
			}else{
				if(page + 1 == pageCount){
					$next.addClass(nextItemEnd);
				}
				$prev.removeClass(prevItemEnd);
				slideLeft($parent, position, slideWidth);
				page++;
			}
			if($curNumObj){
				$curNumObj.text(page);
			}
		}
	});
	$next.hover(function(){
		$(this).addClass(nextItemHover);
	},function(){
		$(this).removeClass(nextItemHover);
	});
	
    //往左/上 按钮
	$prev.click(function(){
		var _this = this;
	    if( !$parent.is(":animated") ){
			if( page == 1 ){  //已经到第一个版面了,如果再向前,停止动画。
				$parent.stop();
			}else{
				if(page - 1 == 1){
					$prev.addClass(prevItemEnd);
				}
				$next.removeClass(nextItemEnd);
				slideRight($parent, position, slideWidth);
				page--;
			}
			if($curNumObj){
				$curNumObj.text(page);
			}
		}
	});
	
	if(auto_scroll == 1){
		$parent.hover(function(){
			clearInterval(switchTimer);
		},function(){
			var $self = $(this);
			switchTimer = setInterval(function(){
				if( !$self.is(":animated") ){
					if( page == pageCount ){  //已经到最后一个版面了,如果再向前,停止动画。
						$self.animate({left : '0px'}, "slow");
						page = 1;
						$prev.addClass(prevItemEnd);
						$next.removeClass(nextItemEnd);
					}else{
						slideLeft($self, "left", slideWidth);
						page++;
						$prev.removeClass(prevItemEnd);
					}
					if($curNumObj){
						$curNumObj.text(page);
					}
				}
			} , 4000);
		}).trigger("mouseleave");
	}
	
	$prev.hover(function(){
		$(this).addClass(prevItemHover);
	},function(){
		$(this).removeClass(prevItemHover);
	});
}

function slideLeft(element, position, slideLimits){
	if(position == "left"){
		element.animate({ left : '-='+slideLimits}, 600);
	}else{
		element.animate({ top : '-='+slideLimits}, 400);
	}
}
function slideRight(element, position, slideLimits){
	if(position == "left"){
		element.animate({ left : '+='+slideLimits}, 600);
	}else{
		element.animate({ top : '+='+slideLimits}, 400);
	}
}

/* 焦点图切换 */
function switchImg(imgheight){
     var len = $(".mainAd_adNum > li").length;
	 var index = 0;
	 var adTimer;
	 $(".mainAd_adNum li").click(function(){
		index = $(".mainAd_adNum li").index(this);
		showImg(index, ".mainAd_adImg li", ".mainAd_adNum li", "adImg_cur", "adNum_cur", imgheight);
	 }).eq(0).click();	
	 //滑入 停止动画，滑出开始动画.
	 $('.mainAd_ad').hover(function(){
			 clearInterval(adTimer);
		 },function(){
			 adTimer = setInterval(function(){
                 showImg(index, ".mainAd_adImg li", ".mainAd_adNum li", "adImg_cur", "adNum_cur", imgheight);
				index ++;
				if(index == len){index = 0;}
			  } , 4000);
	 }).trigger("mouseleave");
}

/* v2013.05 */
function switchFocusImg(imgObj, numObj, currentImg, currentNum) {
    var len = $(numObj).length;
    var index = 0;
    var adTimer;
    $(numObj).click(function(){
        index = $(numObj).index(this);
        showImg(index, imgObj, numObj, currentImg, currentNum);
    }).eq(0).click();
    //滑入 停止动画，滑出开始动画.
    $(imgObj).parent().hover(function(){
        clearInterval(adTimer);
    },function(){
        adTimer = setInterval(function(){
            showImg(index, imgObj, numObj, currentImg, currentNum);
            index ++;
            if(index == len) {index = 0;}
        } , 4000);
    }).trigger("mouseleave");
}

function showImg(index, imgObj, numObj, currentImg, currentNum, imgheight) {
    var ht = "";
    var imgHeight = $(imgObj).height();
    if(imgheight){
        ht = imgheight;
    }
    else{
        ht = imgHeight;
    }

    $(imgObj).eq(index).addClass(currentImg).stop(true,false).animate({opacity: "100"},600).siblings().removeClass(currentImg).animate({opacity: "0"},500);
    $(numObj).eq(index).addClass(currentNum).siblings().removeClass(currentNum);
}

/* 新闻滚动 */
function scrollNews(obj){
   var $self = obj.find("ul:first"); 
   var lineHeight = $self.find("li:first").height(); //获取行高
   $self.stop(true,false).animate({ "marginTop" : -lineHeight +"px" }, 600 , function(){
         $self.css({marginTop:0}).find("li:first").appendTo($self); //appendTo能直接移动元素
   })
}

/* 返回顶部 */
function backToTop(bottomHeight, leftWidth, imgSrc){
    var backToTopImgSrc = imgSrc == undefined ? "common/backtop.png": imgSrc;
    var $backToTopCon = "<img src='" + etImageUrl + "/images/" + backToTopImgSrc + "' alt='返回顶部' />",
		$backToTopEle = $('<div class="r_backtop"></div>').appendTo($("body"))
		.html($backToTopCon).click(function() {
			$("html, body").animate({scrollTop: 0}, 120);
	}), $backToTopFun = function() {
		var st = $(document).scrollTop(), 
		winh = $(window).height(),
		top = st + winh,
		maxHeight = $(document).height();
		limitTop = winh - 100;
		(st > 50)? $backToTopEle.show(): $backToTopEle.hide();
		if(top < maxHeight - bottomHeight){
			if (!window.XMLHttpRequest) { //IE6定位
				top = top - 100;
			}else{
				top = limitTop;
			}
		}else{
			if (!window.XMLHttpRequest) { //IE6定位
				top = top - 100 - (bottomHeight + top - maxHeight);
			}else{
				top = limitTop - (bottomHeight + top - maxHeight);
			}
		}
		$backToTopEle.css({"left": $('#container').offset().left + leftWidth, "top": top});
	};
	$(window).bind("scroll", $backToTopFun);
}

/*多余字符省略号显示，鼠标滑过弹出tooltip*/
function displayEllipsis(element, maxchars, isvisibility){
	var displayItem = $(element);
	displayItem.each(function(){
		var _this = this;
        var textCon = $(_this).html();
		if($(_this).text().length > maxchars){
			$(_this).text($(_this).text().substring(0, maxchars - 2));
			$(_this).html($(_this).html() + "…");
            if(isvisibility != undefined && isvisibility == 1){
                $(_this).css({"cursor": "pointer", "visibility": "visible"});
            }
            else {
                $(_this).css({"cursor": "pointer", "display": "block"});
            }
			var x = 10;
			var y = 20;
			$(_this).off().mouseover(function(e){
				var tooltip = "<div id='tooltip'>"+ textCon +"</div>"; //创建 div 元素
				$("body").append(tooltip);
				$("#tooltip")
					.css({
						"top": (e.pageY+y) + "px",
						"left": (e.pageX+x) - 60  + "px",
						"z-index": 99999
					}).show("fast");
			}).mouseout(function(){
				$("#tooltip").remove();
			}).mousemove(function(e){
				$("#tooltip")
					.css({
						"top": (e.pageY+y) + "px",
						"left": (e.pageX+x) - 60  + "px",
						"z-index": 99999
					});
			});
		}
		else{
			$(_this).css({"display": "block"});
            $(_this).off();
		}
	});
}

/*tab切换*/
function setTab(name, cursel, callback, id){
	var tagItem = $(".tabTag_" + name).children();
	var conItem = $(".tabCon_" + name).children();
	tagItem.each(function(i){
		if(i == cursel){
			tagItem.eq(i).addClass("curTag").siblings().removeClass("curTag");
			conItem.eq(i).show().siblings().hide();
		}
	})
	var imglazy = $(".tabCon_" + name).find("img").attr("original");
	if (imglazy) {
	    $("img[original]").lazyload({ placeholder: "http://www.jiandan100.cn/images/common/grey.gif" });
	}
    if(callback){
		if(id){
			callback(id, cursel);
		}
		else{
        	callback(name, cursel);
		}
	}
}
/* v2013.05 */
function  toggleTab(eventType, triggerObj, contentObj, currentName, callback) {
    var $triggerObj = $(triggerObj);
    $triggerObj.on(eventType, function() {
        var $self = $(this);
        var index = $triggerObj.index($self);
        $self.addClass(currentName).siblings().removeClass(currentName);
        if(contentObj) {
            $(contentObj).eq(index).show().siblings().hide();
        }
        if(callback) {
            callback();
        }
    })
}

/*tab切换*/
function setTabEx(name, cursel, count, prefix, callback){
    for(var i = 0; i < count;i ++){
        if(i == cursel){
            $('#' + prefix + i).addClass("curTag").siblings().removeClass("curTag");
            $('#' + prefix + "content_" + i).show().siblings().hide();
        }
    }
	var imglazy = $(".tabCon_" + name).find("img").attr("original");
	if (imglazy) {
	    $("img[original]").lazyload({ placeholder: "http://www.jiandan100.cn/images/common/grey.gif" });
	}
    if(callback)
        callback(name, cursel);
}
function hoverTab(tagItem){
	var tag = $(tagItem).children();
	tag.hover(function(){
		var _this = this;
		$(_this).addClass("tagHover");
	},function(){
		var _this = this;
		$(_this).removeClass("tagHover");
	})
}

/*鼠标滑过展开下拉*/
function slideMenus(callback){
	var $sildeObj = $(".slideTrigger");
    return $sildeObj.each(function() {
        var self = this;
        var mouseout_tid = "";
        var mouseover_tid = "";
        $(self).hover(function(){
            clearTimeout(mouseout_tid);
            mouseover_tid = setTimeout(function(){
                $(self).addClass("open").css("position", "relative");
                $(self).children(".slideCon").show();
                $(self).siblings().removeClass("open").removeAttr("style").children(".slideCon").hide();
            },200);
            if(callback) {
                callback();
            }
        },function(){
            clearTimeout(mouseover_tid);
            mouseout_tid = setTimeout(function(){
                $(self).removeClass("open").removeAttr("style");
                $(self).children(".slideCon").hide();
            },100);
            if(callback) {
                callback();
            }
        });
    });
}
/*导航购物车鼠标滑过展开下拉*/
function slideCart(){
	var mouseout_tid = "";
	var mouseover_tid = "";
	var flag = true ;
    et.buy.display_course_num();
	$("#id_mycart").hover(function(){
		var self = this;
		clearTimeout(mouseout_tid);
		if(flag){
            et.buy.display_cart("top");
            flag = false ;
        }
		mouseover_tid = setTimeout(function(){
            $(self).addClass("open");
            $(self).children("#id_cartcontent").show();
            $(self).siblings().removeClass("open").children(".slideCon").hide();
		},200);
	},function(){
        clearTimeout(mouseover_tid);
        var self = this;
		mouseout_tid = setTimeout(function(){
            $(self).removeClass("open");
			$(self).children("#id_cartcontent").hide();
		},100);
        flag = true;
	});
}

/*倒计时*/
function gettime(year, time, content, limitMonth, limitDay) {
	var endtime = new Date(year, limitMonth, limitDay, 8, 0, 0, 0);//UTC时间为GMT时间加8
	var nowTime = (endtime.getTime()/1000 - time) < 0 ? 0 : (endtime.getTime()/1000 - time);
	var timeDay = Math.floor(nowTime / 86400);
	var timeHour = Math.floor((nowTime - timeDay * 60 * 60 * 24) / 3600);
	var timeMinute = Math.floor((nowTime - timeDay * 60 * 60 * 24 - timeHour * 60 * 60) / 60);
	var timeSecond = nowTime - timeDay * 60 * 60 * 24 - timeHour * 60 * 60 - timeMinute * 60;
	var strDay = String(timeDay).length > 1 ? (String(timeDay)) : ("0" + String(timeDay));
	var strHour = String(timeHour).length > 1 ? (String(timeHour)) : ("0" + String(timeHour));
	var strMinute = String(timeMinute).length > 1 ? (String(timeMinute)) : ("0" + String(timeMinute));
	var strSecond = String(timeSecond).length > 1 ? (String(timeSecond)) : ("0" + String(timeSecond));
	$(content).find(".t-day").html(strDay);
	$(content).find(".t-hour").html(strHour);
	$(content).find(".t-minute").html(strMinute);
	$(content).find(".t-second").html(strSecond);
};
function displaytime(year, time, content, month, day){
	setInterval(function(){
		gettime(year, time, content, month, day);
		time ++;
	}, 1000);
}

/*textarea限定输入字数*/
function changeWordsNum(inputObj, textObj, num, allowInput){
	var $itemtext_obj = $(textObj);
	var $item_obj = $(inputObj);
	var result = $item_obj.val().replace(/(^\s*)|(\s*$)/g, "");
	var length = parseInt(result.length);

	if(length > num){
		if(allowInput != undefined && allowInput == 1){
			$item_obj.val($item_obj.val().substring(0, num));
			$itemtext_obj.html("超出<span class='fb f14px'>" + num + "</span>字，不能再输入");
		}
		else{
			var num2 = length - num;
			$itemtext_obj.html("");
			$itemtext_obj.html("已超出<span class='fb'>" + num2 + "</span>字");
		}
	}
	if(num >= length){
		var num = num - length;
		$itemtext_obj.html("");
		$itemtext_obj.html("还能输入<span class='fb'>" + num + "</span>字");
	}
}
function changeWordsNumBindEvent(inputObj, textObj, num){
	var $obj = $(inputObj);
	$obj.bind("keyup", function(){changeWordsNum(inputObj, textObj, num)});
	$obj.bind("keydown", function(){changeWordsNum(inputObj, textObj, num)});
	$obj.bind("keypress", function(){changeWordsNum(inputObj, textObj, num)});
}

/*textarea限定输入字数*/
function LimitWordsNum(inputObj, textObj, num){
    var $itemtext_obj = $(textObj);
    var $item_obj = $(inputObj);
    var result = $item_obj.val().replace(/(^\s*)|(\s*$)/g, "");
    var length = parseInt(result.length);

    if(length > num){
        $itemtext_obj.html("");
        $itemtext_obj.html("还能输入<span class='fb'>" + 0 + "</span>字");
        $item_obj.val($item_obj.val().substring(0, num));
    }
    if(num >= length){
        var num = num - length;
        $itemtext_obj.html("");
        $itemtext_obj.html("还能输入<span class='fb'>" + num + "</span>字");
    }
}

/*模拟Marquee，无间断滚动内容*/
;(function($){
	$.fn.kxbdMarquee = function(options){
		var opts = $.extend({},$.fn.kxbdMarquee.defaults, options);
		return this.each(function(){
			var $marquee = $(this);//滚动元素容器
			var _scrollObj = $marquee.get(0);//滚动元素容器DOM
			var scrollW = $marquee.width();//滚动元素容器的宽度
			var scrollH = $marquee.height();//滚动元素容器的高度
			var $element = $marquee.children(); //滚动元素
			var $kids = $element.children();//滚动子元素
			var scrollSize=0;//滚动元素尺寸
			var _type = (opts.direction == 'left' || opts.direction == 'right') ? 1:0;//滚动类型，1左右，0上下
			
			//防止滚动子元素比滚动元素宽而取不到实际滚动子元素宽度
			$element.css(_type?'width':'height',10000);
			//获取滚动元素的尺寸
			if (opts.isEqual) {
				scrollSize = $kids[_type?'outerWidth':'outerHeight'](true) * $kids.length;
			}else{
				$kids.each(function(){
					scrollSize += $(this)[_type?'outerWidth':'outerHeight'](true);
				});
			}
			//滚动元素总尺寸小于容器尺寸，不滚动
			if (scrollSize<(_type?scrollW:scrollH)) return; 
			//克隆滚动子元素将其插入到滚动元素后，并设定滚动元素宽度
			$element.append($kids.clone()).css(_type?'width':'height',scrollSize*2);
			
			var numMoved = 0;
			function scrollFunc(){
				var _dir = (opts.direction == 'left' || opts.direction == 'right') ? 'scrollLeft':'scrollTop';
				if (opts.loop > 0) {
					numMoved+=opts.scrollAmount;
					if(numMoved>scrollSize*opts.loop){
						_scrollObj[_dir] = 0;
						return clearInterval(moveId);
					} 
				}

				if(opts.direction == 'left' || opts.direction == 'up'){
					var newPos = _scrollObj[_dir] + opts.scrollAmount;
					if(newPos>=scrollSize){
						newPos -= scrollSize;
					}
					_scrollObj[_dir] = newPos;
				}else{
					var newPos = _scrollObj[_dir] - opts.scrollAmount;
					if(newPos<=0){
						newPos += scrollSize;
					}
					_scrollObj[_dir] = newPos;
				}
			}
			//滚动开始
			var moveId = setInterval(scrollFunc, opts.scrollDelay);
			//鼠标划过停止滚动
			$marquee.hover(
				function(){
					clearInterval(moveId);
				},
				function(){
					clearInterval(moveId);
					moveId = setInterval(scrollFunc, opts.scrollDelay);
				}
			);
		});
	};
	$.fn.kxbdMarquee.defaults = {
		isEqual:true,//所有滚动的元素长宽是否相等,true,false
		loop: 0,//循环滚动次数，0时无限
		direction: 'left',//滚动方向，'left','right','up','down'
		scrollAmount:1,//步长
		scrollDelay:20//时长
	};
	$.fn.kxbdMarquee.setDefaults = function(settings) {
		$.extend( $.fn.kxbdMarquee.defaults, settings );
	};
})(jQuery);

//加入收藏夹
function addfavorite(){
    try {
        window.external.addFavorite(window.location,document.title);
    }
    catch (e) {
        try {
            window.sidebar.addPanel(window.location, document.title, "");
        }
        catch (e) {
            alert("请按 Ctrl + D 键为你的浏览器添加收藏书签。");
        }
    }
}
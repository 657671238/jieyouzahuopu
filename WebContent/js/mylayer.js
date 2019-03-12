var id = null;
var mylayer = {
		tips:function(msg){
			var ii = layer.open({
	    		  type: 1, //Page层类型
	    		  title:false,
	    		  area: ['150px', '50px'],
	    		  closeBtn: 0,
	    		  shadeClose: true,
	    		  shade: 0.6, //遮罩透明度
	    		  content: "<p style='text-align:center;line-height:30px'>"+msg+"</p>"
	    	}); 
			setTimeout(function(){
			      layer.close(ii);
			      //同时需要进行页面 的跳转
			}, 1000);
		},
		showMessage:function(msg){
			var ii = layer.open({
	    		  type: 1, //Page层类型
	    		  title:false,
	    		  area: ['150px', '50px'],
	    		  closeBtn: 0,
	    		  shadeClose: true,
	    		  shade: 0.6, //遮罩透明度
	    		  content: "<p style='text-align:center;line-height:30px'>"+msg+"</p>"
	    	}); 
			setTimeout(function(){
			      layer.close(ii);
			      //同时需要进行页面 的跳转
			}, 2000);
		},
		//带回调函数的自定义的消息
		showCallBackMessage:function(msg,callback){
			var ii = layer.open({
	    		  type: 1, //Page层类型
	    		  title:false,
	    		  area: ['150px', '50px'],
	    		  closeBtn: 0,
	    		  shadeClose: true,
	    		  shade: 0.6, //遮罩透明度
	    		  content: "<p style='text-align:center;line-height:30px'>"+msg+"</p>"
	    	}); 
			setTimeout(function(){
			      layer.close(ii);
			      callback();
			      //同时需要进行页面 的跳转
			}, 2000);
		},
		showError:function(error){
			var errorElement = layer.open({
	    		  type: 1, //Page层类型
	    		  title:false,
	    		  area: ['150px', '50px'],
	    		  closeBtn: 0,
	    		  shadeClose: true,
	    		  shade: 0.6, //遮罩透明度
	    		  content: "<p style='text-align:center;color:red;line-height:30px'>"+error+"</p>"
	    	});
			setTimeout(function(){
				 layer.close(errorElement);
			},1000)
		},
		showLoading:function(msg){
			id = layer.msg(msg, {
				  icon: 16
				  ,shade: 0.5
				  , time:false
			});
		},
		showLoadingGo:function(){
			id = layer.load(2);
		},
		closeLoading:function(){
			if(id!=null){
				layer.close(id);
			}
		},
		//不可以获取中文的参数
		getQueryString:function(name){
			//定义一个正则表达式
		     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
			 //获取浏览器的地址 然后匹配 正则表达式
		     var r = window.location.search.substr(1).match(reg);
			 //将匹配到的结果返回
		     //浏览器 默认采用的是UTF-8的编码  国际标准 所有传输过来是不需要编码的  浏览器自动帮你编码
		     //接收端 是需要自己手动解码的
		     if(r!=null)return  decodeURI(r[2]); return null;
		}
}
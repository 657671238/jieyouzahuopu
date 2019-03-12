// 注册页面，表单错误提示
(function(){
    var form = document.querySelector('#registerForm');
    form.addEventListener('invalid',function(event){
        var elem = event.target;
        var vali = elem.validity;
        var name = elem.name;

        switch (name){
            case 'phone':
                if(vali.valueMissing){
                    elem.setCustomValidity('手机号码不能为空');
                }else if(vali.patternMismatch){
                    elem.setCustomValidity('请输入正确的手机号码');
                }else{
                    elem.setCustomValidity('');
                }
                break;
            case 'password':
                if(vali.valueMissing){
                    elem.setCustomValidity('密码不能为空');
                }else if(vali.patternMismatch){
                    elem.setCustomValidity('密码为6-10位字符');
                }else{
                    elem.setCustomValidity('');
                }
                break;
            case 'rePassword':
                if(vali.valueMissing){
                    elem.setCustomValidity('确认密码不能为空');
                }else if(vali.patternMismatch){
                    elem.setCustomValidity('确认密码为6-10位字符');
                }else{
                    elem.setCustomValidity('');
                }
                break;
        }
    },true)
})();

//点击验证码，60秒倒计时
var min = 60;
var time;
$('.tableText').click(function() {
    clearInterval(time);
    time = setInterval(fn,1000);
});

function fn(){
    min = --min;
    if(min>0){
        $('.tableText').html('（'+min +'秒）重发');
    }else{
        min = 60;
        $('.tableText').html('发送验证码')
    }
}















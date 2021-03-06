// 存放主要交互逻辑js代码
// js模块化
var seckill={
    // 封装秒杀相关的ajax的url
    URL:{
        now:function () {
            return '/seckill/time/now';
        },
        exposer:function(seckillId){
            return '/seckill/'+seckillId+'/exposer'
        },
        execution:function(seckillId,md5){
            return  '/seckill/'+seckillId+'/'+md5+'/execution';
        }
    },
    handleSeckill:function(seckillId,node){
        node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
        $.post(seckill.URL.exposer(seckillId),{}, function(result){
            if(result && result['success']){
                var exporse = result['data'];
                if(exporse['exposed']){
                    // 开启秒杀
                     // 获取秒杀地址
                    var md5 = exporse['md5'];
                    var killUrl = seckill.URL.execution(seckillId,md5);
                    $('#killBtn').one('click',function () {
                        //执行
                        $(this).addClass('disabled');
                        $.post(killUrl,{},function (resultd) {
                            if(resultd && resultd['success']){
                                var killResult = resultd['data'];
                                var successKill = killResult['successKill'];
                                var state = killResult['state'];
                                var stateInfo = killResult['stateInfo'];
                                node.html('<span class="label label-success">'+stateInfo+'</span>');

                            }else{
                                console.info('result'+resultd['error']);
                            }
                        });

                    });
                    node.show();
                }else{
                    // 未开启秒杀
                    var nowT = exporse['now'];
                    var startT = exporse['start'];
                    var endT = exporse['end'];
                    seckill.countdown(nowT,startT,endT);
                }
            }else{
                console.info('result'+result['error']);
            }
        });
    },
    validatePhone:function(phone){
        if(phone && phone.length == 11 && !isNaN(phone)){
            return true;
        }else{
            return false;
        }
    },
    countdown:function(seckillId,nowTime,startTime,endTime){
        var seckillBox = $('#seckill-box');
        if(nowTime > endTime){
            seckillBox.html('秒杀结束！');
        }else if(nowTime < startTime){
            //秒杀未开始
            var killTime = new Date(startTime+1000);
            seckillBox.countdown(killTime,function (event) {
                // 控制时间的格式
                var formate = event.strftime('秒杀倒计时：%D天 %H时 %M分 %S秒');
                seckillBox.html(formate);
            }).on('finish countdown',function () {
                // 时间完成后回调事件  获取秒杀地址，控制实现逻辑 执行秒杀
                seckill.handleSeckill(seckillId,seckillBox);
            });
        }else{
            seckill.handleSeckill(seckillId,seckillBox);
        }
    },
    // 详情页秒杀逻辑
    detail:{
        // 详情页的初始化
        initl:function(params){
            // 手机验证和登录，计时交互
            //在cookie中查找手机号
            var killPhone = $.cookie("killPhone");
            // 验证手机号
            if(!seckill.validatePhone(killPhone)) {
                //绑定手机号
                var killPhoneModal = $('#killphoneModel');
                killPhoneModal.modal({
                    show: true,//显示弹出层
                    backdrop: 'static',//禁止位置关闭
                    keyboard: false// 关闭键盘事件
                });
                // 绑定按钮事件
                $('#killPhoneBtn').click(function () {
                    var inputPhone = $('#killPhoneKey').val();
                    if (seckill.validatePhone(inputPhone)) {
                        //如果验证手机号通过
                        // 加入cookie中
                        $.cookie('killPhone', inputPhone, {expires: 7, path: '/seckill'});
                        window.location.reload();
                    } else {
                        $('#killPhoneMessage').hide().html('<label class="label label-danger">手机号错误</label>').show(300);
                    }
                });
            }
            // 已经登录
            // 计时交互
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            var seckillId = params['seckillId'];
            $.get(seckill.URL.now(),{},function(result){
                if(result && result['success']){
                    var nowTime = result['data'];
                    // 时间判断
                    seckill.countdown(seckillId,nowTime,startTime,endTime);
                }else{
                    console.log('result'+result);
                }
            });
        }
    }
}
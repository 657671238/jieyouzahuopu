
//顶部显示隐藏效果
$('.ss_list').mouseenter(function(){
    $('.ss_list_bg').stop(true,true).slideDown();
}).mouseleave(function(){
    $('.ss_list_bg').stop(true,true).slideUp();
});



//购物车
(function(){

    //** 展示交互
    var timeoutId
    var $last = $('.last')

    function fadeOut() {
        timeoutId = setTimeout(function() {
            $last.fadeOut('slow');
        }, 500)
    }

    $('.car_t').hover(function() {
        clearTimeout(timeoutId)
        $last.fadeIn('slow');
    }, fadeOut)

    $last.hover(function() {
        clearTimeout(timeoutId)
    }, fadeOut)



    //** 功能交互
    var catObj = {
        list: [
            /*
             {
             price: 0,
             count: 0,
             smallTotalPrice: 0
             } */
        ],
        totalPrice: 0,
        totalCount: 0
    }
    var $totalPrice = $('.J_totalPrice')
    var $totalCount = $('.J_totalCount')
    var $noshop = $('.noshop')
    var $shop = $('.shop')


    // 监听自定义事件
    $shop.on('onInit', function(){
        render(-1)
    }).on('onDelete', function(e, index) {
        catObj.list.splice(index, 1)
        if(!catObj.list.length) {
            $shop.hide()
            $noshop.show()
        }
        render(-1)
    }).on('onChange', function(e, index) {
        render(index)
    })


    // 渲染
    function render(index) {
        // 渲染记录
        if (index > -1) {
            var $item = $('li', $shop).eq(index)
            var item = catObj.list[index]
            $('.J_inputCount', $item).val(item.count)
            $('.J_count', $item).text('共'+item.count+'件商品')
            $('.J_smallTotalPrice', $item).text('￥'+item.smallTotalPrice)
        }

        // 渲染统计
        catObj.totalCount = 0
        catObj.totalPrice = 0
        $.map(catObj.list, function(item) {
            catObj.totalCount += item.count
            catObj.totalPrice += item.smallTotalPrice
        })

        $totalPrice.text('￥'+ catObj.totalPrice)
        $totalCount.text('(' + catObj.totalCount +')')

    }

    // 数量改变
    function setInputCount (index, count) {
        var price = catObj.list[index].price
        count = count - 0
        catObj.list[index] = {
            count: count,
            price: price,
            smallTotalPrice: count * price
        }

        $shop.trigger('onChange', index)
    }

    // 初始化
    (function() {
        var $li = $('li', $shop)
        if (!$li.length) {
            $shop.hide()
            $noshop.show()
            return
        }

        $li.each(function(index) {
            var smallTotalPrice

            // 取值
            var price = $('.J_smallTotalPrice', this).text()
            var count = $('.J_inputCount', this).val()

            // 值转换number
            price = price.slice(1) - 0
            count = count - 0
            smallTotalPrice = price * count

            catObj.list.push({
                price: price,
                count: count,
                smallTotalPrice: smallTotalPrice
            })
        })
        $shop.trigger('onInit')
    })()


    // 删除
    $('.J_btnDelete').click(function() {
        if (!window.confirm('确定要删除吗？')) return

        var $li = $('li', $shop)
        var index = $li.index($(this).parents('li'))

        $li.eq(index).remove()
        $shop.trigger('onDelete', [index])
    })

    // 追加数量
    $('.J_btnAddCount').click(function() {
        var $li = $('li', $shop)
        var index = $li.index($(this).parents('li'))
        var count = $('.J_inputCount', $li.eq(index)).val()
        setInputCount(index, ++count)
    })


    // 减少数量
    $('.J_btnDelCount').click(function() {

        var $li = $('li', $shop)
        var index = $li.index($(this).parents('li'))
        var count = $('.J_inputCount', $li.eq(index)).val()
        if (--count < 1) {
            if (!window.confirm('确定要删除吗？')) return

            $li.eq(index).remove()
            $shop.trigger('onDelete', [index])
        } else {
            setInputCount(index, count)
        }
    })

    // 改变数量
    $('.J_inputCount').change(function(e) {
        var $li = $('li', $shop)
        var index = $li.index($(this).parents('li'))
        var newCount = $(this).val()

        if(/^[1-9]\d*$/.test(newCount)) {
            setInputCount(index, newCount)
        } else {
            $(this).val(catObj.list[index].count)
        }
    })
})()

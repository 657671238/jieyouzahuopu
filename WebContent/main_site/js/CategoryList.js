

// 排序
var $listBox = $('.cate_list')
var isDesc = true
$('#sortPrice').click(function(e){
    var fragment= document.createDocumentFragment()
    var list = $('li', $listBox).toArray()
    var $target = $(e.target)

    isDesc = !isDesc
    list.sort(function(li1, li2) {
        var price1 = $('.price span', li1).text().slice(1)
        var price2 = $('.price span', li2).text().slice(1)
        var diff = price1 - price2

        return isDesc ? -diff : diff
    })

    $.map(list, function(li) {
        fragment.appendChild(li)
    })

    $listBox.empty()
    $listBox.append(list)

    return false
})